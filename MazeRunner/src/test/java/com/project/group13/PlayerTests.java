package com.project.group13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.Player;
import com.project.group13.backend.model.levels.Level1;


public class PlayerTests {
	@Mock
	Player player;
	
	@Mock
	Level1 level;
	
	@BeforeEach
	void setup() {
		player = new Player(new Position(21,15));
		level = new Level1();
	}
	
	
	//
	@Test
	void PlayerConstructTest() {
		assertEquals(21,player.getX());
		assertEquals(15,player.getY());
	}
	
	@Test
	void PlayerDamgeTest() {
		//Cover damage() branch True
		assertEquals(100,player.getHealth());
		player.damage(5);
		assertEquals(95,player.getHealth());
		
		//Cover damage() branch False
		player.damage(110);
		assertEquals(0,player.getHealth());
		
	}

	@Test
	void PlayerHealTest() {
		//Cover heal() branch True
		player.damage(90);
		player.heal(40);
		assertEquals(50,player.getHealth());
		
		//Cover heal() branch False
		player.heal(100);
		assertEquals(100,player.getHealth());
	}
		
	
	@Test 
	void PlayerPowerDownTest(){
		//Cover decreasePower() branch True.
		player.decreasePower(3);
		assertEquals(7,player.getPower());
		
		//Cover decreasePower() branch False.
		player.decreasePower(12);
		assertEquals(0,player.getPower());
		
	}
	
	@Test
	void PlayerPowerUpTest() {
		//Cover IncreasePower() branch True
		player.decreasePower(7);
		player.increasePower(2);
		assertEquals(5,player.getPower());
		
		//Cover increasePower() branch false
		player.increasePower(15);
		assertEquals(10,player.getPower());
	}
	
	@Test
	void MovePlayerTest() {
		// Move player up
		player.moveTo(Directions.UP, level);
		assertEquals(21,player.getX());
		assertEquals(14,player.getY());
		//Move player down
		player.moveTo(Directions.DOWN,level);
		assertEquals(21,player.getX());
		assertEquals(15,player.getY());
		//Move player to left
		player.moveTo(Directions.LEFT, level);
		assertEquals(20,player.getX());
		assertEquals(15,player.getY());
		//Move player to right
		player.moveTo(Directions.RIGHT, level);
		assertEquals(21,player.getX());
		assertEquals(15,player.getY());
	}
	
	@Test
	void PlayerHitWallTest() {
		//Barriers on right and below
		player.setCoordinate(new Position(23,17));
		player.moveRight(level);
		assertEquals(23,player.getX());
		assertEquals(17,player.getY());
		
		player.moveDown(level);
		assertEquals(23,player.getX());
		assertEquals(17,player.getY());
		//Barriers on left and above
		player.setCoordinate(new Position(1,1));
		player.moveUp(level);
		assertEquals(1,player.getX());
		assertEquals(1,player.getY());
		
		player.moveLeft(level);
		assertEquals(1,player.getX());
		assertEquals(1,player.getY());
	
	}
	
	@Test
	void RespawnPlayerTest() {
		//Change the player's position 
		player.moveTo(Directions.UP, level);
		player.moveTo(Directions.UP, level);
		player.respawn();
		double ExpectedX = player.getInitialPos().getXCoord();
		double ResultX = player.getX();
		assertEquals(ExpectedX,ResultX);
		
		double ExpectedY = player.getInitialPos().getXCoord();
		double ResultY = player.getX();
		assertEquals(ExpectedY,ResultY);
	}
	
	
	
	@Test
	void PlayerisDeadTest() {
		//Player is not dead
		player.damage(5);
		assertFalse(player.isDead());
		//kill the player
		player.damage(100);
		assertTrue(player.isDead());			
	}
	
	
	
}
