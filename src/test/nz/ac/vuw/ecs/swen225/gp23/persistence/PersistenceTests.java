package test.nz.ac.vuw.ecs.swen225.gp23.persistence;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.persistence.LevelM;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for Persistence
 *
 * @author Rahul Mahasuriya
 */
public class PersistenceTests {
    private Application app = new Application();
    private GraphicalInterface gui = new GraphicalInterface(app);
    private Board board;



    private static boolean test = false;

    /**
     * trying to make a test that checks for next level number
     */

    @Test
    public void validLevelTransition(){
        gui.updateDisplay();
        gui.onNewGame();
        Game game = gui.getCurrentGame();
        LevelM lm = gui.levelManager;

        assertEquals(1, game.getLevelNumber());
        gui.levelCompleteMessage(1,0,0,11);
        lm.incrementLevel();
        lm.setLevel(2);
        assertEquals(2, game.getLevelNumber());


    }

    /**
     * Possible test that checks correct json input file
     */

    @Test
    public void validJson(){

    }

    /**
     * Possible test that checks for invalid readBoard
     */

    @Test
    public void invalidReadBoard(){

    }

    /**
     *
     */
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