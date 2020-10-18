package nz.ac.vuw.ecs.swen225.gp23.persistence;

import com.google.gson.Gson;
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
import java.util.Map;
import java.util.Scanner;

public class Persistence {

    int boardX;
    int boardY;
    int level;
    int timeLeft;
    Game game;
    Board board;


    public Persistence(Game game) {
        this.game = game;
    }

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
                }
            }

            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return board;
    }



    public static void saveFile(Game game, String fileName) {

        String jGame = gameState(game);

        try {
            Writer writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(jGame);
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to save game");
        }
    }

    public static String gameState(Game game) {
        String jBoard;
        String jPlayer;
        String jGame;

        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("level", game.getLevelNumber())
                .add("timeLeft", game.getTimeLeft())
                .add("boardx", game.getBoard().getBoardHeight())
                .add("boardy", game.getBoard().getBoardWidth())
                .add("board", game.getBoard().toString());

        try (Writer writer = new StringWriter()) {
            Json.createWriter(writer).write(builder.build());
            jGame = writer.toString();
        } catch (IOException e) {
            throw new Error("Failed to parse game");
        }
        return jGame;


    }

    public Board readBoard(String maze) {
        Board board = new Board(game, boardX, boardY);

        Scanner sc = new Scanner(maze).useDelimiter(",");
        int xValue = 0;
        int yValue = 0;

        while (sc.hasNext()) {

            System.out.print("|");
            // System.out.print(sc.next());

            String value = sc.next();
            System.out.printf(value);

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

                default:
                    board.setTile(xValue, yValue, new Empty());
                    break;
            }
            xValue++;

            // increment one row down
            if (xValue == boardX) {
                System.out.print("|\n");
                xValue = 0;
                yValue++;
            }
        }
        return board;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public int getLevel() {
        return level;
    }
}


