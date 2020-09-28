package nz.ac.vuw.ecs.swen225.gp23.persistence;

import nz.ac.vuw.ecs.swen225.gp23.application.Game;
import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.Player;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.*;

/**
 * Class for reading and writing from JSON files
 * @author Rahul Mahasuriya 300473482
 */

public class readWrite {

    public static String getGameState(Game game){
        String Game;
        String Board;
        String Player;

        Board b = game.getBoard();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(Tile[] t : b.getTilesXY()){
            arrayBuilder.add(t.);
        }

        JsonObjectBuilder builder = Json.createObjectBuilder().add("boardSize", b.getBoardDimension()).add("allTiles", arrayBuilder);

        try (Writer w = new StringWriter()){
            Json.createWriter(w).write(builder.build());
            Board = w.toString();

        } catch (IOException e){
            throw new Error("Failed to parse Board");
        }

        Player p = game.getPlayer();
        arrayBuilder = Json.createArrayBuilder();

        for(String l : p.getInventory()){
            arrayBuilder.add(l);
        }

       builder = Json.createObjectBuilder().add("location", p.getCurrentTile().getJson())
             .add("inventory", arrayBuilder)
             .add("treasures", p.getChips());

        try(Writer w = new StringWriter()){
            Json.createWriter(w).write(builder.build());
            Player = w.toString();
        } catch (IOException e){
            throw new Error("Failed");
        }

        arrayBuilder = Json.createArrayBuilder();

      builder = Json.createObjectBuilder().add("level", game.getLevel())
                .add("timeLeft", game.getTimeLeft())
                .add("board", Board)
                .add("player", Player)
                .add("mobs", arrayBuilder);

        try(Writer w = new StringWriter()){
            Json.createWriter(w).write(builder.build());
            Game = w.toString();
        } catch (IOException e){
            throw new Error("Failed");
        }
        return Game;
    }

    public static void saveState(Game game, String n){

    }

    public static Game loadStateFromJsonFIle(String fn, Game game)
        throws IOException{
            InputStream r = new FileInputStream(new File(fn));
            InputStreamReader iR = new InputStreamReader(r);
            BufferedReader bR = new BufferedReader(iR);
            String line = bR.readLine();
            if(line == null){
                line = "";
            }
            Game g = loadStateFromJsonFIle(line, game);
            iR.close();
            bR.close();
            return g;

    }

   public static Game loadState(String save, Game game){
        return null;
    }

    public static Tile createTile(String t){
        return null;
    }
}