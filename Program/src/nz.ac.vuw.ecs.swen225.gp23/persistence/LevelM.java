package nz.ac.vuw.ecs.swen225.gp23.persistence;
import java.io.*;

import java.util.*;


public class LevelM {
    public static int currentLevel = 0;

    static Set<Class> cSet = new HashSet<>();



    public static void loadLevel(){
        File[] folder = new File("src/levels/").listFiles();
        List<File> files = new ArrayList<>();
        if(folder == null){
            return;
        }
        for(File f : folder){
            files.add(f);
        }

    }

    public static int getIntOfCurrentLevel(){
        return currentLevel;
    }

}
