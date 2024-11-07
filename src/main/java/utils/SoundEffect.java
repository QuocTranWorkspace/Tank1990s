package main.java.utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundEffect {
    private static final File bonus = new File("src/main/resource/sound/bonus_fixed.wav");
    private static final File brickhit = new File("src/main/resource/sound/brickhit_fixed.wav");
    private static final File eexplosion = new File("src/main/resource/sound/eexplosion_fixed.wav");
    private static final File fexplosion = new File("src/main/resource/sound/fexplosion_fixed.wav");
    private static final File gameover = new File("src/main/resource/sound/gameover_fixed.wav");
    private static final File ice = new File("src/main/resource/sound/ice_fixed.wav");
    private static final File levelstarting = new File("src/main/resource/sound/levelstarting_fixed.wav");
    private static final File life = new File("src/main/resource/sound/life_fixed.wav");
    private static final File moving = new File("src/main/resource/sound/moving_fixed.wav");
    private static final File nmoving = new File("src/main/resource/sound/nmoving_fixed.wav");
    private static final File pause = new File("src/main/resource/sound/pause_fixed.wav");
    private static final File shieldhit = new File("src/main/resource/sound/shieldhit_fixed.wav");
    private static final File shoot = new File("src/main/resource/sound/shoot_fixed.wav");
    private static final File steelhit = new File("src/main/resource/sound/steelhit_fixed.wav");
    private static final File tbonushit = new File("src/main/resource/sound/tbonushit_fixed.wav");

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
