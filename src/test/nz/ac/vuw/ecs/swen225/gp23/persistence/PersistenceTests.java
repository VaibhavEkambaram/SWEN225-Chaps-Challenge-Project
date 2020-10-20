package test.nz.ac.vuw.ecs.swen225.gp23.persistence;

import nz.ac.vuw.ecs.swen225.gp23.application.Application;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.*;

import javax.swing.*;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * JUnit tests for Persistence
 *
 * @author Rahul Mahasuriya
 */
public class PersistenceTests {
    private Game game;
    private Application application;



    private static boolean test = false;

    /**
     * Invalid load file
     */

    @Test
    public void invalidLoadfile(){

    }

    /**
     * First time game starts up.
     */
    @Test
    public void firstStartUp() {
        GraphicalInterface gui = new GraphicalInterface(application);
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        assert (true);
    }


}