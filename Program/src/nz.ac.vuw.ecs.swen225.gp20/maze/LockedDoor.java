package nz.ac.vuw.ecs.swen225.gp20.maze;

public class LockedDoor extends Tile {
    private String colour;

    public LockedDoor(String colour){
        super(Tiles.LockedDoor);
        this.isPassable = false;
        this.colour = colour;
        this.image = "locked_door_" + colour + ".png";
    }

    @Override
    public boolean action(Player p) {
        return isPassable; //TODO: implement this after implementing player
    }
}
