package nz.ac.vuw.ecs.swen225.gp20.render;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class BoardRender {

    public static final int width = 9;
    public static final int height = 9;

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
    }


}
