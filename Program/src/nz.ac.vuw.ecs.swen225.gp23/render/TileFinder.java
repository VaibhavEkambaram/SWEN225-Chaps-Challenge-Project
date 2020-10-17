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

    private final ImageIcon empty = makeImageIcon("/tiles/empty.png");

    //--------------------
    // Basic Image Icons
    //--------------------

    // Basic Tiles
    private final ImageIcon exit_icon = makeImageIcon("/icons/exit_icon.png");
    private final ImageIcon exit_lock_icon = makeImageIcon("/icons/exit_lock_icon.png");
    private final ImageIcon computer_chip_icon = makeImageIcon("/icons/computer_chip_icon.png");
    private final ImageIcon key_icon = makeImageIcon("/icons/key_icon.png");
    private final ImageIcon door_icon = makeImageIcon("/icons/door_icon.png");
    private final ImageIcon chip_icon = makeImageIcon("/icons/chip_icon.png");
    private final ImageIcon cyclops_icon = makeImageIcon("/icons/cyclops_icon.png");

    // Keys
    private final ImageIcon key_blue = makeImageIcon("/icons/key_blue.png");
    private final ImageIcon key_red = makeImageIcon("/icons/key_red.png");
    private final ImageIcon key_yellow = makeImageIcon("/icons/key_yellow.png");
    private final ImageIcon key_green = makeImageIcon("/icons/key_green.png");

    //--------------------
    // Grass Image Icons
    //--------------------

    // Basic Tiles
    private final ImageIcon floor_grass = makeImageIcon("/tiles/floor_grass.png");
    private final ImageIcon wall_grass = makeImageIcon("/tiles/wall_grass.png");
    private final ImageIcon exit_grass = makeImageIcon("/tiles/exit_grass.png");
    private final ImageIcon exit_lock_grass = makeImageIcon("/tiles/exit_lock_grass.png");
    private final ImageIcon computer_chip_grass = makeImageIcon("/items/computer_chip_grass.png");
    private final ImageIcon hint_grass = makeImageIcon("/tiles/hint_grass.png");

    // Chip
    private final ImageIcon chip_up_grass = makeImageIcon("/chip/chip_up_grass.png");
    private final ImageIcon chip_down_grass = makeImageIcon("/chip/chip_down_grass.png");
    private final ImageIcon chip_left_grass = makeImageIcon("/chip/chip_left_grass.png");
    private final ImageIcon chip_right_grass = makeImageIcon("/chip/chip_right_grass.png");

    // Cyclops
    private final ImageIcon cyclops_up_grass = makeImageIcon("/enemies/cyclops/cyclops_up_grass.png");
    private final ImageIcon cyclops_down_grass = makeImageIcon("/enemies/cyclops/cyclops_down_grass.png");
    private final ImageIcon cyclops_left_grass = makeImageIcon("/enemies/cyclops/cyclops_left_grass.png");
    private final ImageIcon cyclops_right_grass = makeImageIcon("/enemies/cyclops/cyclops_right_grass.png");

    // Keys
    private final ImageIcon key_green_grass = makeImageIcon("/items/keys/key_green_grass.png");
    private final ImageIcon key_blue_grass = makeImageIcon("/items/keys/key_blue_grass.png");
    private final ImageIcon key_yellow_grass = makeImageIcon("/items/keys/key_yellow_grass.png");
    private final ImageIcon key_red_grass = makeImageIcon("/items/keys/key_red_grass.png");

    // Doors
    private final ImageIcon door_red_grass = makeImageIcon("/tiles/doors/door_red_grass.png");
    private final ImageIcon door_blue_grass = makeImageIcon("/tiles/doors/door_blue_grass.png");
    private final ImageIcon door_yellow_grass = makeImageIcon("/tiles/doors/door_yellow_grass.png");
    private final ImageIcon door_green_grass = makeImageIcon("/tiles/doors/door_green_grass.png");

    //--------------------
    // Rock Image Icons
    //--------------------

    // Basic Tiles
    private final ImageIcon floor_rock = makeImageIcon("/tiles/floor_rock.png");
    private final ImageIcon wall_rock = makeImageIcon("/tiles/wall_rock.png");
    private final ImageIcon exit_rock = makeImageIcon("/tiles/exit_rock.png");
    private final ImageIcon exit_lock_rock = makeImageIcon("/tiles/exit_lock_rock.png");
    private final ImageIcon computer_chip_rock = makeImageIcon("/items/computer_chip_rock.png");
    private final ImageIcon hint_rock = makeImageIcon("/tiles/hint_rock.png");

    // Chip
    private final ImageIcon chip_up_rock = makeImageIcon("/chip/chip_up_rock.png");
    private final ImageIcon chip_down_rock = makeImageIcon("/chip/chip_down_rock.png");
    private final ImageIcon chip_left_rock = makeImageIcon("/chip/chip_left_rock.png");
    private final ImageIcon chip_right_rock = makeImageIcon("/chip/chip_right_rock.png");

    // Cyclops
    private final ImageIcon cyclops_up_rock = makeImageIcon("/enemies/cyclops/cyclops_up_rock.png");
    private final ImageIcon cyclops_down_rock = makeImageIcon("/enemies/cyclops/cyclops_down_rock.png");
    private final ImageIcon cyclops_left_rock = makeImageIcon("/enemies/cyclops/cyclops_left_rock.png");
    private final ImageIcon cyclops_right_rock = makeImageIcon("/enemies/cyclops/cyclops_right_rock.png");

    // Keys
    private final ImageIcon key_green_rock = makeImageIcon("/items/keys/key_green_rock.png");
    private final ImageIcon key_blue_rock = makeImageIcon("/items/keys/key_blue_rock.png");
    private final ImageIcon key_yellow_rock = makeImageIcon("/items/keys/key_yellow_rock.png");
    private final ImageIcon key_red_rock = makeImageIcon("/items/keys/key_red_rock.png");

    // Doors
    private final ImageIcon door_red_rock = makeImageIcon("/tiles/doors/door_red_rock.png");
    private final ImageIcon door_blue_rock = makeImageIcon("/tiles/doors/door_blue_rock.png");
    private final ImageIcon door_yellow_rock = makeImageIcon("/tiles/doors/door_yellow_rock.png");
    private final ImageIcon door_green_rock = makeImageIcon("/tiles/doors/door_green_rock.png");

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
    public ImageIcon getTile(String tileName, int tileset) {
        if (tileset >= 0) {
            StringBuilder newString = new StringBuilder();
            newString.append(tileName + "_");
            if (tileset == 0) {
                newString.append("grass");
            } else {
                newString.append("rock");
            }
            tileName = newString.toString();
        }

        if (tileName == null || tileName.startsWith("empty")) {
            return empty;
        }
        if (tileName.endsWith("icon")) {
            return getIcon(tileName);
        }

        if (tileName.startsWith("chip")) {
            return getChip(tileName);
        } else if (tileName.startsWith("key")) {
            return getKey(tileName);
        }else if (tileName.startsWith("door")) {
            return getDoor(tileName);
        }

        return getBasic(tileName);
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
            System.out.println(filename);
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
            case "chip_up_grass":
                return chip_up_grass;
            case "chip_down_grass":
                return chip_down_grass;
            case "chip_left_grass":
                return chip_left_grass;
            case "chip_right_grass":
                return chip_right_grass;
            case "chip_up_rock":
                return chip_up_rock;
            case "chip_down_rock":
                return chip_down_rock;
            case "chip_left_rock":
                return chip_left_rock;
            case "chip_right_rock":
                return chip_right_rock;
            default:
                throw new Error("TileFinder getChip() - No such chip as: " + tileName);
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
    private ImageIcon getCyclops(String tileName) {
        switch(tileName) {
            case "cyclops_up_grass":
                return cyclops_up_grass;
            case "cyclops_down_grass":
                return cyclops_down_grass;
            case "cyclops_left_grass":
                return cyclops_left_grass;
            case "cyclops_right_grass":
                return cyclops_right_grass;
            case "cyclops_up_rock":
                return cyclops_up_rock;
            case "cyclops_down_rock":
                return cyclops_down_rock;
            case "cyclops_left_rock":
                return cyclops_left_rock;
            case "cyclops_right_rock":
                return cyclops_right_rock;
            default:
                throw new Error("TileFinder getChip() - No such chip as: " + tileName);
        }
    }

    /**
     * Check if given tileName Image Icon exists in the Basic Tileset
     *
     * @param tileName
     * Name of possible Tile
     * @return
     * Requested Tile as an ImageIcon
     *
     * @author Cameron Li
     */
    private ImageIcon getBasic(String tileName) {
        switch (tileName) {
            // Basic Tiles
            case "hint_grass":
                return hint_grass;
            case "computer_chip_grass":
                return computer_chip_grass;
            case "floor_grass":
                return floor_grass;
            case "wall_grass":
                return wall_grass;
            case "exit_grass":
                return exit_grass;
            case "exit_lock_grass":
                return exit_lock_grass;
            case "hint_rock":
                return hint_rock;
            case "computer_chip_rock":
                return computer_chip_rock;
            case "floor_rock":
                return floor_rock;
            case "wall_rock":
                return wall_rock;
            case "exit_rock":
                return exit_rock;
            case "exit_lock_rock":
                return exit_lock_rock;
            default:
                throw new Error("TileFinder getTile() - No such tile as: " + tileName);
        }
    }

    /**
     * Given a String that starts with door.
     * Search for the specific door tile.
     *
     * @param tileName
     * Name of door tile
     * @return
     * Requested Door Tile as an ImageIcon.
     *
     * @author Cameron Li
     */
    private ImageIcon getDoor(String tileName){
        switch(tileName) {
            case "door_green_grass":
                return door_green_grass;
            case "door_yellow_grass":
                return door_yellow_grass;
            case "door_blue_grass":
                return door_blue_grass;
            case "door_red_grass":
                return door_red_grass;
            case "door_green_rock":
                return door_green_rock;
            case "door_yellow_rock":
                return door_yellow_rock;
            case "door_blue_rock":
                return door_blue_rock;
            case "door_red_rock":
                return door_red_rock;
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
            case "key_green_grass":
                return key_green_grass;
            case "key_yellow_grass":
                return key_yellow_grass;
            case "key_blue_grass":
                return key_blue_grass;
            case "key_red_grass":
                return key_red_grass;
            case "key_green_rock":
                return key_green_rock;
            case "key_yellow_rock":
                return key_yellow_rock;
            case "key_blue_rock":
                return key_blue_rock;
            case "key_red_rock":
                return key_red_rock;
            case "key_blue":
                return key_blue;
            case "key_red":
                return key_red;
            case "key_green":
                return key_green;
            case "key_yellow":
                return key_yellow;
            default:
                throw new Error("TileFinder getKey() - No such key as: " + tileName);
        }
    }

    /**
     * Check if there exists an Icon with given tileName
     * Icon is an image without a background
     *
     * @param tileName
     * Name of possible Tile
     * @return
     * Requested Tile as an ImageIcon
     *
     * @author Cameron Li
     */
    private ImageIcon getIcon(String tileName) {
        switch (tileName) {
            // Basic Tiles
            case "chip_icon":
                return chip_icon;
            case "computer_chip_icon":
                return computer_chip_icon;
            case "cyclops_icon":
                return cyclops_icon;
            case "door_icon":
                return door_icon;
            case "exit_icon":
                return exit_icon;
            case "exit_lock_icon":
                return exit_lock_icon;
            case "key_icon":
                return key_icon;
            default:
                throw new Error("TileFinder getTile() - No such tile as: " + tileName);
        }
    }
}
