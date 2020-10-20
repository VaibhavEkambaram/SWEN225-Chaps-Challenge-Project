package nz.ac.vuw.ecs.swen225.gp23.recnplay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.application.GraphicalInterface;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;



/**
 * This class is responsible for recording and replaying the game play.
 * Each movement of Chap + cyclops (level 2) is recorded and saved to allow for a replay of a level.
 *
 * @author Tyla Turner - 300473374
 */
public class RecordReplay {

    private static ArrayList<Tile.Directions> movements = new ArrayList<>();
    private static ArrayList<Integer> actors = new ArrayList<>();
    private static String saveFile;
    private static String persistenceSave;

    private static boolean isGameRecording;
    private static boolean isGameRunning;

    private static long delay = 100;
    private static int timeLeft;

    static Thread thread;

    /**
     * Creates a new save file of recording.
     *
     * @param g - instance of game.
     * @param s - name of save file.
     */
    public static void newSave(Game g, String s){
        saveFile = s;
        isGameRecording = true;
        movements.clear();

        SimpleDateFormat ts = new SimpleDateFormat("dd-MM-yyyy'_'HH-mm-ss");
        Date date = new Date(System.currentTimeMillis());
        persistenceSave = "ChapsChallenge_SaveFile_" + ts.format(date) + ".json";
        Persistence.saveFile(g, persistenceSave);
    }

    /**
     * Add the movements of the player to an arraylist.
     *
     * @param d - direction of player movement.
     */
    public static void addMoves(Tile.Directions d){
        if (isGameRecording){
            movements.add(d);
            actors.add(0);
        }
    }

    /**
     * Saves the movements to a JSON file.
     *
     * @param g - instance of game.
     */
    public static void saveRecording(Game g)  {
        if (isGameRecording){
            JsonArrayBuilder a = Json.createArrayBuilder();

            for (int i = 0; i < actors.size(); i++){
                JsonObjectBuilder o = Json.createObjectBuilder().add("actor", actors.get(i)).add("movement", movements.get(i).toString());
                a.add(o.build());
            }

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder().add("save", persistenceSave).add("movements", a).add("timeLeft", g.getTimeLeft());

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
            isGameRecording = false;
            System.out.println("Recorded Movements: " + movements);
        }
    }

    /**
     *  Ends the recording of the game.
     */
    public static void endRecording(){
        isGameRecording = false;
        isGameRunning = false;
        saveFile = null;
        movements.clear();
        actors.clear();
        thread = null;


    }

   //todo: ADD LEVEL 2 STORE CYCLOPS MOVE HERE

    //=============================================================================================
    //                                        REPLAY
    //=============================================================================================


    /**
     * Loads a recording from a specified file into the Game.
     *
     * @param saveFile - file name.
     * @param gui - instance of gui.
     */
    public static void loadRecord(String saveFile, GraphicalInterface gui){
        JsonObject obj = null;

        movements.clear();
        actors.clear();

        try {
            BufferedReader br = new BufferedReader(new FileReader(saveFile));
            JsonReader jr = Json.createReader(new StringReader(br.readLine()));
            br.close();
            obj = jr.readObject();
        } catch (IOException e) {
            System.out.println("There was an error reading from JSON file." + e);
        }

        JsonArray movesArray;
        if (obj != null) {
            movesArray = obj.getJsonArray("movements");
        }
        else {
            movesArray = null;
        }

        if(movesArray != null){
            for(int i = 0; i < movesArray.size(); i++){
                JsonObject object = movesArray.getJsonObject(i);
                String dir = object.getString("movement");
                int actorID = object.getInt("actor");
                actors.add(actorID);

                if ("Up".equals(dir)) {
                    movements.add(Tile.Directions.Up);
                } else if ("Down".equals(dir)) {
                    movements.add(Tile.Directions.Down);
                } else if ("Left".equals(dir)) {
                    movements.add(Tile.Directions.Left);
                } else if ("Right".equals(dir)) {
                    movements.add(Tile.Directions.Right);
                }
            }
        }

        if (obj != null) {
            persistenceSave = obj.getString("save");
        }

        if (movements.size() > 0){
            isGameRunning = true;
        }
        if (obj != null) {
            timeLeft = obj.getInt("timeLeft");
        }

        else {
            timeLeft = 0;
        }

        System.out.println("Movements: " + movements);
        System.out.println("ActorID: " + actors);
        System.out.println("save name: " + persistenceSave);
        gui.onLoadGameNoGui(persistenceSave);

    }

    /**
     * Iterates through the replay one movement at a time until there are no movements left to replay.
     *
     * @param g - instance of game.
     */
    public static void iterateReplay(Game g){

        //if there game is running and there are moves to replay
        if(isGameRunning && movements.size() > 0){
            if(actors.get(0) == 0){
                g.onMovement(movements.get(0));
                movements.remove(0);
                actors.remove(0);
            } else {
                //TODO: move the actor
                movements.remove(0);
                actors.remove(0);
                if (movements.size() > 0){
                    iterateReplay(g);
                }
            }

            if(movements.size() == 0){
                isGameRunning = false;
                g.setTimeLeft(timeLeft);
            }
        }
    }

    /**
     * Uses a thread to go through the replay at a certain speed/delay.
     *
     * @param g - instance of game.
     */
    public static void runReplay(Game g){
        Runnable run = () -> {
            while(isGameRunning && movements.size() > 0){
                try{
                    if (actors.size() > 0 && actors.get(0) == 0){
                        Thread.sleep(delay);
                    }
                    iterateReplay(g);
                } catch(InterruptedException e){
                    System.out.println(e.getMessage());
                }
            }
            isGameRunning = false;
            g.setTimeLeft(timeLeft);
        };
        thread = new Thread(run);
        thread.start();
    }

    /**
     * Set the delay of the replay.
     *
     * @param d - delay.
     */
    public static void setDelay(long d){
        delay = d;
    }

    /**
     * Returns whether the game is being recorded or not.
     * @return - isGameRecording
     */
    public static boolean getIsGameRecording(){
        return isGameRecording;
    }

}
