package nz.ac.vuw.ecs.swen225.gp20.maze;

import nz.ac.vuw.ecs.swen225.gp20.application.Game;

import java.util.List;

public class Board {
    private int boardDimension = 9; //height and width of board (in tiles)
    private int chipCount = 0; //number of computer chips on the board

    private List<Tile> everyTile;
    private Tile[][] tilesXY = new Tile[boardDimension][boardDimension];

    private Game game;

    public Board(Game game){
        this.game = game;
    }



}
