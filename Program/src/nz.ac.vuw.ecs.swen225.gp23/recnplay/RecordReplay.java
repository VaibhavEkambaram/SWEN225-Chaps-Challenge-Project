package nz.ac.vuw.ecs.swen225.gp23.recnplay;

import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp23.persistence.readWrite;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This class is responsible for recording and replaying the gameplay.
 * Each movement of Chap is recorded and saved to allow for a replay of a level.
 *
 * @author Tyla Turner - 300473374
 */
public class RecordReplay {

    private static ArrayList<Tile.Directions> movements = new ArrayList<>();
    private static ArrayList<Integer> actors = new ArrayList<>();
    private static String saveFile;
    private static String state;

    private static boolean isGameRecording;
    private static boolean isGameRunning;

    private static long delay = 100;

    static Thread thread;

    public static void newSave(Game g, String s){
        saveFile = s;
        isGameRecording = true;
        movements.clear();
        state = readWrite.getGameState(g);
    }

    public static void addMoves(Tile.Directions d){
        if (isGameRecording){
            movements.add(d);
            actors.add(0);
        }
    }

    public static void saveRecording(Game g)  {
        if (isGameRecording){
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
            isGameRecording = false;
        }
    }

    public static void endRecording(){
        isGameRecording = false;
        isGameRunning = false;
        saveFile = null;
        movements.clear();
        actors.clear();
        state = null;
        thread = null;
    }

    public static void storeActorMove(Tile.Directions d, int id){
        if(isGameRecording){
            movements.add(d);
            actors.add(id);
        }
    }

    //=============================================================================================


    public static void loadRecord(String saveFile, Game g){
        JsonObject obj = null;

        try{
            readWrite.loadStateFromJsonFIle(saveFile, g);
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

            //JsonArray movesArray = obj
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }


















    public static void setDelay(long d){
        delay = d;
    }

    public static boolean getIsGameRunning(){
        return isGameRunning;
    }











    public static boolean getIsRunning(){
        return isGameRunning;
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
