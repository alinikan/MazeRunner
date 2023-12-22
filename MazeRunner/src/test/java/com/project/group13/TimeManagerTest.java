package com.project.group13;

import com.project.group13.backend.model.TimeManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimeManagerTest {

    private TimeManager timeManager;

    @BeforeEach
    void setUp() {
        timeManager = new TimeManager();
    }

    @Test
    void testInitialTime() {
        // Test that the initial time is zero
        assertEquals(0, timeManager.getElapsedTimeInSeconds(), "Initial time should be 0 seconds");
    }

    @Test
    void testUpdateTime() {
        double elapsedTime = 5.0;
        timeManager.updateTimeBy(elapsedTime);
        assertEquals(5, timeManager.getElapsedTimeInSeconds(), "Time should be updated by 5 seconds");

        elapsedTime = 10.0;
        timeManager.updateTimeBy(elapsedTime);
        assertEquals(15, timeManager.getElapsedTimeInSeconds(), "Total time should now be 15 seconds");
    }

    @Test
    void testResetTime() {
        timeManager.updateTimeBy(10.0);
        timeManager.reset();
        assertEquals(0, timeManager.getElapsedTimeInSeconds(), "Time should reset to 0 seconds");
    }

    @Test
    void testUpdateTimeByNegativeValue() {
        timeManager.updateTimeBy(-5.0);
        assertEquals(0, timeManager.getElapsedTimeInSeconds(), "Time should not decrease");
    }
}
