package nz.ac.vuw.ecs.swen225.gp23.maze;

import nz.ac.vuw.ecs.swen225.gp23.render.RenderPanel;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

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
     *
     */
    public void moveCyclops(){
        Tile nextTile = currentTile.getDirection(direction);
        Player validator = new Player(currentTile);
        if(nextTile.action(validator)) {
            nextTile.setEntityPresent(this.directionImages.get(direction));
            currentTile.setEntityAbsent();
            currentTile = nextTile;
        } else {
            direction = direction.reverse();
            moveCyclops();
        }
    }

    public void startMovement(Board b, RenderPanel br) {
        try {
            new Timer().schedule(new CyclopsMoveTask(this, b, br), 0, 500);
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e){
            System.out.println("Timing error");
        }
    }
}
