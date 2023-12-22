
package com.project.group13.frontend.view;

import com.project.group13.backend.ResLoader;
import com.project.group13.backend.SoundManager;
import com.project.group13.backend.ctrl.CtrlFactory;
import com.project.group13.backend.handler.InputHandler;
import com.project.group13.frontend.components.StyledBtn;

import javax.swing.*;
import java.awt.*;

/**
 * MenuView extends BaseView to display the main menu of the game. It provides options to start the game,
 * view the leaderboard, change settings, and exit the game. Each button is styled and reacts to mouse
 * events to improve user experience. The background and layout are set to fit the theme of the game.
 * The view is initialized with the required components and makes itself visible upon creation.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class MenuView extends BaseView {

    private Image bg; // Background image for the menu view.
    private StyledBtn startBtn, leaderboardBtn, settingsBtn, instructionBtn, exitBtn;
    private Font samurai, samuraiHighlight, nort;

    /**
     * Constructor for MenuView that takes an InputHandler.
     * It initializes the view's components and makes the view visible.
     * 
     * @param inputHandler The handler for user input.
     */
    public MenuView(InputHandler inputHandler) {
        super(inputHandler);
        init(); // Initialize the components.
        showView(); // Display the view.
    }

    /**
     * Constructor for MenuView that takes a JFrame and an InputHandler.
     * It uses the provided JFrame and initializes the view's components.
     * 
     * @param jFrame The frame in which the view is displayed.
     * @param inputHandler The handler for user input.
     */
    public MenuView(JFrame jFrame, InputHandler inputHandler) {
        super(jFrame, inputHandler);
        init(); // Initialize the components.
        revalidateView(); // Revalidate the view.
    }

    /********************** Getter/Setter ***********************/

    public StyledBtn[] getButtonList() {
        return new StyledBtn[]{startBtn, leaderboardBtn, settingsBtn, instructionBtn, exitBtn};
    }

    /********************** Drawing methods ***********************/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass method to ensure proper JPanel painting.
        g.drawImage(bg, 0, 0, BaseView.GAME_WIDTH, BaseView.GAME_HEIGHT, this);

        drawGameTitle(g);
        drawBtn(g, startBtn);
        drawBtn(g, leaderboardBtn);
        drawBtn(g, settingsBtn);
        drawBtn(g, instructionBtn);
        drawBtn(g, exitBtn);
    }

    /**
     * Draws game title
     * 
     * @param g graphics object
     */
    public void drawGameTitle(Graphics g) {
        g.setFont(samuraiHighlight);
        g.setColor(Color.WHITE);
        g.drawString("Maze Runner", 93, 148);
        g.setFont(samurai);
        g.setColor(Color.RED);
        g.drawString("Maze Runner", 100, 150);
    }

    /**
     * Draw button
     * @param g graphics object
     * @param button button object
     */
    public void drawBtn(Graphics g, StyledBtn button) {
        g.drawImage(
                button.getImage(),
                button.getBounds().x,
                button.getBounds().y,
                button.getBounds().width,
                button.getBounds().height,
                this
        );

        // Center the text horizontally and vertically within the button
        FontMetrics fm = g.getFontMetrics(nort);
        int textX = button.getBounds().x + (button.getBounds().width - fm.stringWidth(button.getText())) / 2;
        int textY = button.getBounds().y + ((button.getBounds().height - fm.getHeight()) / 2) + fm.getAscent();

        g.setFont(nort);
        g.setColor(Color.BLACK);
        g.drawString(button.getText(), textX, textY);

    }

    /********************** Internal Methods ***********************/

    /**
     * Initializes the view, setting up the layout, background, and components.
     * This method acts as the central setup function for the MenuView.
     */
    private void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        this.bg = ResLoader.loadSprite("menuBg1.png");
        initializeFonts();
        initializeButtons();
    }

    /**
     * Loads and sets up the fonts used in the MenuView.
     */
    private void initializeFonts() {
        this.samuraiHighlight = ResLoader.loadFont("SamuraiBlast.otf", Font.BOLD, 90);
        this.samurai = ResLoader.loadFont("SamuraiBlast.otf", Font.BOLD, 88);
        this.nort = ResLoader.loadFont("NortRight.otf", Font.BOLD, 24);
    }

    /**
     * Initializes and sets up the buttons used in the MenuView.
     * This includes setting up their size, position, and action listeners.
     */
    private void initializeButtons() {
        int btnWidth = BaseView.GAME_WIDTH / 4;
        int btnHeight = BaseView.GAME_HEIGHT / 10;
        int vBtnMargin = btnHeight / 4;
        int btnStartY = BaseView.GAME_HEIGHT / 3;
        int btnStartX = (BaseView.GAME_WIDTH / 2) - (btnWidth / 2);

        // Set up each button with text, bounds, and actions.
        this.startBtn = createButton("Start", new Rectangle(btnStartX, btnStartY, btnWidth, btnHeight));
        setButtonAction(this.startBtn, () -> {
            SoundManager.playClickSound();
            hideJFrame();
            CtrlFactory.createGameCtrlInstance(getFrame());
        });

        this.leaderboardBtn = createButton("Leaderboard", new Rectangle(btnStartX, btnStartY + btnHeight + vBtnMargin, btnWidth, btnHeight));
        setButtonAction(this.leaderboardBtn, () -> {
            SoundManager.playClickSound();
            hideJFrame();
            CtrlFactory.createLeaderboardCtrlInstance(getFrame());
        });

        this.settingsBtn = createButton("Settings", new Rectangle(btnStartX, btnStartY + (btnHeight * 2) + (vBtnMargin * 2), btnWidth, btnHeight));
        setButtonAction(this.settingsBtn, () -> {
            SoundManager.playClickSound();
            hideJFrame();
            CtrlFactory.createSettingsCtrlInstance(getFrame());
        });

        this.instructionBtn = createButton("Instructions", new Rectangle(btnStartX, btnStartY + (btnHeight * 3) + (vBtnMargin * 3), btnWidth, btnHeight));
        setButtonAction(this.instructionBtn, () -> {
            SoundManager.playClickSound();
            hideJFrame();
            CtrlFactory.createInstructionsCtrlInstance(getFrame());
        });

        this.exitBtn = createButton("Exit", new Rectangle(btnStartX, btnStartY + (btnHeight * 4) + (vBtnMargin * 4), btnWidth, btnHeight));
        setButtonAction(this.exitBtn, () -> {
            SoundManager.playClickSound();
            disposeJFrame();
            System.exit(1);
        });
    }

    /**
     * Creates a styled button with specified text and bounds.
     *
     * @param text The text displayed on the button.
     * @param bounds The dimensions and position of the button.
     * @return StyledBtn The created styled button.
     */
    private StyledBtn createButton(String text, Rectangle bounds) {
        StyledBtn button = new StyledBtn(text, bounds);
        return button;
    }

    /**
     * Sets an action for a button. The action is encapsulated in a Runnable for flexibility.
     *
     * @param button The button to which the action is to be set.
     * @param action The action to be performed when the button is clicked.
     */
    private void setButtonAction(StyledBtn button, Runnable action) {
        button.setActionListener(e -> action.run());
    }

    /**
     * Shows the view,
     * after initializing the jFrame
     */
    private void showView() {
        initJFrame();
        showJFrame();
    }

    /**
     * Shows the view,
     * after revalidating the jFrame
     */
    private void revalidateView() {
        revalidateJFrame();
        showJFrame();
    }

}