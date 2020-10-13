package nz.ac.vuw.ecs.swen225.gp23.application;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class RecordReplayInterface extends JFrame {
    public RecordReplayInterface(){
        super("Record and Replay");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,5));
        mainPanel.add(new JButton(makeImageIcon("/record/record.png")));
        mainPanel.add(new JButton(makeImageIcon("/record/save.png")));
        mainPanel.add(new JButton(makeImageIcon("/record/load.png")));
        mainPanel.add(new JButton(makeImageIcon("/record/play.png")));
        mainPanel.add(new JButton());

        setMinimumSize(new Dimension(300,100));
        setMaximumSize(new Dimension(300,100));
        setPreferredSize(new Dimension(300,100));
        getContentPane().add(mainPanel);
        setLocationByPlatform(true);
        pack();
        setVisible(true);
    }

    private ImageIcon makeImageIcon(String filename) {
        URL imageURL = this.getClass().getResource(filename);
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        } else {
            throw new Error("File not found!");
        }
    }



}
