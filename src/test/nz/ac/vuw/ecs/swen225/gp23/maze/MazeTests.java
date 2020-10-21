package test.nz.ac.vuw.ecs.swen225.gp23.maze;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import nz.ac.vuw.ecs.swen225.gp23.maze.Player;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp23.render.ChipAudioModule;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the Maze Module.
 *
 * @author Baxter Kirikiri
 */
public class MazeTests {
    private Application app = new Application();
    private GraphicalInterface gui = new GraphicalInterface(app);
    private Game game;
    private Tile currentTile;

    @Before
    public void setup(){
        gui.updateDisplay();
        gui.onNewGame();
        game = gui.getCurrentGame();
        currentTile = game.getBoard().getPlayerLoc();
    }

    /**
     * Loads the default level 1 and moves the player 1 tile down on to a passable tile.
     * Testing for correct direction image and correct location based on input.
     */
    @Test
    public void movePlayerPassable(){
        game.onMovement(Tile.Directions.Down);
        Tile boardLocation = game.getBoard().getPlayerLoc();

        assertEquals("chip_down", boardLocation.toString());
        assertEquals(game.getBoard().getTile(7, 7), boardLocation);
        assertEquals(game.getBoard().getTile(7, 7), game.getPlayer().getCurrentTile());
    }

    /**
     * Loads the default level 1 and attempts to move the player on to a wall tile.
     * Testing for correct direction image and correct location based on input.
     */
    @Test
    public void movePlayerNonPassable(){
        game.onMovement(Tile.Directions.Left);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Up);

        Tile boardLocation = game.getBoard().getPlayerLoc();

        assertEquals("chip_up", boardLocation.toString());
        assertEquals(game.getBoard().getTile(6, 4), boardLocation);
        assertEquals(game.getBoard().getTile(6, 4), game.getPlayer().getCurrentTile());
    }

    /**
     * Loads the default level 1 and attempts to move the player on to a ComputerChip tile.
     * Testing for correct chip collection and entity removal.
     */
    @Test
    public void playerChipPickup(){
        game.onMovement(Tile.Directions.Down);
        game.onMovement(Tile.Directions.Down);
        game.onMovement(Tile.Directions.Up);

        assertEquals(1, game.getPlayer().getChips());
        assertEquals("floor", game.getBoard().getTile(7, 8).toString());
    }

    /**
     * Loads the default level 1 and attempts to move the player over a red key.
     * Testing for correct key pickup and key removal.
     */
    @Test
    public void playerKeyPickup() {
        game.onMovement(Tile.Directions.Right);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Right);
        game.onMovement(Tile.Directions.Up);

        assertTrue( game.getPlayer().checkItem("key_red"));
        assertEquals("floor", game.getBoard().getTile(9, 5).toString());
    }

    /**
     * Loads the default level 1 and attempts to move the player over a red key and then over a red door.
     * Testing for correct key pickup, player location, door removal, key removal, and key inventory removal.
     */
    @Test
    public void playerUnlockDoor(){
        game.onMovement(Tile.Directions.Right);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Right);
        game.onMovement(Tile.Directions.Up);

        assertTrue(game.getPlayer().checkItem("key_red"));

        game.onMovement(Tile.Directions.Right);
        game.onMovement(Tile.Directions.Right);

        Tile boardLocation = game.getBoard().getPlayerLoc();

        assertEquals(game.getBoard().getTile(11, 4), boardLocation);
        assertEquals(game.getBoard().getTile(11, 4), game.getPlayer().getCurrentTile());

        assertEquals("floor", game.getBoard().getTile(10, 4).toString());
        assertFalse(game.getPlayer().checkItem("key_red"));
        assertEquals("floor", game.getBoard().getTile(9, 5).toString());
    }

    /**
     * Loads the default level 1, picks up only one chip, and attempts to move through the exit lock.
     * Testing for correct action validation, player position, and exit lock visibility.
     */
    @Test
    public void playerExitLocked(){
        game.onMovement(Tile.Directions.Down);
        game.onMovement(Tile.Directions.Down);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Left);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Right);
        game.onMovement(Tile.Directions.Up);

        Tile boardLocation = game.getBoard().getPlayerLoc();

        assertEquals(1, game.getPlayer().getChips());
        assertEquals("chip_up", boardLocation.toString());
        assertEquals("exit_lock", game.getBoard().getTile(7, 3).toString());
    }

    /**
     * Loads the default level 1, sets the players chips to the required amount for the level 1 exit lock and
     * attempts to open the exit lock.
     * Testing for correct action validation, player position, and exit lock visibility.
     */
    @Test
    public void playerExitUnlocked(){
        game.onMovement(Tile.Directions.Down);
        game.onMovement(Tile.Directions.Down);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Left);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Right);
        game.getPlayer().setChips(11);
        game.onMovement(Tile.Directions.Up);

        Tile boardLocation = game.getBoard().getPlayerLoc();

        assertEquals(11, game.getPlayer().getChips());
        assertEquals("chip_up", boardLocation.toString());
    }

}
