package nz.ac.vuw.ecs.swen225.gp23.maze;

public class Wall extends Tile {
    public Wall(){
        super(Tiles.Wall);
        this.isPassable = false;
        this.image = "floor.png";
    }

    @Override
    public boolean action(Player p) {
        return isPassable;
    }

    @Override
    public String toString() {return "floor";}
}
