package nz.ac.vuw.ecs.swen225.gp23.persistence;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

public class levelM {
    private static ArrayList<String> lDesc = new ArrayList<>();
    public static int currentLevel = 0;
    @SuppressWarnings("rawtypes")
    static Set<Class> cSet = new HashSet<>();



    public static void load(assetManager aManager){
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
