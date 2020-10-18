package nz.ac.vuw.ecs.swen225.gp23.maze;

import java.util.HashMap;
import java.util.Map;

public class Cyclops {
    public Tile currentTile;
    public Tile.Directions direction;
    public Map<Tile.Directions, String> directionImages = new HashMap<>();

    public Cyclops(Tile location, Tile.Directions direction){
        this.currentTile = location;
        this.direction = direction;
        directionImages.put(Tile.Directions.Left, "cyclops_left.png");
        directionImages.put(Tile.Directions.Right, "cyclops_right.png");
        directionImages.put(Tile.Directions.Up, "cyclops_up.png");
        directionImages.put(Tile.Directions.Down, "cyclops_down.png");
    }

    public void moveCyclops(){
        Tile nextTile = currentTile.getDirection(direction);
        Player validator = new Player(currentTile);
        if(nextTile.action(validator)) {
            nextTile.setEntityPresent(this.directionImages.get(direction));
            currentTile = nextTile;
        } else {
            direction = direction.reverse();
            moveCyclops();
        }
    }
}
