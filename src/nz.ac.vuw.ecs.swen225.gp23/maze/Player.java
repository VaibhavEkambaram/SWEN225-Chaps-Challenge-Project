package nz.ac.vuw.ecs.swen225.gp23.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents the player in the game.
 * It is responsible for storing the players inventory, chip count, and directional images.
 *
 * @author Baxter Kirikiri
 */
public class Player {
    private Tile currentTile;
    private List<String> inventory = new ArrayList<>();
    private Map<Tile.Directions, String> directionImages = new HashMap<>();
    private int chips = 0; //how many chips the player has picked up

    /**
     * Constructor for the Player class.
     *
     * @param location - The tile to draw the player on (Tile)
     */
    public Player(Tile location){
        currentTile = location;
        directionImages.put(Tile.Directions.Left, "chip_left.png");
        directionImages.put(Tile.Directions.Right, "chip_right.png");
        directionImages.put(Tile.Directions.Up, "chip_up.png");
        directionImages.put(Tile.Directions.Down, "chip_down.png");
    }

    /**
     * Check if the players inventory has an item (used for keys).
     * e.g. check if the player has the blue key for the blue door.
     *
     * @param item - The toString of the item (String)
     * @return - True if the player has the item (Boolean)
     */
    public boolean checkItem(String item){return inventory.contains(item);}

    /**
     * Adds an item to the players inventory (used for keys).
     * e.g. player picks up a blue key and calls addItem("blue_key") to add it to the inventory.
     *
     * @param item - The toString of the item (String)
     */
    public void addItem(String item){inventory.add(item);}

    /**
     * Removes an item from the players inventory (used for keys).
     * e.g. player opens a door and calls removeItem("blue_key") to remove it from the inventory.
     *
     * @param item - The toString of the item (String)
     */
    public void removeItem(String item){inventory.remove(item);}

    /**
     * Increases the players chip count by one.
     * Used for when the player collects a chip.
     */
    public void pickUpChip(){chips++;}

    /**
     * Getter for the chips field.
     * @return chips - The number of chips the player has collected (int)
     */
    public int getChips(){return chips;}

    /**
     * Setter for the chips field (for testing purposes).
     * @param newChips - the new value of chips
     */
    public void setChips(int newChips){chips = newChips;}

    /**
     * Getter for the inventory.
     * @return inventory - the players inventory (List<String>).
     */
    public List<String> getInventory(){return inventory;}

    /**
     * Setter for the inventory.
     * @param newInventory - the new inventory to replace the players current inventory (list<String>)
     */
    public void setInventory(List<String> newInventory){inventory = newInventory;}

    /**
     * Getter for the players current location.
     * @return currentTile - the tile the player is currently on (Tile)
     */
    public Tile getCurrentTile(){return currentTile;}

    /**
     * Setter for the current tile (used when moving the player).
     * @param newTile - the new location tile for the player (Tile)
     */
    public void setCurrentTile(Tile newTile){currentTile = newTile;}

    /**
     * Gets the appropriate image for a given direction (used when moving the player).
     *
     * @param d - direction of the image so that the player is facing the movement direction (Tile.Directions)
     * @return String - the string of the correct direction image
     */
    public String getImage(Tile.Directions d){return directionImages.get(d);}
}
