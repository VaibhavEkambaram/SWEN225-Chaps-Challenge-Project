package nz.ac.vuw.ecs.swen225.gp23.render;

import javax.swing.*;

public class TestRender {

    JFrame testFrame;
    RenderPanel testRenderPanel;
    String[][] board = new String[5][5];
    ChipAudioModule audio;
    JButton stuff;

    public TestRender() {
        this.testFrame = new JFrame();
        this.testRenderPanel = new RenderPanel(5, 5, 0);

        this.testFrame.add(testRenderPanel);
        this.testFrame.setVisible(true);

        this.testFrame.pack();
        audio = new ChipAudioModule();
        audio.playCurrentLevelTrack(1);

        stuff = new JButton("Sound effect");
        stuff.addActionListener(e -> {
            audio.selectEffect();
        });


        this.testFrame.add(stuff);
    }
}
