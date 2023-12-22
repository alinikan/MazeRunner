package com.project.group13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.LifeReward;
import com.project.group13.backend.model.gameobjects.Player;

public class LifeRewardTest {
	@Mock
	LifeReward life;
	
	@BeforeEach
	void setup() {
		life = new LifeReward(new Position(10,10),10);
	}
	
	
	@Test
	void LifeRewardConstruct() {
		//Object should be at (10,10)
		assertEquals(10,life.getX());
		assertEquals(10,life.getY());
	}
	
	@Test
	void useRewardTest() {
		GameModel model = new GameModel();
		int originLives = model.getLivesManager().getLives();
		life.useReward(model);
		
		//Live counter should be increased 10 after use.
		assertEquals(originLives + 10,model.getLivesManager().getLives());
		
	}
	
	 @Test
	    void testCollisionDetection() {
	        Player Player = new Player(new Position(10,10));
	        assertTrue(life.collidedWith(Player));
	    }

	    @Test
	    void testMovementRestrictions() {
	        life.setPosition(0, 0); // Attempt to move spikeBall


	        // Check if the position remains unchanged by comparing x and y coordinates
	        assertEquals(10,life.getX());
	        assertEquals(10, life.getY());
	    }
		
}
