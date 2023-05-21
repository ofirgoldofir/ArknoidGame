// ofir goldberg, 315141325
package Geometry;
import java.util.ArrayList;

/**
 * The class Rectangle.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    /**
     * Constructor of a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Function that Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line
     * @return the intersection points list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> arr = new ArrayList<Point>();
        Point leftUpPoint = this.getUpperLeft();
        Point rightUpPoint = new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY());
        Point rightDownPoint = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        Point leftDownPoint = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.getHeight());
        Line upLatitude = new Line(leftUpPoint, rightUpPoint);
        Line downLatitude = new Line(leftDownPoint, rightDownPoint);
        Line leftLongitude = new Line(leftUpPoint, leftDownPoint);
        Line rightLongitude = new Line(rightUpPoint, rightDownPoint);
        Point intersectionUpLatitude = line.intersectionWith(upLatitude);
        Point intersectionDownLatitude = line.intersectionWith(downLatitude);
        Point intersectionLeftLongitude = line.intersectionWith(leftLongitude);
        Point intersectionRightLongitude = line.intersectionWith(rightLongitude);

        if (intersectionUpLatitude != null) {
            arr.add(intersectionUpLatitude);
        }
        if (intersectionDownLatitude != null) {
            arr.add(intersectionDownLatitude);
        }
        if (intersectionLeftLongitude != null) {
            arr.add(intersectionLeftLongitude);
        }
        if (intersectionRightLongitude != null) {
            arr.add(intersectionRightLongitude);
        }
        return arr;
    }

    /**
     * Get width double.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get height double.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get upper left point.
     *
     * @return the point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Set width.
     *
     * @param width the width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Set height.
     *
     * @param height the height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Set upper left.
     *
     * @param p the point
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
}