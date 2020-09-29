package nz.ac.vuw.ecs.swen225.gp23.maze;

import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.persistence.levelM;
import nz.ac.vuw.ecs.swen225.gp23.persistence.readWrite;
import nz.ac.vuw.ecs.swen225.gp23.render.RenderPanel;

public class Board {
    private int viewSize = 9; //the number of tiles in view

    int boardXDimension;
    int boardYDimension;


    private Tile[][] tilesXY;
    private Game game;
    private int chipCount = 0;

    public Board(Game game,int x,int y){
        this.game = game;
        this.boardXDimension = x;
        this.boardYDimension = y;
        this.tilesXY = new Tile[boardXDimension][boardYDimension];
    }

    public void setAdjacentTiles(){
       for(int x = 0; x < boardXDimension; x++){
           for(int y = 0; y < boardYDimension; y++){
               Tile t = tilesXY[x][y];
               int leftOrd = Tile.Directions.Left.ordinal();
               t.adjacentTiles.add(leftOrd, x != 0 ? tilesXY[x - 1][y] : new Wall());
               int rightOrd = Tile.Directions.Right.ordinal();
               t.adjacentTiles.add(rightOrd, x != boardXDimension - 1 ? tilesXY[x + 1][y] : new Wall());
               int upOrd = Tile.Directions.Up.ordinal();
               t.adjacentTiles.add(upOrd, y != 0 ? tilesXY[x][y - 1] : new Wall());
               int downOrd = Tile.Directions.Down.ordinal();
               t.adjacentTiles.add(downOrd, y != boardYDimension - 1 ? tilesXY[x][y+1] : new Wall());
           }
       }
    }

    //Getters and setters
    public String[][] getBoardString(Tile currentTile){
        String[][] boardString = new String[viewSize][viewSize];
        for(int x = currentTile.getXLoc() - viewSize/2; x <= currentTile.getXLoc() + viewSize/2; ++x){
            for(int y = currentTile.getYLoc() - viewSize/2; y <= currentTile.getYLoc() + viewSize/2; ++y) {
                if (x < 0 || y < 0 || x >= boardXDimension || y >= boardYDimension) {
                    boardString[x][y] = new Empty().toString();
                } else {
                    boardString[x][y] = tilesXY[x][y].toString();
                }
            }
        }
        return boardString;
    }

    public void setTile(int x, int y,  Tile t){
        t.setXLoc(x);
        t.setYLoc(y);
        tilesXY[x][y] = t;
    }

    public Tile getTile(int x, int y){
        if (x >= boardXDimension || y >= boardYDimension) {
            return null;
        }
        if (x < 0 || y < 0) {
            return null;
        }
        return tilesXY[x][y];
    }

    public Tile getPlayerLoc() {
        for (int x = 0; x < boardXDimension; x++) {
            for (int y = 0; y < boardYDimension; y++) {
                if (tilesXY[x][y].getCurrentImage().startsWith("chip")) {
                    return tilesXY[x][y];
                }
            }
        }
        return null;
    }

    public void redraw(RenderPanel boardRenderpanel){
        String[][] tempBoard = new String[boardYDimension][boardXDimension];


        for (int i = 0; i < boardYDimension; i++) {
            for (int j = 0; j < boardXDimension; j++) {
                tempBoard[i][j] = this.getTile(j, i).toString();
            }
        }


        boardRenderpanel.setBoard(tempBoard);
        boardRenderpanel.repaint();
    }

    public Tile[][] getTilesXY(){ return this.tilesXY;}
    public void setTilesXY(Tile[][] newTilesXY){
        this.tilesXY = newTilesXY;
    }

    public int getBoardDimension(){return this.boardXDimension;}

    public void setBoardDimension(int newBoardDimension){
        this.boardXDimension = newBoardDimension;
    }

    public int getChipCount(){return this.chipCount;}
    public void setChipCount(int newCount){
        this.chipCount = newCount;
    }


    public int getBoardWidth(){
        return boardXDimension;
    }

    public int getBoardHeight(){
        return boardYDimension;
    }

    public int getCurrentLevel(){return levelM.getIntOfCurrentLevel();}
}
