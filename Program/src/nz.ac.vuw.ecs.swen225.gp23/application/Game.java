package nz.ac.vuw.ecs.swen225.gp23.application;

import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.ComputerChip;
import nz.ac.vuw.ecs.swen225.gp23.maze.Exit;
import nz.ac.vuw.ecs.swen225.gp23.maze.Key;
import nz.ac.vuw.ecs.swen225.gp23.maze.LockedDoor;
import nz.ac.vuw.ecs.swen225.gp23.maze.Player;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;
import nz.ac.vuw.ecs.swen225.gp23.recnplay.RecordReplay;
import nz.ac.vuw.ecs.swen225.gp23.render.ChipAudioModule;
import nz.ac.vuw.ecs.swen225.gp23.render.RenderPanel;

import java.awt.*;
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
    private final int levelNumber;
    private boolean gamePaused;
    private final int timeToComplete;
    private final int tileset;

    final ChipAudioModule audio;


    /**
     * Game Constructor
     *
     * @param countFromFile countdown duration
     * @param levelNumber   level number
     * @param gui           gui class
     * @param board         board class
     */
    public Game(int countFromFile, int levelNumber, GraphicalInterface gui, Board board, ChipAudioModule audio, int tileset) {
        this.board = board;
        this.countdownTimer = (countFromFile + 1);
        this.timeToComplete = countFromFile;
        this.gamePaused = false;
        this.gui = gui;
        this.audio = audio;
        this.levelNumber = levelNumber;
        this.tileset = tileset;
        gui.updateInventory();
        gui.getLevelLabel().setText(String.valueOf(levelNumber));
        board.setup();
        this.player = new Player(board.getPlayerLoc());
        initBoardRenderer();
        runTimer();

        gui.setChipsLeftLabel(board.getChipCount());
    }


    /**
     * Initialise the board renderer.
     */
    public void initBoardRenderer() {
        boardRenderPanel = new RenderPanel(9, 9, tileset);
        gui.setRenderPanel(boardRenderPanel);
        boardRenderPanel.setBoard(board);
    }

    /**
     * Create string representation of board
     *
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
                    if (countdownTimer <= 15 && countdownTimer > 10) {
                        gui.getTimeLabel().setForeground(Color.YELLOW);
                    } else if (countdownTimer <= 10 && countdownTimer > 5) {
                        gui.getTimeLabel().setForeground(Color.ORANGE);
                    } else if (countdownTimer <= 5) {
                        gui.getTimeLabel().setForeground(Color.RED);
                    } else {
                        gui.getTimeLabel().setForeground(Color.WHITE);
                    }
                    gui.setTimeLabel(countdownTimer);
                } else if (!gamePaused) {
                    timer.cancel();
                    gui.outOfTime();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec
    }


    /**
     * Move player around the board depending on direction passed in from graphical interface.
     *
     * @param direction north/south/east/west direction
     */
    public void onMovement(Tile.Directions direction) {

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
            audio.moveEffect();
            currentLoc.setEntityAbsent();
            nextLoc.setEntityPresent(player.getImage(direction));
            player.setCurrentTile(nextLoc);
        } else {
            currentLoc.setEntityPresent(player.getImage(direction));
        }

        Tile currentTile = player.getCurrentTile();

        if (currentTile instanceof ComputerChip) {
            gui.setChipsLeftLabel(board.getChipCount() - player.getChips());
        } else if (currentTile instanceof Exit) {
            gui.levelCompleteMessage(levelNumber, countdownTimer, timeToComplete - countdownTimer, board.getChipCount());
        } else if (currentTile instanceof Key || currentTile instanceof LockedDoor) {
            gui.updateInventory();
        }
        boardRenderPanel.setBoard(board);
    }


    public void saveGame() {
        Persistence p = new Persistence(this);
        System.out.println("Level Number: " + levelNumber);
        System.out.println("Time Remaining: " + timeToComplete);
        System.out.println("Items Remaining: " +(board.getChipCount() - player.getChips()));
        System.out.println();
        System.out.println("Inventory");
        for(String s : player.getInventory()){
            System.out.println("\t"+s);
        }
        System.out.println();
        System.out.println("Board");
        System.out.println(board.toString());
        Persistence.saveFile(this,"savedgame.json");

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
