package nz.ac.vuw.ecs.swen225.gp20.application;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private String levelName;
    private int counter;
    private Timer timer;

    public Game(int countFromFile,String levelName,JLabel timeLabel,JLabel levelLabel) {
        this.levelName = levelName;
        this.counter = countFromFile;
        this.counter+= 1;


        levelLabel.setText(levelName);

        timer = new Timer();

        TimerTask task = new TimerTask(){
            public void run(){
                if (counter > 0) {
                    counter--;
                    timeLabel.setText(String.valueOf(counter));
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec
    }

    public void terminateTimer(){
        timer.cancel();
    }
}