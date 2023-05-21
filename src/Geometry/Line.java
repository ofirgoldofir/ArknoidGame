// ofir goldberg, 315141325
package Geometry;
/**
 * Class Line.
 */
public class Line {
    public static final double EPSILON = Math.pow(10, -6);
    private Point start; // Start point of the line
    private Point end; // End point of the line

    /**
     * Constructor of a new Line.
     *
     * @param start Start point.
     * @param end   End point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor of a new Line.
     *
     * @param x1 x of the start point.
     * @param y1 y of the start point.
     * @param x2 x of the end point.
     * @param y2 y of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Function that calculate the length of a line.
     *
     * @return Length of line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Function that return the middle point on a given line.
     *
     * @return Middle point of line.
     */
    public Point middle() {
       double x = (this.start.getX() + this.end.getX()) / 2;
       double y = (this.start.getY() + this.end.getY()) / 2;
       return new Point(x, y);
    }

    /**
     * Function that gets the Start point of line.
     *
     * @return Start point of line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Function that gets the end point of line.
     *
     * @return End point of line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Functions that return if there is an intersection point between two lines.
     *
     * @param other point
     * @return True if there is intersection point, otherwise False.
     */
    public boolean isIntersecting(Line other) {
        if (intersectionWith(other) != null) {
            return true;
        } else {
            return checkIfParallelLinesMerge(this, other);
        }
    }

    /**
     * Function that check if parallel lines merge.
     *
     * @param l1 line 1
     * @param l2 line 2
     * @return true if merged, otherwise false
     */
    private boolean checkIfParallelLinesMerge(Line l1, Line l2) {
        double m1 = findM(l1);
        double m2 = findM(l2);
        double b1 = findB(l1, m1);
        double b2 = findB(l2, m2);

        if (m1 == m2) {
            // check if the first line perpendicular.
            if (m1 == Integer.MAX_VALUE) {
                // if x of the first line not equal to the x of the second line, the lines not merge.
                if (l1.start().getX() != l2.start().getX()) {
                    return false;
                } else {
                    // Checks if the lines are one above the other.
                    return (!(l1.start().getY() > l2.start().getY()) || !(l1.start().getY() > l2.end().getY())
                            || !(l1.end().getY() > l2.start().getY()) || !(l1.end().getY() > l2.end().getY()))
                            && (!(l2.start().getY() > l1.start().getY()) || !(l2.start().getY() > l1.end().getY())
                            || !(l2.end().getY() > l1.start().getY()) || !(l2.end().getY() > l1.end().getY()));
                }
            }
            boolean flag1 = l1.checkIfPointIsOnLine(l2.start());
            boolean flag2 = l1.checkIfPointIsOnLine(l2.end());
            boolean flag3 = l2.checkIfPointIsOnLine(l1.start());
            boolean flag4 = l2.checkIfPointIsOnLine(l1.end());
            return flag1 || flag2 || flag3 || flag4;
        } else {
            return false;
        }
    }


    /**
     * Function that return the intersection point between two lines, if there is one.
     *
     * @param other point.
     * @return Intersecting point if there is one, otherwise null.
     */
    public Point intersectionWith(Line other) {
        double m1, m2; // Slopes (y = mx + b)
        double b1, b2; // Cutting point with y-axis (y = mx + b)
        double intersectionX; // x value of the intersection point
        double intersectionY; // y value of the intersection point
        m1 = findM(this);
        m2 = findM(other);
        // check if m1 == m2 == 0 or Integer.MaxValue or just parallel.
        if (m1 == m2) {
            // Checks if two parallel or converge lines intersecting at only one point
            if (this.start.equals(other.start()) && !this.start.equals(other.end()) && !this.end.equals(other.end())) {
                return new Point(this.start.getX(), this.start.getY());
            }
            if (this.start.equals(other.end()) && !this.start.equals(other.start())
                    && !this.end.equals(other.start())) {
                return new Point(this.start.getX(), this.start.getY());
            }
            if (this.end.equals(other.start()) && !this.end.equals(other.end()) && !this.start.equals(other.end())) {
                return new Point(this.end.getX(), this.end.getY());
            }
            if (this.end.equals(other.end()) && !this.end.equals(other.start()) && !this.start.equals(other.start())) {
                return new Point(this.end.getX(), this.end.getY());
            }
            return null;
        } else if (m1 == Integer.MAX_VALUE) {  // check if line 1 is vertical
            b1 = 0;
            b2 = findB(other, m2);
            intersectionX = this.start().getX();
            intersectionY = (m2 * intersectionX) + b2;
        } else if (m2 == Integer.MAX_VALUE) { // check if line 2 is vertical
            b1 = findB(this, m1);
            b2 = 0;
            intersectionX = other.start().getX();
            intersectionY = (m1 * intersectionX) + b1;
        } else {
            b1 = findB(this, m1);
            b2 = findB(other, m2);
            intersectionX = (b2 - b1) / (m1 - m2);
            intersectionY = (m1 * intersectionX) + b1;
        }
        Point intersectionPoint = new Point(intersectionX, intersectionY);
        if (other.checkIfPointIsOnLine(intersectionPoint)
                && this.checkIfPointIsOnLine(intersectionPoint)) {
            return intersectionPoint;
        } else {
            return null;
        }
    }

