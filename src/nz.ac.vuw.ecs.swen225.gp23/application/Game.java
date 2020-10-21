package nz.ac.vuw.ecs.swen225.gp23.application;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.ComputerChip;
import nz.ac.vuw.ecs.swen225.gp23.maze.Cyclops;
import nz.ac.vuw.ecs.swen225.gp23.maze.Exit;
import nz.ac.vuw.ecs.swen225.gp23.maze.Hint;
import nz.ac.vuw.ecs.swen225.gp23.maze.Key;
import nz.ac.vuw.ecs.swen225.gp23.maze.LockedDoor;
import nz.ac.vuw.ecs.swen225.gp23.maze.Player;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;
import nz.ac.vuw.ecs.swen225.gp23.recnplay.RecordReplay;
import nz.ac.vuw.ecs.swen225.gp23.render.ChipAudioModule;


/**
 * Game Class.
 * Controls and stores information required for a level to function
 *
 * @author Vaibhav Ekambaram - 300472561
 */
public class Game {

  // Internal Classes
  private final Application application;
  private final GraphicalInterface gui;

  // External Classes
  private final Board board;
  private final Player player;
  private final ChipAudioModule audio;

  // Game Information Store
  private final int levelNumber;
  private int countdownTimer;
  private final int timeToComplete;

  ArrayList<Cyclops> cyclops = new ArrayList<>(); // store cyclops entities

  private Timer timer;

  private boolean gamePaused;
  private boolean isRunningTest = false;


  /**
   * Game Constructor.
   *
   * @param countFromFile countdown duration
   * @param levelNumber   level number
   * @param gui           gui class
   * @param board         board class
   * @param audio         audio module
   * @param application   keep track of game state
   */
  public Game(int countFromFile,
              int levelNumber,
              GraphicalInterface gui,
              Board board,
              ChipAudioModule audio,
              Application application) {
    this.application = application;
    this.board = board;
    this.countdownTimer = (countFromFile + 1);
    this.timeToComplete = countFromFile;
    this.gamePaused = false;
    this.gui = gui;
    this.audio = audio;
    this.levelNumber = levelNumber;
    gui.updateInventory();
    gui.getLevelLabel().setText(String.valueOf(levelNumber));
    board.setup();
    this.player = new Player(board.getPlayerLoc());

    // add cyclops enemies
    if (board.getCyclopsLoc().size() >= 1) {
      for (Tile t : board.getCyclopsLoc()) {
        cyclops.add(new Cyclops(t, Tile.Directions.Right));
      }
    }

    // start timer
    runTimer();
    gui.setChipsLeftLabel(board.getChipCount());
  }

  /**
   * Create string representation of board.
   * THIS HAS BEEN DEPRECIATED, USE BOARD TO-STRING INSTEAD
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
   * This timer controls the countdown of the game and updates the GUI time label accordingly.
   * This countdown also accounts for the game being paused, with the countdown only continuing
   * when un-paused.
   * This function operates in an asynchronous manner to the main game interface,
   * so is able to operate independently
   * of graphics redraw calls.
   */
  public void runTimer() {
    timer = new Timer();
    TimerTask task = new TimerTask() {
      public void run() {
        // increment timer down if there is still time remaining and the game has not been paused
        if (countdownTimer > 0 && !gamePaused) {
          countdownTimer--;
          if (cyclops.size() >= 1) {
            for (Cyclops c : cyclops) {
              c.moveCyclops(gui);
            }
          }
          // update gui time label with colour depending on value
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
          gui.getRenderPanel().setBoard(board);
        } else if (!gamePaused) {
          timer.cancel();
          // call out of time message to quit game or restart level
          gui.outOfTime("Out of Time", "Oh no! You have run out of time.");
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
    if (application.getState().equals(Application.GameStates.RUNNING) && !gamePaused) {
      Tile currentLoc = player.getCurrentTile();
      Tile nextLoc;

      // determine movement direction
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
        if (!isRunningTest) {
          // if move is valid then play feedback sound
          audio.moveEffect();
        }
        currentLoc.setEntityAbsent();
        nextLoc.setEntityPresent(player.getImage(direction));
        player.setCurrentTile(nextLoc);
      } else {
        currentLoc.setEntityPresent(player.getImage(direction));
      }

      Tile currentTile = player.getCurrentTile();

      if (currentTile.hasEntity) {

        gui.onStopGame(false);

        if (!isRunningTest) {
          gui.outOfTime("You died", "Oh no! You were caught by a cyclops.");
        }
      }
      if (currentTile instanceof ComputerChip) {
        // update counter when player picks up a chip
        gui.setChipsLeftLabel(board.getChipCount() - player.getChips());
      } else if (currentTile instanceof Hint) {
        // show help menu if player visits the help tile
        if (!isRunningTest) {
          gui.helpMenuContents();
        }
      } else if (currentTile instanceof Exit) {
        if (RecordReplay.getIsGameRecording()) {
          RecordReplay.saveRecording(this);
        }
        gui.levelCompleteMessage(
            levelNumber, countdownTimer, timeToComplete - countdownTimer, board.getChipCount());
      } else if (currentTile instanceof Key || currentTile instanceof LockedDoor) {
        // update inventory if player picks up item
        gui.updateInventory();
      }
      gui.getRenderPanel().setBoard(board);
    }
  }


  /**
   * Save current game by calling persistence.
   */
  public void saveGame() {
    SimpleDateFormat ts = new SimpleDateFormat("dd-MM-yyyy'_'HH-mm-ss");
    Date date = new Date(System.currentTimeMillis());
    // save file using timestamp as a file name
    Persistence.saveFile(this, "ChapsChallenge_SaveFile_" + ts.format(date) + ".json");
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
   * Terminate the countdown timer.
   */
  public void terminateTimer() {
    timer.cancel();
  }

  /**
   * Set paused value.
   *
   * @param value game paused value
   */
  public void setGamePaused(boolean value) {
    gamePaused = value;
  }

  /**
   * Get the current board.
   *
   * @return board
   */
  public Board getBoard() {
    return board;
  }

  /**
   * Get the current player.
   *
   * @return player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Set the amount of time left.
   *
   * @param countdownTimer new timer value
   */
  public void setTimeLeft(int countdownTimer) {
    this.countdownTimer = countdownTimer;
  }

  /**
   * Retrieve the level number.
   *
   * @return level number
   */
  public int getLevelNumber() {
    return levelNumber;
  }

  /**
   * Check if current game is paused.
   *
   * @return boolean validator
   */
  public boolean isPaused() {
    return gamePaused;
  }

  /**
   * Disable certain items when running a test.
   *
   * @param value boolean value
   */
  public void isRunningTest(boolean value) {
    isRunningTest = value;
  }

  /**
   * Getter Method for isRunningTest Boolean.
   *
   * @return boolean value
   */
  public boolean getRunningTest() {
    return isRunningTest;
  }

}
