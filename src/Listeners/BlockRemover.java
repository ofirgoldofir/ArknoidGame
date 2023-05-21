// Ofir Goldberg 315141325
package Listeners;

import Helpers.Counter;
import Levels.GameLevel;
import Interfaces.HitListener;
import Geometry.Ball;
import Geometry.Block;

/**
 * The class Block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor of a new BlockRemover.
     *
     * @param gameLevel          the game
     * @param removedBlocks the number of removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.game = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Sets remaining blocks.
     *
     * @param number the remaining blocks
     */
    public void setRemainingBlocks(Counter number) {
        this.remainingBlocks = number;
    }

    /**
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Remove block that was hit.
     *
     * @param beingHit the hit block.
     * @param hitter   the ball that hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.getIfDeathRegionBlock()) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}