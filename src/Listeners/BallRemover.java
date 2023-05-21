// Ofir Goldberg 315141325
package Listeners;
import Helpers.Counter;
import Levels.GameLevel;
import Interfaces.HitListener;
import Geometry.Ball;
import Geometry.Block;

/**
 * The class Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructor of a new BallRemover.
     *
     * @param game         the game
     * @param removedBalls the number of removed balls
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * Sets remaining balls.
     *
     * @param number the number
     */
    public void setRemainingBalls(Counter number) {
        this.remainingBalls = number;
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Remove block that was hit.
     *
     * @param beingHit the hit block.
     * @param hitter   the ball that hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getIfDeathRegionBlock()) {
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);
        }
    }
}

