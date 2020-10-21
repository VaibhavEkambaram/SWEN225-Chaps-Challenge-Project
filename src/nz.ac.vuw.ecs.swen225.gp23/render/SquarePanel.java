package nz.ac.vuw.ecs.swen225.gp23.render;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {
    /**
     * See getPreferredSize()
     * @return
     * Minimum Dimension of the JPanel
     *
     * @author Cameron Li - 300490702
     */
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /**
     * See getPreferredSize()
     * @return
     * Maximum Dimension of the JPanel
     *
     * @author Cameron Li - 300490702
     */
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    /**
     * Ensures that this JPanel is always square
     * Gets the Parent Container dimensions, size is determined off minimum dimension
     * @return
     * Preferred Dimension of the SquarePanel
     *
     * @author Cameron Li - 300490702
     */
    @Override
    public Dimension getPreferredSize() {
        Container c = getParent();
        int width = c.getWidth();
        int height = c.getHeight();
        int size = width < height ? width : height;
        return new Dimension(size, size);
    }
}
