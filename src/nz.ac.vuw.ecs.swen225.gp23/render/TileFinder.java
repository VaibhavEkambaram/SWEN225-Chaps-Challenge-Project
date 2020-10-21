package nz.ac.vuw.ecs.swen225.gp23.render;

import nz.ac.vuw.ecs.swen225.gp23.application.Main;

import javax.swing.*;
import java.net.URL;

/**
 * Chip ImageIcon Directory.
 * Contains references to all Images necessary for the game.
 *
 * @author Cameron Li - 300490702
 */
public class TileFinder {

    private static final ImageIcon empty = makeImageIcon("/tile_images/tiles/empty.png");

    //--------------------
    // Basic Image Icons
    //--------------------

    // Basic Tiles
    private static final ImageIcon exit_icon = makeImageIcon("/tile_images/icons/exit_icon.png");
    private static final ImageIcon exit_lock_icon = makeImageIcon("/tile_images/icons/exit_lock_icon.png");
    private static final ImageIcon computer_chip_icon = makeImageIcon("/tile_images/icons/computer_chip_icon.png");
    private static final ImageIcon key_icon = makeImageIcon("/tile_images/icons/key_icon.png");
    private static final ImageIcon door_icon = makeImageIcon("/tile_images/icons/door_icon.png");
    private static final ImageIcon chip_icon = makeImageIcon("/tile_images/icons/chip_icon.png");
    private static final ImageIcon cyclops_icon = makeImageIcon("/tile_images/icons/cyclops_icon.png");

    // Keys
    private static final ImageIcon key_blue = makeImageIcon("/tile_images/icons/key_blue.png");
    private static final ImageIcon key_red = makeImageIcon("/tile_images/icons/key_red.png");
    private static final ImageIcon key_yellow = makeImageIcon("/tile_images/icons/key_yellow.png");
    private static final ImageIcon key_green = makeImageIcon("/tile_images/icons/key_green.png");

    //--------------------
    // Grass Image Icons
    //--------------------

    // Basic Tiles
    private static final ImageIcon floor_grass = makeImageIcon("/tile_images/tiles/floor_grass.png");
    private static final ImageIcon wall_grass = makeImageIcon("/tile_images/tiles/wall_grass.png");
    private static final ImageIcon exit_grass = makeImageIcon("/tile_images/tiles/exit_grass.png");
    private static final ImageIcon exit_lock_grass = makeImageIcon("/tile_images/tiles/exit_lock_grass.png");
    private static final ImageIcon computer_chip_grass = makeImageIcon("/tile_images/items/computer_chip_grass.png");
    private static final ImageIcon hint_grass = makeImageIcon("/tile_images/tiles/hint_grass.png");

    // Chip
    private static final ImageIcon chip_up_grass = makeImageIcon("/tile_images/chip/chip_up_grass.png");
    private static final ImageIcon chip_down_grass = makeImageIcon("/tile_images/chip/chip_down_grass.png");
    private static final ImageIcon chip_left_grass = makeImageIcon("/tile_images/chip/chip_left_grass.png");
    private static final ImageIcon chip_right_grass = makeImageIcon("/tile_images/chip/chip_right_grass.png");

    // Cyclops
    private static final ImageIcon cyclops_up_grass = makeImageIcon("/tile_images/enemies/cyclops/cyclops_up_grass.png");
    private static final ImageIcon cyclops_down_grass = makeImageIcon("/tile_images/enemies/cyclops/cyclops_down_grass.png");
    private static final ImageIcon cyclops_left_grass = makeImageIcon("/tile_images/enemies/cyclops/cyclops_left_grass.png");
    private static final ImageIcon cyclops_right_grass = makeImageIcon("/tile_images/enemies/cyclops/cyclops_right_grass.png");

    // Keys
    private static final ImageIcon key_green_grass = makeImageIcon("/tile_images/items/keys/key_green_grass.png");
    private static final ImageIcon key_blue_grass = makeImageIcon("/tile_images/items/keys/key_blue_grass.png");
    private static final ImageIcon key_yellow_grass = makeImageIcon("/tile_images/items/keys/key_yellow_grass.png");
    private static final ImageIcon key_red_grass = makeImageIcon("/tile_images/items/keys/key_red_grass.png");

    // Doors
    private static final ImageIcon door_red_grass = makeImageIcon("/tile_images/tiles/doors/door_red_grass.png");
    private static final ImageIcon door_blue_grass = makeImageIcon("/tile_images/tiles/doors/door_blue_grass.png");
    private static final ImageIcon door_yellow_grass = makeImageIcon("/tile_images/tiles/doors/door_yellow_grass.png");
    private static final ImageIcon door_green_grass = makeImageIcon("/tile_images/tiles/doors/door_green_grass.png");

    //--------------------
    // Rock Image Icons
    //--------------------

