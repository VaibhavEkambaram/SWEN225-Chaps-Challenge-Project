package nz.ac.vuw.ecs.swen225.gp23.maze;

public class Hint extends Tile {
    public Hint(){
        super(Tiles.Hint);
        this.isPassable = true;
        this.image = "hint.png";
    }

    @Override
    public boolean action(Player p) {return isPassable;}

    @Override
    public String toString() {return "hint";}
}
