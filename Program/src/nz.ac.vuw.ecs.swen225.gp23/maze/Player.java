package nz.ac.vuw.ecs.swen225.gp23.maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {

    private Tile currentTile;
    private List<String> inventory = new ArrayList<>();
    private Map<Tile.Directions, String> directionImages = new HashMap<>();
    private int chips = 0; //how many chips the player has picked up

    public Player(Tile location){
        currentTile = location;

        directionImages.put(Tile.Directions.Left, "chip_left.png");
        directionImages.put(Tile.Directions.Right, "chip_right.png");
        directionImages.put(Tile.Directions.Up, "chip_up.png");
        directionImages.put(Tile.Directions.Down, "chip_down.png");
    }

    //Inventory methods
    public boolean checkItem(String item){return inventory.contains(item);}
    public void addItem(String item){inventory.add(item);}
    public void removeItem(String item){inventory.remove(item);}

    public void pickUpChip(){chips++;}

    //Getters and Setters
    public int getChips(){return chips;}
    public void setChips(int numberOfChips){chips = numberOfChips;}

    public List<String> getInventory(){return inventory;}
    public void setInventory(List<String> newInventory){inventory = newInventory;}

    public Tile getCurrentTile(){return currentTile;}
    public void setCurrentTile(Tile newTile){currentTile = newTile;}
}
