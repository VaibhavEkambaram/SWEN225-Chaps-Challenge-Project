package nz.ac.vuw.ecs.swen225.gp23.render;

import nz.ac.vuw.ecs.swen225.gp23.application.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ChipAudioModule extends AudioModule {
    // Sound tracks
    private final String chip_1 = ("/audio/Chip_1.wav");
    private final String chip_2 = ("/audio/Chip_2.wav");
    private final String canyon = ("/audio/Canyon.wav");

    // Effects
    private final String exit = ("/audio/Exit.wav");
    private final String move = ("/audio/Move.wav");
    private final String pickup = ("/audio/Bling.wav");
    private final String select = ("/audio/Select.wav");

    // Audio Clips
    private Clip exitClip;
    private Clip moveClip;
    private Clip pickupClip;
    private Clip selectClip;
    private Clip backgroundClip; // Clip used for background music/soundtracks (looping)

    /**
     * Constructor for Chip Audio Module
     * Initialises all the Effects Clips
     */
    public ChipAudioModule() {
        try {
            exitClip = AudioSystem.getClip();
            moveClip = AudioSystem.getClip();
            pickupClip = AudioSystem.getClip();
            selectClip = AudioSystem.getClip();
            backgroundClip = AudioSystem.getClip();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Depending on the current level
     * Play a different sound track
     * @param level
     * Integer used to determine which track to play
     *
     * @author Cameron Li
     */
    public void playCurrentLevelTrack(int level) {
        if (level % 3 == 1) {
            playSoundTrack(backgroundClip, chip_2);
        } else if (level % 3 == 2) {
            playSoundTrack(backgroundClip, canyon);
        } else {
            playSoundTrack(backgroundClip, chip_1);
        }
    }

    /**
     * Plays the exit sound effect
     */
    public void exitEffect() {
        playSound(exitClip, exit);
    }

    /**
     * Plays the move sound effect
     */
    public void moveEffect() {
        playSound(moveClip, move);
    }

    /**
     * Plays the pickup sound effect
     */
    public void pickupEffect() {
        playSound(pickupClip, pickup);
    }

    /**
     * Plays the select sound effect
     */
    public void selectEffect() {
        playSound(selectClip, select);
    }

    /**
     * Manual search for an audio file, shouldn't be needed
     * @param filename
     * Name of the audio file
     *
     * @author Cameron Li
     */
    private void findSound(String filename) {
        // using the URL means the image loads when stored
        // in a jar or expanded into individual files.
        if (filename.equals("chip_1") || filename.equals("chip_1.wav")) {
            playSoundTrack(backgroundClip, chip_1);
        } else if (filename.equals("chip_2") || filename.equals("chip_2.wav")) {
            playSoundTrack(backgroundClip, chip_2);
        } else if (filename.equals("canyon") || filename.equals("canyon.wav")) {
            playSoundTrack(backgroundClip, canyon);
        } else {
            throw new Error("Unknown audio file " + filename);
        }
    }
}
