package nz.ac.vuw.ecs.swen225.gp23.maze;

/**
 * This class represents the keys the player collects to open the locked doors in the game.
 * They are differentiated by colour so that they can be used with doors of a matching colour.
 * This colour is in string format (ie. "red").
 * Actual colour values are handled in nz.ac.vuw.ecs.swen225.gp23/render
 *
 * @author Baxter Kirikiri - 300472553
 */
public class Key extends Tile {
  private boolean pickedUp = false;

  /**
   * Constructor for Key.
   * Completes necessary setup for a key tile.
   *
   * @param colour - The colour of the key (String)
   */
  public Key(String colour) {
    super(Tiles.Key);
    this.isPassable = true;
    this.currentImage = "key_" + colour + ".png";
    this.defaultImage = "key_" + colour + ".png";
  }

  /**
   * Validates whether the player can travel through this tile.
   * Applies appropriate logic to ensure the player has the key
   * and updates the tile so that the key is not shown.
   *
   * @param p - the player (Player)
   * @return isPassable - should always be true for keys (Boolean)
   */
  @Override
  public boolean action(Player p) {
    if (!pickedUp) {
      p.addItem(this.toString());
      this.defaultImage = "floor.png";
      pickedUp = true;
    }
    return isPassable;
  }

}
