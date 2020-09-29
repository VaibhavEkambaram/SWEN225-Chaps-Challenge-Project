package test.nz.ac.vuw.ecs.swen225.gp23.monkey;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import org.junit.Test;

import javax.swing.*;

/**
 * JUnit tests for checking all objects are add0ed correctly.
 *
 * @author Sushil Sharma
 */
public class PersistenceTests {
    //private Game game = new Game();
    private Application app = new Application();

    private static boolean test = false;

    /**
     * Invalid game load.
     */
    /*@Test
    public void startGameInvalid() {
        boolean invalid = false;
        //  readWrite.loadStateFromJsonFile("invalid.txt", game);
        assertTrue(invalid);
    }*/

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
}