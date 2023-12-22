package com.project.group13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.project.group13.backend.BackendCtrl;
import com.project.group13.backend.model.Axis;
import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.Enemy;
import com.project.group13.backend.model.gameobjects.NormalBarrier;
import com.project.group13.backend.model.gameobjects.NormalEnemy;
import com.project.group13.backend.model.gameobjects.Player;
import com.project.group13.backend.model.gameobjects.PowerReward;
import com.project.group13.backend.model.gameobjects.Reward;
import com.project.group13.backend.model.gameobjects.SpecialEnemy;
import com.project.group13.backend.model.gameobjects.SpecialReward;


public class BackendCtrlTest {
	@Mock
	BackendCtrl backend ;
	@Mock
	GameModel model;
	@Mock
	Player player;
	
	@BeforeEach
	void setup() {
		model = new GameModel();
		backend = new BackendCtrl(model);
		player = model.getPlayer();
	}
	
	@Test
	void nextLevelTest() {
		backend.nextLevel();
		assertEquals(2,model.getLevelManager().getLevel());
	}
	
	@Test
	void resetLevelTest() {
		//alter value of items
		backend.nextLevel();
		model.getLivesManager().setLives(1);
		model.getScoreManager().addScore(100);
		model.getPlayer().setCoordinate(new Position(2,15));
		backend.resetLevel();
		//check if items are reset to origin value properly
		assertEquals(3,model.getLivesManager().getLives());
		assertEquals(1,model.getLevelManager().getLevel());
		assertEquals(0,model.getScoreManager().getScore());
		//origin position of player = (22,16)
		assertEquals(22,model.getPlayer().getX());
		assertEquals(16,model.getPlayer().getY());
	}
	
	@Test
	void checkRewardCollisionTest() {
		//Player is not collided with rewards
		backend.checkRewardsCollision();
		assertEquals(0,model.getScoreManager().getScore());
		
		
		//Player collided with HealthReward
		player.damage(50);
		player.setPosition(12, 6);
		backend.checkRewardsCollision();
		assertEquals(100,player.getHealth());
		
		
		//player collided with PowerReward power2
		PowerReward rewards = (PowerReward) model.getRewards().get(5);
		rewards.setSpawned();
		player.decreasePower(9);
		player.setPosition(10, 16);
		backend.checkRewardsCollision();
		assertEquals(10,player.getPower());
	}
	
	@Test
	void checkEnemyCollisionTest() {
		//No collision with enemies
		backend.checkEnemiesCollision();
		assertEquals(100, player.getHealth());
	
		//collision with lower power trap enemies
		player.setPosition(18, 12);
		backend.checkEnemiesCollision();
		assertEquals(100,player.getHealth());
		assertEquals(20,model.getScoreManager().getScore());
		assertEquals(4,model.getEnemies().size());
		
		//collision with higher power trap enemies
		player.decreasePower(9);
		player.setPosition(17, 4);
		backend.checkEnemiesCollision();
		assertEquals(10,player.getHealth());
		
		SpecialEnemy highPower = new SpecialEnemy(new Position(16,5), Directions.UP,1,Axis.X_AXIS,200,10);
		List<Enemy> enemies = model.getEnemies();
		enemies.add(highPower);
		player.setPosition(16, 5);
		backend.checkEnemiesCollision();
		assertEquals(0,player.getHealth());
		
		player.heal(100);
		SpecialEnemy lowPower = new SpecialEnemy(new Position(17,5), Directions.UP,1,Axis.X_AXIS,200,0);
		enemies.add(lowPower);
		int originScore = model.getScoreManager().getScore();
		player.setPosition(17, 5);
		backend.checkEnemiesCollision();
		assertEquals(100,player.getHealth());
		assertEquals(originScore + 40,model.getScoreManager().getScore());
		
		NormalEnemy highPower2 = new NormalEnemy(new Position(11,4), Directions.UP,1,Axis.X_AXIS,200,9);
		enemies.add(highPower2);
		player.setPosition(11, 4);
		backend.checkEnemiesCollision();
		assertEquals(10,player.getHealth());
		
		
		NormalEnemy lowPower2 = new NormalEnemy(new Position(12,4), Directions.UP,1,Axis.X_AXIS,200,0);
		enemies.add(lowPower2);
		originScore = model.getScoreManager().getScore();
		player.setPosition(12, 4);
		backend.checkEnemiesCollision();
		assertEquals(10,player.getHealth());
		assertEquals(originScore + 30,model.getScoreManager().getScore());
		
		
		
	}
	
	@Test
	void checkBarrierCollisionTest() {
		//No collision with barriers
		backend.checkBarriersCollision();
		assertEquals(100,player.getHealth());
		
		//Collision with special barriers
		player.setPosition(17, 3);
		backend.checkBarriersCollision();
		assertEquals(0,player.getHealth());
		
		player.heal(100);
		NormalBarrier barrier = new NormalBarrier(new Position(22,1));
		model.getBarriers().add(barrier);
		player.setPosition(22, 1);
		backend.checkBarriersCollision();
		assertEquals(0,player.getHealth());
	}
	
	
	@Test
	void spawnSpecialRewardsTest() {
		List<Reward> rewards = model.getRewards();
		SpecialReward power = (SpecialReward) rewards.get(5);
		power.setSpawned();
		PowerReward power2 = mock(PowerReward.class);
		backend.spawnSpecialRewards();
		verify(power2,never()).getSpawnChance();
		power.setHidden();
		
		for(int i = 0; i < 2000; i++) 
			backend.spawnSpecialRewards();
		int j = 5;
		for(; j < rewards.size();j++) {
			power = (SpecialReward) rewards.get(j);
			if(power.isSpawned()) {
				assertFalse(power.isTimesUp());
			}
		}
	}
	
	@Test
	void updateUpTimeForSpecialRewardsTest() {
		SpecialReward power = (SpecialReward) model.getRewards().get(5);
		power.setSpawned();
		backend.updateUpTimeForSpecialRewards(1);
		assertEquals(1,power.getUpTime());
	}
	
	@Test
	void checkTimeUpForSpecialRewardTest() {
		SpecialReward power = (SpecialReward) model.getRewards().get(5);
		power.setSpawned();
		backend.updateUpTimeForSpecialRewards(10);
		backend.checkTimeUpForSpecialReward();
		assertFalse(power.isSpawned());
	}
	

}
