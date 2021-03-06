package nz.ac.vuw.ecs.swen225.gp23.maze;

/**
 * This class represents the exit tile, used to progress to the next level or complete the game.
 *
 * @author Baxter Kirikiri - 300472553
 */
public class Exit extends Tile {
  /**
   * Constructor for Exit.
   * Completes necessary setup for an exit tile.
   */
  public Exit() {
    super(Tiles.Exit);
    this.isPassable = true;
    this.currentImage = "exit.png";
    this.defaultImage = "exit.png";
  }

  /**
   * Validates whether the player can travel through this tile.
   *
   * @param p - the player (Player)
   * @return isPassable - should always be true for an exit tile (Boolean)
   */
  @Override
  public boolean action(Player p) {
    return isPassable;
  }
}
