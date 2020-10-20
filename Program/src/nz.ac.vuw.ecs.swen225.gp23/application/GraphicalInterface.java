package nz.ac.vuw.ecs.swen225.gp23.application;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nz.ac.vuw.ecs.swen225.gp23.maze.Board;
import nz.ac.vuw.ecs.swen225.gp23.maze.Tile;
import nz.ac.vuw.ecs.swen225.gp23.persistence.LevelM;
import nz.ac.vuw.ecs.swen225.gp23.persistence.Persistence;
import nz.ac.vuw.ecs.swen225.gp23.recnplay.RecordReplay;
import nz.ac.vuw.ecs.swen225.gp23.render.ChipAudioModule;
import nz.ac.vuw.ecs.swen225.gp23.render.RenderPanel;
import nz.ac.vuw.ecs.swen225.gp23.render.TileFinder;


/**
 * Graphical Interface Class.
 * This class does not control the game itself, but implements the graphical display of the game
 *
 * @author Vaibhav Ekambaram - 300472561
 */
public class GraphicalInterface extends JFrame implements KeyListener {

  private final JPanel gamePanel;
  private final JPanel itemsPanel;
  private JPanel itemsGrid;

  private RenderPanel renderPanel;

  private final JLabel timeLabel;
  private final JLabel levelLabel;
  private final JLabel chipsLeftLabel;
  private JButton upButton;
  private JButton downButton;
  private JButton leftButton;
  private JButton rightButton;
  private JButton playback;
  private JButton stepToNext;

  private final JMenuItem startRecordingMenu;
  private final JMenuItem stopRecordingMenu;
  private final JMenuItem loadRecordedMenu;


  private final JCheckBoxMenuItem gamePauseMenu;
  private boolean gamePaused = false;

  private final Application application;
  private final ChipAudioModule audio;
  private Game currentGame;


  private final JLabel pausedLabel;

  private BufferedImage image;
  private final LevelM lm;


