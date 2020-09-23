package nz.ac.vuw.ecs.swen225.gp20.render;

import javax.swing.*;
import java.net.URL;

public class TileFinder {

    // Basic Tiles
    private final ImageIcon empty = makeImageIcon("/tiles/empty.png");
    private final ImageIcon floor = makeImageIcon("/tiles/floor.png");
    private final ImageIcon wall = makeImageIcon("/tiles/wall.png");
    private final ImageIcon block = makeImageIcon("/tiles/block.png");

    // Chip
    private final ImageIcon chip_up = makeImageIcon("/chip/chip_up.png");
    private final ImageIcon chip_down = makeImageIcon("/chip/chip_down.png");
    private final ImageIcon chip_left = makeImageIcon("/chip/chip_left.png");
    private final ImageIcon chip_right= makeImageIcon("/chip/chip_right.png");
    private final ImageIcon chip_swim_up = makeImageIcon("/chip/chip_swim_up.png");
    private final ImageIcon chip_swim_down = makeImageIcon("/chip/chip_swim_down.png");
    private final ImageIcon chip_swim_left = makeImageIcon("/chip/chip_swim_left.png");
    private final ImageIcon chip_swim_right= makeImageIcon("/chip/chip_swim_right.png");

    // Keys
    private final ImageIcon key_green = makeImageIcon("/items/keys/key_green.png");
    private final ImageIcon key_blue = makeImageIcon("/items/keys/key_blue.png");
    private final ImageIcon key_yellow = makeImageIcon("/items/keys/key_yellow.png");
    private final ImageIcon key_red = makeImageIcon("/items/keys/key_red.png");

    public ImageIcon getTile(String tileName) {
        if (tileName.startsWith("chip")) {
            return getChip(tileName);
        } else if (tileName.startsWith("key")) {
            return getKey(tileName);
        }
        switch (tileName) {
            // Basic Tiles
            case "empty":
                return empty;
            case "floor":
                return floor;
            case "wall":
                return wall;
            case "block":
                return block;
            // Chip
            default:
                throw new Error("TileFinder getTile() - No such tile as: " + tileName);
        }
    }


    private ImageIcon makeImageIcon(String filename) {
        // using the URL means the image loads when stored
        // in a jar or expanded into individual files.
        URL imageURL = this.getClass().getResource(filename);

        ImageIcon icon = null;
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        return icon;
    }

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
