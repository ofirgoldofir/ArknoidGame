// ofir goldberg, 315141325
package Geometry;

/**
 * Class Velocity.
 */
public class Velocity {
    public static final int MAX_SPEED = 20; // The constant MAX_SPEED.
    public static final int MIN_SPEED = 6; // The constant MIN_SPEED.
    public static final int MAX_ANGLE = 360; // The constant MAX_ANGLE.
    public static final int SAME_SPEED_LIMIT = 50; // The constant SAME_SPEED_LIMIT.
    private double dx;
    private double dy;

    /**
     * Constructor of a new Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Functiont that define dx and dy from angle and speed.
     *
     * @param speed the speed
     * @param angle the angle
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double speed, double angle) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = -Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Get speed.
     *
     * @return the speed
     */
    public double getSpeed() {
        return Math.sqrt((Math.pow(this.dx, 2) + (Math.pow(this.dy, 2))));
    }

    /**
     * Get angle.
     *
     * @return the angle
     */
    public double getAngle() {
        return Math.atan2(this.dy, this.dx);
    }

    /**
     * Sets dx.
     *
     * @param dx the dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }


    /**
     * Sets dy.
     *
     * @param dy the dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Function that add the dx and dy to the x and y of the center point.
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);
    }
}