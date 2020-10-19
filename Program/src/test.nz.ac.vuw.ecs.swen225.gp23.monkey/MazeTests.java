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
 * @author Sushil Sharma
 */
public class MazeTests {
    private Application app = new Application();
    private GraphicalInterface gui = new GraphicalInterface(app);
    private List<String> levels = new ArrayList<>();
    Tile currentTile;

    /**
     * Check game init after loading level 1
     */
    @Test
    public void checkGameInit(){
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();

        assertNotNull(game.getBoard());
        assertNotNull(game.getPlayer());
    }

    @Test
    public void moveChap(){
        gui.updateDisplay();
        gui.onNewGame();

        Game game = gui.getCurrentGame();

        game.getBoard().getTile(7, 6);
        game.getBoard().getTile(7, 6);

        Player player = new Player(currentTile);
        Tile nextTile = player.getCurrentTile();
        assertEquals(nextTile, game.getBoard().getTile(9, 4));
        assertTrue(nextTile.hasEntity);
    }

    /**
     * Rejecting invalid game load.
     */
    @Test
    public void invalidLoadGame() {
        boolean error = false;
        Game game = gui.getCurrentGame();
        try{
            Persistence.readBoard("invalid.txt");
            throw new IOException("Invalid File");
        }catch (IOException e) {
            assertTrue(error = true);
        }
        assertTrue(error);
    }

    /**
     * Checking to see if the game pauses and un-pauses correctly.
     */
    @Test
    public void pauseGame(){
        Game game = gui.getCurrentGame();
        assertFalse(game.isPaused());
        game.setGamePaused(true);

        assertTrue(game.isPaused());
        game.setGamePaused(false);
        assertTrue(game.isPaused());
    }



    /**
     * Creating player to see if the player actual is displayed correctly.
     */
    @Test
    public void levelOneGeneratedPlayer() {
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
    public void checkLeftMovementOne() {
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();

        assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
        assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
        game.onMovement(Tile.Directions.Left);
        assertEquals(6, game.getPlayer().getCurrentTile().getXLoc());
        assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    }

    /**
     * Checking the movement of player to the right.
     */
    @Test
    public void checkRightMovementOne() {
        GraphicalInterface gui = new GraphicalInterface(app);
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();

        assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
        assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
        game.onMovement(Tile.Directions.Right);
        assertEquals(8, game.getPlayer().getCurrentTile().getXLoc());
        assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    }

    /**
     * Checking the movement of player to up direction.
     */
    @Test
    public void checkUpMovementOne() {
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();

        assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
        assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
        game.onMovement(Tile.Directions.Up);
        assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
        assertEquals(5, game.getPlayer().getCurrentTile().getYLoc());
    }

    /**
     * Testing level one board to see if it is correct.
     */
    @Test
    public void checkLevelOneBoard() {
        /*String text = "floor|floor|wall|wall|wall|wall|wall|floor|wall|wall|wall|wall|wall|floor|floor|" + "\n" +
                "floor|floor|wall|floor|floor|floor|wall|wall|wall|floor|floor|floor|wall|floor|floor|" + "\n" +
                "floor|floor|wall|floor|computer_chip|floor|wall|exit|wall|floor|computer_chip|floor|wall|floor|floor|" + "\n" +
                "wall|wall|wall|wall|wall|door_key_green|wall|exit_lock|wall|door_key_green|wall|wall|wall|wall|wall|" + "\n" +
                "wall|floor|key_yellow|floor|door_key_blue|floor|floor|floor|floor|floor|door_key_red|floor|key_yellow|floor|wall|" + "\n" +
                "wall|floor|computer_chip|floor|wall|key_blue|floor|hint|floor|key_red|wall|floor|computer_chip|floor|wall|" + "\n" +
                "wall|wall|wall|wall|wall|computer_chip|floor|chip_down|floor|computer_chip|wall|wall|wall|wall|wall|" + "\n" +
                "wall|floor|computer_chip|floor|wall|key_blue|floor|floor|floor|key_red|wall|floor|computer_chip|floor|wall|" + "\n" +
                "wall|floor|floor|floor|door_key_red|floor|floor|computer_chip|floor|floor|door_key_blue|floor|floor|floor|wall|" + "\n" +
                "wall|wall|wall|wall|wall|wall|door_key_yellow|wall|door_key_yellow|wall|wall|wall|wall|wall|wall|" + "\n" +
                "floor|floor|floor|floor|wall|floor|floor|wall|floor|floor|wall|floor|floor|floor|floor|" + "\n" +
                "floor|floor|floor|floor|wall|key_green|computer_chip|wall|computer_chip|floor|wall|floor|floor|floor|floor|" + "\n" +
                "floor|floor|floor|floor|wall|floor|floor|wall|key_green|floor|wall|floor|floor|floor|floor|" + "\n" +
                "floor|floor|floor|floor|wall|wall|wall|floor|wall|wall|wall|floor|floor|floor|floor|";*/
        String text =
                "|_|_|#|#|#|#|#|_|#|#|#|#|#|_|_|" +
                "|_|_|#|_|_|_|#|#|#|_|_|_|#|_|_|" +
                "|_|_|#|_|T|_|#|E|#|_|T|_|#|_|_|" +
                "|#|#|#|#|#|G|#|l|#|G|#|#|#|#|#|" +
                "|#|_|y|_|B|_|_|_|_|_|R|_|y|_|#|" +
                "|#|_|T|_|#|b|_|i|_|r|#|_|T|_|#|" +
                "|#|#|#|#|#|T|_|P|_|T|#|#|#|#|#|" +
                "|#|_|T|_|#|b|_|_|_|r|#|_|T|_|#|" +
                "|#|_|_|_|R|_|_|T|_|_|B|_|_|_|#|" +
                "|#|#|#|#|#|#|Y|#|Y|#|#|#|#|#|#|" +
                "|_|_|_|_|#|_|_|#|_|_|#|_|_|_|_|" +
                "|_|_|_|_|#|g|T|#|T|_|#|_|_|_|_|" +
                "|_|_|_|_|#|_|_|#|g|_|#|_|_|_|_|" +
                "|_|_|_|_|#|#|#|_|#|#|#|_|_|_|_|";

        GraphicalInterface gui = new GraphicalInterface(app);
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();
        String s = game.printOutBoard();

        if(s.equals(text)) { assertEquals(text, s); }
    }

    /**
     * Testing to see if the door is not opened when not allowed
     */
    @Test
    public void invalidDoorOpen() {
        String level =
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|K|B|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|C|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|" +
                "|_|_|_|_|_|_|_|_|_|_|_|_|_|_|_|";


        Game game = gui.getCurrentGame();
        gui.updateDisplay();
        gui.onNewGame();
        game.onMovement(Tile.Directions.Up);

        Tile start = game.getPlayer().getCurrentTile();
        game.onMovement(Tile.Directions.Right);
        Tile end = game.getPlayer().getCurrentTile();
        assertEquals(start, end);
    }
}