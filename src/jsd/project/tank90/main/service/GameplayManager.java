package jsd.project.tank90.main.service;

import jsd.project.tank90.main.model.environments.*;
import jsd.project.tank90.main.Main;
import jsd.project.tank90.main.controller.BaseScene;
import jsd.project.tank90.main.controller.GameplayMenu;
import jsd.project.tank90.main.model.Bullet;
import jsd.project.tank90.main.model.Home;
import jsd.project.tank90.main.model.PlayerTank;
import jsd.project.tank90.main.model.Point2D;
import jsd.project.tank90.main.model.powerups.PowerUps;
import jsd.project.tank90.main.model.tanks.BaseTank;
import jsd.project.tank90.main.model.tanks.Directions;
import jsd.project.tank90.main.model.tanks.EnemyTank;
import jsd.project.tank90.common.utils.DestroyAnimation;
import jsd.project.tank90.common.utils.LevelRenderer;
import jsd.project.tank90.common.utils.SoundEffect;
import jsd.project.tank90.common.utils.TankSpawner;

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

/**
 * The type Gameplay manager.
 */
public class GameplayManager extends BaseScene implements ActionListener, KeyListener {
    /**
     * The constant VELOCITY_MOVE.
     */
    public static final int VELOCITY_MOVE = Main.FRAME_HEIGHT / 280;
    /**
     * The constant VELOCITY_SHOOT.
     */
    public static final int VELOCITY_SHOOT = Main.FRAME_HEIGHT / 280;
    /**
     * The constant SLIPPERY.
     */
    public static int SLIPPERY = Main.FRAME_HEIGHT / 150;
    /**
     * The constant destroyTanks.
     */
    public static DestroyAnimation destroyTanks;
    /**
     * The constant levelRenderer.
     */
    public static LevelRenderer levelRenderer;
    /**
     * The constant currentEnemies.
     */
    public static Set<EnemyTank> currentEnemies = new HashSet<>();
    /**
     * The constant level.
     */
// Static variables for displaying
    public static int level = 0;
    /**
     * The constant score.
     */
    public static int score = 0;
    /**
     * The constant health.
     */
    public static int health = 2;
    /**
     * The constant enemyLeft.
     */
    public static int enemyLeft = 16;
    private static int currentLevel;
    private final Map<BaseTank, Timer> slidingTimers = new HashMap<>();
    /**
     * The Spawn images.
     */
    List<Image> spawnImages = new ArrayList<>();
    /**
     * The Destroy small images.
     */
    List<Image> destroySmallImages = new ArrayList<>();
    /**
     * The Destroy big images.
     */
    List<Image> destroyBigImages = new ArrayList<>();
    private transient TankSpawner playerTankSpawner;
    private transient TankSpawner enemyTankSpawner;
    private transient DestroyAnimation destroyEnvironment;
    private transient PlayerTank player;
    private transient TankManager tankManager;
    private transient PowerUpsManager powerUpsManager;
    private int nextLevel = currentLevel;
    private transient java.util.List<Environment> map = new ArrayList<>();
    private transient Home home = new Home(new Point2D((int) (13 * Main.FRAME_HEIGHT / 27.9), (int) (25 * Main.FRAME_HEIGHT / 27.9)));
    private boolean isLose = false;
    private boolean isGameActive;

    /**
     * Instantiates a new Gameplay manager.
     *
     * @throws Exception the exception
     */
    public GameplayManager() throws Exception {
        Timer gameLoop = TimerManager.getSharedTimer();
        setupComponents();
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        resetGame();
        isGameActive = false;
        Timer timer = new Timer(2000, e -> {
            isGameActive = true;
        });
        timer.setRepeats(false);
        timer.start();

        gameLoop.addActionListener(this);
        gameLoop.start();

        SoundEffect.levelStartingSound();
    }

    private void setupComponents() throws Exception {
        player = new PlayerTank(new Point2D((int) (10 * Main.FRAME_HEIGHT / 27.9), (int) (25 * Main.FRAME_HEIGHT / 27.9)));
        tankManager = new TankManager();
        powerUpsManager = new PowerUpsManager(tankManager);

        loadImages();
        playerTankSpawner = new TankSpawner(spawnImages);
        enemyTankSpawner = new TankSpawner(spawnImages);
        destroyTanks = new DestroyAnimation(destroyBigImages);
        destroyEnvironment = new DestroyAnimation(destroySmallImages);

        isGameActive = false;
    }

