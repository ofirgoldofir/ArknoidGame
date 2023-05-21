// ofir goldberg, 315141325
package Geometry;
import Levels.GameLevel;
import GameRunner.GameEnvironment;
import Interfaces.HitListener;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Ball.
 */
public class Ball implements Sprite {
    // The maximum length of radius.
    public static final int RADIUS_MAX_LENGTH = (Math.min(GameLevel.HEIGHT, GameLevel.WIDTH) / 2);
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity v;
    private Point upperLeft;
    private double width;
    private double height;
    private GameEnvironment game;
    private List<HitListener> hitListeners;

    /**
     * Constructor of a new Ball.
     *
     * @param center the center
     * @param r      the r
     */
    public Ball(Point center, int r) {
//        this.upperLeft = new Point(0, 0);
//        this.width = 0;
//        this.height = 0;
        this.upperLeft = new Point(0, 0);
        this.width = GameLevel.WIDTH;
        this.height = GameLevel.HEIGHT;
        if (r > 0 && r < RADIUS_MAX_LENGTH) {
            this.radius = r;
        } else if (r > RADIUS_MAX_LENGTH) {
            this.radius = RADIUS_MAX_LENGTH;
        } else {
            this.radius = 1;
        }
        this.center = center;
        this.color = Color.black;
        this.v = new Velocity(0, 0);
        this.game = null;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Constructor of a new Ball.
     *
     * @param x     the x
     * @param y     the y
     * @param r     the r
     */
    public Ball(double x, double y, int r) {
//        this.upperLeft = new Point(0, 0);
//        this.width = 0;
//        this.height = 0;
        this.upperLeft = new Point(0, 0);
        this.width = GameLevel.WIDTH;
        this.height = GameLevel.HEIGHT;
        if (r > 0 && r < RADIUS_MAX_LENGTH) {
            this.radius = r;
        } else if (r > RADIUS_MAX_LENGTH) {
            this.radius = RADIUS_MAX_LENGTH;
        } else {
            this.radius = 1;
        }
        this.center = new Point(x, y);
        this.color = Color.black;
        this.v = new Velocity(0, 0);
        this.game = null;
        this.hitListeners = new ArrayList<>();
    }

//    /**
//     * function that define the center of a point in the frame limits.
//     *
//     * @param p      the point
//     * @param radius the radius
//     * @param width  the width
//     * @param height the height
//     * @return the suitable point
//     */
//    public Point defineCenterPosition(Point p, int radius, int width, int height) {
//        if (p.getX() - radius <= 0 && p.getY() - radius <= 0) {
//            return new Point(radius, radius);
//        }
//        if (p.getX() + radius >= width && p.getY() - radius <= 0) {
//            return new Point(width - radius, radius);
//        }
//        if (p.getX() + radius >= width && p.getY() + radius >= height) {
//            return new Point(width - radius, height - radius);
//        }
//        if (p.getX() - radius <= 0 && p.getY() + radius >= height) {
//            return new Point(radius, height - radius);
//        }
//        if (p.getX() + radius >= width) {
//            return new Point(width - radius, p.getY());
//        }
//        if (p.getX() - radius <= 0) {
//            return new Point(width - radius, p.getY());
//        }
//        if (p.getY() + radius >= height) {
//            return new Point(p.getX(), height - radius);
//        }
//        if (p.getY() - radius <= 0) {
//            return new Point(p.getX(), radius);
//        }
//        return p;
//    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
//        return (int) (Math.PI * (this.radius * this.radius));
        return this.radius;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public GameEnvironment getGame() {
        return this.game;
    }

    /**
     * Get center point.
     *
     * @return the point
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Set game.
     *
     * @param game the game.
     */
    public void setGame(GameEnvironment game) {
        this.game = game;
    }

    /**
     * Set color.
     *
     * @param color the color.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Function that define a boll the frame in which he can move.
     *
     * @param xLeftUp the x value of the left up corner
     * @param yLeftUp the y value of the left up corner
     * @param width   the width
     * @param height  the height
     */
    public void setCorners(int xLeftUp, int yLeftUp, int width, int height) {
        this.upperLeft = new Point(xLeftUp, yLeftUp);
        this.width = width;
        this.height = height;
        Line temp1 = new Line(xLeftUp, yLeftUp, xLeftUp + width, yLeftUp);
        Line temp2 = new Line(xLeftUp, yLeftUp, xLeftUp, yLeftUp + height);
        this.center.setX(temp1.middle().getX());
        this.center.setY(temp2.middle().getY());
        if (this.center.getX() + this.radius > xLeftUp + width || this.center.getX() - this.radius < yLeftUp
                || this.center.getX() + this.radius > yLeftUp + height || this.center.getX() - this.radius < xLeftUp
                || this.center.getY() + this.radius > yLeftUp + width || this.center.getY() - this.radius < yLeftUp
                || this.center.getY() + this.radius > yLeftUp + height || this.center.getY() - this.radius < xLeftUp) {
            this.setVelocity(0, 0);
        }

    }

    /**
     * Print the balls corners of the limits.
     */
    public void printCorners() {
        System.out.println("left up corner x: " + this.upperLeft.getX());
        System.out.println("left up corner y: " + this.upperLeft.getY());
        System.out.println("right up corner x: " + this.width);
        System.out.println("right up corner y: " + this.upperLeft.getY());
        System.out.println("right down corner x: " + this.width);
        System.out.println("right down corner y: " + this.height);
        System.out.println("left down corner x: " + this.upperLeft.getX());
        System.out.println("left down corner y: " + this.height);
        System.out.println("/n");
    }

    /**
     * Function that find if ball have borders.
     *
     * @return true if there borders, otherwise false.
     */
    public boolean haveBorders() {
        Point p = new Point(0, 0);
        return !this.upperLeft.equals(p) || this.width != 0 || this.height != 0;
    }

    /**
     * Function that draw the ball on the given DrawSurface.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        if (!this.haveBorders()) {
            this.setCorners(0, 0, surface.getWidth(), surface.getHeight());
        }
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(Color.red);
//        drawTrajectory(surface, this.center, this.getVelocity());
//        drawCenterPoint(surface, this.center);
//        drawFace(surface, this.center);
    }

    /**
     * Function that draw the Trajectory of the ball on the given DrawSurface.
     *
     * @param surface the surface.
     * @param center the center point.
     * @param v the velocity.
     */
    private void drawTrajectory(DrawSurface surface, Point center, Velocity v) {
        surface.drawLine((int) center.getX(), (int)  center.getY(),
                (int) (center.getX() + v.getDx()), (int) (center.getY() + v.getDy()));
    }

    /**
     * Function that draw the center point x and y values.
     *
     * @param surface the surface.
     * @param center the center point.
     */
    private void drawCenterPoint(DrawSurface surface, Point center) {
        surface.setColor(Color.BLACK);
        String pointStr = "(" + (int) center.getX() + "," + (int) center.getY() + ")";
        surface.drawText((int) center.getX(), (int) center.getY(), pointStr, 20);
    }

    private void drawFace(DrawSurface surface, Point center) {
        surface.setColor(Color.WHITE);
        int eye1X = (int) center.getX() - (int) (this.radius / 2);
        int eye2X = (int) center.getX() + (int) (this.radius / 2);
        int eyeY = (int) center.getY() - (int) (this.radius / 2);
        int eyeRadius = 2;
        surface.fillCircle(eye1X, eyeY, eyeRadius);
        surface.fillCircle(eye2X, eyeY, eyeRadius);
    }

    /**
     * Set velocity.
     *
     * @param v the velocity
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Set velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Function that move the ball one step according to the dx, dy values.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
        double x = this.center.getX(), y = this.center.getY(), r = this.radius;
        double dx = this.v.getDx(), dy = this.v.getDy();
        Velocity currentV = new Velocity(dx, dy);
        Point trajectoryStart = new Point(x, y);
        Point trajectoryEnd = new Point((x + dx), (y + dy));
        Line trajectory = new Line(trajectoryStart, trajectoryEnd);
        Line axisX = new Line(x - r, y, x + r, y);
        Line axisY = new Line(x, y - r, x, y + r);
        CollisionInfo c1 = this.game.getClosestCollision(trajectory);
        CollisionInfo c2 = this.game.getClosestCollision(axisX);
        CollisionInfo c3 = this.game.getClosestCollision(axisY);
        CollisionInfo c = null;
        // check if the ball is hitting an object on the screen.
        if (c1 != null || c2 != null || c3 != null) {
            if (c1 != null) {
                c = c1;
            }
            if (c2 != null) {
                c = c2;
            }
            if (c3 != null) {
                c = c3;
            }
            double x1 = c.collisionPoint().getX();
            double y1 = c.collisionPoint().getY();
            double width1 = c.collisionObject().getCollisionRectangle().getWidth();
            double height1 = c.collisionObject().getCollisionRectangle().getHeight();
            double xLeftUp1 = c.collisionObject().getCollisionRectangle().getUpperLeft().getX();
            double yLeftUp1 = c.collisionObject().getCollisionRectangle().getUpperLeft().getY();
            Line upLine = new Line(xLeftUp1, yLeftUp1, xLeftUp1 + width1, yLeftUp1);
            Line downLine = new Line(xLeftUp1, yLeftUp1 + height1, xLeftUp1 + width1, yLeftUp1 + height1);
            Line leftLine = new Line(xLeftUp1, yLeftUp1, xLeftUp1, yLeftUp1 + height1);
            Line rightLine = new Line(xLeftUp1 + width1, yLeftUp1, xLeftUp1 + width1, yLeftUp1 + height1);
            boolean onUpLine = upLine.checkIfPointIsOnLine(c.collisionPoint());
            boolean onDownLine = downLine.checkIfPointIsOnLine(c.collisionPoint());
            boolean onLeftLine = leftLine.checkIfPointIsOnLine(c.collisionPoint());
            boolean onRightLine = rightLine.checkIfPointIsOnLine(c.collisionPoint());
            if (onUpLine) {
                this.center.setY(y1 - r);
            }
            if (onDownLine) {
                this.center.setY(y1 + r);
            }
            if (onLeftLine) {
                this.center.setX(x1 - r);
            }
            if (onRightLine) {
                this.center.setX(x1 + r);
            }
            this.setVelocity(c.collisionObject().hit(this, c.collisionPoint(), currentV));
        }
//        double xLeftUp = this.upperLeft.getX();
//        double yLeftUp = this.upperLeft.getY();
//        double width = this.width;
//        double height = this.height;
//        // check if the ball is in the limits of the frame.
//        if ((x + r + dx) >= width || (x + dx) - r <= xLeftUp) {
//            if ((x + r + dx) >= width) {
//                this.center.setX(width - r);
//            } else {
//                this.center.setX(xLeftUp + r);
//            }
//            dx = dx * (-1);
//            this.setVelocity(dx, dy);
//        }
//        // check if the ball is in the limits of the frame.
//        if ((y + r + dy) >= height || (y + dy) - r <= yLeftUp) {
//            if ((y + r + dy) >= height) {
//                this.center.setY(height - r);
//            } else {
//                this.center.setY(yLeftUp + r);
//            }
//            dy = dy * (-1);
//            this.setVelocity(dx, dy);
//        }
    }
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}