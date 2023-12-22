package com.project.group13;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.LevelManager;
import com.project.group13.backend.model.levels.Level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class LevelManagerTest {

    private LevelManager levelManager;

    @Mock
    private GameModel mockGameModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        levelManager = new LevelManager(mockGameModel);
    }

    @Test
    void testInitialLevel() {
        // Test that the initial level is set correctly
        assertEquals(1, levelManager.getLevel());
    }

    @Test
    void testNextLevel() {
        // The game has more than one level
        levelManager.nextLevel();
        assertEquals(2, levelManager.getLevel());
    }

    @Test
    void testPreviousLevel() {
        levelManager.nextLevel();
        levelManager.previousLevel();
        assertEquals(1, levelManager.getLevel());
    }

    @Test
    void testResetLevel() {
        levelManager.nextLevel();
        levelManager.resetLevel();
        assertEquals(1, levelManager.getLevel());
    }

    @Test
    void testUpdateMap() {
        levelManager.nextLevel();
        Level currentLevel = levelManager.getLevelMap();
        assertNotNull(currentLevel);
    }
}
