package nz.ac.vuw.ecs.swen225.gp23.application;

public class Application {

    public enum gameStates {
        IDLE, RUNNING, PAUSED
    }

    private gameStates state;
    private GraphicalInterface gui;


    public Application() {
        state = gameStates.IDLE;
        gui = new GraphicalInterface(this);
        gui.updateDisplay();
    }


    public void transitionToRunning() {
        if (state == gameStates.IDLE) {
            state = gameStates.RUNNING;
            gui.updateDisplay();
        } else {
            throw new Error("Excepted Init, but found "+ state.toString());
        }
    }

    public void transitionToInit(){
        if(state == gameStates.RUNNING){
            state = gameStates.IDLE;
            gui.updateDisplay();
            // gui update
        } else {
            throw new Error("Excepted running, but found state "+ state.toString());
        }
    }



    public gameStates getState(){
        return state;
    }

}
