package nz.ac.vuw.ecs.swen225.gp23.persistence;

import com.google.common.io.Files;
import com.google.gson.Gson;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.*;

import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;


public class LevelM {


    HashMap<Integer, String> levels = new HashMap<>();
    HashMap<String, Integer> currentLevelInteger = new HashMap<>();
    int currentLevel = 1;


    public void setLevel(int value) { currentLevel = value; }


    public void incrementLevel() {
        currentLevel++;
    }

    public String getCurrentLevel() {
        return levels.get(currentLevel);
    }






}