    private void loadImages() {
        spawnImages = new ArrayList<>();
        destroySmallImages = new ArrayList<>();
        destroyBigImages = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            spawnImages.add(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../common/resources/img/spawning/spawn_" + i + ".png"))).getImage());
            if (i <= 7)
                destroyBigImages.add(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../common/resources/img/explode/explode_big_" + i + ".png"))).getImage());
            if (i <= 5)
                destroySmallImages.add(new ImageIcon(Objects.requireNonNull(getClass().getResource("../../common/resources/img/explode/explode_small_" + i + ".png"))).getImage());
        }
    }

    /**
     * Reset game.
     *
     * @throws Exception the exception
     */
    public void resetGame() throws Exception {
        currentLevel = 0;
        nextLevel = 0;
        level = 0;
        score = 0;
        health = 2;
        enemyLeft = 16;
        isLose = false;

        player = new PlayerTank(new Point2D((int) (10 * Main.FRAME_HEIGHT / 27.9), (int) (25 * Main.FRAME_HEIGHT / 27.9)));
        tankManager = new TankManager();
        powerUpsManager = new PowerUpsManager(tankManager);
        home = new Home(new Point2D((int) (13 * Main.FRAME_HEIGHT / 27.9), (int) (25 * Main.FRAME_HEIGHT / 27.9)));

        levelRenderer = new LevelRenderer(currentLevel);
        map = levelRenderer.getMap();

        TimerManager.getSharedTimer().start();
    }

    private void updateLevel() {
        level = currentLevel;
        if (tankManager.getTankList().isEmpty() && isGameActive) {
            enemyLeft = 0;
            nextLevel = currentLevel + 1;
            if (nextLevel > 35) {
                Random random = new Random();
                nextLevel = random.nextInt(35);
            }
        }

        if (currentLevel < nextLevel && isGameActive) {
            currentLevel = nextLevel;
            resetGameComponents();
        }
    }

    private void updateGameState() {
        isLose = player.getHealth() <= 0 || !home.isAlive();
        if (isLose) {
            SoundEffect.gameOverSound();
            TimerManager.getSharedTimer().stop();
            GameplayMenu.displayGameOverPanel();
        }
    }

    private void resetGameComponents() {
        try {
            tankManager.stopAllTimer();
            powerUpsManager.stopAllTimer();
            player = new PlayerTank(new Point2D((int) (10 * Main.FRAME_HEIGHT / 27.9), (int) (25 * Main.FRAME_HEIGHT / 27.9)));
            tankManager = new TankManager();
            home = new Home(new Point2D((int) (13 * Main.FRAME_HEIGHT / 27.9), (int) (25 * Main.FRAME_HEIGHT / 27.9)));
            powerUpsManager = new PowerUpsManager(tankManager);

            levelRenderer = new LevelRenderer(currentLevel);
            map = levelRenderer.getMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update enemy tank.
     */
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
                    SoundEffect.iceSound();
                    startSlidingEffect(tank);
                }
                return;
            }
        }
    }

    private boolean isTankSliding(EnemyTank tank) {
        return slidingTimers.containsKey(tank) && slidingTimers.get(tank).isRunning();
    }

    /**
     * Update enemy bullet.
     *
     * @param tank the tank
     * @param g    the g
     */
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
                destroyTanks.startDestroyAnimation(home.getPosition().getX(), home.getPosition().getY(), Directions.UP);
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
            destroyEnvironment.startDestroyAnimation(bullet.getX(), bullet.getY(), bullet.getDirection());
            bulletIterator.remove();

            if (player.isShield()) {
                SoundEffect.shieldHitSound();
                player.setShield(false);
            } else {
                player.setHealth(Math.max(0, player.getHealth() - 1));
                destroyTanks.startDestroyAnimation(player.getPosition().getX(), player.getPosition().getY(), player.getDirection());
                player.respawn();
                if (player.getHealth() <= 0) {
                    isLose = true;
                    revalidate();
                    repaint();
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
                if (environment instanceof BrickWall) {
                    SoundEffect.brickHitSound();
                } else if (environment instanceof SteelWall) {
                    SoundEffect.steelHitSound();
                }
                environment.setHealth(environment.getHealth() - 1);
                destroyEnvironment.startDestroyAnimation(bullet.getX(), bullet.getY(), bullet.getDirection());
                bulletIterator.remove();

                if (environment.getHealth() <= 0) {
                    SoundEffect.eExplosionSound();
                    environmentIterator.remove();
                }
                break;
            }
        }
    }

    /**
     * Update player bullet.
     */
