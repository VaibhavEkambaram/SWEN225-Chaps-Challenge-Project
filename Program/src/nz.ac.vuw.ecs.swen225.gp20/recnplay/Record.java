package nz.ac.vuw.ecs.swen225.gp20.recnplay;


import nz.ac.vuw.ecs.swen225.gp20.application.Game;
import nz.ac.vuw.ecs.swen225.gp20.maze.Tile;

import java.util.ArrayList;

/**
 * This class is responsible for recording and replaying the gameplay.
 * Each movement of Chap is recorded and saved to allow for a replay of a level.
 *
 * @author Tyla Turner - 300473374
 */
public class Record {

    private static ArrayList<Tile.Directions> movements = new ArrayList<>();
    private static String saveFile;
    private static String state;

    private static boolean isRecording;
    private static boolean isRunning;

    public static void newSave(Game g, String s){
        
    }
    public static boolean getIsRunning(){
        return isRunning;
    }
}
