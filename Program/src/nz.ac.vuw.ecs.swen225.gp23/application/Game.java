package nz.ac.vuw.ecs.swen225.gp23.application;

import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.Player;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp23.recnplay.RecordReplay;
import nz.ac.vuw.ecs.swen225.gp23.render.RenderPanel;
import nz.ac.vuw.ecs.swen225.gp23.persistence.assetManager;
import nz.ac.vuw.ecs.swen225.gp23.persistence.levelM;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Game Class
 * Controls and stores information required for a level to function
 *
 * @author Vaibhav Ekambaram - 300472561
 */
public class Game {

    private RenderPanel boardRenderPanel;
    private final GraphicalInterface gui;
    private final Board board;
    private final Player player;

    private Timer timer;

    private int countdownTimer;
    private boolean gamePaused;


    /**
     * Game Constructor
     *
     * @param countFromFile countdown duration
     * @param levelNumber   level number
     * @param gui           gui class
     * @param board         board class
     */
    public Game(int countFromFile, int levelNumber, GraphicalInterface gui, Board board) {
        this.board = board;
        this.countdownTimer = (countFromFile + 1);
        this.gamePaused = false;
        this.gui = gui;
        gui.getLevelLabel().setText(String.valueOf(levelNumber));
        board.setup();
        this.player = new Player(board.getPlayerLoc());
        initBoardRenderer();
        runTimer();
    }


    /**
     * Initialise the board renderer.
     */
    public void initBoardRenderer() {
        assetManager aM = new assetManager();
        levelM.load(aM);
        boardRenderPanel = new RenderPanel(board.getBoardHeight(), board.getBoardWidth());
        gui.setRenderPanel(boardRenderPanel);
        board.redraw(boardRenderPanel);
    }

    /**
     * Create string representation of board
     * @return string
     */
    public String printOutBoard() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < board.getBoardHeight(); i++) {
            if (i != 0) {
                text.append("\n");
            }
            for (int j = 0; j < board.getBoardWidth(); j++) {
                text.append(board.getTile(j, i).toString()).append("|");
            }
        }

        return text.toString();
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
                    gui.outOfTime();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec
    }


    /**
     * Move player around the board depending on direction passed in from graphical interface
     *
     * @param direction north/south/east/west direction
     */
    public void onMovement(Tile.Directions direction) {
        if (gamePaused) {
            return;
        }

        Tile currentLoc = player.getCurrentTile();
        Tile nextLoc;

        switch (direction) {
            case Left:
                nextLoc = currentLoc.getDirection(Tile.Directions.Left);
                RecordReplay.addMoves(Tile.Directions.Left);
                break;
            case Right:
                nextLoc = currentLoc.getDirection(Tile.Directions.Right);
                RecordReplay.addMoves(Tile.Directions.Right);
                break;
            case Up:
                nextLoc = currentLoc.getDirection(Tile.Directions.Up);
                RecordReplay.addMoves(Tile.Directions.Up);
                break;
            case Down:
                nextLoc = currentLoc.getDirection(Tile.Directions.Down);
                RecordReplay.addMoves(Tile.Directions.Down);
                break;
            default:
                nextLoc = null;
        }

        if (nextLoc.action(player)) {
            currentLoc.setEntityAbsent();
            nextLoc.setEntityPresent(player.getImage(direction));
            player.setCurrentTile(nextLoc);
            board.redraw(boardRenderPanel);
        } else {
            currentLoc.setEntityPresent(player.getImage(direction));
        }

    }

    /**
     * Get the amount of time left in the countdown.
     *
     * @return timer integer
     */
    public int getTimeLeft() {
        return countdownTimer;
    }

    /**
     * Terminate the countdown timer
     */
    public void terminateTimer() {
        timer.cancel();
    }

    /**
     * Set paused value
     *
     * @param value game paused value
     */
    public void setGamePaused(boolean value) {
        gamePaused = value;
    }

    /**
     * Get the current board
     *
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Get the current player
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set the amount of time left
     *
     * @param countdownTimer new timer value
     */
    public void setTimeLeft(int countdownTimer) {
        this.countdownTimer = countdownTimer;
    }

    /**
     * Retrieve the level number
     *
     * @return level number
     */
    public int getLevelNumber() {
        return board.getCurrentLevel();
    }
}
