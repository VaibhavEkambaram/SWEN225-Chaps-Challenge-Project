package nz.ac.vuw.ecs.swen225.gp23.render;

import nz.ac.vuw.ecs.swen225.gp23.application.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class AudioModule {
    private final String chip_1 = ("/Chip_1.wav");
    private final String chip_2 = ("/Chip_2.wav");
    private final String canyon = ("/Canyon.wav");

    private Clip backgroundClip;
    private Clip effectClip;

    public AudioModule() {
        try {
            backgroundClip = AudioSystem.getClip();
            effectClip = AudioSystem.getClip();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void findSound(String filename) {
        // using the URL means the image loads when stored
        // in a jar or expanded into individual files.
        if (filename.equals("chip_1") || filename.equals("chip_1.wav")) {
            playSoundTrack(chip_1);
        } else if (filename.equals("chip_2") || filename.equals("chip_2.wav")) {
            playSoundTrack(chip_2);
        } else if (filename.equals("canyon") || filename.equals("canyon.wav")) {
            playSoundTrack(canyon);
        } else {
            throw new Error("Unknown audio file " + filename);
        }
    }

    public void playSoundTrack(final String audioFile) {
        try {
            if (backgroundClip.isOpen()) {
                backgroundClip.close();
                backgroundClip.stop();
                backgroundClip.setFramePosition(0);
            }
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(audioFile));
            backgroundClip.open(inputStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void playSound(final String audioFile) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Main.class.getResourceAsStream(audioFile));
            effectClip.open(inputStream);
            effectClip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
