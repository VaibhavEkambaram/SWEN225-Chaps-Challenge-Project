package nz.ac.vuw.ecs.swen225.gp23.maze;

/**
 * This class represents a wall tile in the game.
 *
 * @author Baxter Kirikiri - 300472553
 */
public class Wall extends Tile {

    /**
     * Constructor for Wall.
     * Completes necessary setup for a wall tile.
     *
     */
    public Wall(){
        super(Tiles.Wall);
        this.isPassable = false;
        this.currentImage = "wall.png";
        this.defaultImage = "wall.png";
    }

    /**
     * Validates whether the player can travel through this tile.
     *
     * @param p - the player (Player)
     * @return isPassable - should always be false for a wall because the player is not intended travel over them (Boolean)
     */
    @Override
    public boolean action(Player p) {
        return isPassable;
    }
}
