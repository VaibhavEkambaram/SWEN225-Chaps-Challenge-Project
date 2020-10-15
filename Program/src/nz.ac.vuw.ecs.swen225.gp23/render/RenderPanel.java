package nz.ac.vuw.ecs.swen225.gp23.render;

import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;

import javax.swing.*;
import java.awt.*;

/**
 * RenderPanel JPanel used for Board Rendering
 *
 * @author Cameron Li
 */
public class RenderPanel extends JPanel {

    // Find Tile
    private String[][] displayBoard; // Current board state to display
    private final TileFinder tileFinder;

    // Size of Grid
    private final int rows;
    private final int cols;

    private boolean isPaused;

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

        this.setBackground(new Color(25, 25, 112));
    }

    /**
     * Update the rendered board to display a new board.
     * @param currentBoard
     * Next board state.
     *
     * @author Cameron Li
     */
    public void setBoard(Board currentBoard) {
        displayBoard = new String[this.rows][this.cols];
        Tile centerTile = currentBoard.getPlayerLoc();
        int centerRow = centerTile.getYLoc();
        int centerCol = centerTile.getXLoc();
        int rowRadius = (this.rows)/2;
        int colRadius = (this.cols)/2;
        int startRow = centerRow - rowRadius;
        int startCol = centerCol - colRadius;
        for (int row = 0; row < this.rows; row++) {
            int tempCol = startCol;
            for (int col = 0; col < this.cols; col++) {
                Tile currentTile = currentBoard.getTile(tempCol, startRow);
                if (currentTile == null) {
                    displayBoard[row][col] = "empty";
                } else {
                    displayBoard[row][col] = currentTile.toString();
                }
                tempCol++;
            }
            startRow++;
        }
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

        displayGrid = new JPanel(new GridLayout(rows, cols, 0, 0));
        for (int row = 0; row < rows; row++) { // Add JLabels to each tile in the display
            for (int col = 0; col < cols; col++) {
                displayGrid.add(tileGrid[row][col]);
            }
        }
    }

    public void setPaused(boolean pause) {
        isPaused = pause;
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
        int margin = rows < cols ? rows : cols;
        int width = this.getWidth() - margin;
        int height = this.getHeight() - margin;
        int size = width < height ? width/cols : height/rows;
        super.paint(g);
        if (!isPaused) {
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    ImageIcon foundTile = tileFinder.getTile(displayBoard[row][col]);
                    Image resizeImage = foundTile.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
                    tileGrid[row][col].setIcon(new ImageIcon(resizeImage));
                }
            }
        } else {
            // draw pause string
            g.setColor(Color.BLACK);
            g.fillRect(0, 0,  3000, 3000);
            g.setColor(Color.WHITE);
            Font font = g.getFont();
            FontMetrics metrics = g.getFontMetrics(font);
            // Determine the X coordinate for the text
            String text = "Game has been paused...press ESC to resume";
            int x = (this.getWidth() - metrics.stringWidth(text)) / 2;
            // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
            int y = (this.getHeight() - metrics.getHeight())/2;
            // Set the font
            g.setFont(font);
            g.drawString(text, x, y);
        }
    }

}
