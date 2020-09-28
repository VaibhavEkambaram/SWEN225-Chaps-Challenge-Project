package nz.ac.vuw.ecs.swen225.gp23.persistence;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.*;
import java.util.*;

public class levelM {
    private static ArrayList<String> lDesc = new ArrayList<>();
    public static int currentLevel = 0;
    @SuppressWarnings("rawtypes")
    static Set<Class> cSet = new HashSet<>();



    public static void load(assetManager aManager) throws FileNotFoundException {
        File[] folder = new File("src/levels/").listFiles();
        List<File> files = new ArrayList<>();
        if(folder == null){
            return;
        }
        for(File f : folder){
            files.add(f);
        }

        JsonParser jsonParser = Json.createParser(new StringReader("[]"));


        Collections.sort(files);
        for(File f : files){
            try(FileReader r = new FileReader(f)){

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int getIntOfCurrentLevel(){
        return currentLevel;
    }

}
