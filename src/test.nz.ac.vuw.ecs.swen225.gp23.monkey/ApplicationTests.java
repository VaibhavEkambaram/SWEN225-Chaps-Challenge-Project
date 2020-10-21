package test.nz.ac.vuw.ecs.swen225.gp23.monkey;

import static junit.framework.TestCase.assertEquals;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import org.junit.Test;



/**
 * Tests for Application Module.
 *
 * @author Sushil Sharma
 */
public class ApplicationTests {
  private Application app = new Application();
  private Application.GameStates runningState = Application.GameStates.RUNNING;
  private Application.GameStates idleState = Application.GameStates.IDLE;

  /**
   * Testing to see if the state is correct.
   * state should be running.
   */
  @Test
  public void testingTransitionRunningState() {
    app = new Application();
    app.transitionToRunning();
    assertEquals(runningState, app.getState());
  }

  /**
   * Testing to see if the state is correct.
   * state should be idle.
   */
  @Test
  public void testingTransitionIdleState() {
    app = new Application();
    assertEquals(idleState, app.getState());
  }
}
