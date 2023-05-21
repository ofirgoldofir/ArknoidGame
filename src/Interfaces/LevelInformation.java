// Ofir Goldberg 315141325
package Interfaces;

import Geometry.Velocity;
import Geometry.Block;
import java.awt.Color;
import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name.
     *
     * @return the string
     */
    String levelName();

    /**
     * Gets background, Returns a sprite with the background of the level.
     *
     * @return the background
     */
    Sprite getBackground();

    /**
     * Blocks list, The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove before the level is considered to be "cleared".
     *
     * @return the int
     */
    int numberOfBlocksToRemove();

    /**
     * Gets ball color.
     *
     * @return the ball color
     */
    Color getBallColor();

    /**
     * Gets paddle color.
     *
     * @return the paddle color
     */
    Color getPaddleColor();

    /**
     * Gets level name.
     *
     * @return the level name
     */
    String getLevelName();
}
