package nz.ac.vuw.ecs.swen225.gp20.persistence;

import nz.ac.vuw.ecs.swen225.gp20.application.Game;
import nz.ac.vuw.ecs.swen225.gp20.maze.Board;
import nz.ac.vuw.ecs.swen225.gp20.maze.Player;
import nz.ac.vuw.ecs.swen225.gp20.maze.Tile;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonPatch;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class readWrite {

    public static String getGameState(Game game){
        String Game;
        String Board;
        String Player;

        Board b = game.getBoard();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(Tile t : ){}

        JsonObjectBuilder builder = Json.createObjectBuilder().add("boardSize", );

        try (Writer w = new StringWriter()){
            Json.createWriter(w).write();
            Board = w.toString();

        } catch (IOException e){
            throw new Error("Failed to parse Board");
        }

        Player p = game.getPlayer();
        arrayBuilder = Json.createArrayBuilder();

    }

    public static void saveState(Game game, String n){

    }

    public static Game loadStateFromJsonFIle(String fn, Game game){

    }

    public static Tile createTile(String t){

    }
}
