package test.nz.ac.vuw.ecs.swen225.gp23.monkey;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Sushil Sharma
 */
public class MazeTests {
    private Application app = new Application();
    private GraphicalInterface gui = new GraphicalInterface(app);
    private List<String> levels = new ArrayList<>();

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

}
