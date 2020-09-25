package nz.ac.vuw.ecs.swen225.gp20.render;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {

    // Find Tile
    private String[][] currentBoard;
    private final TileFinder tileFinder;

    // Size of Grid
    private final int rows;
    private final int cols;

    // Render Display
    private JLabel[][] tileGrid;
    private JPanel displayGrid;
    private JPanel innerPanel;

    public RenderPanel(int rows, int cols) {
        this.tileFinder = new TileFinder();
        this.rows = rows;
        this.cols = cols;

        makeInnerPanel();
        this.add(innerPanel);
        this.setVisible(true);
    }

    public void setBoard(String[][] currentBoard) {
        this.currentBoard = currentBoard;
        repaint();
    }

    private void makeInnerPanel() {
        tileGrid = new JLabel[rows][cols];
        displayGrid = new JPanel();
        innerPanel = new JPanel(new BorderLayout());
        createGrid();
        innerPanel.add(displayGrid, BorderLayout.CENTER);
    }

    private void createGrid() {
        tileGrid = new JLabel[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                tileGrid[row][col] = new JLabel(tileFinder.getTile("empty"));
            }
        }

        displayGrid = new JPanel(new GridLayout(rows, cols, 1, 1));
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                displayGrid.add(tileGrid[row][col]);
            }
        }
    }

    public void paint(Graphics g) {
        int size = this.getWidth() < this.getHeight() ? this.getWidth()/cols : this.getHeight()/rows;
        super.paint(g);
        for (int row = 0; row < rows; row ++) {
            for (int col = 0; col < cols; col ++) {
                ImageIcon foundTile = tileFinder.getTile(currentBoard[row][col]);
                Image resizeImage = foundTile.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
                tileGrid[row][col].setIcon(new ImageIcon(resizeImage));
            }
        }
    }

}
