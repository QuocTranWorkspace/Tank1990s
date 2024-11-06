package main.java.service;

import main.java.App;
import main.java.controller.BaseScene;
import main.java.controller.GameplayMenu;
import main.java.model.Bullet;
import main.java.model.Home;
import main.java.model.PlayerTank;
import main.java.model.Point2D;
import main.java.model.environments.Environment;
import main.java.model.environments.Ice;
import main.java.model.environments.SteelWall;
import main.java.model.environments.Tree;
import main.java.model.powerups.PowerUps;
import main.java.model.tanks.BaseTank;
import main.java.model.tanks.Directions;
import main.java.model.tanks.EnemyTank;
import main.java.utils.DestroyAnimation;
import main.java.utils.LevelRenderer;
import main.java.utils.TankSpawner;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class GameplayManager extends BaseScene implements ActionListener, KeyListener {
    public static int SLIPPERY = App.FRAME_HEIGHT / 150;
    public static final int VELOCITY_MOVE = App.FRAME_HEIGHT / 280;
    public static final int VELOCITY_SHOOT = App.FRAME_HEIGHT / 280;

    private final transient TankSpawner playerTankSpawner;
    private final transient TankSpawner enemyTankSpawner;
    private final transient DestroyAnimation destroyTanks;
    private final transient DestroyAnimation destroyEnvironment;
    private transient PlayerTank player;
    private TankManager tankManager;
    private final transient java.util.List<Image> spawnImages = new ArrayList<>();
    private final transient java.util.List<Image> destroySmallImages = new ArrayList<>();
    private final transient java.util.List<Image> destroyBigImages = new ArrayList<>();

    private int currentLevel = 0;
    private int nextLevel = currentLevel;
    private transient LevelRenderer levelRenderer = new LevelRenderer(currentLevel);
    private transient java.util.List<Environment> map = levelRenderer.getMap();

    private transient Home home = new Home(new Point2D((int) (13 * App.FRAME_HEIGHT / 27.9), (int) (25 * App.FRAME_HEIGHT / 27.9)));
    private boolean isLose = false;

    public static Set<EnemyTank> currentEnemies = new HashSet<>();
    private PowerUpsManager powerUpsManager;

    public GameplayManager() throws Exception {
        Timer gameLoop = TimerManager.getSharedTimer();
        player = new PlayerTank(new Point2D((int) (10 * App.FRAME_HEIGHT / 27.9), (int) (25 * App.FRAME_HEIGHT / 27.9)));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        tankManager = new TankManager();

        powerUpsManager = new PowerUpsManager(tankManager);

        for (int i = 1; i <= 10; i++) {
            spawnImages.add(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/spawning/spawn_" + i + ".png"))).getImage());
            if (i <= 7) {
                destroyBigImages.add(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/explode/explode_big_" + i + ".png"))).getImage());
            }
            if (i <= 5) {
                destroySmallImages.add(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../resource/img/explode/explode_small_" + i + ".png"))).getImage());
            }
        }

        playerTankSpawner = new TankSpawner(spawnImages);
        enemyTankSpawner = new TankSpawner(spawnImages);
        destroyTanks = new DestroyAnimation(destroyBigImages);
        destroyEnvironment = new DestroyAnimation(destroySmallImages);

        gameLoop.addActionListener(this);
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        drawComponents(g);
    }

    public void updateGameLogic() {
        updateEnemyTank();
        updateLevel();
        updateGameState();
    }

    private void updateGameState() {
        if (player.getHealth() <= 0 || !home.isAlive()) {
            isLose = true;
        }
        if (isLose) {
            //TODO: save game or return
//            TimerManager.getSharedTimer().stop();
        }
    }

    private void updateLevel() {
        if (tankManager.getTankList().isEmpty()) {
            nextLevel++;
        }
        if (currentLevel < nextLevel) {
            tankManager.stopAllTimer();
            powerUpsManager.stopAllTimer();
            try {
                player = new PlayerTank(new Point2D((int) (10 * App.FRAME_HEIGHT / 27.9), (int) (25 * App.FRAME_HEIGHT / 27.9)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                tankManager = new TankManager();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            home = new Home(new Point2D((int) (13 * App.FRAME_HEIGHT / 27.9), (int) (25 * App.FRAME_HEIGHT / 27.9)));

            powerUpsManager = new PowerUpsManager(tankManager);
            currentLevel = nextLevel;
            levelRenderer = new LevelRenderer(currentLevel);
            map = levelRenderer.getMap();
        }
    }

    // Enemy tank
    public void updateEnemyTank() {
        for (EnemyTank tank : tankManager.getTankList()) {
            if (!tank.isDisplay()) continue;

            try {
                tank.move(tank.getMovementSpeed());
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            handleEnvironmentCollisions(tank);
        }
    }

    private void handleEnvironmentCollisions(EnemyTank tank) {
        for (Environment environment : map) {
            if (environment == null || !collision2D(tank, environment)) continue;
            if (!environment.isWalkable()) {
                tank.revertToPreviousPosition();
                return;
            } else if (environment instanceof Ice) {
                if (!isTankSliding(tank)) {
                    startSlidingEffect(tank);
                }
                return;
            }
        }
    }

    private boolean isTankSliding(EnemyTank tank) {
        return slidingTimers.containsKey(tank) && slidingTimers.get(tank).isRunning();
    }

    //Enemy bullet
    public void updateEnemyBullet(EnemyTank tank, Graphics g) {
        Iterator<Bullet> bulletIterator = tank.getBulletList().iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            if (!bullet.isActive()) {
                bulletIterator.remove();
                continue;
            }

            g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(), null);
            bullet.move();

            if (collision2D(bullet, home)) {
                bulletIterator.remove();
                isLose = true;
                home.setAlive(false);
                break;
            }

            if (player.isDisplay() && handlePlayerCollision(bullet, bulletIterator)) {
                continue;
            }

            handleEnvironmentCollisions(bullet, bulletIterator);
        }
    }

    private boolean handlePlayerCollision(Bullet bullet, Iterator<Bullet> bulletIterator) {
        if (collision2D(bullet, player) && !player.isInvincible()) {
            bulletIterator.remove();

            if (player.isShield()) {
                player.setShield(false);
            } else {
                player.setHealth(player.getHealth() - 1);
                if (player.getHealth() <= 0) {
                    isLose = true;
                }
            }
            return true;
        }
        return false;
    }

    private void handleEnvironmentCollisions(Bullet bullet, Iterator<Bullet> bulletIterator) {
        Iterator<Environment> environmentIterator = map.iterator();

        while (environmentIterator.hasNext()) {
            Environment environment = environmentIterator.next();

            if (environment == null || !environment.isDestroyable()) continue;

            if (collision2D(bullet, environment)) {
                environment.setHealth(environment.getHealth() - 1);
                bulletIterator.remove();

                if (environment.getHealth() <= 0) {
                    environmentIterator.remove();
                }
                break;
            }
        }
    }

    // Player bullet
    public void updatePlayerBullet(Graphics g) {
        Iterator<Bullet> bulletIterator = player.getBulletList().iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            boolean isHandled = false;

            if (!bullet.isActive()) {
                bulletIterator.remove();
                continue;
            }

            if (collision2D(bullet, home)) {
                bulletIterator.remove();
                isLose = true;
                home.setAlive(false);
                break;
            }

            bullet.move();

            isHandled = handleTankCollision(bullet, bulletIterator, g);

            if (!isHandled) {
                handleEnvironmentCollision(bullet, bulletIterator, g);
            }
        }
    }

    private boolean handleTankCollision(Bullet bullet, Iterator<Bullet> bulletIterator, Graphics g) {
        Iterator<EnemyTank> tankIterator = tankManager.getTankList().iterator();

        while (tankIterator.hasNext()) {
            EnemyTank tank = tankIterator.next();

            if (tank.isDisplay() && collision2D(bullet, tank) && !tank.isInvincible()) {
                bulletIterator.remove();
                tank.takeDamage(1);
                destroyEnvironment.startDestroyAnimation(bullet.getX(), bullet.getY(), bullet.getDirection());

                if (tank.getHealth() <= 0) {
                    player.addPoints(tank.getPoint());
                    currentEnemies.remove(tank);
                    tankIterator.remove();
                }
                return true;
            }
        }
        return false;
    }

    private void handleEnvironmentCollision(Bullet bullet, Iterator<Bullet> bulletIterator, Graphics g) {
        Iterator<Environment> environmentIterator = map.iterator();

        while (environmentIterator.hasNext()) {
            Environment environment = environmentIterator.next();

            if (environment != null && environment.isDestroyable() && collision2D(bullet, environment)) {
                int damage = calculateEnvironmentDamage(player.getTier(), environment);
                environment.setHealth(environment.getHealth() - damage);

                destroyEnvironment.startDestroyAnimation(bullet.getX(), bullet.getY(), bullet.getDirection());

                bulletIterator.remove();

                if (environment.getHealth() <= 0) {
                    environmentIterator.remove();
                }
                break;
            }
        }
    }

    private int calculateEnvironmentDamage(int playerTier, Environment environment) {
        if (playerTier == 4) {
            return (environment instanceof SteelWall) ? 50000 : 2;
        }
        return 1;
    }

    public void drawComponents(Graphics g) {
        setBackground(Color.BLACK);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, App.FRAME_HEIGHT, App.FRAME_HEIGHT);

        drawEnvironment(g, false);
        drawPlayer(g);
        drawEnemies(g);
        drawBullets(g);
        drawEnvironment(g, true);
        drawHome(g);
        drawPowerUps(g);
    }

    private void drawPowerUps(Graphics g) {
        List<PowerUps> powerUps = powerUpsManager.getActivePowerUps();
        for (PowerUps powerUp : powerUps) {
            g.drawImage(powerUp.getImage(), powerUp.getX(), powerUp.getY(), powerUp.getWidth(), powerUp.getHeight(), null);
        }
    }

    private void drawEnvironment(Graphics g, boolean treesOnly) {
        for (Environment environment : map) {
            if (environment == null) continue;

            boolean isTree = environment instanceof Tree;
            if ((treesOnly && isTree) || (!treesOnly && !isTree)) {
                g.drawImage(environment.getImage(), environment.getPosition().getX(), environment.getPosition().getY(),
                        environment.getWidth(), environment.getHeight(), null);
            }
        }
    }

    private void drawHome(Graphics g) {
        if (home.isAlive()) {
            g.drawImage(home.getImage(), home.getPosition().getX(), home.getPosition().getY(), home.getWidth(), home.getHeight(), null);
        } else {
            home.setImage(Home.deathImage);
            g.drawImage(home.getImage(), home.getPosition().getX(), home.getPosition().getY(), home.getWidth(), home.getHeight(), null);
        }
    }

    private void drawPlayer(Graphics g) {
        if (!player.isDisplay()) {
            playerTankSpawner.startSpawnAnimation(player);
            player.setDisplay(true);
        }
        playerTankSpawner.drawTank(g, player);

            if (player.isInvincible()) {
                if (player.getCurrentInvincible() == PlayerTank.invincibleImage1) {
                    player.setCurrentInvincible(PlayerTank.invincibleImage2);
                } else {
                    player.setCurrentInvincible(PlayerTank.invincibleImage1);
                }
                g.drawImage(player.getCurrentInvincible(), player.getPosition().getX(), player.getPosition().getY(), player.getWidth(), player.getHeight(), null);
            }

        updatePlayerBullet(g);
        destroyEnvironment.drawAnimations(g);
    }

    private void drawEnemies(Graphics g) {
        int tankOnMap = 0;
        int enemyLeft = Math.min(4, tankManager.getTankList().size());

        while (tankOnMap < enemyLeft) {
            EnemyTank tank = tankManager.getTankList().get(tankOnMap);

            if (!tank.isDisplay()) {
                enemyTankSpawner.startSpawnAnimation(tank);
                tank.setDisplay(true);
            }

            currentEnemies.add(tank);

            enemyTankSpawner.drawTank(g, tank);
            updateEnemyBullet(tank, g);
            tankOnMap++;
        }
    }

    private void drawBullets(Graphics g) {
        for (Bullet bullet : player.getBulletList()) {
            if (bullet.isActive()) {
                g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(), null);
            }
        }
    }

    private boolean collision2D(Bullet bullet, BaseTank tank) {
        return bullet.getX() < tank.getPosition().getX() + tank.getWidth()
                && bullet.getX() + bullet.getWidth() > tank.getPosition().getX()
                && bullet.getY() < tank.getPosition().getY() + tank.getHeight()
                && bullet.getY() + bullet.getHeight() > tank.getPosition().getY();
    }

    private boolean collision2D(Bullet bullet, Environment environment) {
        return bullet.getX() <= environment.getPosition().getX() + environment.getWidth()
                && bullet.getX() + bullet.getWidth() >= environment.getPosition().getX()
                && bullet.getY() <= environment.getPosition().getY() + environment.getHeight()
                && bullet.getY() + bullet.getHeight() >= environment.getPosition().getY();
    }

    private boolean collision2D(BaseTank tank, Environment environment) {
        int tolerance = 4;

        int tankLeft = tank.getPosition().getX() + tolerance;
        int tankRight = tank.getPosition().getX() + tank.getWidth() - tolerance;
        int tankTop = tank.getPosition().getY() + tolerance;
        int tankBottom = tank.getPosition().getY() + tank.getHeight() - tolerance;

        int envLeft = environment.getPosition().getX();
        int envRight = environment.getPosition().getX() + environment.getWidth();
        int envTop = environment.getPosition().getY();
        int envBottom = environment.getPosition().getY() + environment.getHeight();

        return tankRight > envLeft && tankLeft < envRight && tankBottom > envTop && tankTop < envBottom;
    }

    private boolean collision2D(Bullet bullet, Home home) {
        return bullet.getX() <= home.getPosition().getX() + home.getWidth()
                && bullet.getX() + bullet.getWidth() >= home.getPosition().getX()
                && bullet.getY() <= home.getPosition().getY() + home.getHeight()
                && bullet.getY() + bullet.getHeight() >= home.getPosition().getY();
    }

    private boolean collision2D(PlayerTank tank, PowerUps powerUp) {
        return powerUp.getX() <= tank.getPosition().getX() + tank.getWidth()
                && powerUp.getX() + powerUp.getWidth() >= tank.getPosition().getX()
                && powerUp.getY() <= tank.getPosition().getY() + tank.getHeight()
                && powerUp.getY() + powerUp.getHeight() >= tank.getPosition().getY();
    }

    private final Map<BaseTank, Timer> slidingTimers = new HashMap<>();

    public void startSlidingEffect(BaseTank tank) {
        if (slidingTimers.containsKey(tank) && slidingTimers.get(tank).isRunning()) {
            slidingTimers.get(tank).stop();
            slidingTimers.remove(tank);
        }

        AtomicReference<Timer> slipperyTimer = new AtomicReference<>();

        SLIPPERY = App.FRAME_HEIGHT / 150;

        Timer newSlipperyTimer = new Timer(80, e -> {
            if (SLIPPERY > 0) {
                try {
                    tank.move(SLIPPERY);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                // Check for collision with non-ice environments
                boolean collided = false;
                for (Environment environment : map) {
                    if (environment != null && !(environment instanceof Ice) && collision2D(tank, environment)) {
                        collided = true;
                        break;
                    }
                }

                if (collided) {
                    tank.revertToPreviousPosition();
                    slipperyTimer.get().stop();
                } else {
                    SLIPPERY--;
                }
            } else {
                // Stop the timer when sliding ends
                slipperyTimer.get().stop();
            }
        });

        slidingTimers.put(tank, newSlipperyTimer);
        slipperyTimer.set(newSlipperyTimer);
        newSlipperyTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        revalidate();
        repaint();
        updateGameLogic();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //
    }

    public void keyPressed(KeyEvent e) {
        Directions direction = switch (e.getKeyCode()) {
            case KeyEvent.VK_S -> Directions.DOWN;
            case KeyEvent.VK_W -> Directions.UP;
            case KeyEvent.VK_D -> Directions.RIGHT;
            case KeyEvent.VK_A -> Directions.LEFT;
            default -> null;
        };

        if (direction != null) {
            Iterator<Environment> environmentIterator = map.iterator();
            handleEnvironmentCollision(player, environmentIterator);

            Iterator<PowerUps> powerUpsIterator = powerUpsManager.getActivePowerUps().iterator();
            handlePowerUpCollision(player, powerUpsIterator);
            try {
                player.setDirection(direction);
                player.move(VELOCITY_MOVE);
            } catch (Exception ex) {
                throw new RuntimeException("Movement error: " + ex.getMessage(), ex);
            }
        }
    
        if (player.isShooting() && e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.shoot();
        }
    
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GameplayMenu.togglePause(GameplayMenu.pausePanel);
        }
    }

    private void handleEnvironmentCollision(PlayerTank player, Iterator<Environment> environmentIterator) {
        while (environmentIterator.hasNext()) {
            Environment environment = environmentIterator.next();
            if (environment != null && collision2D(player, environment) && !environment.isWalkable()) {
                player.revertToPreviousPosition();
                break;
            }
        }
    }

    private void handlePowerUpCollision(PlayerTank player, Iterator<PowerUps> powerUpsIterator) {
        while (powerUpsIterator.hasNext()) {
            PowerUps powerUp = powerUpsIterator.next();
            if (powerUp != null && collision2D(player, powerUp)) {
                powerUpsManager.collectPowerUp(player, powerUp);
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_D) {
            for (Environment environment : map) {
                if (environment != null && collision2D(player, environment) && environment instanceof Ice)
                        startSlidingEffect(player);
            }
        }
    }
}
