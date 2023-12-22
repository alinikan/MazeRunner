package com.project.group13;

import com.project.group13.backend.BackendCtrl;
import com.project.group13.backend.model.GameState;
import com.project.group13.backend.model.*;
import com.project.group13.backend.model.gameobjects.Player;
import com.project.group13.backend.model.LevelManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class GameStateTest {

    @Mock
    private GameModel gameModelMock;
    @Mock
    private LivesManager livesManagerMock;
    @Mock
    private ScoreManager scoreManagerMock;
    @Mock
    private LevelManager levelManagerMock;
    @Mock
    private TimeManager timeManagerMock;

    private BackendCtrl backendCtrl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a mock Player
        Player mockPlayer = mock(Player.class);

        when(gameModelMock.getPlayer()).thenReturn(mockPlayer);
        when(gameModelMock.getLivesManager()).thenReturn(livesManagerMock);
        when(gameModelMock.getScoreManager()).thenReturn(scoreManagerMock);
        when(gameModelMock.getLevelManager()).thenReturn(levelManagerMock);
        when(gameModelMock.getTimeManager()).thenReturn(timeManagerMock);

        backendCtrl = new BackendCtrl(gameModelMock);
    }

    @Test
    void testBackendCtrlInteractionsWithGameModel() {
        // Simulate a scenario in your game and verify interactions
        backendCtrl.nextLevel();

        // Verify if resetGame() method was called as part of nextLevel()
        verify(livesManagerMock).setLives(3);
        verify(gameModelMock).getLevelManager();
        verify(levelManagerMock).nextLevel();

        // Verify GameState change
        assertEquals(GameState.IDLE, BackendCtrl.currState);
    }

    @Test
    void testPlayerCollisionWithRewards() {
        backendCtrl.checkRewardsCollision();

        // Verify interactions with rewards
        verify(gameModelMock).getRewards();
    }

    @Test
    void testPlayerCollisionWithEnemies() {
        backendCtrl.checkEnemiesCollision();

        // Verify interactions with enemies
        verify(gameModelMock).getEnemies();
    }

    @Test
    void testPlayerCollisionWithBarriers() {
        backendCtrl.checkBarriersCollision();

        // Verify interactions with barriers
        verify(gameModelMock).getBarriers();
    }

    @Test
    void testUpdateObjectsPosition() {
        double deltaTime = 1.0; // Example time delta
        backendCtrl.updateObjectsPosition(deltaTime);

        // Verify updatePosition calls for enemies and barriers
        verify(gameModelMock).getEnemies();
        verify(gameModelMock).getBarriers();
    }

    @Test
    void testSpawnSpecialRewards() {
        backendCtrl.spawnSpecialRewards();

        // Verify the logic of spawning special rewards
        verify(gameModelMock).getRewards();
    }

    @Test
    void testUpdateUpTimeForSpecialRewards() {
        double elapsed = 5.0; // Example elapsed time
        backendCtrl.updateUpTimeForSpecialRewards(elapsed);

        // Verify time update logic for special rewards
        verify(gameModelMock).getRewards();
        // Assert based on expected behavior
    }

    @Test
    void testCheckTimeUpForSpecialReward() {
        backendCtrl.checkTimeUpForSpecialReward();

        // Verify time-up checking logic for special rewards
        verify(gameModelMock).getRewards();
        // Assert based on expected behavior
    }


    @Test
    void testResetLevelChangesGameState() {
        backendCtrl.resetLevel();

        // Verify GameState transition and level reset logic
        assertEquals(GameState.IDLE, BackendCtrl.currState);
        verify(gameModelMock).getLevelManager();
        verify(levelManagerMock).resetLevel();
    }

}
