package nz.ac.vuw.ecs.swen225.gp23.maze;

/**
 * This class represents the exit lock tile in the game.
 * This tile is inaccessible until the player has collected a set number of computer chips
 *
 * @author Baxter Kirikiri - 300472553
 */
public class ExitLock extends Tile {
  private int chipsNeeded;

  /**
   * Constructor for ExitLock.
   * Completes necessary setup for an exit lock tile.
   */
  public ExitLock() {
    super(Tiles.ExitLock);
    this.isPassable = false;
    this.currentImage = "exit_lock.png";
    this.defaultImage = "exit_lock.png";
  }

  /**
   * Validates whether the player can travel through this tile.
   * Applies appropriate logic to check if the player has the correct number of computer chips.
   * If the player has the correct number of chips, the tile is updated so that the exit lock is not shown.
   *
   * @param p - the player (Player)
   * @return isPassable - true if the player has the correct number of chips (Boolean)
   */
  @Override
  public boolean action(Player p) {
    if (chipsNeeded == p.getChips()) {
      this.defaultImage = "floor.png";
      isPassable = true;
    }
    return isPassable;
  }

  /**
   * Sets the number of chips required to open the exit lock.
   *
   * @param numberOfChips - the number of chips required (int)
   */
  public void setChipsNeeded(int numberOfChips) {
    chipsNeeded = numberOfChips;
  }

  /**
   * Gets the number of chips required to open the exit lock.
   *
   * @return chipsNeeded - the number of chips required (int)
   */
  public int getChipsNeeded() {
    return chipsNeeded;
  }
}
