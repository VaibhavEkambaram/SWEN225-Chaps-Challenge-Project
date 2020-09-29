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
    private final ImageIcon block = makeImageIcon("/tiles/block.png");
    private final ImageIcon exit = makeImageIcon("/tiles/exit.png");
    private final ImageIcon exit_lock = makeImageIcon("/tiles/exit_lock.png");
    private final ImageIcon fire = makeImageIcon("/tiles/fire.png");
    private final ImageIcon computer_chip = makeImageIcon("/items/computer_chip.png");
    private final ImageIcon hint = makeImageIcon("/tiles/hint.png");

    // Chip
    private final ImageIcon chip_up = makeImageIcon("/chip/chip_up.png");
    private final ImageIcon chip_down = makeImageIcon("/chip/chip_down.png");
    private final ImageIcon chip_left = makeImageIcon("/chip/chip_left.png");
    private final ImageIcon chip_right= makeImageIcon("/chip/chip_right.png");
    private final ImageIcon chip_swim_up = makeImageIcon("/chip/chip_swim_up.png");
    private final ImageIcon chip_swim_down = makeImageIcon("/chip/chip_swim_down.png");
    private final ImageIcon chip_swim_left = makeImageIcon("/chip/chip_swim_left.png");
    private final ImageIcon chip_swim_right= makeImageIcon("/chip/chip_swim_right.png");

    // Bug
    private final ImageIcon bug_up = makeImageIcon("/monsters/bug/bug_up.png");
    private final ImageIcon bug_down = makeImageIcon("/monsters/bug/bug_down.png");
    private final ImageIcon bug_left = makeImageIcon("/monsters/bug/bug_left.png");
    private final ImageIcon bug_right= makeImageIcon("/monsters/bug/bug_right.png");

    // Tank
    private final ImageIcon tank_up = makeImageIcon("/monsters/tank/tank_up.png");
    private final ImageIcon tank_down = makeImageIcon("/monsters/tank/tank_down.png");
    private final ImageIcon tank_left = makeImageIcon("/monsters/tank/tank_left.png");
    private final ImageIcon tank_right= makeImageIcon("/monsters/tank/tank_right.png");
    // Glider
    private final ImageIcon glider_up = makeImageIcon("/monsters/glider/glider_up.png");
    private final ImageIcon glider_down = makeImageIcon("/monsters/glider/glider_down.png");
    private final ImageIcon glider_left = makeImageIcon("/monsters/glider/glider_left.png");
    private final ImageIcon glider_right= makeImageIcon("/monsters/glider/glider_right.png");

    // Keys
    private final ImageIcon key_green = makeImageIcon("/items/keys/key_green.png");
    private final ImageIcon key_blue = makeImageIcon("/items/keys/key_blue.png");
    private final ImageIcon key_yellow = makeImageIcon("/items/keys/key_yellow.png");
    private final ImageIcon key_red = makeImageIcon("/items/keys/key_red.png");

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
        } else if (tileName.startsWith("tank")) {
            return getTank(tileName);
        } else if (tileName.startsWith("bug")) {
            return getBug(tileName);
        } else if (tileName.startsWith("glider")) {
            return getGlider(tileName);
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
            case "block":
                return block;
            case "exit":
                return exit;
            case "exit_lock":
                return exit_lock;
            case "fire":
                return fire;
            // Chip

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
            case "chip_swim_up":
                return chip_swim_up;
            case "chip_swim_down":
                return chip_swim_down;
            case "chip_swim_left":
                return chip_swim_left;
            case "chip_swim_right":
                return chip_swim_right;
            default:
                throw new Error("TileFinder getChip() - No such chip as: " + tileName);
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

    /**
     * Given a String that starts with tank.
     * Search for the specific tank tile.
     *
     * @param tileName
     * Name of tank tile
     * @return
     * Requested tank Tile as an ImageIcon.
     *
     * @author Cameron Li
     */
    private ImageIcon getTank(String tileName) {
        switch(tileName) {
            case "tank_up":
                return tank_up;
            case "tank_down":
                return tank_down;
            case "tank_left":
                return tank_left;
            case "tank_right":
                return tank_right;
            default:
                throw new Error("TileFinder getTank() - No such Tank as: " + tileName);
        }
    }

    /**
     * Given a String that starts with bug.
     * Search for the specific bug tile.
     *
     * @param tileName
     * Name of bug tile
     * @return
     * Requested bug Tile as an ImageIcon.
     *
     * @author Cameron Li
     */
    private ImageIcon getBug(String tileName) {
        switch(tileName) {
            case "bug_up":
                return bug_up;
            case "bug_down":
                return bug_down;
            case "bug_left":
                return bug_left;
            case "bug_right":
                return bug_right;
            default:
                throw new Error("TileFinder getBug() - No such Bug as: " + tileName);
        }
    }

    /**
     * Given a String that starts with glider.
     * Search for the specific glider tile.
     *
     * @param tileName
     * Name of glider tile
     * @return
     * Requested glider Tile as an ImageIcon.
     *
     * @author Cameron Li
     */
    private ImageIcon getGlider(String tileName) {
        switch(tileName) {
            case "glider_up":
                return glider_up;
            case "glider_down":
                return glider_down;
            case "glider_left":
                return glider_left;
            case "glider_right":
                return glider_right;
            default:
                throw new Error("TileFinder getGlider() - No such Glider as: " + tileName);
        }
    }
}
