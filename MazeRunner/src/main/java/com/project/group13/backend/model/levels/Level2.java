
package com.project.group13.backend.model.levels;

import java.util.List;

import com.project.group13.backend.model.Axis;
import com.project.group13.backend.model.Directions;
import com.project.group13.backend.model.GameModel;
import com.project.group13.backend.model.Position;
import com.project.group13.backend.model.gameobjects.*;

/**
 * Level2 implements the Level interface and defines the specific layout and initialization for the first level
 * of the game. It initializes the map layout, places rewards and enemies, and sets their behavior for this level.
 * The layout is represented by a matrix where different integers represent different types of tiles or spaces.
 *
 * @author Ali Nikan
 * @author Kelvin Lu
 * @author Steven Wong
 * @author Shaima El Masry
 * @version 1.0
 */
public class Level2 implements Level {

    private final GameMap gameMap; // The game map specific to Level 2.

    /**
     * Constructs game map for level 2 (Similar to Level 1)
     */
    public Level2() {
        final int[][] map = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        this.gameMap = new GameMap(map);
    }

    /************************ Getter/Setter ************************/

    @Override
    public GameMap getMap() {
        return this.gameMap;
    }

    
    
    @Override
    public void initModels(GameModel model) {

        // place life reward
    	Position[] life = {new Position(3, 3),new Position(22, 7)};
    	Level.RewardFactory(life, "LifeReward", model.getRewards());
    	
        // place health rewards
    	Position[] health = {new Position(8, 14),new Position(19, 6), new Position(3, 15)};
    	Level.RewardFactory(health, "HealthReward", model.getRewards());
    
    	// place special items
        Position[] power = {new Position(2, 7),new Position(15, 1), new Position(10, 15), new Position(1,16)};
        Level.RewardFactory(power, "PowerReward", model.getRewards());
        
        // place static enemies
        SpikeBall spikeBall = new SpikeBall(new Position(18, 12), 8);
        SpikeBall spikeBall2 = new SpikeBall(new Position(8, 10), 6);
        SpikeBall spikeBall3 = new SpikeBall(new Position(16, 4), 9);
        SpikeBall spikeBall4 = new SpikeBall(new Position(5, 4), 2);
        SpikeBall spikeBall5 = new SpikeBall(new Position(2, 14), 5);

        // place moving normal enemies
        Spikes spikes = new Spikes(new Position(20, 1), 3, Axis.X_AXIS, Directions.DOWN, 150);
        Spikes spikes2 = new Spikes(new Position(7, 9), 5, Axis.X_AXIS, Directions.DOWN, 50);
        Spikes spikes3 = new Spikes(new Position(12, 13), 1, Axis.X_AXIS, Directions.UP, 250);

        // add enemies
        model.getEnemies().add(spikeBall);
        model.getEnemies().add(spikeBall2);
        model.getEnemies().add(spikeBall3);
        model.getEnemies().add(spikeBall4);
        model.getEnemies().add(spikeBall5);

        model.getBarriers().add(spikes);
        model.getBarriers().add(spikes2);
        model.getBarriers().add(spikes3);



    }
}