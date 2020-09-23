package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Treasure extends Tile {
    public Treasure(){
        super(Tiles.Treasure);
        this.isPassable = true;
        this.image = "treasure.png";
    }

    @Override
    public boolean action(Player p) {
        return isPassable; //TODO: implement this after implementing player
    }
}
