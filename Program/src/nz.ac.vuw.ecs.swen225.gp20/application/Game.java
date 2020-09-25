package nz.ac.vuw.ecs.swen225.gp20.application;

import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Player;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {


    public enum GameState {
        PREGAME, RUNNING, PAUSED
    }





    private int counter;
    private Timer timer;
    boolean gamePaused = false;

    Board board;
    Player player;




    public Game(int countFromFile, int chipsLeft, String levelName, JLabel timeLabel, JLabel levelLabel, JLabel chipsLeftLabel) {
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
                } else if (!gamePaused) {
                    timer.cancel();
                    System.out.println("time is up!");
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

    public Board getBoard(){
        return board;
    }

    public Player getPlayer(){
        return player;
    }
}