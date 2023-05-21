// ofir goldberg, 315141325
package Interfaces;
import Geometry.Velocity;
import Geometry.Ball;
import Geometry.Point;
import Geometry.Rectangle;

/**
 * The interface Collidable.
 */
public interface Collidable {
    /**
     * Function that return the "collision shape" of the object.
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Function that return new velocity expected after the hit with object at a collision point.
     *
     * @param hitter  the ball that hit
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}