package test.nz.ac.vuw.ecs.swen225.gp23.monkey;
import nz.ac.vuw.ecs.swen225.gp23.maze.Key;
import nz.ac.vuw.ecs.swen225.gp23.maze.LockedDoor;
import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * @author Sushil Sharma
 */
public class MazeTests {

    /**
     * Checking if the key is blue.
     */
    @Test
    public void blueKey(){
        Key key = new Key("Blue");
        String keyCol = key.toString();
        assertEquals(keyCol, "key_Blue");
    }

    /**
     * Checking if the key is red.
     */
    @Test
    public void redKey(){
        Key key = new Key("Red");
        String keyCol = key.toString();
        assertEquals(keyCol, "key_Red");
    }

    /**
     * Checking if the key is green.
     */
    @Test
    public void greenKey(){
        Key key = new Key("Green");
        String keyCol = key.toString();
        assertEquals(keyCol, "key_Green");
    }

    /**
     * Checking if the key is yellow.
     */
    @Test
    public void yellowKey(){
        Key key = new Key("Yellow");
        String keyCol = key.toString();
        assertEquals(keyCol, "key_Yellow");
    }

    /**
     * Checking if the locked door is blue.
     */
    @Test
    public void lockedBlueDoor(){
        LockedDoor ld = new LockedDoor("Blue");
        String doorCol = ld.toString();
        assertEquals(doorCol, "door_key_Blue");
    }

    /**
     * Checking if the locked door is red.
     */
    @Test
    public void lockedRedDoor(){
        LockedDoor ld = new LockedDoor("Red");
        String doorCol = ld.toString();
        assertEquals(doorCol, "door_key_Red");
    }

    /**
     * Checking if the locked door is green.
     */
    @Test
    public void lockedGreenDoor(){
        LockedDoor ld = new LockedDoor("Green");
        String doorCol = ld.toString();
        assertEquals(doorCol, "door_key_Green");
    }

    /**
     * Checking if the locked door is yellow.
     */
    @Test
    public void lockedYellowDoor(){
        LockedDoor ld = new LockedDoor("Yellow");
        String doorCol = ld.toString();
        assertEquals(doorCol, "door_key_Yellow");
    }
}
