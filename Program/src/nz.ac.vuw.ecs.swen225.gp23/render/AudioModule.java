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
    private Clip backgroundClip; // Clip used for background music/soundtracks (looping)
    private Clip effectClip; // Clip used for sound effects (non-looping)

    /**
     * Constructor, initialise the Clips
     *
     * @author Cameron Li
     */
    public AudioModule() {
        try {
            backgroundClip = AudioSystem.getClip();
            effectClip = AudioSystem.getClip();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Play specified audio file from url string
     * Audio file is played on background clip and loops
     *
     * @author Cameron Li
     */
    public void playSoundTrack(final String audioFile) {
        try {
            if (backgroundClip.isOpen()) {
                resetSoundTrack();
            }
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(audioFile));
            backgroundClip.open(inputStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Play specified audio file from url string
     * Audio file is played on effects clip, does not loop
     * @param audioFile
     *
     * @author Cameron Li
     */
    public void playSound(final String audioFile) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(audioFile));
            effectClip.open(inputStream);
            effectClip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Pause the current sound track on the background clip
     *
     * @author Cameron Li
     */
    public void pauseSoundTrack() {
        try {
            backgroundClip.stop();
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
    public void playSoundTrack() {
        try {
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
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
    public void resetSoundTrack() {
        try {
            backgroundClip.stop();
            backgroundClip.close();
            backgroundClip.setFramePosition(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
