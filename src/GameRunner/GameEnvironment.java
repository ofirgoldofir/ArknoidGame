// ofir goldberg, 315141325
package GameRunner;
import Geometry.CollisionInfo;
import Interfaces.Collidable;
import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;

/**
 * The class Game environment.
 */
public class GameEnvironment {
    private ArrayList<Collidable> objects;
    private Point upperLeft;
    private double width;
    private double height;
    /**
     * Constructor of a new Game environment.
     */
    public GameEnvironment() {
        this.objects = new ArrayList<Collidable>();
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.objects.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.objects.remove(c);
    }

    /**
     * Gets objects.
     *
     * @return the objects
     */
    public ArrayList<Collidable> getObjects() {
        return this.objects;
    }

    /**
     * Function that return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point p, closestPoint = null;
        Collidable closestObject = null;
        int i;
        boolean flag = false;
        double distance, minVal = Integer.MAX_VALUE;
        for (i = 0; i < this.objects.size(); i++) {
            p = trajectory.closestIntersectionToStartOfLine(this.objects.get(i).getCollisionRectangle());
            if (p != null) {
                distance = p.distance(trajectory.start());
                if (distance < minVal) {
                    minVal = distance;
                    closestObject = this.objects.get(i);
                    closestPoint = p;
                    flag = true;
                }
            }
        }
        if (flag) {
            return new CollisionInfo(closestObject, closestPoint);
        }
        return null;
    }
}

