package nz.ac.vuw.ecs.swen225.gp23.application;

/**
 * Application Class.
 * Persistent class which observes game states.
 *
 * @author Vaibhav Ekambaram - 300472561
 */
public class Application {

  /**
   * States to track the overall game.
   */
  public enum GameStates {
    IDLE, RUNNING
  }

  private GameStates state;
  private final GraphicalInterface gui;


  /**
   * Application Constructor.
   * Create new GUI
   */
  public Application() {
    state = GameStates.IDLE;
    gui = new GraphicalInterface(this);
    gui.updateDisplay();
  }


  /**
   * Transition state from IDLE to RUNNING.
   */
  public void transitionToRunning() {
    if (state == GameStates.IDLE) {
      state = GameStates.RUNNING;
      gui.updateDisplay();
    } else {
      throw new Error("Excepted Init, but found " + state.toString());
    }
  }

  /**
   * Transition state from RUNNING to IDLE.
   */
  public void transitionToInit() {
    if (state == GameStates.RUNNING) {
      state = GameStates.IDLE;
      gui.updateDisplay();
    } else {
      throw new Error("Excepted running, but found state " + state.toString());
    }
  }


  /**
   * Get Application state.
   *
   * @return current state
   */
  public GameStates getState() {
    return state;
  }
}
