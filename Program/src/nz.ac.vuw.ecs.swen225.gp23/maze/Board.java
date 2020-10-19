package nz.ac.vuw.ecs.swen225.gp23.maze;

import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.persistence.LevelM;

import java.util.ArrayDeque;
import java.util.Queue;

public class Board {
    int boardXDimension;
    int boardYDimension;


    private Tile[][] tilesXY;
    private int chipCount = 0;

    public Board(int x,int y){
        this.boardXDimension = x;
        this.boardYDimension = y;
        this.tilesXY = new Tile[boardXDimension][boardYDimension];
    }

    public void setup(){
        setAdjacentTiles();
        setExitLock();
    }


    private void setAdjacentTiles(){
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

    private void setExitLock(){
        int exitX = boardXDimension + 1;
        int exitY = boardYDimension + 1;
        for (int x = 0; x < boardXDimension; x++) {
            for (int y = 0; y < boardYDimension; y++) {
                if(tilesXY[x][y].getCurrentImage().startsWith("computer_chip")){
                    chipCount++;
                }
                if (tilesXY[x][y].getCurrentImage().startsWith("exit_lock")) {
                    exitX = x;
                    exitY = y;
                }
            }
        }
        if(exitX != boardXDimension + 1){
            ExitLock lock = (ExitLock) tilesXY[exitX][exitY];
            lock.setChipsNeeded(chipCount);
            tilesXY[exitX][exitY] = lock;
        }
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

    public Tile getCyclopsLoc(){
        for (int x = 0; x < boardXDimension; x++) {
            for (int y = 0; y < boardYDimension; y++) {
                if (tilesXY[x][y].getCurrentImage().startsWith("cyclops")) {
                    return tilesXY[x][y];
                }
            }
        }
        return null;
    }

    @Override
    public String toString(){
        String boardString;
        StringBuilder cases = new StringBuilder();
        Queue<Tile> tileStack = new ArrayDeque<>();
        for (int y = 0; y < boardYDimension; y++) {
            for (int x = 0; x < boardXDimension; x++) {
                tileStack.add(this.getTile(x,y));
            }
        }

        while(!tileStack.isEmpty()){
            String tileName = tileStack.peek().toString();
            switch (tileName) {
                case "door_blue":
                    cases.append("B|");
                    tileStack.poll();
                    break;
                case "door_green":
                    cases.append("G|");
                    tileStack.poll();
                    break;
                case "door_red":
                    cases.append("R|");
                    tileStack.poll();
                    break;
                case "door_yellow":
                    cases.append("Y|");
                    tileStack.poll();
                    break;
                case "key_green":
                    cases.append("g|");
                    tileStack.poll();
                    break;
                case "key_blue":
                    cases.append("b|");
                    tileStack.poll();
                    break;
                case "key_yellow":
                    cases.append("y|");
                    tileStack.poll();
                    break;
                case "key_red":
                    cases.append("r|");
                    tileStack.poll();
                    break;
                case "floor":
                    cases.append("_|");
                    tileStack.poll();
                    break;
                case "wall":
                    cases.append("#|");
                    tileStack.poll();
                    break;
                case "hint":
                    cases.append("i|");
                    tileStack.poll();
                    break;
                case "computer_chip":
                    cases.append("T|");
                    tileStack.poll();
                    break;
                case "exit_lock":
                    cases.append("l|");
                    tileStack.poll();
                    break;
                case "exit":
                    cases.append("E|");
                    tileStack.poll();
                    break;
                default:
                    cases.append("P|");
                    tileStack.poll();
                    break;
            }
        }

        boardString = cases.toString();
        return boardString.substring(0, boardString.length()-1);
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

    public int getCurrentLevel(){return LevelM.getIntOfCurrentLevel();}
}
