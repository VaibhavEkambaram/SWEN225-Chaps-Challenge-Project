package nz.ac.vuw.ecs.swen225.gp23.render;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Extension of AudioModule.
 * Specifically made to handle Chap's Challenge audio effects.
 *
 * @author Cameron Li - 300490702
 */
public class ChipAudioModule extends AudioModule {
  // Sound tracks
  private final String chip1 = ("/audio/Chip_1.wav");
  private final String chip2 = ("/audio/Chip_2.wav");
  private final String canyon = ("/audio/Canyon.wav");

  // Effects
  private final String move = ("/audio/Move.wav");

  // Unused Sound Effects
  // private final String exit = ("/audio/Exit.wav");
  // private final String pickup = ("/audio/Bling.wav");
  // private final String select = ("/audio/Select.wav");

  // Audio Clips
  private Clip moveClip;
  private Clip backgroundClip; // Clip used for background music/soundtracks (looping)

  /**
   * Constructor for Chip Audio Module.
   * Initialises all the Effects Clips.
   *
   * @author Cameron Li - 300490702
   */
  public ChipAudioModule() {
    try {
      moveClip = AudioSystem.getClip();
      backgroundClip = AudioSystem.getClip();
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * Depending on the current level.
   * Play a different sound track.
   *
   * @param level Integer used to determine which track to play
   * @author Cameron Li - 300490702
   */
  public void playCurrentLevelTrack(int level) {
    if (level % 3 == 1) {
      playSoundTrack(backgroundClip, chip2);
    } else if (level % 3 == 2) {
      playSoundTrack(backgroundClip, canyon);
    } else {
      playSoundTrack(backgroundClip, chip1);
    }
  }

  /**
   * Plays the move sound effect.
   *
   * @author Cameron Li - 300490702
   */
  public void moveEffect() {
    playSound(moveClip, move);
  }


}
