package nz.ac.vuw.ecs.swen225.gp20.render;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class BoardRender {

    private ImageIcon empty = makeImageIcon("/tiles/empty.png");
    private ImageIcon floor = makeImageIcon("/tiles/floor.png");

    JPanel chipGrid;
    JFrame testFrame;

    private static final int width = 9;
    private static final int height = 9;

    private JPanel outerMostPanel;
    private JLabel[][] grid;

    private String[][] board;

    public BoardRender() {
        board = new String[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (row % 2 == 0) {
                    board[row][col] = "floor";
                } else {
                    board[row][col] = "empty";
                }
            }
        }
        this.testFrame = new JFrame();

        makeOutermostPanel();
        testFrame.getContentPane().add(outerMostPanel);
        drawBoard();


        testFrame.pack();

        testFrame.setVisible(true);
    }

    /**
     * This method is used to create the outermost panel.
     * @return
     */
    private void makeOutermostPanel() {
        grid = new JLabel[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = new JLabel(empty);
            }
        }

        chipGrid = new JPanel(new GridLayout(height, width, 1, 1));
        chipGrid.setBorder(new EmptyBorder(15, 15, 15, 15));
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                chipGrid.add(grid[row][col]);
            }
        }

        outerMostPanel = new JPanel(new BorderLayout());
        outerMostPanel.add(chipGrid, BorderLayout.CENTER);
        drawBoard();
    }

    public void drawBoard() {
        for (int row = 0; row < height; row ++) {
            for (int col = 0; col < width; col ++) {
                grid[row][col].setIcon(getSquareAt(row, col));
            }
        }
    }

    public ImageIcon getSquareAt(int row, int col) {
        if (board[row][col] == "empty") {
            return empty;
        } else if (board[row][col] == "floor") {
            return floor;
        } else {
            return null;
        }
    }

    private ImageIcon makeImageIcon(String filename) {
        // using the URL means the image loads when stored
        // in a jar or expanded into individual files.
        URL imageURL = this.getClass().getResource(filename);

        ImageIcon icon = null;
        if (imageURL != null) {
            icon = new ImageIcon(imageURL);
        }
        return icon;
    }



}
