package nz.ac.vuw.ecs.swen225.gp20.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicalInterface extends JFrame implements KeyListener {

    List<Integer> pressedKeys = new ArrayList<>();


    JPanel centrePanel;
    JPanel rightPanel;
    JPanel rightPanelMain;

    public GraphicalInterface() {
        super("Chaps Challenge");

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

        final JMenuItem newGameMenu = new JMenuItem("New Game (Ctrl-1)");
        gameMenu.add(newGameMenu);

        final JMenuItem newGameFromLastLevelMenu = new JMenuItem("New Game from Last Level (Ctrl-P)");
        gameMenu.add(newGameFromLastLevelMenu);

        final JMenuItem resumeASavedGameMenu = new JMenuItem("Resume Saved Game (Ctrl-R)");
        gameMenu.add(resumeASavedGameMenu);

        final JMenuItem saveAndExitGameMenu = new JMenuItem("Save and Exit (Ctrl-S)");
        gameMenu.add(saveAndExitGameMenu);

        final JMenuItem exitMenuItemMenu = new JMenuItem("Exit (Ctrl-X)");
        gameMenu.add(exitMenuItemMenu);

        final JMenu optionsMenu = new JMenu("Options");

        final JMenuItem gamePauseMenu = new JMenuItem("Toggle Game Pause");
        optionsMenu.add(gamePauseMenu);

        tableMenuBar.add(gameMenu);
        tableMenuBar.add(optionsMenu);

        final JMenu helpMenu = new JMenu("Help");
        tableMenuBar.add(helpMenu);

        final JMenuItem howToPlayMenu = new JMenuItem("How to Play");
        helpMenu.add(howToPlayMenu);

        final JMenuItem aboutMenu = new JMenuItem("About");
        helpMenu.add(aboutMenu);


        JPanel p = new JPanel(new BorderLayout(20,0));




        p.setBorder(new EmptyBorder(30, 30, 30, 30));
        centrePanel = new JPanel();

        rightPanelMain = new JPanel(new BorderLayout());
        rightPanel = new JPanel(new GridLayout(8,1));


        Font  f1  = new Font(Font.MONOSPACED, Font.BOLD,  20);
        JLabel level = new JLabel("LEVEL",JLabel.CENTER);
        level.setFont(f1);
        JLabel time = new JLabel("TIME",JLabel.CENTER);
        time.setFont(f1);
        JLabel chipsLeft = new JLabel("CHIPS LEFT",JLabel.CENTER);
        chipsLeft.setFont(f1);



        rightPanel.add(level);
        rightPanel.add(time);
        rightPanel.add(chipsLeft);



        rightPanelMain.add(rightPanel,BorderLayout.CENTER);


        JPanel secondRightPanel = new JPanel(new GridLayout(2,3));
        rightPanelMain.add(secondRightPanel,BorderLayout.SOUTH);
        secondRightPanel.setPreferredSize(new Dimension(215, 120));



        p.add(rightPanelMain, BorderLayout.EAST);
        p.add(centrePanel, BorderLayout.CENTER);

        rightPanel.setPreferredSize(new Dimension(215, 480));
        centrePanel.setPreferredSize(new Dimension(585, 600));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centrePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        secondRightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        centrePanel.setBackground(Color.LIGHT_GRAY);
        centrePanel.setBorder(BorderFactory.createRaisedBevelBorder());
        p.setBackground(new Color(3,192,60));
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        secondRightPanel.setBorder(BorderFactory.createRaisedBevelBorder());


        JButton upButton = new JButton("^");
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("UP");
            }
        });

        JButton downButton = new JButton("v");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("DOWN");
            }
        });

        JButton leftButton = new JButton("<");
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("LEFT");
            }
        });

        JButton rightButton = new JButton(">");
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("RIGHT");
            }
        });


        secondRightPanel.add(new JLabel());
        secondRightPanel.add(upButton);
        secondRightPanel.add(new JLabel());
        secondRightPanel.add(leftButton);
        secondRightPanel.add(downButton);
        secondRightPanel.add(rightButton);

        rightPanelMain.setFocusable(false);
        upButton.setFocusable(false);
        downButton.setFocusable(false);
        leftButton.setFocusable(false);
        rightButton.setFocusable(false);




        getContentPane().add(p);


        setLocationByPlatform(true);


        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(600,450));
        pack();
        setVisible(true);



        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                rightPanelMain.setMinimumSize(new Dimension((int) (getBounds().getSize().getWidth() * 0.26875), 300));
                centrePanel.setMinimumSize(new Dimension((int) (getBounds().getSize().getWidth() * 0.73125), 600));
                repaint();
            }
        });


    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Detect Key Presses for keyboard shortcuts and perform actions if applicable
     * @param e key event
     */
    @Override
    public void keyPressed(KeyEvent e) {

        // add keys to array of pressed keys
        if (!pressedKeys.contains(e.getKeyCode())) {
            pressedKeys.add(e.getKeyCode());
        }

        // Perform action based on keys that are currently pressed by checking the array of pressed keys
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


    /**
     * Detect Key Released.
     * If a key has been released, then remove it from the array of pressed keys
     *
     * @param e key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (pressedKeys.contains(e.getKeyCode())) {
            pressedKeys.remove((Integer) e.getKeyCode());
        }
    }
}
