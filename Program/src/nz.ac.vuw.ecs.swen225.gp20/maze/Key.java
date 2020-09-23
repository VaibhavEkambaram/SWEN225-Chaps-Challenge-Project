package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Key extends Tile {
    private String colour;

    public Key(String colour){
        super(Tiles.Key);
        this.isPassable = true;
        this.colour = colour;
        this.image = "key_" + colour + ".png";
    }

    @Override
    public boolean action(Player p) {
        return isPassable; //TODO: implement this after implementing player
    }
}
