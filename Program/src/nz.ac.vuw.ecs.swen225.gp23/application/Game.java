package nz.ac.vuw.ecs.swen225.gp23.application;

import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.Player;
import nz.ac.vuw.ecs.swen225.gp23.render.RenderPanel;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private Board board;
    private Player player;

    private int levelNumber;
    private int countdownTimer;

    private boolean gamePaused;

    private Timer timer;
    GraphicalInterface gui;






    public Game(int countFromFile,int levelNumber, GraphicalInterface gui) {
        this.countdownTimer = (countFromFile + 1);
        this.gamePaused = false;
        this.gui = gui;
        this.levelNumber = levelNumber;
        gui.getLevelLabel().setText(String.valueOf(levelNumber));

        initBoardRenderer();
        runTimer();
    }


    public void initBoardRenderer(){
        RenderPanel boardRenderPanel = new RenderPanel(9, 9);

        String[][] board = new String[9][9];

        gui.setRenderPanel(boardRenderPanel);

        board[0][0] = "floor";

        boardRenderPanel.setBoard(board);
    }


    /**
     * Execute countdown timer.
     * This timer controls the countdown of the game and updates the GUI time label accordingly. This countdown also
     * accounts for the game being paused, with the countdown only continuing when un-paused.
     * This function operates in an asynchronous manner to the main game interface, so is able to operate independently
     * of graphics redraw calls.
     */
    public void runTimer() {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                // increment timer down if there is still time remaining and the game has not been paused
                if (countdownTimer > 0 && !gamePaused) {
                    countdownTimer--;
                    // update gui time label
                    gui.getTimeLabel().setText(String.valueOf(countdownTimer));
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

    public void setTimeLeft(int countdownTimer) {
        this.countdownTimer = countdownTimer;
    }

    public int getLevelNumber(){
        return board.getCurrentLevel();
    }

    public void setLevelNumber(int number){
        this.levelNumber = number;
    }

}
