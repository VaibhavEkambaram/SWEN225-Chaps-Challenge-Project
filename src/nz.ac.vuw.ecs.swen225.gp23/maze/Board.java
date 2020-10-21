package nz.ac.vuw.ecs.swen225.gp23.maze;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * This class represents the board of the game.
 * The board stores every tile in the current level
 *
 * @author Baxter Kirikiri - 300472553
 */
public class Board {
  private int boardHorizontal;
  private int boardVertical;
  private Tile[][] allTiles;
  private int chipCount = 0;

  /**
   * Constructor for the board class.
   *
   * @param x - The horizontal size of the board in tiles (int)
   * @param y - The vertical size of the board in tile (int)
   */
  public Board(int x, int y) {
    this.boardHorizontal = x;
    this.boardVertical = y;
    this.allTiles = new Tile[boardHorizontal][boardVertical];
  }

  /**
   * Board setup.
   * Sets up tile adjacency and the exit lock required chips.
   */
  public void setup() {
    setAdjacentTiles();
    setExitLock();
  }

  /**
   * Sets up tile adjacency so that for each tile,
   * its adjacent tiles are associated with the directions in Tile.Directions.
   */
  private void setAdjacentTiles() {
    for (int x = 0; x < boardHorizontal; x++) {
      for (int y = 0; y < boardVertical; y++) {
        Tile t = allTiles[x][y];
        int leftOrd = Tile.Directions.Left.ordinal();
        t.adjacentTiles.add(leftOrd, x != 0 ? allTiles[x - 1][y] : new Wall());
        int rightOrd = Tile.Directions.Right.ordinal();
        t.adjacentTiles.add(rightOrd, x != boardHorizontal - 1 ? allTiles[x + 1][y] : new Wall());
        int upOrd = Tile.Directions.Up.ordinal();
        t.adjacentTiles.add(upOrd, y != 0 ? allTiles[x][y - 1] : new Wall());
        int downOrd = Tile.Directions.Down.ordinal();
        t.adjacentTiles.add(downOrd, y != boardVertical - 1 ? allTiles[x][y + 1] : new Wall());
      }
    }
  }

  /**
   * Checks for chips on the board and counts them.
   * Then checks for exit locks on the board.
   * If an exit lock exists, setExitLock() sets the number
   * of chips on the board as the required number of chips for the exit lock.
   */
  private void setExitLock() {
    int exitX = boardHorizontal + 1;
    int exitY = boardVertical + 1;
    for (int x = 0; x < boardHorizontal; x++) {
      for (int y = 0; y < boardVertical; y++) {
        if (allTiles[x][y].getCurrentImage().startsWith("computer_chip")) {
          chipCount++;
        }
        if (allTiles[x][y].getCurrentImage().startsWith("exit_lock")) {
          exitX = x;
          exitY = y;
        }
      }
    }
    if (exitX != boardHorizontal + 1) {
      ExitLock lock = (ExitLock) allTiles[exitX][exitY];
      lock.setChipsNeeded(chipCount);
      allTiles[exitX][exitY] = lock;
    }
  }

  /**
   * Replaces a tile in the array of all tiles on the board.
   *
   * @param x - the horizontal index of the tile to be set (int)
   * @param y - the vertical index of the tile to be set (int)
   * @param t - the new tile to be set at tilesXY[x][y] (Tile)
   */
  public void setTile(int x, int y, Tile t) {
    t.setHorizLoc(x);
    t.setVertLoc(y);
    allTiles[x][y] = t;
  }

  /**
   * Gets a tile from the array of tiles.
   *
   * @param x - the horizontal index of the tile to be retrieved (int)
   * @param y - the vertical index of the tile to be retrieved (int)
   * @return Tile - the tile at the given indexes (Tile)
   */
  public Tile getTile(int x, int y) {
    if (x >= boardHorizontal || y >= boardVertical) {
      return null;
    }
    if (x < 0 || y < 0) {
      return null;
    }
    return allTiles[x][y];
  }

  /**
   * Gets the location of the player.
   *
   * @return Tile - Tile the player is currently on. Null if the player not found (Tile)
   */
  public Tile getPlayerLoc() {
    for (int x = 0; x < boardHorizontal; x++) {
      for (int y = 0; y < boardVertical; y++) {
        if (allTiles[x][y].getCurrentImage().startsWith("chip")) {
          return allTiles[x][y];
        }
      }
    }
    return null;
  }

  /**
   * Gets all the cyclops locations in the current level.
   *
   * @return cyclops - the tiles occupied by cyclops
   */
  public ArrayList<Tile> getCyclopsLoc() {
    ArrayList<Tile> cyclops = new ArrayList<>();
    for (int x = 0; x < boardHorizontal; x++) {
      for (int y = 0; y < boardVertical; y++) {
        if (allTiles[x][y].getCurrentImage().startsWith("cyclops")) {
          cyclops.add(allTiles[x][y]);
        }
      }
    }
    return cyclops;
  }

  /**
   * Converts the board into string representation in the same format as persistence.
   *
   * @return boardString - a string of characters representing the board that can be saved (String)
   */
  @Override
  public String toString() {
    String boardString;
    StringBuilder cases = new StringBuilder();
    Queue<Tile> tileQueue = new ArrayDeque<>();
    for (int y = 0; y < boardVertical; y++) {
      for (int x = 0; x < boardHorizontal; x++) {
        tileQueue.add(this.getTile(x, y));
      }
    }

    while (!tileQueue.isEmpty()) {
      String tileName = tileQueue.peek().toString();
      switch (tileName) {
        case "door_blue":
          cases.append("B|");
          tileQueue.poll();
          break;
        case "door_green":
          cases.append("G|");
          tileQueue.poll();
          break;
        case "door_red":
          cases.append("R|");
          tileQueue.poll();
          break;
        case "door_yellow":
          cases.append("Y|");
          tileQueue.poll();
          break;
        case "key_green":
          cases.append("g|");
          tileQueue.poll();
          break;
        case "key_blue":
          cases.append("b|");
          tileQueue.poll();
          break;
        case "key_yellow":
          cases.append("y|");
          tileQueue.poll();
          break;
        case "key_red":
          cases.append("r|");
          tileQueue.poll();
          break;
        case "floor":
          cases.append("_|");
          tileQueue.poll();
          break;
        case "wall":
          cases.append("#|");
          tileQueue.poll();
          break;
        case "hint":
          cases.append("i|");
          tileQueue.poll();
          break;
        case "computer_chip":
          cases.append("T|");
          tileQueue.poll();
          break;
        case "exit_lock":
          cases.append("l|");
          tileQueue.poll();
          break;
        case "exit":
          cases.append("E|");
          tileQueue.poll();
          break;
        default:
          if (tileName.startsWith("chip")) {
            cases.append("P|");
          } else {
            cases.append("h|");
          }
          tileQueue.poll();
          break;
      }
    }

    boardString = cases.toString();
    return boardString.substring(0, boardString.length() - 1);
  }

  /**
   * Getter for the number of chips on the board at the time of loading.
   *
   * @return chipCount - the number of chips on the board at the time of loading (int).
   */
  public int getChipCount() {
    return this.chipCount;
  }

  /**
   * Getter for the horizontal dimension of the board.
   *
   * @return boardXDimension - used for looping through the tilesXY[][] array (int)
   */
  public int getBoardWidth() {
    return boardHorizontal;
  }


  /**
   * Getter for the vertical dimension of the board.
   *
   * @return boardYDimension - used for looping through the tilesXY[][] array (int)
   */
  public int getBoardHeight() {
    return boardVertical;
  }
}
