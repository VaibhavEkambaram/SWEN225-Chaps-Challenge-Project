package nz.ac.vuw.ecs.swen225.gp23.maze;

public class Exit extends Tile {
    public Exit(){
        super(Tiles.Exit);
        this.isPassable = true;
        this.image = "exit.png";
    }

    @Override
    public boolean action(Player p) {
        return isPassable;
    }

    @Override
    public String toString() {return "exit";}
}
