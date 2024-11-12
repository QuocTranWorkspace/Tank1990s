package jsd.project.tank90.common.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * The type Sound effect.
 */
public class SoundEffect {
    private static final File bonus = new File("src/jsd/project/tank90/common/resources/sound/bonus_fixed.wav");
    private static final File brickhit = new File("src/jsd/project/tank90/common/resources/sound/brickhit_fixed.wav");
    private static final File eexplosion = new File("src/jsd/project/tank90/common/resources/sound/eexplosion_fixed.wav");
    private static final File fexplosion = new File("src/jsd/project/tank90/common/resources/sound/fexplosion_fixed.wav");
    private static final File gameover = new File("src/jsd/project/tank90/common/resources/sound/gameover_fixed.wav");
    private static final File ice = new File("src/jsd/project/tank90/common/resources/sound/ice_fixed.wav");
    private static final File levelstarting = new File("src/jsd/project/tank90/common/resources/sound/levelstarting_fixed.wav");
    private static final File life = new File("src/jsd/project/tank90/common/resources/sound/life_fixed.wav");
    private static final File moving = new File("src/jsd/project/tank90/common/resources/sound/moving_fixed.wav");
    private static final File nmoving = new File("src/jsd/project/tank90/common/resources/sound/nmoving_fixed.wav");
    private static final File pause = new File("src/jsd/project/tank90/common/resources/sound/pause_fixed.wav");
    private static final File shieldhit = new File("src/jsd/project/tank90/common/resources/sound/shieldhit_fixed.wav");
    private static final File shoot = new File("src/jsd/project/tank90/common/resources/sound/shoot_fixed.wav");
    private static final File steelhit = new File("src/jsd/project/tank90/common/resources/sound/steelhit_fixed.wav");
    private static final File tbonushit = new File("src/jsd/project/tank90/common/resources/sound/tbonushit_fixed.wav");

    /**
     * Bonus sound.
     */
    public static void bonusSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bonus);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Brick hit sound.
     */
    public static void brickHitSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(brickhit);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * E explosion sound.
     */
    public static void eExplosionSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(eexplosion);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * F explosion sound.
     */
    public static void fExplosionSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(fexplosion);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Game over sound.
     */
    public static void gameOverSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(gameover);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Ice sound.
     */
    public static void iceSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(ice);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Level starting sound.
     */
    public static void levelStartingSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(levelstarting);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Life sound.
     */
    public static void lifeSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(life);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Moving sound.
     */
    public static void movingSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(moving);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-25.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * N moving sound.
     */
    public static void nMovingSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(nmoving);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-25.0f);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Pause sound.
     */
    public static void pauseSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(pause);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Steel hit sound.
     */
    public static void steelHitSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(steelhit);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Shoot sound.
     */
    public static void shootSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(shoot);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Shield hit sound.
     */
    public static void shieldHitSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(shieldhit);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * T bonus hit sound.
     */
    public static void tBonusHitSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(tbonushit);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }
}
