package nz.ac.vuw.ecs.swen225.gp23.render;

import javax.swing.*;
import java.awt.*;

/**
 * RenderPanel JPanel used for Board Rendering
 *
 * @author Cameron Li
 */
public class RenderPanel extends JPanel {

    // Find Tile
    private String[][] currentBoard; // Current board state to display
    private final TileFinder tileFinder;

    // Size of Grid
    private final int rows;
    private final int cols;

    // Render Display
    private JLabel[][] tileGrid;
    private JPanel displayGrid;
    private JPanel innerPanel;

    /**
     * Intialise the size of the rendered board.
     * @param rows
     * Number of rows for the display grid.
     * @param cols
     * Number of cols for the display grid.
     *
     * @author Cameron Li
     */
    public RenderPanel(int rows, int cols) {
        this.tileFinder = new TileFinder();
        this.rows = rows;
        this.cols = cols;

        makeInnerPanel();
        this.add(innerPanel);
        this.setVisible(true);
    }

    /**
     * Update the rendered board to display a new board.
     * @param currentBoard
     * Next board state.
     *
     * @author Cameron Li
     */
    public void setBoard(String[][] currentBoard) {
        this.currentBoard = currentBoard;
        repaint();
    }

    /**
     * Create the inner panel where the grid is stored.
     *
     * @author Cameron Li
     */
    private void makeInnerPanel() {
        tileGrid = new JLabel[rows][cols];
        displayGrid = new JPanel();
        innerPanel = new JPanel(new BorderLayout());
        createGrid();
        innerPanel.add(displayGrid, BorderLayout.CENTER);
    }

    /**
     * Create the grid and initialise as empty.
     * Add the grid to the display.
     *
     * @author Cameron Li
     */
    private void createGrid() {
        tileGrid = new JLabel[rows][cols];

        for (int row = 0; row < rows; row++) { // Create JLabels (that reference ImageIcons) for each tile
            for (int col = 0; col < cols; col++) {
                tileGrid[row][col] = new JLabel(tileFinder.getTile("empty"));
            }
        }

        displayGrid = new JPanel(new GridLayout(rows, cols, 1, 1));
        for (int row = 0; row < rows; row++) { // Add JLabels to each tile in the display
            for (int col = 0; col < cols; col++) {
                displayGrid.add(tileGrid[row][col]);
            }
        }
    }

    /**
     * Paint method.
     * Used to refresh the board along with GUI.
     * @param g
     * Graphics to draw on.
     *
     * @author Cameron Li.
     */
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
