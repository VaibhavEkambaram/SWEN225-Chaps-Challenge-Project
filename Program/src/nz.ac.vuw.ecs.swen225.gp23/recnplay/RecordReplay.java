package nz.ac.vuw.ecs.swen225.gp23.recnplay;

import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;

import javax.json.*;
import java.io.*;
import java.util.ArrayList;

/**
 * This class is responsible for recording and replaying the gameplay.
 * Each movement of Chap + actors (level 2) is recorded and saved to allow for a replay of a level.
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
        //state = readWrite.getGameState(g);
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

            //was getting nullpointer here
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder().add("movements", a).add("timeLeft", g.getTimeLeft());

            //add("game", state)

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
        state = null;
        thread = null;


    }

    /**
     * Adds the movements and ID of the actor (level 2) to arraylists.
     *
     * @param d - direction of actor.
     * @param id - ID of actor.
     */
    public static void storeActorMove(Tile.Directions d, int id){
        if(isGameRecording){
            movements.add(d);
            actors.add(id);
        }
    }

    //=============================================================================================
    //                                        REPLAY
    //=============================================================================================


    /**
     * Loads a recording from a specified file into the Game.
     *
     * @param saveFile - file name.
     * @param g - instance of game.
     */
    public static void loadRecord(String saveFile, Game g){
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

                //might need to change to a switch statement
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

        //UPDATE BOARD

    }

    /**
     * Iterates through the replay one movement at a time until there are no movements left to replay.
     *
     * @param g - instance of game.
     */
    public static void iterateReplay(Game g){

        //add try catch for out of bounds?

        //if there game is running and there are moves to replay
        if(isGameRunning && movements.size() > 0){
            if(actors.get(0) == 0){
               //move the player, need to call move method here parsing in movements.get(0)
                g.onMovement(movements.get(0));
                //remove the first iteration
                movements.remove(0);
                actors.remove(0);
            } else {
                //move the actor
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
            //update board

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
     * Gets the status of whether the game is running.
     * @return - isGameRunning.
     */
    public static boolean getIsGameRunning(){
        return isGameRunning;
    }

    /**
     * Returns the arraylist of movements.
     * @return - movements.
     */
    public static ArrayList<Tile.Directions> getMovements() {
        return movements;
    }

    /**
     * Returns the arraylist of actor IDs.
     * @return - actors.
     */
    public static ArrayList<Integer> getActors() {
        return actors;
    }

    /**
     * Returns the saveFile string name.
     * @return - saveFile.
     */
    public static String getSaveFile() {
        return saveFile;
    }

    /**
     * Returns the current state of the game.
     * @return - state.
     */
    public static String getState() {
        return state;
    }

    //Returns the Thread replay.
    public static Thread getThread() {
        return thread;
    }
}
