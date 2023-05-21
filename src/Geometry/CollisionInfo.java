// ofir goldberg, 315141325
package Geometry;
import Interfaces.Collidable;

/**
 * The class Collision info.
 */
public class CollisionInfo {

    private Collidable object;
    private Point collisionPoint;

    /**
     * Constructor of a new Collision info.
     *
     * @param object         the object
     * @param collisionPoint the collision point
     */
    public CollisionInfo(Collidable object, Point collisionPoint) {
        this.object = object;
        this.collisionPoint = collisionPoint;
    }

    /**
     * Constructor of a new Collision info.
     *
     * @param object the object
     * @param x      the x
     * @param y      the y
     */
    public CollisionInfo(Collidable object, double x, double y) {
        this.object = object;
        this.collisionPoint = new Point(x, y);
    }

    /**
     * Get the collision point.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Get the collision object.
     *
     * @return the object.
     */
    public Collidable collisionObject() {
        return this.object;
    }
}