package nz.ac.vuw.ecs.swen225.gp20.maze;

public class ExitLock extends Tile {
    public ExitLock(){
        super(Tiles.ExitLock);
        this.isPassable = false;
        this.image = "exit_lock.png";
    }

    @Override
    public boolean action(Player p) {
        return isPassable; //TODO: implement this after implementing player
    }
}
