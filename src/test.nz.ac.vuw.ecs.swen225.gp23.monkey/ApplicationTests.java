package test.nz.ac.vuw.ecs.swen225.gp23.monkey;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Sushil Sharma
 */
public class ApplicationTests {
  private Application app;
  private Application.GameStates runningState = Application.GameStates.RUNNING;
  private Application.GameStates idleState = Application.GameStates.IDLE;

  /**
   * Testing to see if the state is correct.
   * state should be running.
   */
  @Test
  public void transRunningState() {
    app = new Application();
    app.transitionToRunning();
    assertEquals(runningState, app.getState());
  }

  /**
   * Testing to see if the state is correct.
   * state should be idle.
   */
  @Test
  public void transIdleState() {
    app = new Application();
    assertEquals(idleState, app.getState());
  }
}
