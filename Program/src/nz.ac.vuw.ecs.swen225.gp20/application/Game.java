package nz.ac.vuw.ecs.swen225.gp20.application;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private String levelName;
    private int counter;
    private Timer timer;
    boolean gamePaused = false;

    public Game(int countFromFile, int chipsLeft, String levelName, JLabel timeLabel, JLabel levelLabel, JLabel chipsLeftLabel) {
        this.levelName = levelName;
        this.counter = countFromFile;
        this.counter += 1;


        levelLabel.setText(levelName);

        timer = new Timer();

        TimerTask task = new TimerTask() {
            public void run() {
                if (counter > 0 && !gamePaused) {
                    chipsLeftLabel.setText(String.valueOf(chipsLeft));
                    counter--;
                    timeLabel.setText(String.valueOf(counter));
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec
    }

    public int getTimeLeft(){
        return counter;
    }

    public void terminateTimer() {
        timer.cancel();
    }

    public void setGamePaused(boolean value) {
        gamePaused = value;
    }
}