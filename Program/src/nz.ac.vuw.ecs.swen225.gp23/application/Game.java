package nz.ac.vuw.ecs.swen225.gp23.application;

import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.Player;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Board board;
    private Player player;

    private int levelNumber;
    private int countdownTimer;

    private boolean gamePaused;

    private Timer timer;


    public Game(int countFromFile, int chipsLeft, String levelName, JLabel timeLabel, JLabel levelLabel, JLabel chipsLeftLabel, GraphicalInterface gui) {
        this.countdownTimer = (countFromFile + 1);
        this.gamePaused = false;

        levelLabel.setText(levelName);

        runTimer(timeLabel);
    }


    /**
     * Execute countdown timer.
     * This timer controls the countdown of the game and updates the GUI time label accordingly. This countdown also
     * accounts for the game being paused, with the countdown only continuing when un-paused.
     * This function operates in an asynchronous manner to the main game interface, so is able to operate independently
     * of graphics redraw calls.
     */
    public void runTimer(JLabel timeLabel) {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                // increment timer down if there is still time remaining and the game has not been paused
                if (countdownTimer > 0 && !gamePaused) {
                    countdownTimer--;
                    // update gui time label
                    timeLabel.setText(String.valueOf(countdownTimer));
                } else if (!gamePaused) {
                    timer.cancel();
                    System.out.println("time is up!");
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec
    }


    public int getTimeLeft() {
        return countdownTimer;
    }

    public void terminateTimer() {
        timer.cancel();
    }

    public void setGamePaused(boolean value) {
        gamePaused = value;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return player;
    }
}
