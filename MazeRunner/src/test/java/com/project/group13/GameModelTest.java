package com.project.group13;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {

    private GameModel gameModel;

    @BeforeEach
    void setUp() {
        gameModel = new GameModel();
        gameModel.getEnemies().clear(); // Clear existing enemies
        gameModel.getRewards().clear(); // Clear existing rewards
    }

    @Test
    void testInitialization() {
        assertNotNull(gameModel.getPlayer());
        assertTrue(gameModel.getEnemies().isEmpty());
        assertTrue(gameModel.getRewards().isEmpty());
    }

    @Test
    void testPlayerManagement() {
        Player player = gameModel.getPlayer();
        // Simulate damage to the player and test health changes
        int initialHealth = player.getHealth();
        player.damage(10);
        assertTrue(player.getHealth() < initialHealth);
    }

    @Test
    void testEnemyManagement() {
        Enemy enemy = new SpikeBall(new Position(5, 5), 5);
        gameModel.getEnemies().add(enemy);
        assertEquals(1, gameModel.getEnemies().size());
    }

    @Test
    void testRewardHandling() {
        Reward reward = new HealthReward(new Position(3, 3));
        gameModel.getRewards().add(reward);
        assertEquals(1, gameModel.getRewards().size());
    }

    @Test
    void testGameStateUpdates() {
        // Test level changes, score updates, etc.
        gameModel.getScoreManager().addScore(100);
        assertEquals(100, gameModel.getScoreManager().getScore());
    }
}
