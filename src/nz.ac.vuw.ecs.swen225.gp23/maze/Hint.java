package nz.ac.vuw.ecs.swen225.gp23.maze;

/**
 * This class represents the hint tile in the game.
 * Used to give the player a hint about the game.
 *
 * @author Baxter Kirikiri - 300472553
 */
public class Hint extends Tile {
  /**
   * Constructor for Hint.
   * Completes necessary setup for a hint tile.
   */
  public Hint() {
    super(Tiles.Hint);
    this.isPassable = true;
    this.currentImage = "hint.png";
    this.defaultImage = "hint.png";
  }

  /**
   * Validates whether the player can travel through this tile.
   *
   * @param p - the player (Player)
   * @return isPassable - should always be true for a hint tile (Boolean)
   */
  @Override
  public boolean action(Player p) {
    return isPassable;
  }
}
