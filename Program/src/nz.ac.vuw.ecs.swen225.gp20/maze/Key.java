package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Key extends Tile {
    private String colour;
    private boolean pickedUp = false;

    public Key(String colour){
        super(Tiles.Key);
        this.isPassable = true;
        this.colour = colour;
        this.image = "key_" + colour + ".png";
    }

    @Override
    public boolean action(Player p) {
       if(!pickedUp){
           p.addItem(this.toString());
           image = "empty.png";
           pickedUp = true;
       }
       return isPassable;
    }

    @Override
    public String toString() {return "key_"+colour;}

    //Getters and Setters
    public String getColour(){return colour;}
    public void setColour(String newColour){colour = newColour;}

    public boolean getPickedUp(){return pickedUp;}
    public void setPickedUp(boolean isPickedUp){pickedUp = isPickedUp;}
}
