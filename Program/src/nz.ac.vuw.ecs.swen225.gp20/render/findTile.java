package nz.ac.vuw.ecs.swen225.gp20.render;

import javax.swing.*;
import java.net.URL;

public class findTile {

    private ImageIcon empty = makeImageIcon("/tiles/empty.png");
    private ImageIcon floor = makeImageIcon("/tiles/floor.png");
    private ImageIcon wall;
    private ImageIcon block;

    public static ImageIcon getTile(String filename) {
        return null;
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
}
