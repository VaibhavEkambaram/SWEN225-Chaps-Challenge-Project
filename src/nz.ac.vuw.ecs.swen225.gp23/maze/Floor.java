package nz.ac.vuw.ecs.swen225.gp23.maze;

/**
 * This class represents the floor tile in the game.
 *
 * @author Baxter Kirikiri - 300472553
 */
public class Floor extends Tile {

  /**
   * Constructor for Floor.
   * Completes necessary setup for a floor tile.
   */
  public Floor() {
    super(Tiles.Floor);
    this.isPassable = true;
    this.currentImage = "floor.png";
    this.defaultImage = "floor.png";
  }

  /**
   * Validates whether the player can travel through this tile.
   *
   * @param p - the player (Player)
   * @return isPassable - should always be true for a floor tile so that the player can walk on it (Boolean)
   */
  @Override
  public boolean action(Player p) {
    return isPassable;
  }
}
