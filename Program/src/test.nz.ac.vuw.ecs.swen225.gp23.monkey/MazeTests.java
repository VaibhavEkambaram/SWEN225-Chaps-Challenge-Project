package test.nz.ac.vuw.ecs.swen225.gp23.monkey;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import nz.ac.vuw.ecs.swen225.gp23.maze.Key;
import nz.ac.vuw.ecs.swen225.gp23.maze.LockedDoor;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Sushil Sharma
 */
public class MazeTests {
    private Application app = new Application();

    /**
     * Checking if the key is blue.
     */
    @Test
    public void blueKey(){
        Key key = new Key("Blue", "grass");
        String keyCol = key.toString();
        assertEquals(keyCol, "key_Blue");
    }

    /**
     * Checking if the key is red.
     */
    @Test
    public void redKey(){
        Key key = new Key("Red", "grass");
        String keyCol = key.toString();
        assertEquals(keyCol, "key_Red");
    }

    /**
     * Checking if the key is green.
     */
    @Test
    public void greenKey(){
        Key key = new Key("Green", "grass");
        String keyCol = key.toString();
        assertEquals(keyCol, "key_Green");
    }

    /**
     * Checking if the key is yellow.
     */
    @Test
    public void yellowKey(){
        Key key = new Key("Yellow", "grass");
        String keyCol = key.toString();
        assertEquals(keyCol, "key_Yellow");
    }


    /**
     * Check game init after loading level 1
     */
    @Test
    public void checkGameInit(){
        GraphicalInterface gui = new GraphicalInterface(app);
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();

        assertNotNull(game.getBoard());
        assertNotNull(game.getPlayer());
    }

    /**
     * Testing level one board to see if it is correct.
     */
    @Test
    public void checkLevelOneBoard() {
        String text = "floor|floor|wall|wall|wall|wall|wall|floor|wall|wall|wall|wall|wall|floor|floor|" + "\n" +
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
                "floor|floor|floor|floor|wall|wall|wall|floor|wall|wall|wall|floor|floor|floor|floor|";

        GraphicalInterface gui = new GraphicalInterface(app);
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();
        String s = game.printOutBoard();

        if (s.equals(text)) {
            assertEquals(text, s);
        }
    }

    /**
     * Creatign player to see if the player actual is displayed correctly.
     */
    @Test
    public void levelOneGeneratedPlayer() {

        GraphicalInterface gui = new GraphicalInterface(app);
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();

        assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
        assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
        assertEquals("chip_down.png", game.getBoard().getTile(7, 6).getCurrentImage());

    }

    @Test
    public void checkMovementOne() {

        GraphicalInterface gui = new GraphicalInterface(app);
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();

        assertEquals(7, game.getPlayer().getCurrentTile().getXLoc());
        assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
        game.onMovement(Tile.Directions.Left);
        assertEquals(6, game.getPlayer().getCurrentTile().getXLoc());
        assertEquals(6, game.getPlayer().getCurrentTile().getYLoc());
    }
}
