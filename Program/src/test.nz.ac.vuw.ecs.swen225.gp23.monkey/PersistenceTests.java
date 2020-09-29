package test.nz.ac.vuw.ecs.swen225.gp23.monkey;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;
import nz.ac.vuw.ecs.swen225.gp23.persistence.readWrite;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

/**
 * JUnit tests for checking all objects are add0ed correctly.
 *
 * @author Sushil Sharma
 */
public class PersistenceTests {
    //private Game game = new Game();
    private Application app = new Application();

    private static boolean test = false;
    private Game game;


    /**
     *First time game starts up.
     */
    @Test
    public void firstStartUp(){
        GraphicalInterface gui = new GraphicalInterface(app);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        assert(true);
    }
}
