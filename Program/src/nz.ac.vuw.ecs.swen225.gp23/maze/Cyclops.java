package nz.ac.vuw.ecs.swen225.gp23.maze;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents the Cyclops or enemies in the game.
 * Current intention is for the cyclops to 'bounce' between walls.
 * Meaning it will travel in one direction until it hits a wall, and then travel in the opposite direction.
 *
 * @author Baxter Kirikiri
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
     * If the cyclops cant travel in its direction. Reverse it's direciton.
     */
    public void moveCyclops(){
        Tile nextTile = currentTile.getDirection(direction);
        Player validator = new Player(currentTile);
        if(nextTile.action(validator)) {
            if(!nextTile.toString().startsWith("chip")) {
                nextTile.setEntityPresent(this.directionImages.get(direction));
                currentTile.setEntityAbsent();
                currentTile = nextTile;
            }
        } else {
            direction = direction.reverse();
            moveCyclops();
        }
    }

}
