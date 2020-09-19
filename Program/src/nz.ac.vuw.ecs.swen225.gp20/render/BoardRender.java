package nz.ac.vuw.ecs.swen225.gp20.render;

import nz.ac.vuw.ecs.swen225.gp20.application.Main;

import javax.swing.*;
import java.net.URL;

public class BoardRender {

    private static ImageIcon boardPartition = makeImageIcon("boardPartition.png");

    public BoardRender() {

    }

    private static ImageIcon makeImageIcon(String filename) {
        // using the URL means the image loads when stored
        // in a jar or expanded into individual files.
        URL imageURL = Main.class.getResource(filename);

        ImageIcon icon = null;
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        return icon;
    }

}
