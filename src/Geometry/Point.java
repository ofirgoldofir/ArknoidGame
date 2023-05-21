// ofir goldberg, 315141325
package Geometry;
/**
 * Class Point.
 */
public class Point {
    private double x; // x Value
    private double y; // y Value

    /**
     * Constructor of a new Point.
     *
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Function that calculate the distance between two points.
     *
     * @param other point.
     * @return The distance.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow((this.x - other.getX()), 2) + Math.pow((this.y - other.getY()), 2));
    }
    /**
     * Check if two points are equal.
     *
     * @param other point.
     * @return True if equal, otherwise False.
     */
    public boolean equals(Point other) {
        return (other.getX() == this.x) && (other.getY() == this.y);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets x.
     *
     * @param x New x.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets y.
     *
     * @param y New y.
     */
    public void setY(double y) {
        this.y = y;
    }
}

