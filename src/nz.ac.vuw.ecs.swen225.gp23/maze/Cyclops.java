package nz.ac.vuw.ecs.swen225.gp23.maze;

import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the Cyclops or enemies in the game.
 * Handles cyclops movement and stores necessary information for each cyclops.
 *
 * @author Baxter Kirikiri - 300472553
 */
public class Cyclops {
    public Tile currentTile;
    public Tile.Directions direction;
    public Map<Tile.Directions, String> directionImages = new HashMap<>();

    /**
     * Constructor for the Cyclops class.
     *
     * @param location - Starting location for the Cyclops
     * @param direction - Direction that the cyclops will travel
     */
    public Cyclops(Tile location, Tile.Directions direction){
        this.currentTile = location;
        this.direction = direction;
        directionImages.put(Tile.Directions.Left, "cyclops_left.png");
        directionImages.put(Tile.Directions.Right, "cyclops_right.png");
        directionImages.put(Tile.Directions.Up, "cyclops_up.png");
        directionImages.put(Tile.Directions.Down, "cyclops_down.png");
    }

    /**
     * Moves the cyclops 1 tile in its current direction
     * If the cyclops can't travel in it's direction, it's direction is reversed.
     * This means the cyclops will 'bounce' between non passable objects along whichever axis the direction from the constructor is.
     * If the tile where the cyclops is trying to move has the player, end the game.
     *
     * @param gui - graphical interface passed from game. Used to end the game (GraphicalInterface)
     */
    public void moveCyclops(GraphicalInterface gui){
        Tile nextTile = currentTile.getDirection(direction);
        Player validator = new Player(currentTile);
        if(nextTile.action(validator)) {
            if(!nextTile.toString().startsWith("chip")) {
                nextTile.setEntityPresent(this.directionImages.get(direction));
                currentTile.setEntityAbsent();
                currentTile = nextTile;
            } else {
                gui.onStopGame(false);
                gui.outOfTime("You died","Oh no! You were caught by a cyclops.");
            }
        } else {
            direction = direction.reverse();
            moveCyclops(gui);
        }
    }

}
