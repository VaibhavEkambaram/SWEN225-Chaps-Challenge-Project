package nz.ac.vuw.ecs.swen225.gp23.persistence;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.ComputerChip;
import nz.ac.vuw.ecs.swen225.gp23.maze.Empty;
import nz.ac.vuw.ecs.swen225.gp23.maze.Exit;
import nz.ac.vuw.ecs.swen225.gp23.maze.ExitLock;
import nz.ac.vuw.ecs.swen225.gp23.maze.Floor;
import nz.ac.vuw.ecs.swen225.gp23.maze.Hint;
import nz.ac.vuw.ecs.swen225.gp23.maze.Key;
import nz.ac.vuw.ecs.swen225.gp23.maze.LockedDoor;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp23.maze.Wall;


import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class for Persistence reading and writing
 *
 * @author Rahul Mahasuriya 300473482
 */
public class Persistence {

    public static int boardX;
    public static int boardY;
    int level;
    int timeLeft;
    String inventory;
    public static Game game;
    Board board;


    public Persistence() {
    }

    /**
     * Loads json file
     * Uses Gson to convert Json
     * Checks for correct text input for board, level and time
     * Applies these to the assigned variables
     *
     * @param filepath
     * @return the board
     * @author Rahul Mahasuriya
     */
    public Board loadFile(String filepath) {

        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get(filepath));

            Map<?, ?> map = gson.fromJson(reader, Map.class);

            for (Map.Entry<?, ?> entry : map.entrySet()) {

                if (entry.getKey().equals("board")) {
                    board = readBoard(entry.getValue().toString());
                } else if (entry.getKey().equals("boardx")) {
                    boardX = (int) (double) entry.getValue();
                } else if (entry.getKey().equals("boardy")) {
                    boardY = (int) (double) entry.getValue();
                } else if (entry.getKey().equals("level")) {
                    level = (int) (double) entry.getValue();
                } else if (entry.getKey().equals("timeLeft")) {
                    timeLeft = (int) (double) entry.getValue();
                } else if (entry.getKey().equals("inventory")) {
                    inventory = (String) entry.getValue();
                }
            }

            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return board;
    }


    /**
     * Saves file
     * Uses gameState method
     * Using a writer, a for loop will check through the special characters in the board json file
     * writes in these special characters between the tile characters
     *
     * @param game
     * @param fileName
     * @author Rahul Mahasuriya
     */
    public static void saveFile(Game game, String fileName) {

        String jGame = gameState(game);

        try {
            Writer writer = new BufferedWriter(new FileWriter(fileName));

            for (int i = 0; i < jGame.length(); i++) {
                char next = jGame.charAt(i);
                if (next == ',' || next == '{') writer.write(next + "\n\t");
                else if (next == '}') writer.write("\n" + next);
                else writer.write(next);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves current game state for the level
     * Builds a json schema
     * Assigns variables from current game
     * Writes these variables to a file in saveFile
     *
     * @param game
     * @return schema format
     * @author Rahul Mahasuriya
     */
    public static String gameState(Game game) {
        String jGame;

        StringBuilder inventoryString = new StringBuilder();
        for (String s : game.getPlayer().getInventory()) {
            inventoryString.append(s + "|");
        }


        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("level", game.getLevelNumber())
                .add("timeLeft", game.getTimeLeft())
                .add("boardx", game.getBoard().getBoardWidth())
                .add("boardy", game.getBoard().getBoardHeight())
                .add("inventory", inventoryString.toString())
                .add("board", game.getBoard().toString());


        try (
                Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(builder.build());
            jGame = writer.toString();
        } catch (
                IOException e) {
            throw new Error("Failed to parse game");
        }
        return jGame;


    }

    /**
     * Reads board from json
     * Uses a Scanner for reading characters
     * Switch case is used for distinguishing between different characters from the board string
     * Sets tile depending on what string is read
     *
     * @param maze
     * @return the board
     * @author Rahul Mahasuriya
     */
    public static Board readBoard(String maze) {
        Board board = new Board(boardX, boardY);

        Scanner sc = new Scanner(maze).useDelimiter("\\|");
        int xValue = 0;
        int yValue = 0;

        while (sc.hasNext()) {

            String value = sc.next();


            switch (value) {
                case "B":
                    board.setTile(xValue, yValue, new LockedDoor("blue"));
                    break;
                case "G":
                    board.setTile(xValue, yValue, new LockedDoor("green"));
                    break;
                case "R":
                    board.setTile(xValue, yValue, new LockedDoor("red"));
                    break;
                case "Y":
                    board.setTile(xValue, yValue, new LockedDoor("yellow"));
                    break;
                case "g":
                    board.setTile(xValue, yValue, new Key("green"));
                    break;
                case "b":
                    board.setTile(xValue, yValue, new Key("blue"));
                    break;
                case "y":
                    board.setTile(xValue, yValue, new Key("yellow"));
                    break;
                case "r":
                    board.setTile(xValue, yValue, new Key("red"));
                    break;
                case "_":
                    board.setTile(xValue, yValue, new Floor());
                    break;
                case "#":
                    board.setTile(xValue, yValue, new Wall());
                    break;
                case "i":
                    board.setTile(xValue, yValue, new Hint());
                    break;
                case "T":
                    board.setTile(xValue, yValue, new ComputerChip());
                    break;
                case "l":
                    board.setTile(xValue, yValue, new ExitLock());
                    break;
                case "E":
                    board.setTile(xValue, yValue, new Exit());
                    break;
                case "P":
                    Tile playerStart = new Floor();
                    playerStart.setEntityPresent("chip_down.png");
                    board.setTile(xValue, yValue, playerStart);
                    break;
                case "h": //horizontal cyclops
                    Tile cyclopsStart = new Floor();
                    cyclopsStart.setEntityPresent("cyclops_right.png");
                    cyclopsStart.setEntityDirection(Tile.Directions.Right);
                    board.setTile(xValue, yValue, cyclopsStart);
                    break;
                default:
                    board.setTile(xValue, yValue, new Empty());
                    break;
            }
            xValue++;

            // increment one row down
            if (xValue == boardX) {
                xValue = 0;
                yValue++;
            }
        }
        return board;
    }

    /**
     * Sets inventory for the player
     * @return list of players inventory
     * @author Rahul Mahasuriya
     */
    public List<String> setInventory() {

        String[] array = inventory.split("\\|");

        List<String> inventoryList = new ArrayList<>();

        if (inventory.length() > 0) {
            inventoryList.addAll(Arrays.asList(array));
        }
        return inventoryList;
    }


    /**
     * Gets the current time left in the game
     *
     * @return the current time remaining
     * @author Rahul Mahasuriya
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Gets the current level
     *
     * @return the current level
     * @author Rahul Mahasuriya
     */
    public int getLevel() {
        return level;
    }
}


