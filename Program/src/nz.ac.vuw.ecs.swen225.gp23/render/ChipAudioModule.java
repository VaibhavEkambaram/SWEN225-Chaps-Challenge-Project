package nz.ac.vuw.ecs.swen225.gp23.render;

public class ChipAudioModule extends AudioModule {
    private final String chip_1 = ("/Chip_1.wav");
    private final String chip_2 = ("/Chip_2.wav");
    private final String canyon = ("/Canyon.wav");

    /**
     * Depending on the current level
     * Play a different sound track
     * @param level
     *
     * @author Cameron Li
     */
    public void playCurrentLevelTrack(int level) {
        if (level % 3 == 1) {
            playSoundTrack(chip_2);
        } else if (level % 3 == 2) {
            playSoundTrack(canyon);
        } else {
            playSoundTrack(chip_1);
        }
    }

    /**
     * Manual search for an audio file, shouldn't be needed
     * @param filename
     *
     * @author Cameron Li
     */
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
}
