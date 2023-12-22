package com.project.group13;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.gameobjects.AutoMovingObject;
import com.project.group13.backend.model.levels.Level1;

/**
 * Unit test for simple App.
 */
public class AutoMovingObjectTest
{
    @Mock
    private AutoMovingObject autoMovingObject;

    @Mock
    private Level1 level;

    @BeforeEach
    public void setUp() {
        autoMovingObject = new AutoMovingObject(null, null, 0, null, 0);
        level = new Level1();
    }

    @Test
    // Update Position where shouldMove is true
    public void testShouldMove() {
        assertTrue(autoMovingObject.shouldMove(0.1));
    }

    @Test
    // Update Position where shouldMove is false
    public void testShouldNotMove() {
        assertTrue(!autoMovingObject.shouldMove(0.0));
    }

    @Test
    // Testing updatePosition
    public void testUpdatePosition() {
        autoMovingObject.updatePosition(level, 0.1);
        assertTrue(autoMovingObject.shouldMove(0.1));
    }

    // Test updatePosition
    @Test
    public void testUpdatePosition2() {
        autoMovingObject.updatePosition(level, 0.0);
        assertTrue(!autoMovingObject.shouldMove(0.0));
    }

    // Set Orientation and get Orientation
    @Test
    public void testSetOrientation() {
        autoMovingObject.setOrientation(Directions.LEFT);
        assertTrue(autoMovingObject.getOrientation() == Directions.LEFT);
    }    
}
