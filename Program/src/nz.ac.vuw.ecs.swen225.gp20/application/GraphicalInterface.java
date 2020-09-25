package nz.ac.vuw.ecs.swen225.gp20.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class GraphicalInterface extends JFrame implements KeyListener {

    List<Integer> pressedKeys = new ArrayList<>();

    JPanel gamePanel;
    JPanel informationPanel;
    JPanel rightPanel;
    JPanel movementPanel;

    JLabel timeLabel;
    JLabel levelLabel;
    JLabel chipsLeftLabel;

    Game currentGame;

    boolean gamePaused = false;

    public GraphicalInterface() {
        super("Chaps Challenge");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setDefaultCloseOperation(EXIT_ON_CLOSE);


        final JMenuBar tableMenuBar = new JMenuBar();
        this.setJMenuBar(tableMenuBar);

        // ------------------------------------------------------------------------------------------------
        // Menu Bar Structure
        // ------------------------------------------------------------------------------------------------
        final JMenu gameMenu = new JMenu("Game");

        final JMenuItem newGameMenu = new JMenuItem("New Game (Ctrl-1)");

        newGameMenu.addActionListener(e ->
                onNewGame()
        );


        final JMenuItem newGameFromLastLevelMenu = new JMenuItem("New Game from Last Level (Ctrl-P)");
        final JMenuItem resumeASavedGameMenu = new JMenuItem("Resume Saved Game (Ctrl-R)");
        final JMenuItem saveAndExitGameMenu = new JMenuItem("Save and Exit (Ctrl-S)");
        final JMenuItem exitMenuItemMenu = new JMenuItem("Exit (Ctrl-X)");

        gameMenu.add(newGameMenu);
        gameMenu.add(newGameFromLastLevelMenu);
        gameMenu.add(resumeASavedGameMenu);
        gameMenu.add(saveAndExitGameMenu);
        gameMenu.add(exitMenuItemMenu);
        // ------------------------------------------------------------------------------------------------
        final JMenu optionsMenu = new JMenu("Options");

        final JCheckBoxMenuItem gamePauseMenu = new JCheckBoxMenuItem("Toggle Game Pause");
        gamePauseMenu.setState(false);

        gamePauseMenu.addActionListener(e -> {
            boolean paused = onPauseGame();
            gamePauseMenu.setState(paused);

        });

        final JMenu recordAndReplayMenu = new JMenu("Record and Replay");
        final JMenuItem startRecordingMenu = new JMenuItem("Start Recording");
        final JMenuItem stopRecordingMenu = new JMenuItem("Stop Recording");
        final JMenuItem loadRecordedMenu = new JMenuItem("Load Recorded Game");

        optionsMenu.add(gamePauseMenu);
        optionsMenu.add(recordAndReplayMenu);
        recordAndReplayMenu.add(startRecordingMenu);
        recordAndReplayMenu.add(stopRecordingMenu);
        recordAndReplayMenu.add(loadRecordedMenu);
        // ------------------------------------------------------------------------------------------------
        final JMenu helpMenu = new JMenu("Help");

        final JMenuItem howToPlayMenu = new JMenuItem("How to Play");
        howToPlayMenu.addActionListener(e -> {
            JPanel fields = new JPanel(new GridLayout(0, 1));

            fields.add(new JLabel("Hello and welcome to Chaps Challenge. Here is how to play the game:"));


            JOptionPane.showMessageDialog(null, fields, "How to Play", JOptionPane.PLAIN_MESSAGE);
        });
        final JMenuItem aboutMenu = new JMenuItem("About");

        helpMenu.add(howToPlayMenu);
        helpMenu.add(aboutMenu);
        // ------------------------------------------------------------------------------------------------

        tableMenuBar.add(gameMenu);
        tableMenuBar.add(optionsMenu);
        tableMenuBar.add(helpMenu);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 0));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        movementPanel = new JPanel(new GridLayout(2, 3));

        gamePanel = new JPanel();

        rightPanel = new JPanel(new BorderLayout());
        informationPanel = new JPanel();
        informationPanel.setLayout(new GridLayout(9, 1));
        informationPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font infoTextFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
        JLabel level = new JLabel("LEVEL", JLabel.CENTER);
        level.setFont(infoTextFont);
        JLabel time = new JLabel("TIME", JLabel.CENTER);
        time.setFont(infoTextFont);
        JLabel chipsLeft = new JLabel("CHIPS LEFT", JLabel.CENTER);
        chipsLeft.setFont(infoTextFont);

        levelLabel = new JLabel("undefined");
        timeLabel = new JLabel("undefined");
        chipsLeftLabel = new JLabel("undefined");
        JLabel label4 = new JLabel("   Items Collected");
        JLabel label5 = new JLabel("CollectedItemString");

        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chipsLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);

        informationPanel.add(level);
        informationPanel.add(levelLabel);
        informationPanel.add(time);
        informationPanel.add(timeLabel);
        informationPanel.add(chipsLeft);
        informationPanel.add(chipsLeftLabel);
        informationPanel.add(label4);

        rightPanel.add(informationPanel, BorderLayout.CENTER);

        rightPanel.add(movementPanel, BorderLayout.SOUTH);
        movementPanel.setPreferredSize(new Dimension(240, 120));

        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        informationPanel.setPreferredSize(new Dimension(240, 480));
        gamePanel.setPreferredSize(new Dimension(560, 600));

        gamePanel.setBackground(Color.LIGHT_GRAY);
        gamePanel.setBorder(BorderFactory.createRaisedBevelBorder());
        mainPanel.setBackground(new Color(3, 192, 60));
        informationPanel.setBackground(Color.LIGHT_GRAY);
        informationPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        movementPanel.setBorder(BorderFactory.createRaisedBevelBorder());


        JButton upButton = new JButton("^");
        upButton.setToolTipText("Move Chap Up");
        upButton.addActionListener(e -> System.out.println("UP"));

        JButton downButton = new JButton("v");
        downButton.setToolTipText("Move Chap Down");
        downButton.addActionListener(e -> System.out.println("DOWN"));

        JButton leftButton = new JButton("<");
        leftButton.setToolTipText("Move Chap to the Left");
        leftButton.addActionListener(e -> System.out.println("LEFT"));

        JButton rightButton = new JButton(">");
        rightButton.setToolTipText("Move Chap to the Right");
        rightButton.addActionListener(e -> System.out.println("RIGHT"));


        movementPanel.add(new JLabel());
        movementPanel.add(upButton);
        movementPanel.add(new JLabel());
        movementPanel.add(leftButton);
        movementPanel.add(downButton);
        movementPanel.add(rightButton);

        upButton.setFocusable(false);
        downButton.setFocusable(false);
        leftButton.setFocusable(false);
        rightButton.setFocusable(false);

        getContentPane().add(mainPanel);
        addKeyListener(this);
        setLocationByPlatform(true);


        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(600, 450));


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                gamePanel.setMinimumSize(new Dimension((int) (getBounds().getSize().getWidth() * 0.7), (int) (getBounds().getSize().getHeight())));
                movementPanel.setMinimumSize(new Dimension((int) (getBounds().getSize().getWidth() * 0.3), (int) (getBounds().getSize().getHeight() * 0.2)));
                informationPanel.setMinimumSize(new Dimension((int) (getBounds().getSize().getWidth() * 0.4), (int) (getBounds().getSize().getHeight() * 0.6)));
                rightPanel.setMinimumSize(new Dimension((int) (getBounds().getSize().getWidth() * 0.3), (int) (getBounds().getSize().getHeight() * 0.5)));
                repaint();
            }
        });


        pack();
        setVisible(true);
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Detect Key Presses for keyboard shortcuts and perform actions if applicable
     *
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
                onNewGame();
            }
        } else if (pressedKeys.size() == 1) {
            if (pressedKeys.contains(32)) {
                System.out.println("Pause the game and display a \"game is paused\" dialog");
                onPauseGame();
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


    public void onNewGame() {
        System.out.println("start a new game at level 1");

        if (currentGame != null) {
            currentGame.terminateTimer();
        }

        currentGame = new Game(30, -1, "$levelName", timeLabel, levelLabel, chipsLeftLabel);

    }

    public void onNewGameFromLastLevel() {

    }

    public boolean onPauseGame() {

        if (gamePaused) {
            gamePaused = false;
            currentGame.setGamePaused(false);
            return false;
        } else {
            gamePaused = true;
            currentGame.setGamePaused(true);
            return true;
        }
    }
}
