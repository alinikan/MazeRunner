package com.project.group13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.HealthReward;
import com.project.group13.backend.model.gameobjects.Player;

public class HealthRewardTest {
	@Mock
	HealthReward heart;
	
	@BeforeEach
	void setup() {
		heart = new HealthReward(new Position(10,12));
	}
	
	@Test
	void HealthRewardConstruct() {
		//Object should be at (10,12)
		assertEquals(10,heart.getX());
		assertEquals(12,heart.getY());
	}
	
	@Test
	void useRewardTest() {
		GameModel model = new GameModel();
		Player player = model.getPlayer();
		//reduce health to 10
		player.damage(90);
		assertEquals(10,player.getHealth());
		heart.useReward(model);
		
		//Health should be increased by 50 to 60
		assertEquals(60,player.getHealth());
		
	}
	
	 @Test
	    void testCollisionDetection() {
	        Player player = new Player(new Position(10,12));
	        assertTrue(heart.collidedWith(player));
	        
	        player.setCoordinate(new Position(15,7));
	        assertFalse(heart.collidedWith(player));
	    }

	    @Test
	    void testMovementRestrictions() {
	        heart.setPosition(0, 0); // Attempt to move spikeBall

	        // Check if the position remains unchanged by comparing x and y coordinates
	        assertEquals(10, heart.getX());
	        assertEquals(12, heart.getY());
	    }
		
}
	