  /**
   * Interface Constructor Method.
   * Creates GUI structure and assigns listeners to buttons and menus
   */
  public GraphicalInterface(Application application) {
    super("Chaps Challenge");


    // carry through persistent application class to keep track of game state
    this.application = application;

    lm = new LevelM();


    // Set java swing theme to native operating system theme
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException
        | InstantiationException
        | IllegalAccessException
        | UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

    pausedLabel = new JLabel("Game has been paused...press ESC to resume");
    audio = new ChipAudioModule();

    setPreferredSize(new Dimension(800, 600));
    setMinimumSize(new Dimension(800, 600));

    setDefaultCloseOperation(EXIT_ON_CLOSE);

    final JMenuBar tableMenuBar = new JMenuBar();
    this.setJMenuBar(tableMenuBar);

    // ---------------------------------------------------------------------------------------------
    // Menu Bar Structure
    // ---------------------------------------------------------------------------------------------
    final JMenu gameMenu = new JMenu("Game");

    final JMenuItem newMenu = new JMenuItem("New Game");
    newMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK));
    newMenu.addActionListener(e -> onNewGame());

    final JMenuItem newFromLastLevelMenu = new JMenuItem("New Game from Last Level");
    newFromLastLevelMenu.setAccelerator(
        KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
    newFromLastLevelMenu.addActionListener(e -> {

      onLoadGameNoGui(new LevelM().getCurrentLevel());
    });

    final JMenuItem resumeSavedMenu = new JMenuItem("Load Game");
    resumeSavedMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
    resumeSavedMenu.addActionListener(e -> onLoadGame());

    final JMenuItem saveAndExitMenu = new JMenuItem("Save and Exit");
    saveAndExitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
    saveAndExitMenu.addActionListener(e -> {


      int closeDialogButton = JOptionPane.YES_NO_OPTION;
      int closeDialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit? Game progress WILL be saved.", "Warning", closeDialogButton);
      if (closeDialogResult == JOptionPane.YES_OPTION) {
        if (currentGame != null) {
          lm.saveLevel();
          currentGame.saveGame();
        }
        onStopGame(false);
        dispose();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.exit(0);
      } else {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      }
    });

    final JMenuItem exitMenu = new JMenuItem("Exit");
    exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
    exitMenu.addActionListener(e -> {
      int closeDialogButton = JOptionPane.YES_NO_OPTION;
      int closeDialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit? Game progress will NOT be saved.", "Warning", closeDialogButton);
      if (closeDialogResult == JOptionPane.YES_OPTION) {
        dispose();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.exit(0);
      } else {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      }
    });

    gameMenu.add(newMenu);
    gameMenu.add(newFromLastLevelMenu);
    gameMenu.add(resumeSavedMenu);
    gameMenu.add(saveAndExitMenu);
    gameMenu.add(exitMenu);

    // ------------------------------------------------------------------------------------------------
    final JMenu optionsMenu = new JMenu("Options");

    gamePauseMenu = new JCheckBoxMenuItem("Toggle Game Pause");
    gamePauseMenu.setState(false);

    createButtons();


    final JMenu recordAndReplayMenu = new JMenu("Record and Replay");

    startRecordingMenu = new JMenuItem("Start Recording");
    startRecordingMenu.addActionListener(e -> {
      String fileName = JOptionPane.showInputDialog(this, "Enter a filename: (.json will be appended)");
      if (fileName != null) {
        RecordReplay.newSave(currentGame, fileName + ".json");
        updateRecordReplayGUI();
      }
    });

    stopRecordingMenu = new JMenuItem("Stop and Save Recording");
    stopRecordingMenu.addActionListener(e -> {
      RecordReplay.saveRecording(currentGame);
      updateRecordReplayGUI();
    });


    loadRecordedMenu = new JMenuItem("Load Recorded Game");
    loadRecordedMenu.addActionListener(e -> {
      String fileName = JOptionPane.showInputDialog(this, "Enter saved filename: (.json will be appended)");
      if (fileName != null) {

        RecordReplay.loadRecord(fileName + ".json", this);
        playback.setEnabled(true);
        stepToNext.setEnabled(true);
      }
    });


    optionsMenu.add(gamePauseMenu);

    recordAndReplayMenu.add(startRecordingMenu);
    recordAndReplayMenu.add(stopRecordingMenu);
    recordAndReplayMenu.add(loadRecordedMenu);
    // ------------------------------------------------------------------------------------------------
    final JMenu helpMenu = new JMenu("Help");


    helpMenu.add(displayHelpMenu());
    helpMenu.add(displayAboutMenu());
    // ------------------------------------------------------------------------------------------------

    tableMenuBar.add(gameMenu);
    tableMenuBar.add(optionsMenu);
    tableMenuBar.add(recordAndReplayMenu);
    tableMenuBar.add(helpMenu);


    //reads the image
    try {
      image = ImageIO.read(getClass().getResource("/background.png"));

    } catch (IOException io) {
      System.out.println("Could not read in the pic");
    }

    JPanel mainPanel = new JPanel() {

      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
      }
    };

    mainPanel.setLayout(new BorderLayout(0, 0));
    mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));


    JPanel movementPanel = new JPanel(new GridLayout(2, 3));

    gamePanel = new JPanel(new BorderLayout());


    JPanel rightPanel = new JPanel(new BorderLayout());
    JPanel informationPanel = new JPanel();
    informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));


    JPanel levelPanel = new JPanel(new GridLayout(2, 1));
    Color secondColor = Color.BLACK;
    levelPanel.setBackground(secondColor);
    levelPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    JLabel level = new JLabel("Level Number", JLabel.CENTER);
    level.setForeground(Color.ORANGE);
    level.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
    level.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    levelPanel.add(level);
    levelPanel.setMinimumSize(new Dimension(240, 50));
    levelPanel.setPreferredSize(new Dimension(240, 120));
    levelPanel.setMaximumSize(new Dimension(240, 120));


    levelLabel = new JLabel("", JLabel.CENTER);
    levelLabel.setForeground(Color.WHITE);
    levelLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
    levelPanel.add(levelLabel, JLabel.CENTER_ALIGNMENT);


    JPanel timePanel = new JPanel(new GridLayout(2, 1));
    timePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    timePanel.setBackground(Color.BLACK);

    JLabel time = new JLabel("Time Left", JLabel.CENTER);
    time.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
    time.setForeground(Color.ORANGE);
    time.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    timePanel.add(time);
    timeLabel = new JLabel("", JLabel.CENTER);
    timeLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    timeLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
    timePanel.add(timeLabel);

    timePanel.setMinimumSize(new Dimension(240, 50));
    timePanel.setPreferredSize(new Dimension(240, 100));
    timePanel.setMaximumSize(new Dimension(240, 100));

    JPanel chipsLeftPanel = new JPanel(new GridLayout(2, 1));
    chipsLeftPanel.setBackground(Color.BLACK);
    chipsLeftPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    JLabel chipsLeft = new JLabel("Items Remaining", JLabel.CENTER);
    chipsLeft.setForeground(Color.ORANGE);
    chipsLeft.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
    chipsLeft.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    chipsLeftPanel.add(chipsLeft);
    chipsLeftPanel.setMinimumSize(new Dimension(240, 50));
    chipsLeftPanel.setPreferredSize(new Dimension(240, 100));
    chipsLeftPanel.setMaximumSize(new Dimension(240, 100));

    chipsLeftLabel = new JLabel("", JLabel.CENTER);
    chipsLeftLabel.setForeground(Color.WHITE);
    chipsLeftLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    chipsLeftLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
    chipsLeftPanel.add(chipsLeftLabel);

    itemsPanel = new JPanel();
    itemsPanel.setBackground(Color.BLACK);
    itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
    itemsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    JLabel items = new JLabel("Inventory", JLabel.CENTER);
    items.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
    items.setForeground(Color.ORANGE);
    items.setAlignmentX(JLabel.CENTER_ALIGNMENT);
    itemsPanel.add(items);

    itemsPanel.setMinimumSize(new Dimension(240, 120));
    itemsPanel.setPreferredSize(new Dimension(240, 120));
    itemsPanel.setMaximumSize(new Dimension(240, 120));

    informationPanel.add(levelPanel);
    informationPanel.add(timePanel);
    informationPanel.add(chipsLeftPanel);
    informationPanel.add(itemsPanel);

    rightPanel.add(informationPanel, BorderLayout.CENTER);

    rightPanel.add(movementPanel, BorderLayout.SOUTH);
    movementPanel.setPreferredSize(new Dimension(240, 120));

    mainPanel.add(rightPanel, BorderLayout.EAST);
    mainPanel.add(gamePanel, BorderLayout.CENTER);

    informationPanel.setPreferredSize(new Dimension(240, 480));

    gamePanel.setBackground(Color.LIGHT_GRAY);
    Color firstColor = new Color(25, 25, 112);
    mainPanel.setBackground(firstColor);


    informationPanel.setBackground(Color.LIGHT_GRAY);
    informationPanel.setBorder(BorderFactory.createRaisedBevelBorder());
    movementPanel.setBorder(BorderFactory.createRaisedBevelBorder());


    // Window Close Listener
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        int closeDialogButton = JOptionPane.YES_NO_OPTION;
        int closeDialogResult = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to exit? Game progress will NOT be saved.",
            "Warning",
            closeDialogButton);
        if (closeDialogResult == JOptionPane.YES_OPTION) {
          dispose();
          setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          System.exit(0);
        } else {
          setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
      }
    });


    movementPanel.add(playback);
    movementPanel.add(upButton);
    movementPanel.add(stepToNext);
    movementPanel.add(leftButton);
    movementPanel.add(downButton);
    movementPanel.add(rightButton);

    upButton.setFocusable(false);
    downButton.setFocusable(false);
    leftButton.setFocusable(false);
    rightButton.setFocusable(false);
    playback.setFocusable(false);
    stepToNext.setFocusable(false);

    renderPanel = new RenderPanel(9, 9, 0);
    this.setRenderPanel(renderPanel);

    getContentPane().add(mainPanel);
    addKeyListener(this);
    setLocationByPlatform(true);

    pack();
    setVisible(true);

    gamePauseMenu.addActionListener(e -> {
      if (currentGame != null) {
        boolean paused;

        if (gamePaused) {
          paused = false;
          onPauseGame(false);
        } else {
          paused = true;
          onPauseGame(true);
        }
        gamePauseMenu.setState(paused);
      }

    });
  }


  private void createButtons() {
    upButton = new JButton("⇑");
    upButton.setToolTipText("Move Chap Up");
    upButton.addActionListener(e -> {
      if (application.getState().equals(Application.GameStates.RUNNING) && !gamePaused) {
        currentGame.onMovement(Tile.Directions.Up);
      }
    });

    downButton = new JButton("⇓");
    downButton.setToolTipText("Move Chap Down");
    downButton.addActionListener(e -> {
      if (application.getState().equals(Application.GameStates.RUNNING) && !gamePaused) {
        currentGame.onMovement(Tile.Directions.Down);
      }
    });

    leftButton = new JButton("⇐");
    leftButton.setToolTipText("Move Chap to the Left");
    leftButton.addActionListener(e -> {
      if (application.getState().equals(Application.GameStates.RUNNING) && !gamePaused) {
        currentGame.onMovement(Tile.Directions.Left);
      }
    });

    rightButton = new JButton("⇒");
    rightButton.setToolTipText("Move Chap to the Right");
    rightButton.addActionListener(e -> {
      if (application.getState().equals(Application.GameStates.RUNNING) && !gamePaused) {
        currentGame.onMovement(Tile.Directions.Right);
      }
    });

    playback = new JButton("\t\uD83C\uDFC3");
    playback.setToolTipText("Playback Recorded Moves");


    playback.addActionListener(e -> {
      long value;
      String delaySpeedString = JOptionPane.showInputDialog(this, "Enter playback delay speed: (milliseconds)");
      if (delaySpeedString != null) {
        value = Long.parseLong(delaySpeedString);
        RecordReplay.setDelay(value);
      }

      RecordReplay.runReplay(currentGame);
    });


    stepToNext = new JButton("⏭");
    stepToNext.setToolTipText("Step to Next Recorded Movement");

    stepToNext.addActionListener(e -> RecordReplay.iterateReplay(currentGame));
  }


  /**
   * Help Menu showing how to play the game.
   *
   * @return Help Menu Panel Component
   */
  public JMenuItem displayHelpMenu() {
    final JMenuItem howToPlayMenu = new JMenuItem("How to Play");
    howToPlayMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
    howToPlayMenu.addActionListener(e -> {

      // Panels
      JPanel primaryOptionPaneField = new JPanel();
      JPanel firstInnerPanel = new JPanel();
      JPanel secondInnerPanel = new JPanel();

      // Layouts
      BorderLayout optionPaneLayout = new BorderLayout();
      optionPaneLayout.setHgap(60);
      optionPaneLayout.setVgap(20);
      primaryOptionPaneField.setLayout(optionPaneLayout);

      GridLayout InnerPanelInfoLayout = new GridLayout(0, 1);
      firstInnerPanel.setLayout(InnerPanelInfoLayout);
      secondInnerPanel.setLayout(InnerPanelInfoLayout);


      JLabel titleTextLabel = new JLabel("Hello and welcome to Chaps Challenge");
      titleTextLabel.setFont(titleTextLabel.getFont().deriveFont(
          titleTextLabel.getFont().getStyle() | Font.BOLD));
      primaryOptionPaneField.add(titleTextLabel, BorderLayout.NORTH);

      JLabel controlsHeadingLabel = new JLabel("Controls");
      controlsHeadingLabel.setFont(controlsHeadingLabel.getFont().deriveFont(
          controlsHeadingLabel.getFont().getStyle() | Font.BOLD));
      firstInnerPanel.add(controlsHeadingLabel);

      JLabel generalControlsSubtitleLabel = new JLabel("   General ");
      generalControlsSubtitleLabel.setFont(generalControlsSubtitleLabel.getFont().deriveFont(
          generalControlsSubtitleLabel.getFont().getStyle() | Font.ITALIC));
      firstInnerPanel.add(generalControlsSubtitleLabel);
      firstInnerPanel.add(new JLabel("      Pause Game - [Space]"));
      firstInnerPanel.add(new JLabel("      Unpause Game - [Escape]"));
      firstInnerPanel.add(new JLabel("      New Game from First level - [Ctrl-1]"));
      firstInnerPanel.add(new JLabel("      Exit Game without Saving - [Ctrl-X]"));
      firstInnerPanel.add(new JLabel(""));
      JLabel gameplayControlsSubtitleLabel = new JLabel("   Gameplay ");
      gameplayControlsSubtitleLabel.setFont(gameplayControlsSubtitleLabel.getFont().deriveFont(
          gameplayControlsSubtitleLabel.getFont().getStyle() | Font.ITALIC));
      firstInnerPanel.add(gameplayControlsSubtitleLabel);
      firstInnerPanel.add(new JLabel("      Movement Up - [Up Arrow]"));
      firstInnerPanel.add(new JLabel("      Movement Down - [Down Arrow]"));
      firstInnerPanel.add(new JLabel("      Movement Left - [Left Arrow]"));
      firstInnerPanel.add(new JLabel("      Movement Right - [Right Arrow]"));
      firstInnerPanel.add(new JLabel(" "));

      JLabel itemsHeadingLabel = new JLabel("Items");
      itemsHeadingLabel.setFont(
          itemsHeadingLabel.getFont().deriveFont(
              itemsHeadingLabel.getFont().getStyle() | Font.BOLD));
      secondInnerPanel.add(itemsHeadingLabel);

      JLabel chipItemLabel = new JLabel(
          "Chap - Your Player Character");
      chipItemLabel.setIcon(TileFinder.getTile("chip_icon", -1));
      secondInnerPanel.add(chipItemLabel);
      JLabel chipItemLabel2 = new JLabel(
          "Room Portals - These must be opened using a key with the same colour");
      chipItemLabel2.setIcon(TileFinder.getTile("door_icon", -1));
      secondInnerPanel.add(chipItemLabel2);
      JLabel chipItemLabel3 = new JLabel(
          "Crystals - These are used to open doors corresponding with their colour");
      chipItemLabel3.setIcon(TileFinder.getTile("key_icon", -1));
      secondInnerPanel.add(chipItemLabel3);
      JLabel chipItemLabel4 = new JLabel(
          "Shards - These must be collected to open the exit gate");
      chipItemLabel4.setIcon(TileFinder.getTile("computer_chip_icon", -1));
      secondInnerPanel.add(chipItemLabel4);
      JLabel chipItemLabel5 = new JLabel(
          "Exit Gate - All of these shards must be collected to reach the exit portal");
      chipItemLabel5.setIcon(TileFinder.getTile("exit_lock_icon", -1));
      secondInnerPanel.add(chipItemLabel5);
      JLabel chipItemLabel6 = new JLabel("Exit Portal - This portal is used to finish the level");
      chipItemLabel6.setIcon(TileFinder.getTile("exit_icon", -1));
      secondInnerPanel.add(chipItemLabel6);
      JLabel chipItemLabel7 = new JLabel("Cyclops - Very scary, avoid!");
      chipItemLabel7.setIcon(TileFinder.getTile("cyclops_icon", -1));
      secondInnerPanel.add(chipItemLabel7);

      primaryOptionPaneField.add(firstInnerPanel, BorderLayout.WEST);
      primaryOptionPaneField.add(secondInnerPanel, BorderLayout.EAST);

      JPanel infoText = new JPanel();
      GridLayout layout3 = new GridLayout(0, 1);
      infoText.setLayout(layout3);

      JLabel subtitle3 = new JLabel("Game Objective ");
      subtitle3.setFont(subtitle3.getFont().deriveFont(subtitle3.getFont().getStyle() | Font.BOLD));
      infoText.add(subtitle3);
      infoText.add(new JLabel(
          "The objective of the game is for Chap (The player character) to reach the"
              + " exit portal within the amount of time allocated."));
      infoText.add(new JLabel("This is done by collecting all the shards scattered around"
          + " the level in order to open the exit gate."));
      infoText.add(new JLabel("Several of these shards are hidden in various rooms."
          + " In order to access these rooms, room portals must be unlocked using crystals"
          + " corresponding to the portal colours."));
      primaryOptionPaneField.add(infoText, BorderLayout.SOUTH);

      onPauseGame(true);
      JOptionPane.showMessageDialog(this, primaryOptionPaneField, "How to Play",
          JOptionPane.PLAIN_MESSAGE);
      onPauseGame(false);
    });
    return howToPlayMenu;
  }


  /**
   * About Menu showing Software Version and Contributors.
   *
   * @return About Menu Panel Component
   */
  public JMenuItem displayAboutMenu() {
    final JMenuItem aboutMenu = new JMenuItem("About");
    aboutMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
    aboutMenu.addActionListener(e -> {
      JPanel fieldPanel = new JPanel(new GridLayout(2, 1));

      JLabel titleLabel = new JLabel("Chaps Challenge, Version 1.0");
      titleLabel.setFont(titleLabel.getFont().deriveFont(titleLabel.getFont().getStyle() | Font.BOLD));
      titleLabel.setIcon(TileFinder.getTile("chip_icon", -1));
      fieldPanel.add(titleLabel);


      JPanel subPanel = new JPanel(new GridLayout(0, 1));

      JLabel subtitleLabel = new JLabel("A SWEN225 group project by: ");
      subtitleLabel.setFont(subtitleLabel.getFont().deriveFont(subtitleLabel.getFont().getStyle() | Font.ITALIC));
      subPanel.add(subtitleLabel);
      subPanel.add(new JLabel("Vaibhav Ekambaram - Application"));
      subPanel.add(new JLabel("Baxter Kirikiri - Maze"));
      subPanel.add(new JLabel("Cameron Li - Renderer"));
      subPanel.add(new JLabel("Rahul Mahasuriya - Persistence"));
      subPanel.add(new JLabel("Tyla Turner - Record and Replay"));
      subPanel.add(new JLabel("Sushil Sharma - Monkey Tests"));
      fieldPanel.add(subPanel);

      onPauseGame(true);
      JOptionPane.showMessageDialog(null, fieldPanel, "About", JOptionPane.PLAIN_MESSAGE);
      onPauseGame(false);
    });
    return aboutMenu;
  }


  public void sendMessage(String message) {
    if (application.getState().equals(Application.GameStates.RUNNING)) {
      onPauseGame(true);
      JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.PLAIN_MESSAGE);
      onPauseGame(false);
    }
  }


  public void updateInventory() {
    if (itemsGrid != null) {
      itemsPanel.remove(itemsGrid);
    }

    if (currentGame != null) {
      itemsGrid = new JPanel(new GridLayout(0, 5));
      itemsGrid.setBackground(Color.BLACK);
      ArrayList<String> inventory = (ArrayList<String>) currentGame.getPlayer().getInventory();

      for (String s : inventory) {
        itemsGrid.add(new JLabel(new ImageIcon(TileFinder.getTile(s, -1).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)), JLabel.CENTER));
      }
      itemsPanel.add(itemsGrid);
    }
  }


  /**
   * Generate a New Game.
   * This class loads from the file using the persistence model.
   */
  public void onNewGame() {
    onStopGame(false);
    gamePaused = false;
    currentGame = null;
    Persistence p = new Persistence();
    Board board = p.loadFile("Program/src/levels/level1.json");
    int tileset = (int) (Math.random() * 2);
    currentGame = new Game(p.getTimeLeft(), p.getLevel(), this, board, audio, tileset, application);

    List<String> inventoryStartingArray = p.setInventory();

    if (inventoryStartingArray.size() > 0) {
      currentGame.getPlayer().setInventory(inventoryStartingArray);
      updateInventory();
    }
    application.transitionToRunning();
    renderPanel.setPaused(false);
    renderPanel.setBoard(board);
  }

  /**
   * Load a Game.
   * Loads a new game from a file showing a file explorer GUI
   */
  public void onLoadGame() {
    onStopGame(false);

    gamePaused = false;
    currentGame = null;
    JFileChooser chooser = new JFileChooser("");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".json files", "json");
    chooser.setFileFilter(filter);

    int returnVal = chooser.showOpenDialog(null);
    if (returnVal == JFileChooser.APPROVE_OPTION) {


      Persistence p = new Persistence();
      Board board = p.loadFile(chooser.getSelectedFile().toString());
      int tileset = (int) (Math.random() * 2);
      currentGame = new Game(p.getTimeLeft(),
          p.getLevel(),
          this,
          board,
          audio,
          tileset,
          application);

      List<String> inventoryStartingArray = p.setInventory();

      if (inventoryStartingArray.size() > 0) {
        currentGame.getPlayer().setInventory(inventoryStartingArray);
        updateInventory();
      }
      application.transitionToRunning();
      renderPanel.setPaused(false);
      renderPanel.setBoard(board);
    }
  }


  /**
   * Load game without GUI to allow for a custom file to be loaded.
   *
   * @param filepath file path string
   */
  public void onLoadGameNoGui(String filepath) {
    onStopGame(true);

    gamePaused = false;
    currentGame = null;

    Persistence p = new Persistence();
    Board board = p.loadFile(filepath);
    int tileset = 2;
    currentGame = new Game(p.getTimeLeft(), p.getLevel(), this, board, audio, tileset, application);

    List<String> inventoryStartingArray = p.setInventory();

    if (inventoryStartingArray.size() > 0) {
      currentGame.getPlayer().setInventory(inventoryStartingArray);
      updateInventory();
    }
    application.transitionToRunning();
    renderPanel.setPaused(false);
    renderPanel.setBoard(board);
  }


  /**
   * Stop Game.
   *
   * @param isLoadingRecordedGame flag to avoid conflict with record and replay.
   */
  public void onStopGame(boolean isLoadingRecordedGame) {
    if (currentGame != null) {

      if (application.getState().equals(Application.GameStates.RUNNING)) {
        application.transitionToInit();
        currentGame.terminateTimer();
        currentGame = null;

        updateInventory();
        itemsGrid = null;
      }

      timeLabel.setText("");
      levelLabel.setText("");
      chipsLeftLabel.setText("");

    }

    onPauseGame(false);
    gamePauseMenu.setState(false);
    if (!isLoadingRecordedGame) {
      RecordReplay.endRecording();
    }

    if (renderPanel != null) {
      renderPanel.setPaused(true);
    }
  }


  /**
   * Pause the game.
   * Replace render window with
   *
   * @param value set game pause statue
   */
  public void onPauseGame(boolean value) {
    pausedLabel.setHorizontalAlignment(SwingConstants.CENTER);
    if (application.getState().equals(Application.GameStates.RUNNING)) {

      if (value) {
        gamePaused = true;
        currentGame.setGamePaused(true);
        System.out.println("pausing");
        renderPanel.setPaused(true);
      } else {
        gamePaused = false;
        currentGame.setGamePaused(false);
        renderPanel.setPaused(false);
      }
      renderPanel.repaint();
    }

    updateDisplay();
  }


  /**
   * Enable or Disable Movement Buttons
   *
   * @param value boolean value
   */
  public void setMovementButtonEnabled(boolean value) {
    upButton.setEnabled(value);
    downButton.setEnabled(value);
    leftButton.setEnabled(value);
    rightButton.setEnabled(value);
  }

  /**
   * Update display properties depending on game state.
   * Does things such as set button visibility depending on game state and pause
   */
  public void updateDisplay() {
    if (application.getState().equals(Application.GameStates.IDLE)) {
      setMovementButtonEnabled(false);
      playback.setEnabled(false);
      stepToNext.setEnabled(false);
      gamePanel.remove(pausedLabel);
      gamePaused = false;

      startRecordingMenu.setEnabled(false);
      stopRecordingMenu.setEnabled(false);
    } else if (application.getState().equals(Application.GameStates.RUNNING)) {

      setMovementButtonEnabled(!gamePaused);
      startRecordingMenu.setEnabled(true);
    }
  }

  public void updateRecordReplayGUI() {
    if (RecordReplay.getIsGameRecording()) {
      startRecordingMenu.setEnabled(false);
      stopRecordingMenu.setEnabled(true);
    } else {
      stopRecordingMenu.setEnabled(false);
      startRecordingMenu.setEnabled(true);
    }
  }


  public void levelCompleteMessage(int levelNumber, int timeRemaining, int time, int chipCount) {
    if (currentGame != null) {
      if (application.getState().equals(Application.GameStates.RUNNING)) {
        application.transitionToInit();
        currentGame.terminateTimer();
      }
      onPauseGame(false);
      gamePauseMenu.setState(false);
    }

    String[] options = new String[]{"Next Level", "Play Again", "Save and Exit", "Exit"};

    JPanel fields = new JPanel(new GridLayout(0, 1));
    fields.add(new JLabel("You have completed level " + levelNumber));
    fields.add(new JLabel("You collected " + chipCount + " items in " + time + " seconds (" + timeRemaining + " seconds remaining)"));
    int response = JOptionPane.showOptionDialog(this, fields, "Level Complete!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

    if (response >= 0 && response <= 3) {
      if (response == 0) {
        lm.incrementLevel();
        onLoadGameNoGui(lm.getCurrentLevel());
      } else if (response == 1) {
        onNewGame();
      } else if (response == 2) {
        lm.incrementLevel();
        lm.saveLevel();

        onStopGame(false);
        dispose();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.exit(0);
      } else if (response == 3) {
        onStopGame(false);
      }
    }
  }


  /**
   * Out of time.
   * Display message when the player has run out of time
   */
  public void outOfTime(String title, String message) {
    if (currentGame != null) {
      if (application.getState().equals(Application.GameStates.RUNNING)) {
        application.transitionToInit();
        currentGame.terminateTimer();
      }
      onPauseGame(false);
      gamePauseMenu.setState(false);
    }

    String[] options = new String[]{"Play Again", "Exit"};
    JPanel fields = new JPanel(new GridLayout(0, 1));
    fields.add(new JLabel(message));
    int response = JOptionPane.showOptionDialog(this,
        fields,
        title,
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.PLAIN_MESSAGE,
        null,
        options,
        options[0]);
    timeLabel.setText("");
    chipsLeftLabel.setText("");
    levelLabel.setText("");
    if (response == 0) {
      onLoadGameNoGui(lm.getCurrentLevel());
    } else if (response == 1) {
      onStopGame(false);
    }
  }


  /**
   * Add the Render Panel to the Board.
   *
   * @param renderPanel render Panel created in game class
   */
  public void setRenderPanel(RenderPanel renderPanel) {
    this.renderPanel = renderPanel;
    if (renderPanel != null) {
      gamePanel.add(renderPanel, BorderLayout.CENTER);
    }
  }


  /**
   * Key Typed - Doesn't really do anything.
   *
   * @param e key event
   */
  @Override
  public void keyTyped(KeyEvent e) {
  }

  /**
   * Detect Key Presses for keyboard shortcuts and perform actions if applicable.
   *
   * @param e key event
   */
  @Override
  public void keyPressed(KeyEvent e) {

    switch (e.getKeyCode()) {
      case 32:
        onPauseGame(true);
        break;
      case 27:
        onPauseGame(false);
        break;
      case 38:
        if (application.getState().equals(Application.GameStates.RUNNING) && !gamePaused) {
          currentGame.onMovement(Tile.Directions.Up);
        }
        break;
      case 40:
        if (application.getState().equals(Application.GameStates.RUNNING) && !gamePaused) {
          currentGame.onMovement(Tile.Directions.Down);
        }
        break;
      case 37:
        if (application.getState().equals(Application.GameStates.RUNNING) && !gamePaused) {
          currentGame.onMovement(Tile.Directions.Left);
        }
        break;
      case 39:
        if (application.getState().equals(Application.GameStates.RUNNING) && !gamePaused) {
          currentGame.onMovement(Tile.Directions.Right);
        }
        break;
      default:
        break;
    }
  }


  /**
   * Detect Key Released.
   * Doesn't do anything
   *
   * @param e key event
   */
  @Override
  public void keyReleased(KeyEvent e) {
    // doesn't do anything
  }


  // -----------------------------------------------------------------------------------------------
  // Get and Set Methods
  // -----------------------------------------------------------------------------------------------

  public RenderPanel getRenderPanel() {
    return renderPanel;
  }

  /**
   * Get Current game.
   *
   * @return game
   */
  public Game getCurrentGame() {
    return currentGame;
  }

  /**
   * Get Level Label for game to update.
   *
   * @return level label
   */
  public JLabel getLevelLabel() {
    return levelLabel;
  }

  /**
   * Get Time Level for game to update.
   *
   * @param number time value
   */
  public void setTimeLabel(int number) {
    timeLabel.setText(String.valueOf(number));
  }

  public JLabel getTimeLabel() {
    return timeLabel;
  }

  /**
   * Set the number of chips left.
   *
   * @param number chip count
   */
  public void setChipsLeftLabel(int number) {
    chipsLeftLabel.setText(String.valueOf(number));
  }
}
