package nz.ac.vuw.ecs.swen225.gp20.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GraphicalInterface extends JFrame implements KeyListener {


    List<Integer> pressedKeys = new ArrayList<>();


    public GraphicalInterface() {
        super("Chap's Challenge!");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);


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


        JPanel p = new JPanel(new BorderLayout());

        JPanel centrePanel = new JPanel();
        JPanel rightPanel = new JPanel();

        p.add(rightPanel,BorderLayout.EAST);
        p.add(centrePanel,BorderLayout.CENTER);

        rightPanel.setPreferredSize(new Dimension(200, 0));
        centrePanel.setPreferredSize(new Dimension(600, 600));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));



        getContentPane().add(p);


        setLocationByPlatform(true);


        setPreferredSize(new Dimension(800,600));
        pack();
        setVisible(true);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("Window resized to width: "+ getBounds().getSize().getWidth() + " height: "+ getBounds().getSize().getHeight());
            }
        });


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!pressedKeys.contains(e.getKeyCode())) {
            pressedKeys.add(e.getKeyCode());
        }

        if (pressedKeys.size() == 2) {
            if (pressedKeys.contains(17) && pressedKeys.contains(88)) {
                System.out.println("exit the game, the current game state will be lost, the next time the game is started, it will resume from the last unfinished level");
            } else if (pressedKeys.contains(17) && pressedKeys.contains(83)) {
                System.out.println("exit the game, saves the game state, game will resume next time the application will be started");
            } else if (pressedKeys.contains(17) && pressedKeys.contains(82)) {
                System.out.println("Resume a saved game");
            } else if (pressedKeys.contains(17) && pressedKeys.contains(80)) {
                System.out.println("Start a new game at the last unfinished level");
            } else if (pressedKeys.contains(17) && pressedKeys.contains(49)) {
                System.out.println("start a new game at level 1");
            }
        } else if (pressedKeys.size() == 1) {
            if (pressedKeys.contains(32)) {
                System.out.println("Pause the game and display a \"game is paused\" dialog");
            } else if (pressedKeys.contains(27)) {
                System.out.println("close the \"game is paused\" dialog and resume the game");
            } else if (pressedKeys.contains(38)) {
                System.out.println("UP");
            } else if (pressedKeys.contains(40)) {
                System.out.println("DOWN");
            } else if (pressedKeys.contains(37)) {
                System.out.println("LEFT");
            } else if (pressedKeys.contains(39)) {
                System.out.println("RIGHT");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (pressedKeys.contains(e.getKeyCode())) {
            pressedKeys.remove((Integer) e.getKeyCode());
        }
    }
}
