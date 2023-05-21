// Ofir goldberg 315141325
package Listeners;
import Helpers.Counter;
import Interfaces.HitListener;
import Geometry.Ball;
import Geometry.Block;

/**
 * The class Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    public static final int ADDING_POINTS = 5; // The constant ADDING_POINTS.
    private Counter currentScore;

    /**
     * Constructor a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (!beingHit.getIfDeathRegionBlock()) {
            this.currentScore.increase(ADDING_POINTS);
            beingHit.removeHitListener(this);
        }
    }
}