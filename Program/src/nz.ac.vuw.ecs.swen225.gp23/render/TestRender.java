package nz.ac.vuw.ecs.swen225.gp23.render;

import javax.swing.*;

public class TestRender {

    JFrame testFrame;
    RenderPanel testRenderPanel;
    String[][] board = new String[5][5];

    public TestRender() {
        this.testFrame = new JFrame();
        this.testRenderPanel = new RenderPanel(5, 5);

        this.testFrame.add(testRenderPanel);
        this.testFrame.setVisible(true);

        board[0][0] = "floor";
        this.testRenderPanel.setBoard(board);
        this.testFrame.pack();
    }
}