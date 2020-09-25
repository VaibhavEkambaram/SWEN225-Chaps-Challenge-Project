package nz.ac.vuw.ecs.swen225.gp23.maze;

import nz.ac.vuw.ecs.swen225.gp23.application.Game;

import java.util.List;

public class Board {
    private int boardDimension = 9; //height and width of board (in tiles)

    private Tile[][] tilesXY = new Tile[boardDimension][boardDimension];

    private Game game;

    public Board(Game game){
        this.game = game;
    }

    //TODO:JSON level loading
    //TODO: Make a setup method to populate the board based on JSON levels

    //Getters and setters
    public void setTile(int x, int y,  Tile t){
        t.setXLoc(x);
        t.setYLoc(y);
        tilesXY[x][y] = t;
    }

    public Tile getTile(int x, int y){
        if (x >= boardDimension || y >= boardDimension) {
            return null;
        }
        if (x < 0 || y < 0) {
            return null;
        }
        return tilesXY[x][y];
    }

    public Tile getPlayerLoc() {
        for (int x = 0; x < boardDimension; x++) {
            for (int y = 0; y < boardDimension; y++) {
                if (tilesXY[x][y].getImage().startsWith("chap")) {
                    return tilesXY[x][y];
                }
            }
        }
        return null;
    }

    public Tile[][] getTilesXY(){ return this.tilesXY;}
    public void setTilesXY(Tile[][] newTilesXY){
        this.tilesXY = newTilesXY;
    }

    public int getBoardDimension(){return this.boardDimension;}
    public void setBoardDimension(int newBoardDimension){
        this.boardDimension = newBoardDimension;
    }

}
