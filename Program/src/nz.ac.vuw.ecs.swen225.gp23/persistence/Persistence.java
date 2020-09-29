package nz.ac.vuw.ecs.swen225.gp23.persistence;

import com.google.gson.Gson;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.maze.*;


import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class Persistence {

    int boardX;
    int boardY;
    Game game;
    Board board;


    public Persistence(Game game){
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
                }
            }

            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println(boardX + " " + boardY);
        return board;
    }

    public Board readBoard(String maze) {
        Board board = new Board(game,boardX,boardY);

        Scanner sc = new Scanner(maze).useDelimiter(",");
        int xValue = 0;
        int yValue = 0;

        while (sc.hasNext()) {

            System.out.print("|");
           // System.out.print(sc.next());

            String value = sc.next();
            System.out.printf(value);

            switch (value) {

                case "_":
                    board.setTile(xValue,yValue,new Wall());
                    break;
                case "#":
                    board.setTile(xValue,yValue,new Empty());
                    break;
                case "i":
                    board.setTile(xValue, yValue, new Hint());
                    break;
                case "T":
                    board.setTile(xValue, yValue, new ComputerChip());
                    break;
                case "E":
                    board.setTile(xValue,yValue,new Exit());
                    break;
                default:
                    board.setTile(xValue,yValue,new Empty());
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
}


