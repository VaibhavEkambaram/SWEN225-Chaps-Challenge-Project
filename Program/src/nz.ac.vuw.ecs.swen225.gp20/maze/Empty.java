package nz.ac.vuw.ecs.swen225.gp20.maze;

public class Empty extends Tile {
    public Empty(){
        super(Tiles.Empty);
        this.isPassable = true;
        this.image = "empty.png";
    }

    @Override
    public boolean action(Player p) {return isPassable;}

    @Override
    public String toString() {return "empty";}
}
