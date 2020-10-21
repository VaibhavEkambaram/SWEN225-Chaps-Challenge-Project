package nz.ac.vuw.ecs.swen225.gp23.render;

import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * RenderPanel JPanel used for Board Rendering
 *
 * @author Cameron Li - 300490702
 */
public class RenderPanel extends JPanel {

    // Find Tile
    private String[][] displayBoard; // Current board state to display

    // Size of Grid
    private final int rows;
    private final int cols;

    private int tileset;
    private boolean isPaused;

    // Render Display
    private JLabel[][] tileGrid;
    private SquarePanel displayGrid;

    /**
     * Intialise the size of the rendered board.
     * @param rows
     * Number of rows for the display grid.
     * @param cols
     * Number of cols for the display grid.
     *
     * @author Cameron Li - 300490702
     */
    public RenderPanel(int rows, int cols, int tileset) {
        this.rows = rows;
        this.cols = cols;
        this.tileset = tileset;

        this.setLayout(new GridBagLayout());
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(false);
        createGrid();
        this.setVisible(true);
    }

    /**
     * Update the rendered board to display a new board.
     * @param currentBoard
     * Next board state.
     *
     * @author Cameron Li - 300490702
     */
    public void setBoard(Board currentBoard) {
        displayBoard = new String[this.rows][this.cols];
        // Find current player position to determine center of Render Panel
        Tile centerTile = currentBoard.getPlayerLoc();
        // Center Player Location
        int centerRow = centerTile.getYLoc();
        int centerCol = centerTile.getXLoc();
        // Radius from center
        int rowRadius = (this.rows)/2;
        int colRadius = (this.cols)/2;
        int startRow = centerRow - rowRadius;
        int startCol = centerCol - colRadius;
        // Get tiles within radius of center, if null -> empty
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
     * Create the grid and initialise as empty.
     * Add the grid to the display.
     *
     * @author Cameron Li - 300490702
     */
    private void createGrid() {
        tileGrid = new JLabel[rows][cols];

        // Initialise as empty grid
        for (int row = 0; row < rows; row++) { // Create JLabels (that reference ImageIcons) for each tile
            for (int col = 0; col < cols; col++) {
                tileGrid[row][col] = new JLabel(TileFinder.getTile("empty", -1));
            }
        }

        // Initialise the Display Grid before adding JLabel references to ImageIcons
        displayGrid = new SquarePanel();
        displayGrid.setLayout(new GridLayout(rows, cols, 0, 0));
        displayGrid.setBackground(Color.BLACK);
        // Add the empty grid to be displayed
        for (int row = 0; row < rows; row++) { // Add JLabels to each tile in the display
            for (int col = 0; col < cols; col++) {
                displayGrid.add(tileGrid[row][col]);
            }
        }
        // Add it to main Render Panel
        this.add(displayGrid);
    }

    /**
     * Method to set paused
     * @param pause T/F - Whether or not game should be paused
     *
     * @author Cameron Li - 300490702
     */
    public void setPaused(boolean pause) {
        isPaused = pause;
    }

    /**
     * Sets currently used tileset
     * Changes which tiles are used for display
     *
     * @param tileset which tileset to use (0 for grass, 1+ for rock)
     *
     * @author Cameron Li - 300490702
     */
    public void setTileset(int tileset) {
        if (tileset < 0) {
            return;
        }
        this.tileset = tileset;
    }

    /**
     * Paint method.
     * Used to refresh the board along with GUI.
     * @param g
     * Graphics to draw on.
     *
     * @author Cameron Li - 300490702
     */
    public void paint(Graphics g) {
        // Determine maximum usable size by minimum dimension
        int width = this.getWidth();
        int height = this.getHeight();
        int size = width < height ? width/cols : height/rows; // Determine smallest size

        if (!isPaused) {
            if (tileGrid == null || displayBoard == null) {
                String text = "Start a new game using [GAME] menu above or [CTRL + 1]";
                drawString(g, text);
                return;
            }
            // Resize images to fill current Dimensions
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    ImageIcon foundTile = TileFinder.getTile(displayBoard[row][col], tileset);
                    Image resizeImage = foundTile.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
                    tileGrid[row][col].setIcon(new ImageIcon(resizeImage));
                }
            }
            super.paint(g); // Redraw the ImageIcons
        } else {
            String text = "Game has been paused...press ESC to resume";
            drawString(g, text);
        }
    }

    /**
     * Draws a string on a black background centered in the middle
     *
     * @param g Graphics
     * @param text Text to draw
     *
     * @author Cameron Li - 300490702
     */
    private void drawString(Graphics g, String text) {
        // Font information
        Font font = g.getFont();
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X position
        int x = (this.getWidth() - metrics.stringWidth(text)) / 2;
        // Determine the Y position
        int y = (this.getHeight() - metrics.getHeight())/2;
        // Set the font
        g.setFont(font);
        // Black Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0,  this.getWidth(), this.getHeight());
        // Pause Menu String
        g.setColor(Color.ORANGE);
        g.drawString(text, x, y);
    }
}
