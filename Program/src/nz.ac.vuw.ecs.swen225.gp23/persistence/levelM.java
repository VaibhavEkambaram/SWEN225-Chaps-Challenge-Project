package nz.ac.vuw.ecs.swen225.gp23.persistence;

import java.io.File;
import java.util.*;

public class levelM {
    private static ArrayList<String> lDesc = new ArrayList<>();
    public static int currentLevel = 0;
    @SuppressWarnings("rawtypes")
    static Set<Class> cSet = new HashSet<>();



    public static void loadLevel(assetManager aManager){
        File[] folder = new File("src/levels/").listFiles();
        List<File> files = new ArrayList<>();
        if(folder == null){
            return;
        }
        for(File f : folder){
            files.add(f);
        }
        Collections.sort(files);
        for(File f : files){

        }
    }
}
