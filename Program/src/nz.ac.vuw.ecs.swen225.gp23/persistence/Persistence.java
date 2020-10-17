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


import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class Persistence {

    int boardX;
    int boardY;
    int level;
    int timeLeft;
    String tileset;
    Game game;
    Board board;


    public Persistence(Game game) {
        this.game = game;
    }

    public Board loadFile() {

        try {
            Gson gson = new Gson();

            Reader reader = Files.newBufferedReader(Paths.get("Program/src/levels/level1.json"));

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
                } else if (entry.getKey().equals("tileset")) {
                    tileset = (String) entry.getValue();
                }
            }

            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return board;
    }

    public Board readBoard(String maze) {
        Board board = new Board(game, boardX, boardY);

        Scanner sc = new Scanner(maze).useDelimiter(",");
        int xValue = 0;
        int yValue = 0;

        while (sc.hasNext()) {

            String value = sc.next();

            switch (value) {
                case "B":
                    board.setTile(xValue, yValue, new LockedDoor("blue", tileset));
                    break;
                case "G":
                    board.setTile(xValue, yValue, new LockedDoor("green", tileset));
                    break;
                case "R":
                    board.setTile(xValue, yValue, new LockedDoor("red", tileset));
                    break;
                case "Y":
                    board.setTile(xValue, yValue, new LockedDoor("yellow", tileset));
                    break;
                case "g":
                    board.setTile(xValue, yValue, new Key("green", tileset));
                    break;
                case "b":
                    board.setTile(xValue, yValue, new Key("blue", tileset));
                    break;
                case "y":
                    board.setTile(xValue, yValue, new Key("yellow", tileset));
                    break;
                case "r":
                    board.setTile(xValue, yValue, new Key("red", tileset));
                    break;
                case "_":
                    board.setTile(xValue, yValue, new Floor(tileset));
                    break;
                case "#":
                    board.setTile(xValue, yValue, new Wall(tileset));
                    break;
                case "i":
                    board.setTile(xValue, yValue, new Hint(tileset));
                    break;
                case "T":
                    board.setTile(xValue, yValue, new ComputerChip(tileset));
                    break;
                case "l":
                    board.setTile(xValue, yValue, new ExitLock(tileset));
                    break;
                case "E":
                    board.setTile(xValue, yValue, new Exit(tileset));
                    break;
                case "P":
                    Tile playerStart = new Floor(tileset);
                    playerStart.setEntityPresent("chip_down.png");
                    board.setTile(xValue,yValue, playerStart);
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

    public int getTimeLeft(){
        return timeLeft;
    }

    public int getLevel(){
        return level;
    }
}

