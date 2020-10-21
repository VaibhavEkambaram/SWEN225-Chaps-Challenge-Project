package nz.ac.vuw.ecs.swen225.gp23.render;

import nz.ac.vuw.ecs.swen225.gp23.application.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Audio Modile
 * Used to play audio
 *
 * @author Cameron Li
 */
public class AudioModule {

    /**
     * Play specified audio file from url string
     * Audio file is played on background clip and loops
     *
     * @author Cameron Li
     */
    public static void playSoundTrack(Clip clip, String audioFile) {
        try {
            if (clip.isOpen()) {
                resetSoundTrack(clip);
            }
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(audioFile));
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Play specified audio file from url string
     * Audio file is played on effects clip, does not loop
     *
     * @author Cameron Li
     */
    public static void playSound(Clip clip, String audioFile) {
        try {
            if (!clip.isOpen()) {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(audioFile));
                clip.open(inputStream);
            }
            resetTrack(clip);
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Pause the current sound track on the clip
     *
     * @author Cameron Li
     */
    public static void pauseSoundTrack(Clip clip) {
        try {
            clip.stop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Resume/play the current sound track on the background clip
     * Loops
     *
     * @author Cameron Li
     */
    public static void playSoundLoop(Clip clip) {
        try {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reset the sound track of the background clip
     * Does not play anything anymore
     *
     * @author Cameron Li
     */
    public static void resetSoundTrack(Clip clip) {
        try {
            if (clip.isRunning()) {
                clip.stop();
                clip.close();
                clip.setFramePosition(0);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Reset the sound track of the background clip
     * Does not play anything anymore
     *
     * @author Cameron Li
     */
    public static void resetTrack(Clip clip) {
        try {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
