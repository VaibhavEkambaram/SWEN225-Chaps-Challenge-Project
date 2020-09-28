package nz.ac.vuw.ecs.swen225.gp23.application;

import nz.ac.vuw.ecs.swen225.gp23.render.RenderPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Graphical Interface Class.
 * This class does not control the game itself, but implements the graphical display of the game
 *
 * @author Vaibhav Ekambaram - 300472561
 */
public class GraphicalInterface extends JFrame implements KeyListener {

    private final List<Integer> pressedKeys = new ArrayList<>();

    private final JPanel gamePanel;
    private final JPanel rightPanel;
    private final JPanel informationPanel;
    private final JPanel movementPanel;

    RenderPanel renderPanel;

    private final JLabel timeLabel, levelLabel, chipsLeftLabel;
    JButton upButton, downButton, leftButton, rightButton;


    private Game currentGame;

    boolean gamePaused = false;

    Application application;

    /**
     * Interface Constructor Method.
     * Creates GUI structure and assigns listeners to buttons and menus
     */
    public GraphicalInterface(Application application) {
        super("Chaps Challenge");

        // carry through persistent application class to keep track of game state
        this.application = application;

        // Set java swing theme to native operating system theme
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(600, 450));


        setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JMenuBar tableMenuBar = new JMenuBar();
        this.setJMenuBar(tableMenuBar);

        // ------------------------------------------------------------------------------------------------
        // Menu Bar Structure
        // ------------------------------------------------------------------------------------------------
        final JMenu gameMenu = new JMenu("Game");

        final JMenuItem newMenu = new JMenuItem("New Game");
        newMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK));
        newMenu.addActionListener(e -> {
            onNewGame();
        });

        final JMenuItem newFromLastLevelMenu = new JMenuItem("New Game from Last Level");
        newFromLastLevelMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        newFromLastLevelMenu.addActionListener(e -> {
            gameLoad();
        });

        final JMenuItem resumeSavedMenu = new JMenuItem("Resume Saved Game");
        resumeSavedMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        resumeSavedMenu.addActionListener(e -> {
            System.out.println("resume a saved game using persistence");
        });

        final JMenuItem saveAndExitMenu = new JMenuItem("Save and Exit");
        saveAndExitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        saveAndExitMenu.addActionListener(e -> {
            System.out.println("save and exit game using persistence");
            System.exit(0);
        });

        final JMenuItem exitMenu = new JMenuItem("Exit");
        exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        exitMenu.addActionListener(e -> {
            System.out.println("exit the game, the current game state will be lost, the next time the game is started, it will resume from the last unfinished level");
            System.exit(0);
        });

        gameMenu.add(newMenu);
        gameMenu.add(newFromLastLevelMenu);
        gameMenu.add(resumeSavedMenu);
        gameMenu.add(saveAndExitMenu);
        gameMenu.add(exitMenu);

        // ------------------------------------------------------------------------------------------------
        final JMenu optionsMenu = new JMenu("Options");

        final JCheckBoxMenuItem gamePauseMenu = new JCheckBoxMenuItem("Toggle Game Pause");
        gamePauseMenu.setState(false);

        gamePauseMenu.addActionListener(e -> {
            boolean paused;

            if(gamePaused){
                paused = false;
                onPauseGame(false);
            } else {
                paused = true;
                onPauseGame(true);
            }
            gamePauseMenu.setState(paused);
        });

        final JMenu recordAndReplayMenu = new JMenu("Record and Replay");
        final JMenuItem startRecordingMenu = new JMenuItem("Start Recording");
        startRecordingMenu.addActionListener(e -> {

        });
        final JMenuItem stopRecordingMenu = new JMenuItem("Stop Recording");
        stopRecordingMenu.addActionListener(e -> {

        });
        final JMenuItem loadRecordedMenu = new JMenuItem("Load Recorded Game");
        loadRecordedMenu.addActionListener(e -> {

        });

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
        mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        movementPanel = new JPanel(new GridLayout(2, 3));


        // TODO: testing board draw
        String[][] board = new String[9][9];


        gamePanel = new JPanel(new GridLayout(1, 1));


        rightPanel = new JPanel(new BorderLayout());
        informationPanel = new JPanel();
        informationPanel.setLayout(new GridLayout(10, 1));
        informationPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font infoTextFont = new Font(Font.MONOSPACED, Font.BOLD, 20);
        JLabel level = new JLabel("LEVEL", JLabel.CENTER);
        level.setFont(infoTextFont);
        JLabel time = new JLabel("TIME", JLabel.CENTER);
        time.setFont(infoTextFont);
        JLabel chipsLeft = new JLabel("ITEMS LEFT", JLabel.CENTER);
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
        gamePanel.setPreferredSize(new Dimension(560, 560));

        gamePanel.setBackground(Color.LIGHT_GRAY);
        gamePanel.setBorder(BorderFactory.createRaisedBevelBorder());
        mainPanel.setBackground(new Color(3, 192, 60));
        informationPanel.setBackground(Color.LIGHT_GRAY);
        informationPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        movementPanel.setBorder(BorderFactory.createRaisedBevelBorder());


        upButton = new JButton("^");
        upButton.setToolTipText("Move Chap Up");
        upButton.addActionListener(e -> System.out.println("UP"));

        downButton = new JButton("v");
        downButton.setToolTipText("Move Chap Down");
        downButton.addActionListener(e -> System.out.println("DOWN"));

        leftButton = new JButton("<");
        leftButton.setToolTipText("Move Chap to the Left");
        leftButton.addActionListener(e -> System.out.println("LEFT"));

        rightButton = new JButton(">");
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
        if (pressedKeys.size() == 1) {
            if (pressedKeys.contains(32)) {
                System.out.println("Pause the game and display a \"game is paused\" dialog");
                onPauseGame(true);
            } else if (pressedKeys.contains(27)) {
                System.out.println("close the \"game is paused\" dialog and resume the game");
                onPauseGame(false);
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


    /**
     * GUI for loading the game
     * @return chosen file
     */
    public boolean gameLoad(){
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("File Load");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            currentGame.setFileLoad(chooser.getSelectedFile());
            return true;
        }
        return false;
    }

    public void onNewGame() {
        if (currentGame != null) {
            application.transitionToInit();
            currentGame.terminateTimer();
            gamePanel.remove(renderPanel);
            renderPanel = null;
        }



        currentGame = new Game(60, -1,this);
        application.transitionToRunning();





    }


    public void onPauseGame(boolean value) {

        if(application.getState().equals(Application.gameStates.RUNNING)) {
            if (value) {
                gamePaused = true;
                currentGame.setGamePaused(true);
            } else {
                gamePaused = false;
                currentGame.setGamePaused(false);
            }
        }
    }

    public void setMovementButtonVisibility(boolean value){
        upButton.setEnabled(value);
        downButton.setEnabled(value);
        leftButton.setEnabled(value);
        rightButton.setEnabled(value);
    }


    public void updateDisplay(){
        System.out.println("Current game state: "+ application.getState());

        if(application.getState().equals(Application.gameStates.IDLE)){
            setMovementButtonVisibility(false);

        } else if(application.getState().equals(Application.gameStates.RUNNING)){
            setMovementButtonVisibility(true);
        }

    }

    public JLabel getTimeLabel(){
        return timeLabel;
    }

    public JLabel getLevelLabel(){
        return levelLabel;
    }

    public void setRenderPanel(RenderPanel renderPanel) {
        this.renderPanel = renderPanel;
        if(renderPanel!=null){
            gamePanel.add(renderPanel);
        }
    }
}
