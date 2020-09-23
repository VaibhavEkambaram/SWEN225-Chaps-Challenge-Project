package nz.ac.vuw.ecs.swen225.gp20.render;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RenderPanel extends JPanel {

    private JLabel[][] grid;
    private JPanel chipGrid;
    private JPanel innerPanel;

    private final TileFinder tileFinder;

    public RenderPanel() {
        this.tileFinder = new TileFinder();
        makeInnerPanel();
    }

    /**
     * This method is used to create the outermost panel.
     * @return
     */
    private void makeInnerPanel() {
        grid = new JLabel[0][0];

        innerPanel = new JPanel(new BorderLayout());
        innerPanel.add(chipGrid, BorderLayout.CENTER);
        this.add(innerPanel);
    }

    public void refreshBoard(String[][] currentBoard) {
        int width = currentBoard.length;
        int height = currentBoard[0].length;
        createGrid(width, height);
        for (int row = 0; row < width; row ++) {
            for (int col = 0; col < height; col ++) {
                grid[row][col].setIcon(tileFinder.getTile(currentBoard[row][col]));
            }
        }
    }

    private void createGrid(int width, int height) {
        grid = new JLabel[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new JLabel(tileFinder.getTile("empty"));
            }
        }

        chipGrid = new JPanel(new GridLayout(height, width, 1, 1));
        chipGrid.setBorder(new EmptyBorder(15, 15, 15, 15));
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                chipGrid.add(grid[row][col]);
            }
        }
    }





}
