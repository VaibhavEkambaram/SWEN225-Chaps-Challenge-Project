package nz.ac.vuw.ecs.swen225.gp23.persistence;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class assetManager {

    private static final String pathForAsset = "Program/src/nz.ac.vuw.ecs.swen225.gp23/render/tile_images";
    private boolean scaling = false;

    private Map<String, ImageIcon> bImageIcons = new HashMap<>();
    private Map<String, ImageIcon> scaledImageIcons = new HashMap<>();
    private int cSize = 10;

    void loadAssetInputStream(InputStream iS, String fn)
        throws IOException {
            fn = fn.toLowerCase();
            BufferedImage bI = ImageIO.read(iS);

            ImageIcon iIcon = new ImageIcon(bI);
            iIcon.setDescription(fn);

        }


    private void loadAsset(ImageIcon iI, String fn) {
        if(bImageIcons.containsKey(fn)){
            return;
        }

        ImageIcon bIcon = new ImageIcon(iI.getImage());

        ImageIcon scaleIcon = new ImageIcon(bIcon.getImage().getScaledInstance(cSize,cSize, Image.SCALE_SMOOTH));
        scaleIcon.setDescription(bIcon.getDescription());

        bImageIcons.put(fn,bIcon);
        bImageIcons.put(fn, scaleIcon);

    }


}
