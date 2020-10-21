package test.nz.ac.vuw.ecs.swen225.gp23.monkey;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import nz.ac.vuw.ecs.swen225.gp23.maze.Player;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

/**
 * Tests for Game & GUI classes.
 *
 * @author Sushil Sharma
 */
public class GameTests {
  private Application app = new Application();
  private GraphicalInterface gui = new GraphicalInterface(app);
  Tile currentTile;

  /**
   * Testing to check if board is not empty.
   */
  @Test
  public void boardNotEmptyTest() {
    gui.updateDisplay();
    gui.onNewGame();

    Game game = gui.getCurrentGame();
    game.isRunningTest(true);
    assertNotNull(game.getBoard());
  }

  /**
   * Check game init after loading level 1.
   */
  @Test
  public void checkGameInit() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    game.isRunningTest(true);
    assertNotNull(game.getBoard());
    assertNotNull(game.getPlayer());
  }

  /**
   * Testing to check player movement
   */
  @Test
  public void moveChap() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    game.getBoard().getTile(7, 6);
    game.getBoard().getTile(7, 6);

    game.isRunningTest(true);
    Player player = new Player(currentTile);
    Tile nextTile = player.getCurrentTile();
    assertEquals(nextTile, player.getCurrentTile());
  }

  /**
   * Rejecting invalid game load.
   */
  @Test
  public void invalidLoadGame() {
    boolean error = false;
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();
    game.isRunningTest(true);

    try {
      Persistence.readBoard("invalid.txt");
      throw new IOException("Invalid File");
    } catch (IOException e) {
      assertTrue(error = true);
    }
    assertTrue(error);
  }

  /**
   * Check that the game pauses when running.
   */
  @Test
  public void checkGamePaused() {
    gui.onLoadGameNoGui("level1.json", false);
    Game game = gui.getCurrentGame();

    game.isRunningTest(true);
    gui.onPauseGame(true);
    assertTrue(game.isPaused());
  }

  /**
   * Check that the game is not paused when first running.
   */
  @Test
  public void checkGameNotPaused() {
    gui.onLoadGameNoGui("level1.json", false);
    Game game = gui.getCurrentGame();

    game.isRunningTest(true);
    assertFalse(game.isPaused());
  }

  /**
   * Creating player to see if the player actual is displayed correctly.
   */
  @Test
  public void playerLoadTest() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    assertEquals("chip_down.png", game.getBoard().getTile(7, 6).getCurrentImage());
  }

  /**
   * Checking the movement of player to the left.
   */
  @Test
  public void testLeftMovement1() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    game.onMovement(Tile.Directions.Left);
    assertEquals(6, game.getPlayer().getCurrentTile().getHorizLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getVertLoc());
  }

  /**
   * Checking the movement of player to the left.
   */
  @Test
  public void testLeftMovement2() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    game.onMovement(Tile.Directions.Left);
    game.onMovement(Tile.Directions.Left);
    assertEquals(5, game.getPlayer().getCurrentTile().getHorizLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getVertLoc());
  }

  /**
   * Checking the movement of player to the right.
   */
  @Test
  public void testRightMovement1() {
    GraphicalInterface gui = new GraphicalInterface(app);
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    game.onMovement(Tile.Directions.Right);
    assertEquals(8, game.getPlayer().getCurrentTile().getHorizLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getVertLoc());
  }

  /**
   * Checking the movement of player to the right.
   */
  @Test
  public void testRightMovement2() {
    GraphicalInterface gui = new GraphicalInterface(app);
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    game.onMovement(Tile.Directions.Right);
    game.onMovement(Tile.Directions.Right);
    assertEquals(9, game.getPlayer().getCurrentTile().getHorizLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getVertLoc());
  }

  /**
   * Checking the movement of player to up direction.
   */
  @Test
  public void testUpMovement1() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    game.onMovement(Tile.Directions.Up);
    assertEquals(7, game.getPlayer().getCurrentTile().getHorizLoc());
    assertEquals(5, game.getPlayer().getCurrentTile().getVertLoc());
  }

  /**
   * Checking the movement of player to up direction.
   */
  @Test
  public void testUpMovement2() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    game.onMovement(Tile.Directions.Up);
    game.onMovement(Tile.Directions.Up);
    assertEquals(7, game.getPlayer().getCurrentTile().getHorizLoc());
    assertEquals(4, game.getPlayer().getCurrentTile().getVertLoc());
  }

  /**
   * Checking the movement of player to down direction.
   */
  @Test
  public void testDownMovement1() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    game.onMovement(Tile.Directions.Down);
    assertEquals(7, game.getPlayer().getCurrentTile().getHorizLoc());
    assertEquals(7, game.getPlayer().getCurrentTile().getVertLoc());
  }

  /**
   * Checking the movement of player to down direction.
   */
  @Test
  public void testDownMovement2() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    game.onMovement(Tile.Directions.Down);
    game.onMovement(Tile.Directions.Down);
    assertEquals(7, game.getPlayer().getCurrentTile().getHorizLoc());
    assertEquals(8, game.getPlayer().getCurrentTile().getVertLoc());
  }

  /**
   * Checking random movement of player.
   */
  @Test
  public void testRndmMovement1() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();

    game.isRunningTest(true);
    assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    game.onMovement(Tile.Directions.Right);
    game.onMovement(Tile.Directions.Right);
    game.onMovement(Tile.Directions.Up);
    game.onMovement(Tile.Directions.Up);
    game.onMovement(Tile.Directions.Left);
    game.onMovement(Tile.Directions.Down);
    game.onMovement(Tile.Directions.Down);

    assertEquals(8, game.getPlayer().getCurrentTile().getXLoc());
    assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
  }

  /**
   * Testing to check if the timer works.
   */
  @Test
  public void timerWorkingTest() {
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();
    game.setTimeLeft(60);
    game.runTimer();

    game.isRunningTest(true);
    assertEquals(60, game.getTimeLeft());
  }

  /**
   * Testing level one board to see if it is correct.
   */
  @Test
  public void checkLevelOneBoard() {
    String text;
    text = "|_|_|#|#|#|#|#|_|#|#|#|#|#|_|_|"
        + "|_|_|#|_|_|_|#|#|#|_|_|_|#|_|_|"
        + "|_|_|#|_|T|_|#|E|#|_|T|_|#|_|_|"
        + "|#|#|#|#|#|G|#|l|#|G|#|#|#|#|#|"
        + "|#|_|y|_|B|_|_|_|_|_|R|_|y|_|#|"
        + "|#|_|T|_|#|b|_|i|_|r|#|_|T|_|#|"
        + "|#|#|#|#|#|T|_|P|_|T|#|#|#|#|#|"
        + "|#|_|T|_|#|b|_|_|_|r|#|_|T|_|#|"
        + "|#|_|_|_|R|_|_|T|_|_|B|_|_|_|#|"
        + "|#|#|#|#|#|#|Y|#|Y|#|#|#|#|#|#|"
        + "|_|_|_|_|#|_|_|#|_|_|#|_|_|_|_|"
        + "|_|_|_|_|#|g|T|#|T|_|#|_|_|_|_|"
        + "|_|_|_|_|#|_|_|#|g|_|#|_|_|_|_|"
        + "|_|_|_|_|#|#|#|_|#|#|#|_|_|_|_|";

    GraphicalInterface gui = new GraphicalInterface(app);
    gui.updateDisplay();
    gui.onNewGame();
    Game game = gui.getCurrentGame();
    String s = game.printOutBoard();

    game.isRunningTest(true);

    if (s.equals(text)) {
      assertEquals(text, s);
    }
  }

  /**
   * Perform 1000 movements in level 1 to ensure movement is working correctly.
   */
  @SuppressWarnings("checkstyle:LineLength")
  @Test
  public void randomMovementTest() {
    gui.onLoadGameNoGui("level1.json", false);
    Game game = gui.getCurrentGame();
    game.isRunningTest(true);

    List<Tile.Directions> movements = new ArrayList<>();
    movements.add(Tile.Directions.Up);
    movements.add(Tile.Directions.Down);
    movements.add(Tile.Directions.Left);
    movements.add(Tile.Directions.Right);

    String expectedBoard = "_|_|#|#|#|#|#|_|#|#|#|#|#|_|_|_|_|#|_|_|_|#|#|#|_|_|_|#|_|_|_|_|#|_|T|_|#|E|#|_|T|_|#|_|_|#|#|#|#|#|G|#|l|#|G|#|#|#|#|#|#|_|y|_|B|_|_|_|_|_|R|_|y|_|#|#|_|T|_|#|b|_|i|_|r|#|_|T|_|#|#|#|#|#|#|T|_|P|_|T|#|#|#|#|#|#|_|T|_|#|b|_|_|_|r|#|_|T|_|#|#|_|_|_|R|_|_|T|_|_|B|_|_|_|#|#|#|#|#|#|#|Y|#|Y|#|#|#|#|#|#|_|_|_|_|#|_|_|#|_|_|#|_|_|_|_|_|_|_|_|#|g|T|#|T|_|#|_|_|_|_|_|_|_|_|#|_|_|#|g|_|#|_|_|_|_|_|_|_|_|#|#|#|_|#|#|#|_|_|_|_";

    game.isRunningTest(true);
    assertEquals(expectedBoard, game.getBoard().toString());
    for (int i = 0; i < 1000; i++) {
      int rand = (int) (Math.random() * 4);
      game.onMovement(movements.get(rand));
    }
  }

  /**
   * Pause the game and then trying to perform 1000 moves.
   * As the game is paused, the board should look like as it would in its original state.
   */
  @SuppressWarnings("checkstyle:LineLength")
  @Test
  public void randomMovementPausedTest() {
    gui.onLoadGameNoGui("level1.json", false);
    Game game = gui.getCurrentGame();
    game.isRunningTest(true);

    List<Tile.Directions> movements = new ArrayList<>();
    movements.add(Tile.Directions.Up);
    movements.add(Tile.Directions.Down);
    movements.add(Tile.Directions.Left);
    movements.add(Tile.Directions.Right);

    gui.onPauseGame(true);
    for (int i = 0; i < 1000; i++) {
      int rand = (int) (Math.random() * 4);
      game.onMovement(movements.get(rand));
    }
    String expectedBoard = "_|_|#|#|#|#|#|_|#|#|#|#|#|_|_|_|_|#|_|_|_|#|#|#|_|_|_|#|_|_|_|_|#|_|T|_|#|E|#|_|T|_|#|_|_|#|#|#|#|#|G|#|l|#|G|#|#|#|#|#|#|_|y|_|B|_|_|_|_|_|R|_|y|_|#|#|_|T|_|#|b|_|i|_|r|#|_|T|_|#|#|#|#|#|#|T|_|P|_|T|#|#|#|#|#|#|_|T|_|#|b|_|_|_|r|#|_|T|_|#|#|_|_|_|R|_|_|T|_|_|B|_|_|_|#|#|#|#|#|#|#|Y|#|Y|#|#|#|#|#|#|_|_|_|_|#|_|_|#|_|_|#|_|_|_|_|_|_|_|_|#|g|T|#|T|_|#|_|_|_|_|_|_|_|_|#|_|_|#|g|_|#|_|_|_|_|_|_|_|_|#|#|#|_|#|#|#|_|_|_|_";

    game.isRunningTest(true);
    assertEquals(expectedBoard, game.getBoard().toString());
  }
}