package nz.ac.vuw.ecs.swen225.gp23.maze;

public class LockedDoor extends Tile {
    private String colour;
    private boolean locked = true;


    public LockedDoor(String colour){
        super(Tiles.LockedDoor);
        this.isPassable = false;
        this.colour = colour;
        this.image = "door_key_" + colour + ".png";
    }

    @Override
    public boolean action(Player p) {
        if(!locked){
            return true;
        }
        if(p.checkItem("key_" + colour)){
            isPassable = true;
            image = "empty.png";
            p.removeItem("key_" + colour);
            locked = false;
        }
        return isPassable;
    }

    @Override
    public String toString() {return "door_key_" + colour;}

    //Getters and Setters
    public String getColour(){return colour;}
    public void setColour(String newColour){colour = newColour;}

    public Boolean getLocked(){return locked;}
    public void setLocked(Boolean isLocked){locked = isLocked;}
}
