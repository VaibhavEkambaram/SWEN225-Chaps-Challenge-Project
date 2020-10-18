package nz.ac.vuw.ecs.swen225.gp23.maze;

import nz.ac.vuw.ecs.swen225.gp23.render.RenderPanel;

import java.util.TimerTask;

public class CyclopsMoveTask extends TimerTask {
    Cyclops cyclops;
    Board board;
    RenderPanel boardRender;

    CyclopsMoveTask(Cyclops c, Board b, RenderPanel br){
        this.cyclops = c;
        this.board = b;
        this.boardRender = br;
    }
    @Override
    public void run() {
        cyclops.moveCyclops();
        boardRender.setBoard(board);
    }
}
