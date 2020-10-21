package nz.ac.vuw.ecs.swen225.gp23.render;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    /**
     * Ensures that this JPanel is always square
     * @return
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
