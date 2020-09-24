package nz.ac.vuw.ecs.swen225.gp20.application;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private int counter;


    public Game(int countFromFile) {
        counter = countFromFile;
        counter+= 1;

        Timer timer = new Timer();

        TimerTask task = new TimerTask(){
            public void run(){
                if (counter > 0) {
                    counter--;
                    System.out.println(counter);
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000); //1000ms = 1sec
    }

    public static void main(String[] args){
        new Game(30);
    }
}