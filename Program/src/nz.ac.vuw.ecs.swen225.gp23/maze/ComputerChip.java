package nz.ac.vuw.ecs.swen225.gp23.maze;

public class ComputerChip extends Tile {
    private boolean pickedUp = false;

    public ComputerChip(){
        super(Tiles.ComputerChip);
        this.isPassable = true;
        this.image = "computer_chip.png";
    }

    @Override
    public boolean action(Player p) {
        if(!pickedUp){
            p.pickUpChip();
            image = "empty.png";
            pickedUp = true;
        }
        return isPassable;
    }

    @Override
    public String toString() {return "computer_chip";}

    //Getters and setters
    public boolean getPickedUp(){return pickedUp;}
    public void setPickedUp(boolean isPickedUp){pickedUp = isPickedUp;}
}
