package nz.ac.vuw.ecs.swen225.gp23.application;

import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.Player;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp23.render.RenderPanel;
import nz.ac.vuw.ecs.swen225.gp23.persistence.assetManager;
import nz.ac.vuw.ecs.swen225.gp23.persistence.levelM;
import nz.ac.vuw.ecs.swen225.gp23.persistence.readWrite;


import java.io.File;
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


    public Game(int countFromFile,int levelNumber, GraphicalInterface gui, Board board) {
        this.board = board;
        this.countdownTimer = (countFromFile + 1);
        this.gamePaused = false;
        this.gui = gui;
        this.levelNumber = levelNumber;
        gui.getLevelLabel().setText(String.valueOf(levelNumber));
        initBoardRenderer();
        runTimer();
    }



    public void initBoardRenderer(){
        assetManager aM = new assetManager();
        levelM.load(aM);
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();



        RenderPanel boardRenderPanel = new RenderPanel(boardHeight, boardWidth);
         gui.setRenderPanel(boardRenderPanel);



        String[][] tempBoard = new String[boardHeight][boardWidth];


        for(int i=0; i < boardHeight; i++){
            for(int j=0; j < boardWidth; j++){
                tempBoard[i][j] = "empty";
            }
        }


         for(int i=0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                System.out.print("|"+board.getTile(j,i).toString()+"|");
                tempBoard[i][j] = board.getTile(j,i).toString();
             }
            System.out.println();
         }


        //  for(int i=0; i < 15; i++){
        //     for(int j=0; j < 14; j++){
        //    if(board.getTile(i,j)!=null) {
        //       tempBoard[i][j] = board.getTile(i, j).toString();
        //System.out.println(board.getTile(i, j).toString());
        //    }
        //  }
        //   }

        boardRenderPanel.setBoard(tempBoard);
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


    public void onMovement(Tile.Directions direction){
        System.out.println(direction.toString());
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
