package nz.ac.vuw.ecs.swen225.gp23.persistence;

import com.google.common.io.Files;
import com.google.gson.Gson;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.*;

import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

/**
 * Level Manager for making level transitions
 * loads saved level with use of loadFile method
 * @author Rahul Mahasuriya
 */
public class LevelM {


  HashMap<Integer, String> levels = new HashMap<>();
  HashMap<String, Integer> currentLevelInteger = new HashMap<>();
  int currentLevel = 1;

  public LevelM() {
    File folder = new File("levels");
    File[] listOfFiles = folder.listFiles();

    assert listOfFiles != null;
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        if (Files.getFileExtension(listOfFiles[i].getName()).equals("json")) {
          levels.put(i + 1, listOfFiles[i].getName());
          currentLevelInteger.put(listOfFiles[i].getName(), i + 1);
        }
      }
    }
    loadFile("ChapsChallenge_PersistenceLevel.json");
  }

  /**
   * Helper method for loading saved file upon game exit
   * @param filepath file for loading
   * @author Rahul Mahasuriya
   */
  public void loadFile(String filepath) {

    try {
      Gson gson = new Gson();

      Reader reader = java.nio.file.Files.newBufferedReader(Paths.get(filepath));

      Map<?, ?> map = gson.fromJson(reader, Map.class);

      for (Map.Entry<?, ?> entry : map.entrySet()) {

        if (entry.getKey().equals("currentLevel")) {
          currentLevel = currentLevelInteger.get(entry.getValue().toString());
        }
      }

      reader.close();
    } catch (Exception ex) {
      saveLevel();
    }

  }

  /**
   * Saves level to a json file of current game when exiting
   * @author Rahul Mahasuriya
   */
  public void saveLevel() {
    String jGame;
    String levelURL = levels.get(currentLevel);

    JsonObjectBuilder builder = Json.createObjectBuilder()
        .add("currentLevel", levelURL);
    try (
        Writer writer = new StringWriter()) {
      Json.createWriter(writer).write(builder.build());
      jGame = writer.toString();
    } catch (
        IOException e) {
      throw new Error("Failed to parse current level");
    }

    try {
      Writer writer = new FileWriter("ChapsChallenge_PersistenceLevel.json");

      for (int i = 0; i < jGame.length(); i++) {
        char next = jGame.charAt(i);
        switch (next) {
          case ',':
          case '{':
            writer.write(next + "\n\t");
            break;
          case '}':
            writer.write("\n" + next);
            break;
          default:
            writer.write(next);
            break;
        }
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets level for game
   * @param value Sets value of current level
   * @author Rahul Mahasuriya
   */
  public void setLevel(int value) { currentLevel = value; }


  /**
   * Method for incrementing level
   * @author Rahul Mahasuriya
   */
  public void incrementLevel() {
    currentLevel++;
  }

  /**
   * Gets current level
   * @return the current level
   * @author Rahul Mahasuriya
   */
  public String getCurrentLevel() {
    return levels.get(currentLevel);
  }

  /**
   * Checks maximum state of the level
   * @return boolean for if this is true
   * @author Rahul Mahasuriya
   */
  public boolean checkMaximumState(){
    return (currentLevel == levels.size()+1);
  }






}
