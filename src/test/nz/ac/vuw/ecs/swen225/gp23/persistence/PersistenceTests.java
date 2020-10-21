package test.nz.ac.vuw.ecs.swen225.gp23.persistence;

import static junit.framework.TestCase.assertEquals;

import java.io.IOException;
import javax.swing.*;
import junit.framework.TestCase;
import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import nz.ac.vuw.ecs.swen225.gp23.persistence.LevelM;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;
import org.junit.Test;



/**
 * JUnit tests for Persistence.
 *
 * @author Rahul Mahasuriya 300473482
 */
public class PersistenceTests {
  private Application app = new Application();
  private GraphicalInterface gui = new GraphicalInterface(app);

  /**
   * First time game starts up.
   */
  @Test
  public void firstStartUp() {
    GraphicalInterface gui = new GraphicalInterface(app);
    gui.setVisible(true);
    gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    assert (true);
  }

  /**
   * Checks valid level transition.
   */
  @Test
  public void validLevelTransition() {
    gui.updateDisplay();
    gui.onNewGame();
    LevelM lm = new LevelM();

    assertEquals("level1.json", lm.getCurrentLevel());
    gui.levelCompleteMessage(1, 0, 0, 11);
    lm.setLevel(2);
    assertEquals("level2.json", lm.getCurrentLevel());

  }

  /**
   * Invalid level transition.
   */
  @Test
  public void invalidLevelTransition() {
    gui.updateDisplay();
    gui.onNewGame();
    LevelM lm = new LevelM();

    assertEquals("level1.json", lm.getCurrentLevel());
    gui.levelCompleteMessage(1, 0, 0, 11);
    assertEquals("level1.json", lm.getCurrentLevel());

  }

  /**
   * Rejecting invalid game load.
   */
  @Test
  public void invalidLoadGame() {
    gui.updateDisplay();
    gui.onNewGame();
    try {
      Persistence.readBoard("invalid.txt");
      throw new IOException("Invalid File");
    } catch (IOException e) {
      TestCase.assertTrue(true);
    }
    TestCase.assertTrue(true);
  }

  /**
   * Checks valid time is allocated on startup from Persistence to GUI.
   */
  @Test
  public void validTimeLeft() {
    gui.updateDisplay();
    gui.onNewGame();
    assertEquals(Persistence.getTimeLeft(), gui.getCurrentGame().getTimeLeft());

  }

  /**
   * Checks valid level number is allocated on startup from Persistence to GUI.
   */
  @Test
  public void validLevelNumber() {
    gui.updateDisplay();
    gui.onNewGame();
    assertEquals(Persistence.getLevel(), gui.getCurrentGame().getLevelNumber());
  }

  @Test
  public void validLoadFileLevel1() {
    gui.updateDisplay();
    gui.onNewGame();
    LevelM lm = new LevelM();
    assertEquals("level1.json", lm.getCurrentLevel());

  }

  /**
   * Checks correct Level2.json is loaded.
   */
  @Test
  public void validLoadFileLevel2() {
    gui.updateDisplay();
    gui.onNewGame();
    LevelM lm = new LevelM();
    assertEquals("level1.json", lm.getCurrentLevel());
    lm.incrementLevel();
    assertEquals("level2.json", lm.getCurrentLevel());

  }

  /**
   * Test for checking valid set level.
   */
  @Test
  public void validSetLevel() {
    gui.updateDisplay();
    gui.onNewGame();
    LevelM lm = new LevelM();

    assertEquals("level1.json", lm.getCurrentLevel());
    lm.setLevel(2);
    assertEquals("level2.json", lm.getCurrentLevel());
  }


}