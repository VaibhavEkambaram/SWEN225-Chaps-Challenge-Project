package nz.ac.vuw.ecs.swen225.gp23.maze;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;
import nz.ac.vuw.ecs.swen225.gp23.render.ChipAudioModule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class MazeTests {
    private Application app = new Application();
    private GraphicalInterface gui = new GraphicalInterface(app);
    private ChipAudioModule audio = new ChipAudioModule();
    private List<String> levels = new ArrayList<>();
    private Tile currentTile;

    /**
     * Loads the default level 1 and moves the player 1 tile down on to a passable tile.
     * Testing for correct direction image and correct location based on input.
     */
    @Test
    public void movePlayerPassable(){
        gui.updateDisplay();
        gui.onNewGame();

        Game game = gui.getCurrentGame();
        game.getBoard().getTile(7, 6);
        currentTile = game.getBoard().getPlayerLoc();

        game.onMovement(Tile.Directions.Down);
        Tile boardLocation = game.getBoard().getPlayerLoc();

        assertEquals("chip_down", boardLocation.toString());
        assertEquals(game.getBoard().getTile(7, 7), boardLocation);
    }

    /**
     * Loads the default level 1 and attempts to move the player on to a wall tile.
     * Testing for correct direction image and correct location based on input.
     */
    @Test
    public void movePlayerNonPassable(){
        gui.updateDisplay();
        gui.onNewGame();

        Game game = gui.getCurrentGame();

        game.getBoard().getTile(7, 6);
        currentTile = game.getBoard().getPlayerLoc();

        game.onMovement(Tile.Directions.Left);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Up);
        game.onMovement(Tile.Directions.Up);

        Tile boardLocation = game.getBoard().getPlayerLoc();

        assertEquals("chip_up", boardLocation.toString());
        assertEquals(game.getBoard().getTile(6, 4), boardLocation);
    }


}
