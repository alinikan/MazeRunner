package com.project.group13.frontend.components;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Styled button interface
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public interface SButton {

    /**
     * Enumeration representing the possible states of a button.
     */
    public enum BtnState {
        /** The button is in idle state, not being interacted with. */
        IDLE,

        /** The button is in hover state, with the cursor over it. */
        HOVER,

        /** The button is in down state, indicating it is being pressed. */
        DOWN
    }

    /**
     * Sets an action listener for the button.
     *
     * @param listener The ActionListener to handle button actions.
     */
    public void setActionListener(ActionListener listener);

    /**
     * Executes the action associated with the button.
     */
    public void act();

    /**
     * Gets the bounds of the button.
     *
     * @return The bounding rectangle of the button.
     */
    public Rectangle getBounds();

    /**
     * Gets the image representing the button's current state.
     *
     * @return The image of the button.
     */
    public Image getImage();

    /**
     * Gets the text displayed on the button.
     *
     * @return The text of the button.
     */
    public String getText();

    /**
     * Checks if the specified coordinates are within the bounds of the button.
     *
     * @param x The x-coordinate to check.
     * @param y The y-coordinate to check.
     * @return True if the coordinates are within the bounds of the button, false otherwise.
     */
    public boolean isInBounds(int x, int y);

    /**
     * Sets the state of the button.
     *
     * @param state The new state of the button.
     */
    public void setState(BtnState state);

    /**
     * Gets the current state of the button.
     *
     * @return The current state of the button.
     */
    public BtnState getState();

    /**
     * Sets the text to be displayed on the button.
     *
     * @param text The text to set on the button.
     */
    public void setText(String text);
}