package main.java.service;

import main.java.App;
import main.java.controller.BaseScene;
import main.java.controller.GameplayMenu;
import main.java.model.Bullet;
import main.java.model.PlayerTank;
import main.java.model.Point2D;
import main.java.model.environments.BrickWall;
import main.java.model.environments.Environment;
import main.java.model.environments.SteelWall;
import main.java.model.environments.Tree;
import main.java.model.tanks.BaseTank;
import main.java.model.tanks.Directions;
import main.java.model.tanks.EnemyTank;
import main.java.utils.LevelRenderer;
import main.java.utils.TankSpawner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;

public class GameplayManager extends BaseScene implements ActionListener, KeyListener {
    public static final int VELOCITY_MOVE = 3;
    public static final int VELOCITY_SHOOT = 3;
    private final transient TankSpawner playerTankSpawner = new TankSpawner();
    private final transient TankSpawner enemyTankSpawner = new TankSpawner();
    private transient PlayerTank player;
    private transient TankManager tankManager;
    private int currentLevel = 0;
    private int nextLevel = currentLevel;
    private transient LevelRenderer levelRenderer = new LevelRenderer(currentLevel);
    private transient java.util.List<Environment> map = levelRenderer.getMap();

    public GameplayManager() throws Exception {
        Timer gameLoop = TimerManager.getSharedTimer();
        player = new PlayerTank(new Point2D(4 * App.FRAME_HEIGHT / 13, 12 * App.FRAME_HEIGHT / 13));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        tankManager = new TankManager();

        gameLoop.addActionListener(this);
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        drawComponents(g);
    }

    public void updateGameLogic() {
        updateEnemyTank();
        updatePlayerBullet();
        updateLevel();
    }

    private void updateLevel() {
        if (tankManager.getTankList().isEmpty()) {
            nextLevel++;
        }
        if (currentLevel < nextLevel) {
            tankManager.stopAllTimer();
            try {
                player = new PlayerTank(new Point2D(4 * App.FRAME_HEIGHT / 13, 12 * App.FRAME_HEIGHT / 13));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            try {
                tankManager = new TankManager();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            currentLevel = nextLevel;
            levelRenderer = new LevelRenderer(currentLevel);
            map = levelRenderer.getMap();
        }
    }

    public void updateEnemyTank() {
        for (EnemyTank tank : tankManager.getTankList()) {
            if (tank.isDisplay()) {
                try {
                    tank.move();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Iterator<Environment> environmentIterator = map.iterator();
                while (environmentIterator.hasNext()) {
                    Environment environment = environmentIterator.next();
                    if (environment != null && !environment.isWalkable()) {
                        if (collision2D(tank, environment)) {
                            tank.revertToPreviousPosition();
                            break;
                        }
                    }
                }
            }
        }
    }

    public void updateEnemyBullet(EnemyTank tank, Graphics g) {
        Iterator<Bullet> bulletIterator = tank.getBulletList().iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            if (bullet.isActive()) {
                g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(), null);
                bullet.move();
                if (player.isDisplay() && collision2D(bullet, player)) {
                    bulletIterator.remove();
                    if (player.isShield()) {
                        player.setShield(false);
                    } else {
                        player.setHealth(tank.getHealth() - 1);
                        if (player.getHealth() <= 0) {
                            System.out.println("hehe");
                        }
                    }
                    break;
                }

                Iterator<Environment> environmentIterator = map.iterator();
                while ((environmentIterator.hasNext())) {
                    Environment environment = environmentIterator.next();
                    if (environment != null) {
                        if (environment.isDestroyable() && collision2D(bullet, environment)) {
                            environment.setHealth(environment.getHealth() - 1);
                            bulletIterator.remove();
                            if (environment.getHealth() <= 0) {
                                environmentIterator.remove();
                                break;
                            }
                            break;
                        }
                    }
                }
            } else {
                bulletIterator.remove();
            }
        }
    }

    public void updatePlayerBullet() {
        Iterator<Bullet> bulletIterator = player.getBulletList().iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            if (bullet.isActive()) {
                bullet.move();

                Iterator<EnemyTank> tankIterator = tankManager.getTankList().iterator();
                while (tankIterator.hasNext()) {
                    EnemyTank tank = tankIterator.next();
                    if (tank.isDisplay() && collision2D(bullet, tank)) {
                        bulletIterator.remove();
                        tank.takeDamage(1);
                        if (tank.getHealth() <= 0) {
                            player.addPoints(tank.getPoint());
                            tankIterator.remove();
                        }
                        break;
                    }
                }

                Iterator<Environment> environmentIterator = map.iterator();
                while ((environmentIterator.hasNext())) {
                    Environment environment = environmentIterator.next();
                    if (environment != null) {
                        if (environment.isDestroyable() && collision2D(bullet, environment)) {
                            if (player.getTier() == 4) {
                                if (environment instanceof SteelWall) {
                                    environment.setHealth(environment.getHealth() - 50000);
                                } else if (environment instanceof BrickWall) {
                                    environment.setHealth(environment.getHealth() - 2);
                                }
                            } else {
                                environment.setHealth(environment.getHealth() - 1);
                            }
                            bulletIterator.remove();
                            if (environment.getHealth() <= 0) {
                                environmentIterator.remove();
                                break;
                            }
                            break;
                        }
                    }
                }
            } else {
                bulletIterator.remove();
            }
        }
    }

