package com.project.group13.frontend.components;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a styled toggle button for graphical user interfaces.
 * This button supports toggling between states and can trigger specified actions.
 * 
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0 
 */
public class StyledToggleBtn implements SButton {
    private final Rectangle bounds;
    private BtnState curr_state;
    private String btn_text;
    private ActionListener actionListener = null;
    private boolean isOn;

    /**
     * Creates styled button
     * 
     * @param btnText text for the button
     * @param bounds pass button bounds
     * @param isOn is button on
     */
    public StyledToggleBtn(String btnText, Rectangle bounds, boolean isOn) {
        this.bounds = bounds;
        this.curr_state = BtnState.IDLE;
        this.btn_text = btnText;
        this.isOn = isOn;
    }

    /********************** Functions/Getters/Setters ***********************/

    /**
     * Gets button bounds
     * 
     * @return rectangle bounds
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Checks if the given coordinate is in bounds
     * 
     * @param x x position
     * @param y y position
     * @return true if is in bounds otherwise false
     */
    public boolean isInBounds(int x, int y) {
        return x >= bounds.getX() && x <= bounds.getX()+bounds.getWidth() && y >= bounds.getY() && y <= bounds.getY()+bounds.getHeight();
    }

    /**
     * Gets button state
     * 
     * @return button state
     */
    public BtnState getState() {
        return curr_state;
    }

    /**
     * Sets the current button state
     * 
     * @param state button state
     */
    public void setState(BtnState state) {
        this.curr_state = state;
    }

    /**
     * returns background image for the button based upon the current state
     * 
     * @return background image
     */
    public Image getImage() {
        if (isOn && curr_state != BtnState.HOVER)
            return BtnRes.IDLE;
        else if (!isOn && curr_state != BtnState.HOVER)
            return BtnRes.DOWN;
        else
            return BtnRes.HOVER;
    }

    /**
     * returns button text
     * 
     * @return text
     */
    public String getText() {
        return btn_text;
    }

    /**
     * sets button text
     * 
     * @param text text
     */
    public void setText(String text) {
        this.btn_text = text;
    }

    /**
     * sets action for this button
     * 
     * @param listener action listener
     */
    public void setActionListener(ActionListener listener) {
        this.actionListener = listener;
    }

    /**
     * Performs the action assigned to this button.
     * This method is triggered when the button is activated.
     */
    public void act() {
        if (actionListener != null) {
            isOn = !isOn;
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "click"));
        }
    }

}