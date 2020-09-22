package nz.ac.vuw.ecs.swen225.gp20.render;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RenderPanel extends JPanel {

    private JPanel chipGrid;
    private JPanel innerPanel;
    private JLabel[][] grid;
    private final BoardRender currentRender;
    private final int width;
    private final int height;

    public RenderPanel() {
        this.currentRender = new BoardRender();
        this.width = BoardRender.width;
        this.height = BoardRender.height;
    }

    /**
     * This method is used to create the outermost panel.
     * @return
     */
    private void makeOutermostPanel() {
        grid = new JLabel[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new JLabel();
            }
        }

        chipGrid = new JPanel(new GridLayout(height, width, 1, 1));
        chipGrid.setBorder(new EmptyBorder(15, 15, 15, 15));
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                chipGrid.add(grid[row][col]);
            }
        }

        innerPanel = new JPanel(new BorderLayout());
        innerPanel.add(chipGrid, BorderLayout.CENTER);
        this.add(innerPanel);
        drawBoard();
    }

    public void drawBoard() {
        for (int row = 0; row < height; row ++) {
            for (int col = 0; col < width; col ++) {
                //get tile from findTIle
            }
        }
    }

}
