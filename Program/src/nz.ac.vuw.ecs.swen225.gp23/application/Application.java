package nz.ac.vuw.ecs.swen225.gp23.application;

/**
 * Application Class.
 * Persistent class which observes game states.
 *
 * @author Vaibhav Ekambaram - 300472561
 */
public class Application {

    /**
     * States to track the overall game
     */
    public enum gameStates {
        IDLE, RUNNING
    }

    private gameStates state;
    private GraphicalInterface gui;


    /**
     * Application Constructor
     * Create new GUI
     */
    public Application() {
        state = gameStates.IDLE;
        gui = new GraphicalInterface(this);
        gui.updateDisplay();
    }


    /**
     * Transition state from IDLE to RUNNING
     */
    public void transitionToRunning() {
        if (state == gameStates.IDLE) {
            state = gameStates.RUNNING;
            gui.updateDisplay();
        } else {
            throw new Error("Excepted Init, but found " + state.toString());
        }
    }

    /**
     * Transition state from RUNNING to IDLE
     */
    public void transitionToInit() {
        if (state == gameStates.RUNNING) {
            state = gameStates.IDLE;
            gui.updateDisplay();
        } else {
            throw new Error("Excepted running, but found state " + state.toString());
        }
    }


    /**
     * Get Application state
     *
     * @return current state
     */
    public gameStates getState() {
        return state;
    }
}
