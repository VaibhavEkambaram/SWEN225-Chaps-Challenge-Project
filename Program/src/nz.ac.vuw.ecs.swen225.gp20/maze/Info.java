package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Info extends Tile {
    public Info(){
        super(Tiles.Info);
        this.isPassable = true;
        this.image = "info.png";
    }

    @Override
    public boolean action(Player p) {
        return isPassable; //TODO: implement this after implementing player
    }
}
