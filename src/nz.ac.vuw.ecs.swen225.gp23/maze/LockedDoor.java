package nz.ac.vuw.ecs.swen225.gp23.maze;

/**
 * This class represents the locked door tiles in the game.
 * They are differentiated by colour so that they can be opened with keys of a matching colour.
 * This colour is in string format (ie. "red").
 * Actual colour values are handled in nz.ac.vuw.ecs.swen225.gp23/render
 *
 * @author Baxter Kirikiri - 300472553
 */
public class LockedDoor extends Tile {
  private String colour;
  private boolean locked = true;

  /**
   * Constructor for LockedDoor.
   * Completes necessary setup for a locked door tile.
   *
   * @param colour - The colour of the door (String)
   */
  public LockedDoor(String colour) {
    super(Tiles.LockedDoor);
    this.isPassable = false;
    this.colour = colour;
    this.currentImage = "door_" + colour + ".png";
    this.defaultImage = "door_" + colour + ".png";
  }

  /**
   * Validates whether the player can travel through this tile.
   * Applies appropriate logic to check if the player has the correct coloured key.
   * If the player has the correct coloured key,
   * the tile is updated so that the locked door is not shown.
   * Once this key has been used, it is removed from the players inventory.
   *
   * @param p - the player (Player)
   * @return isPassable - true if the player has the correct coloured key (Boolean)
   */
  @Override
  public boolean action(Player p) {
    if (!locked) {
      return true;
    }
    if (p.checkItem("key_" + colour)) {
      isPassable = true;
      this.defaultImage = "floor.png";
      p.removeItem("key_" + colour);
      locked = false;
    }
    return isPassable;
  }
}
