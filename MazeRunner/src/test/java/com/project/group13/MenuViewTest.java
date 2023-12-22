package com.project.group13;

import com.project.group13.backend.handler.InputHandler;
import com.project.group13.frontend.components.StyledBtn;
import com.project.group13.frontend.view.MenuView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import javax.swing.*;

import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test suite for MenuView.
 */
public class MenuViewTest {

    private MenuView menuView;
    private JFrame frame;
    private InputHandler inputHandler;

    @BeforeEach
    public void setUp() {
        // Initialize the components required by MenuView
        frame = new JFrame();
        inputHandler = new InputHandler();
        menuView = new MenuView(inputHandler);
    }

    @Test
    public void testButtonInitialization() {
        StyledBtn[] buttons = menuView.getButtonList();

        // Check if the correct number of buttons are initialized
        assertNotNull(buttons);
        assertEquals(5, buttons.length, "Menu should have 5 buttons");

        // Check the text of each button
        assertEquals("Start", buttons[0].getText(), "First button should be Start");
        assertEquals("Leaderboard", buttons[1].getText(), "Second button should be Leaderboard");
        assertEquals("Settings", buttons[2].getText(), "Third button should be Settings");
        assertEquals("Instructions", buttons[3].getText(), "Fourth button should be Instructions");
        assertEquals("Exit", buttons[4].getText(), "Fifth button should be Exit");
    }

    @Test
    public void testStartButtonAction() {
        StyledBtn startButton = menuView.getButtonList()[0];
        ActionListener listenerMock = mock(ActionListener.class);
        startButton.setActionListener(listenerMock);

        SwingUtilities.invokeLater(startButton::act);

        verify(listenerMock, timeout(1000)).actionPerformed(any());
    }

    @Test
    public void testLeaderboardButtonAction() {
        StyledBtn leaderboardButton = menuView.getButtonList()[1];
        ActionListener listenerMock = mock(ActionListener.class);
        leaderboardButton.setActionListener(listenerMock);

        SwingUtilities.invokeLater(leaderboardButton::act);

        verify(listenerMock, timeout(1000)).actionPerformed(any());
    }

    @Test
    public void testSettingsButtonAction() {
        StyledBtn settingsButton = menuView.getButtonList()[2];
        ActionListener listenerMock = mock(ActionListener.class);
        settingsButton.setActionListener(listenerMock);

        SwingUtilities.invokeLater(settingsButton::act);

        verify(listenerMock, timeout(1000)).actionPerformed(any());
    }

    @Test
    public void testInstructionsButtonAction() {
        StyledBtn instructionsButton = menuView.getButtonList()[3];
        ActionListener listenerMock = mock(ActionListener.class);
        instructionsButton.setActionListener(listenerMock);

        SwingUtilities.invokeLater(instructionsButton::act);

        verify(listenerMock, timeout(1000)).actionPerformed(any());
    }

    @Test
    public void testExitButtonAction() {
        StyledBtn exitButton = menuView.getButtonList()[4];
        ActionListener listenerMock = mock(ActionListener.class);
        exitButton.setActionListener(listenerMock);

        SwingUtilities.invokeLater(exitButton::act);

        verify(listenerMock, timeout(1000)).actionPerformed(any());
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources
        frame.dispose();
    }
}
