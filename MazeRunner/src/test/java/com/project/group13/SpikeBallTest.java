package com.project.group13;

import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.Player;
import com.project.group13.backend.model.gameobjects.SpikeBall;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpikeBallTest {

    private Player player;
    private SpikeBall spikeBall;
    private final int spikeBallPower = 5;
    private final int initialPlayerHealth = Player.MAX_HEALTH;

    @BeforeEach
    void setUp() {
        // Assume these positions result in a collision
        Position spikeBallPosition = new Position(10, 10);
        Position playerPosition = new Position(10, 10);

        spikeBall = new SpikeBall(spikeBallPosition, spikeBallPower);
        player = new Player(playerPosition);
    }

    @Test
    void testCollisionWithPlayer() {
        spikeBall.attack(player);

        // Check if player health is reduced
        assertTrue(player.getHealth() < initialPlayerHealth);
    }

    @Test
    void testDamageInfliction() {
        spikeBall.attack(player);

        // Damage calculation is player's health minus spikeBall's power times 10
        assertEquals(initialPlayerHealth - spikeBallPower * 10, player.getHealth());
    }

    @Test
    void testNoCollision() {
        // Move player to a position where they won't collide with the spikeBall
        player.setPosition(20.0, 20.0);

        // Check for collision before attacking
        if (!spikeBall.collidedWith(player)) {
            // If no collision, player's health should remain unchanged
            assertEquals(initialPlayerHealth, player.getHealth());
        } else {
            fail("Collision detected when there should be none.");
        }
    }


    @Test
    void testPowerLimit() {
        SpikeBall overpoweredSpikeBall = new SpikeBall(new Position(10, 10), 15);

        // SpikeBall's power cannot exceed a certain maximum limit
        assertEquals(SpikeBall.MAX_POWER, overpoweredSpikeBall.getPower());
    }

    @Test
    void testSpikeBallInitialization() {
        Position expectedPosition = new Position(10, 10);

        // Retrieve the current position of the spikeBall
        Position currentPosition = spikeBall.getCoordinate();

        // Compare the x and y coordinates separately
        assertEquals(expectedPosition.getXCoord(), currentPosition.getXCoord());
        assertEquals(expectedPosition.getYCoord(), currentPosition.getYCoord());
    }

    @Test
    void testCollisionDetection() {
        Position samePosition = new Position(10, 10);
        Player anotherPlayer = new Player(samePosition);
        assertTrue(spikeBall.collidedWith(anotherPlayer));
    }

    @Test
    void testMovementRestrictions() {
        spikeBall.setPosition(15, 15); // Attempt to move spikeBall

        // Retrieve the current position of the spikeBall
        Position currentPosition = spikeBall.getCoordinate();

        // Check if the position remains unchanged by comparing x and y coordinates
        assertEquals(10, currentPosition.getXCoord());
        assertEquals(10, currentPosition.getYCoord());
    }

    @Test
    void testPowerAdjustment() {
        SpikeBall invalidPowerSpikeBall = new SpikeBall(new Position(10, 10), -1);
        assertTrue(invalidPowerSpikeBall.getPower() >= 0 && invalidPowerSpikeBall.getPower() <= SpikeBall.MAX_POWER);
    }
}