    /**
     * Function that check if a given point is on line between two points.
     *
     * @param  p point to check if on the line.
     * @return True if p on the line, False otherwise.
     */
    public boolean checkIfPointIsOnLine(Point p) {
        double x = p.getX();
        double y = p.getY();
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minX = Math.min(this.start.getX(), this.end.getX());

        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minY = Math.min(this.start.getY(), this.end.getY());

        boolean xCon = x >= minX - EPSILON && x <= maxX + EPSILON;
        boolean yCon = y >= minY - EPSILON && y <= maxY + EPSILON;

        return xCon && yCon;
    }

    /**
     * Function that find the slope of a line.
     *
     * @param line the line
     * @return the slope
     */
    public double findM(Line line) {
        double m;
        // Check if the m is vertical
        if (line.start().getX() - line.end().getX() == 0) {
            m = Integer.MAX_VALUE;
        } else {
            m = (line.start().getY() - line.end().getY()) / (line.start().getX() - line.end().getX());
        }
        return m;
    }

    /**
     * Function that find the Y value cutting point with Axis Y of a point.
     *
     * @param line the line
     * @param m    slope of the line
     * @return the Y value of the cutting point
     */
    public double findB(Line line, double m) {
        double b;
        // calculate b by the equation b = y - mx
        b = line.start().getY() - (m * line.start().getX());
        return b;
    }

    /**
     * Function that check if two lines are equal.
     *
     * @param other point.
     * @return True if lines equal, False otherwise.
     */
    public boolean equals(Line other) {
        return other.start().getX() == this.start.getX() && other.start().getY() == this.start.getY()
                && other.end().getX() == this.end.getX() && other.end().getY() == this.end.getY();
    }

    /**
     * Function that find the closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the closest intersection point, or null if line does not intersect with the rectangle
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Point leftUpPoint = rect.getUpperLeft();
        Point rightUpPoint = new Point(rect.getUpperLeft().getX() + rect.getWidth(), rect.getUpperLeft().getY());
        Point rightDownPoint = new Point(rect.getUpperLeft().getX() + rect.getWidth(),
                rect.getUpperLeft().getY() + rect.getHeight());
        Point leftDownPoint = new Point(rect.getUpperLeft().getX(), rect.getUpperLeft().getY() + rect.getHeight());
        Line upLatitude = new Line(leftUpPoint, rightUpPoint);
        Line downLatitude = new Line(leftDownPoint, rightDownPoint);
        Line leftLongitude = new Line(leftUpPoint, leftDownPoint);
        Line rightLongitude = new Line(rightUpPoint, rightDownPoint);
        double distance1 = Integer.MAX_VALUE, distance2 = Integer.MAX_VALUE;
        double distance3 = Integer.MAX_VALUE, distance4 = Integer.MAX_VALUE;
        double minDistance;
        if (!this.isIntersecting(upLatitude) && !this.isIntersecting(downLatitude)
                && !this.isIntersecting(leftLongitude) && !this.isIntersecting(rightLongitude)) {
            return null;
        } else {
            Point intersectionUpLatitude = this.intersectionWith(upLatitude);
            Point intersectionDownLatitude = this.intersectionWith(downLatitude);
            Point intersectionLeftLongitude = this.intersectionWith(leftLongitude);
            Point intersectionRightLongitude = this.intersectionWith(rightLongitude);
            if (intersectionUpLatitude != null) {
                distance1 = this.start().distance(intersectionUpLatitude);
            }
            if (intersectionDownLatitude != null) {
                distance2 = this.start().distance(intersectionDownLatitude);
            }
            if (intersectionLeftLongitude != null) {
                distance3 = this.start().distance(intersectionLeftLongitude);
            }
            if (intersectionRightLongitude != null) {
                distance4 = this.start().distance(intersectionRightLongitude);
            }
            minDistance = Math.min(Math.min(distance1, distance2), Math.min(distance3, distance4));
            if (minDistance == distance1) {
                return intersectionUpLatitude;
            }
            if (minDistance == distance2) {
                return intersectionDownLatitude;
            }
            if (minDistance == distance3) {
                return intersectionLeftLongitude;
            }
            if (minDistance == distance4) {
                return intersectionRightLongitude;
            }
        }
        return null;
    }
}
