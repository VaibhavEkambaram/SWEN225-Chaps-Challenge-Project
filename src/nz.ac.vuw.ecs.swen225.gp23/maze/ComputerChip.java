package nz.ac.vuw.ecs.swen225.gp23.maze;

/**
 * This class represents the computer chips or treasure that the player collects in the game.
 *
 * @author Baxter Kirikiri
 */
public class ComputerChip extends Tile {
    private boolean pickedUp = false;

    /**
     * Constructor for ComputerChip.
     * Completes necessary setup for a computer chip tile.
     *
     */
    public ComputerChip(){
        super(Tiles.ComputerChip);
        this.isPassable = true;
        this.currentImage = "computer_chip.png";
        this.defaultImage = "computer_chip.png";
    }

    /**
     * Validates whether the player can travel through this tile.
     * Applies appropriate logic to ensure the player has the computer chip
     * and updates the tile so that the computer chip is not shown.
     *
     * @param p - the player (Player)
     * @return isPassable - should always be true for computer chips so that the player can pick them up (Boolean)
     */
    @Override
    public boolean action(Player p) {
        if(!pickedUp){
            p.pickUpChip();
            this.defaultImage = "floor.png";
            pickedUp = true;
        }
        return isPassable;
    }
}