    // Basic Tiles
    private static final ImageIcon floor_rock = makeImageIcon("/tile_images/tiles/floor_rock.png");
    private static final ImageIcon wall_rock = makeImageIcon("/tile_images/tiles/wall_rock.png");
    private static final ImageIcon exit_rock = makeImageIcon("/tile_images/tiles/exit_rock.png");
    private static final ImageIcon exit_lock_rock = makeImageIcon("/tile_images/tiles/exit_lock_rock.png");
    private static final ImageIcon computer_chip_rock = makeImageIcon("/tile_images/items/computer_chip_rock.png");
    private static final ImageIcon hint_rock = makeImageIcon("/tile_images/tiles/hint_rock.png");

    // Chip
    private static final ImageIcon chip_up_rock = makeImageIcon("/tile_images/chip/chip_up_rock.png");
    private static final ImageIcon chip_down_rock = makeImageIcon("/tile_images/chip/chip_down_rock.png");
    private static final ImageIcon chip_left_rock = makeImageIcon("/tile_images/chip/chip_left_rock.png");
    private static final ImageIcon chip_right_rock = makeImageIcon("/tile_images/chip/chip_right_rock.png");

    // Cyclops
    private static final ImageIcon cyclops_up_rock = makeImageIcon("/tile_images/enemies/cyclops/cyclops_up_rock.png");
    private static final ImageIcon cyclops_down_rock = makeImageIcon("/tile_images/enemies/cyclops/cyclops_down_rock.png");
    private static final ImageIcon cyclops_left_rock = makeImageIcon("/tile_images/enemies/cyclops/cyclops_left_rock.png");
    private static final ImageIcon cyclops_right_rock = makeImageIcon("/tile_images/enemies/cyclops/cyclops_right_rock.png");

    // Keys
    private static final ImageIcon key_green_rock = makeImageIcon("/tile_images/items/keys/key_green_rock.png");
    private static final ImageIcon key_blue_rock = makeImageIcon("/tile_images/items/keys/key_blue_rock.png");
    private static final ImageIcon key_yellow_rock = makeImageIcon("/tile_images/items/keys/key_yellow_rock.png");
    private static final ImageIcon key_red_rock = makeImageIcon("/tile_images/items/keys/key_red_rock.png");

    // Doors
    private static final ImageIcon door_red_rock = makeImageIcon("/tile_images/tiles/doors/door_red_rock.png");
    private static final ImageIcon door_blue_rock = makeImageIcon("/tile_images/tiles/doors/door_blue_rock.png");
    private static final ImageIcon door_yellow_rock = makeImageIcon("/tile_images/tiles/doors/door_yellow_rock.png");
    private static final ImageIcon door_green_rock = makeImageIcon("/tile_images/tiles/doors/door_green_rock.png");

    /**
     * Get an Image from a string.
     *
     * @param tileName
     * Name of the requested tile.
     * @param tileset
     * Tileset to use (less than 0 for none, 0 for grass, greater than 0 for rock)
     *
     * @return
     * Requested tile as an ImageIcon.
     *
     * @author Cameron Li - 300490702
     */
    public static ImageIcon getTile(String tileName, int tileset) {
        if (tileset >= 0) {
            StringBuilder newString = new StringBuilder();
            newString.append(tileName).append("_");
            if (tileset == 0) {
                newString.append("grass");
            } else {
                newString.append("rock");
            }
            tileName = newString.toString();
        }

        if (tileName == null || tileName.startsWith("empty") || tileName.startsWith("null")) {
            return empty;
        }
        if (tileName.endsWith("icon")) {
            return getIcon(tileName);
        }

        if (tileName.startsWith("chip")) {
            return getChip(tileName);
        } else if(tileName.startsWith("cyclops")){
            return getCyclops(tileName);
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
     * @author Cameron Li - 300490702
     */
    private static ImageIcon makeImageIcon(String filename) {
        URL imageURL = Main.class.getResource(filename);
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
     * @author Cameron Li - 300490702
     */
    private static ImageIcon getChip(String tileName) {
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
     * @author Cameron Li - 300490702
     */
    private static ImageIcon getCyclops(String tileName) {
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
                throw new Error("TileFinder getCyclops() - No such cyclops as: " + tileName);
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
     * @author Cameron Li - 300490702
     */
    private static ImageIcon getBasic(String tileName) {
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
                throw new Error("TileFinder getBasic() - No such tile as: " + tileName);
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
     * @author Cameron Li - 300490702
     */
    private static ImageIcon getDoor(String tileName){
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
                throw new Error("TileFinder getDoor() - No such door as: " + tileName);
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
     * @author Cameron Li - 300490702
     */
    private static ImageIcon getKey(String tileName) {
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
     * @author Cameron Li - 300490702
     */
    private static ImageIcon getIcon(String tileName) {
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
                throw new Error("TileFinder getIcon() - No such icon as: " + tileName);
        }
    }
}