// Player bullet
    public void updatePlayerBullet() {
        Iterator<Bullet> bulletIterator = player.getBulletList().iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            boolean isHandled;

            if (!bullet.isActive()) {
                bulletIterator.remove();
                continue;
            }

            if (collision2D(bullet, home)) {
                destroyTanks.startDestroyAnimation(home.getPosition().getX(), home.getPosition().getY(), Directions.UP);
                bulletIterator.remove();
                isLose = true;
                home.setAlive(false);
                break;
            }

            bullet.move();

            isHandled = handleTankCollision(bullet, bulletIterator);

            if (!isHandled) {
                handleEnvironmentCollision(bullet, bulletIterator);
            }
        }
    }

    private boolean handleTankCollision(Bullet bullet, Iterator<Bullet> bulletIterator) {
        Iterator<EnemyTank> tankIterator = tankManager.getTankList().iterator();

        while (tankIterator.hasNext()) {
            EnemyTank tank = tankIterator.next();

            if (tank.isDisplay() && collision2D(bullet, tank) && !tank.isInvincible()) {
                bulletIterator.remove();
                tank.takeDamage(1);
                destroyEnvironment.startDestroyAnimation(bullet.getX(), bullet.getY(), bullet.getDirection());

                if (tank.getHealth() <= 0) {
                    score += tank.getPoint();
                    destroyTanks.startDestroyAnimation(tank.getPosition().getX(), tank.getPosition().getY(), tank.getDirection());
                    player.addPoints(tank.getPoint());
                    currentEnemies.remove(tank);
                    tankIterator.remove();
                }
                return true;
            }
        }
        return false;
    }

    private void handleEnvironmentCollision(Bullet bullet, Iterator<Bullet> bulletIterator) {
        Iterator<Environment> environmentIterator = map.iterator();

        while (environmentIterator.hasNext()) {
            Environment environment = environmentIterator.next();

            if (environment != null && environment.isDestroyable() && collision2D(bullet, environment)) {
                if (environment instanceof BrickWall) {
                    SoundEffect.brickHitSound();
                } else if (environment instanceof SteelWall) {
                    SoundEffect.steelHitSound();
                }
                int damage = calculateEnvironmentDamage(player.getTier(), environment);
                environment.setHealth(environment.getHealth() - damage);

                destroyEnvironment.startDestroyAnimation(bullet.getX(), bullet.getY(), bullet.getDirection());

                bulletIterator.remove();

                if (environment.getHealth() <= 0) {
                    SoundEffect.eExplosionSound();
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        drawComponents(g);
    }

    /**
     * Draw components.
     *
     * @param g the g
     */
    public void drawComponents(Graphics g) {
        updateGameLogic();
        setBackground(Color.BLACK);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Main.FRAME_HEIGHT, Main.FRAME_HEIGHT);

        drawEnvironment(g, false);
        drawPlayer(g);
        drawEnemies(g);
        drawBullets(g);
        drawEnvironment(g, true);
        drawHome(g);
        drawPowerUps(g);
        destroyEnvironment.drawAnimations(g, (int) (Main.FRAME_HEIGHT / 27.9));
        destroyTanks.drawAnimations(g, (int) (3 * Main.FRAME_HEIGHT / 27.9));
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

        health = player.getHealth();

        if (player.isInvincible()) {
            if (player.getCurrentInvincible() == PlayerTank.invincibleImage1) {
                player.setCurrentInvincible(PlayerTank.invincibleImage2);
            } else {
                player.setCurrentInvincible(PlayerTank.invincibleImage1);
            }
            g.drawImage(player.getCurrentInvincible(), player.getPosition().getX(), player.getPosition().getY(), player.getWidth(), player.getHeight(), null);
        }

        if (player.isShield()) {
            g.drawImage(PlayerTank.shieldImage, player.getPosition().getX(), player.getPosition().getY(), player.getWidth(), player.getHeight(), null);
        }

        updatePlayerBullet();
    }

    private void drawEnemies(Graphics g) {
        int tankOnMap = 0;
        int enemyLeft1 = Math.min(4, tankManager.getTankList().size());

        while (tankOnMap < enemyLeft1) {
            EnemyTank tank = tankManager.getTankList().get(tankOnMap);

            if (!tank.isDisplay()) {
                enemyTankSpawner.startSpawnAnimation(tank);
                tank.setDisplay(true);
            }

            currentEnemies.add(tank);

            enemyTankSpawner.drawTank(g, tank);
            updateEnemyBullet(tank, g);
            tankOnMap++;

            enemyLeft = tankManager.getTankList().size();
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

    /**
     * Start sliding effect.
     *
     * @param tank the tank
     */
    public void startSlidingEffect(BaseTank tank) {
        if (slidingTimers.containsKey(tank) && slidingTimers.get(tank).isRunning()) {
            slidingTimers.get(tank).stop();
            slidingTimers.remove(tank);
        }

        AtomicReference<Timer> slipperyTimer = new AtomicReference<>();

        SLIPPERY = Main.FRAME_HEIGHT / 150;

        Timer newSlipperyTimer = new Timer(80, e -> {
            if (SLIPPERY > 0) {
                try {
                    tank.move(SLIPPERY);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

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
    }

    private void updateGameLogic() {
        updateEnemyTank();
        updateLevel();
        updateGameState();
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
                if (environment != null && collision2D(player, environment) && environment instanceof Ice) {
                    SoundEffect.iceSound();
                    startSlidingEffect(player);
                }
            }
        }
    }
}
