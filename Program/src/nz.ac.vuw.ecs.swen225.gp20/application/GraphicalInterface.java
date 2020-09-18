package nz.ac.vuw.ecs.swen225.gp20.application;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GraphicalInterface extends JFrame implements KeyListener {


    List<Integer> pressedKeys = new ArrayList<>();



    public GraphicalInterface(){
        super("Chap's Challenge!");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
        setVisible(true);

        addKeyListener(this);


        final JMenuBar tableMenuBar = new JMenuBar();
        this.setJMenuBar(tableMenuBar);



        final JMenu gameMenu = new JMenu("Game");

        final JMenuItem newGameMenu = new JMenuItem("Start a New Game");
        gameMenu.add(newGameMenu);

        final JMenuItem newGameFromLastLevelMenu = new JMenuItem("Start a New Game from Last Played Level");
        gameMenu.add(newGameFromLastLevelMenu);

        final JMenuItem resumeASavedGameMenu = new JMenuItem("Resume a Saved Game");
        gameMenu.add(resumeASavedGameMenu);

        final JMenuItem saveAndExitGameMenu = new JMenuItem("Save and Exit Game");
        gameMenu.add(saveAndExitGameMenu);

        final JMenuItem exitMenuItemMenu = new JMenuItem("Force Quit and Exit Game");
        gameMenu.add(exitMenuItemMenu);

        final JMenu optionsMenu = new JMenu("Options");

        final JMenuItem gamePauseMenu = new JMenuItem("Toggle Game Pause");
        optionsMenu.add(gamePauseMenu);

        tableMenuBar.add(gameMenu);
        tableMenuBar.add(optionsMenu);



    }



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        if(!pressedKeys.contains(e.getKeyCode())){
            pressedKeys.add(e.getKeyCode());

        }

        for(Integer v : pressedKeys){
            System.out.println(v);
        }

        if(pressedKeys.contains(17) && pressedKeys.contains(88) && pressedKeys.size()==2){
            System.out.println("exit the game, the current game state will be lost, the next time the game is started, it will resume from the last unfinished level");
        }



    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(pressedKeys.contains(e.getKeyCode())){
            pressedKeys.remove(pressedKeys.indexOf(e.getKeyCode()));
        }
    }
}