    public void drawComponents(Graphics g) {
        this.setBackground(Color.BLACK);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, App.FRAME_HEIGHT, App.FRAME_HEIGHT);

        for (Environment environment : map) {
            if (environment == null) {
                continue;
            }
            if (!(environment instanceof Tree)) {
                g.drawImage(environment.getImage(), environment.getPosition().getX(), environment.getPosition().getY(), environment.getWidth(), environment.getHeight(), null);
            }
        }

        if (!player.isDisplay()) {
            playerTankSpawner.startSpawnAnimation(player);
            player.setDisplay(true);
        }
        playerTankSpawner.drawTank(g, player);

        int tankOnMap = 0;
        int enemyLeft = Math.min(4, tankManager.getTankList().size());

        while (tankOnMap < enemyLeft) {
            EnemyTank tank = tankManager.getTankList().get(tankOnMap);

            if (!tank.isDisplay()) {
                enemyTankSpawner.startSpawnAnimation(tank);
                tank.setDisplay(true);
            }

            enemyTankSpawner.drawTank(g, tank);
            tankOnMap++;

            updateEnemyBullet(tank, g);
        }

        // Draw active bullets
        for (Bullet bullet : player.getBulletList()) {
            if (bullet.isActive()) {
                g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(), null);
            }
        }

        for (Environment environment : map) {
            if (environment == null) {
                continue;
            }
            if (environment instanceof Tree) {
                g.drawImage(environment.getImage(), environment.getPosition().getX(), environment.getPosition().getY(), environment.getWidth(), environment.getHeight(), null);
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

    @Override
    public void keyPressed(KeyEvent e) {
        Directions direction = null;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_S -> direction = Directions.DOWN;
            case KeyEvent.VK_W -> direction = Directions.UP;
            case KeyEvent.VK_D -> direction = Directions.RIGHT;
            case KeyEvent.VK_A -> direction = Directions.LEFT;
            default -> direction = null;
        }

        if (direction != null) {
            Iterator<Environment> environmentIterator = map.iterator();
            while (environmentIterator.hasNext()) {
                Environment environment = environmentIterator.next();
                if (environment != null && !environment.isWalkable()) {
                    if (collision2D(player, environment)) {
                        player.revertToPreviousPosition();
                        break;
                    }
                }
            }
            try {
                player.move(direction, VELOCITY_MOVE);
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

    @Override
    public void keyReleased(KeyEvent e) {
        //
    }
}
