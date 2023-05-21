// Ofir Goldberg 315141325
package Interfaces;
import Geometry.Ball;
import Geometry.Block;

/**
 * The interface HitListener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}