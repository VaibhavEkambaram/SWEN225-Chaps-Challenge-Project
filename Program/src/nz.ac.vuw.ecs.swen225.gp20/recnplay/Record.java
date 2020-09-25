package nz.ac.vuw.ecs.swen225.gp20.recnplay;

import nz.ac.vuw.ecs.swen225.gp20.application.Game;
import nz.ac.vuw.ecs.swen225.gp20.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp20.persistence.readWrite;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.*;
import java.util.ArrayList;

/**
 * This class is responsible for recording and replaying the gameplay.
 * Each movement of Chap is recorded and saved to allow for a replay of a level.
 *
 * @author Tyla Turner - 300473374
 */
public class Record {

    private static ArrayList<Tile.Directions> movements = new ArrayList<>();
    private static ArrayList<Integer> actors = new ArrayList<>();
    private static String saveFile;
    private static String state;

    private static boolean isRecording;
    private static boolean isRunning;

    static Thread thread;

    public static void newSave(Game g, String s){
        saveFile = s;
        isRecording = true;
        movements.clear();
        state = readWrite.getGameState(g);
    }

    public static void addMoves(Tile.Directions d){
        if (isRecording){
            movements.add(d);
            actors.add(0);
        }
    }

    public static void saveRecording(Game g)  {
        if (isRecording){
            JsonArrayBuilder a = Json.createArrayBuilder();

            for (int i = 0; i < actors.size(); i++){
                JsonObjectBuilder o = Json.createObjectBuilder().add("count", actors.get(i)).add("movement", movements.get(i).toString());
                a.add(o.build());
            }

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder().add("game", state).add("movements", a).add("timeLeft", g.getTimeLeft());

            //Attempt to save the moves to a JSON file
            try(Writer writer = new StringWriter()){
                Json.createWriter(writer).write(objectBuilder.build());
                try {
                    BufferedWriter buffWriter = new BufferedWriter(new FileWriter(saveFile));
                    buffWriter.write(writer.toString());
                    buffWriter.close();
                }
                catch (IOException e){
                    throw new Error("Movements were unable to save." + e);
                }
            }
            catch(IOException e){
                throw new Error("Movements were unable to save." + e);
            }
            isRecording = false;
        }
    }

    public static void endRecording(){
        isRecording = false;
        isRunning = false;
        saveFile = null;
        movements.clear();
        actors.clear();
        state = null;
        thread = null;
    }

    public static void storeActorMove(Tile.Directions d, int id){
        if(isRecording){
            movements.add(d);
            actors.add(id);
        }
    }
    public static boolean getIsRunning(){
        return isRunning;
    }

    public static ArrayList<Tile.Directions> getMovements() {
        return movements;
    }

    public static ArrayList<Integer> getActors() {
        return actors;
    }

    public static String getSaveFile() {
        return saveFile;
    }

    public static String getState() {
        return state;
    }

    public static Thread getThread() {
        return thread;
    }
}
