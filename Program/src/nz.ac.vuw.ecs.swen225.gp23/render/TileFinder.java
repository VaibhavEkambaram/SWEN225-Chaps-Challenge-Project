package nz.ac.vuw.ecs.swen225.gp23.render;

import javax.swing.*;
import java.net.URL;

/**
 * Chip ImageIcon Directory.
 * Contains references to all Images necessary for the game.
 *
 * @author Cameron Li
 */
public class TileFinder {

    // Basic Tiles
    private final ImageIcon empty = makeImageIcon("/tiles/empty.png");
    private final ImageIcon floor = makeImageIcon("/tiles/floor.png");
    private final ImageIcon wall = makeImageIcon("/tiles/wall.png");
    private final ImageIcon exit = makeImageIcon("/tiles/exit.png");
    private final ImageIcon exit_lock = makeImageIcon("/tiles/exit_lock.png");
    private final ImageIcon computer_chip = makeImageIcon("/items/computer_chip.png");
    private final ImageIcon hint = makeImageIcon("/tiles/hint.png");

    // Chip
    private final ImageIcon chip_up = makeImageIcon("/chip/chip_up.png");
    private final ImageIcon chip_down = makeImageIcon("/chip/chip_down.png");
    private final ImageIcon chip_left = makeImageIcon("/chip/chip_left.png");
    private final ImageIcon chip_right= makeImageIcon("/chip/chip_right.png");

    // Keys
    private final ImageIcon key_green = makeImageIcon("/items/keys/key_green.png");
    private final ImageIcon key_blue = makeImageIcon("/items/keys/key_blue.png");
    private final ImageIcon key_yellow = makeImageIcon("/items/keys/key_yellow.png");
    private final ImageIcon key_red = makeImageIcon("/items/keys/key_red.png");

    // Doors
    private final ImageIcon door_red = makeImageIcon("/tiles/doors/door_key_red.png");
    private final ImageIcon door_blue = makeImageIcon("/tiles/doors/door_key_blue.png");
    private final ImageIcon door_yellow = makeImageIcon("/tiles/doors/door_key_yellow.png");
    private final ImageIcon door_green = makeImageIcon("/tiles/doors/door_key_green.png");

    /**
     * Get an Image from a string.
     *
     * @param tileName
     * Name of the requested tile.
     * @return
     * Requested tile as an ImageIcon.
     *
     * @author Cameron Li
     */
    public ImageIcon getTile(String tileName) {
        if (tileName == null) {
            return empty;
        }
        if (tileName.startsWith("chip")) {
            return getChip(tileName);
        } else if (tileName.startsWith("key")) {
            return getKey(tileName);
        }else if (tileName.startsWith("door")) {
            return getDoor(tileName);
        }

        switch (tileName) {
            // Basic Tiles
            case "hint":
                return hint;
            case "computer_chip":
                return computer_chip;
            case "empty":
                return empty;
            case "floor":
                return floor;
            case "wall":
                return wall;
            case "exit":
                return exit;
            case "exit_lock":
                return exit_lock;
            default:
                throw new Error("TileFinder getTile() - No such tile as: " + tileName);
        }
    }

    /**
     * Find the resource from a given URL
     * If it doesn't exist, return the empty tile
     * @param filename
     * URL of the file
     * @return
     * Requested Tile as an ImageIcon
     *
     * @author Cameron Li
     */
    private ImageIcon makeImageIcon(String filename) {
        URL imageURL = this.getClass().getResource(filename);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        } else {
            return empty;
        }
    }

    /**
     * Given a String that starts with chip.
     * Search for the specific chip tile.
     *
     * @param tileName
     * Name of chip tile
     * @return
     * Requested Chip Tile as an ImageIcon.
     *
     * @author Cameron Li
     */
    private ImageIcon getChip(String tileName) {
        switch(tileName) {
            case "chip_up":
                return chip_up;
            case "chip_down":
                return chip_down;
            case "chip_left":
                return chip_left;
            case "chip_right":
                return chip_right;
            default:
                throw new Error("TileFinder getChip() - No such chip as: " + tileName);
        }
    }

    private ImageIcon getDoor(String tileName){
        switch(tileName) {
            case "door_key_green":
                return door_green;
            case "door_key_yellow":
                return door_yellow;
            case "door_key_blue":
                return door_blue;
            case "door_key_red":
                return door_red;
            default:
                throw new Error("TileFinder getKey() - No such key as: " + tileName);
        }
    }

    /**
     * Given a String that starts with key.
     * Search for the specific key tile.
     *
     * @param tileName
     * Name of key tile
     * @return
     * Requested key Tile as an ImageIcon.
     *
     * @author Cameron Li
     */
    private ImageIcon getKey(String tileName) {
        switch(tileName) {
            case "key_green":
                return key_green;
            case "key_yellow":
                return key_yellow;
            case "key_blue":
                return key_blue;
            case "key_red":
                return key_red;
            default:
                throw new Error("TileFinder getKey() - No such key as: " + tileName);
        }
    }
}
