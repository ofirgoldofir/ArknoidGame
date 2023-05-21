// ofir goldberg, 315141325
package Geometry;
import Levels.GameLevel;
import Interfaces.Collidable;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The class Paddle.
 */
public class Paddle implements Sprite, Collidable {
    public static final int PADDLE_MOVEMENT = 10;    // The constant PADDLE_MOVEMENT.
    public static final int PART_1_ANGLE = 150;     // Angle after hit for part 1 of the paddle
    public static final int PART_2_ANGLE = 120;     // Angle after hit for part 2 of the paddle
    public static final int PART_4_ANGLE = 60;      // Angle after hit for part 4 of the paddle
    public static final int PART_5_ANGLE = 30;      // Angle after hit for part 5 of the paddle
    private KeyboardSensor keyboard;
    private Rectangle paddle;
    private Color color;
    private int paddleSpeed;

    /**
     * Constructor of a new Paddle.
     *
     * @param keyboard the keyboard
     * @param rect     the rectangle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rect) {
        this.keyboard = keyboard;
        this.paddle = rect;
        this.color = Color.black;
        this.paddleSpeed = PADDLE_MOVEMENT;
    }

    /**
     * Move the paddle left.
     */
    public void moveLeft() {
        double oldX = this.paddle.getUpperLeft().getX();
        if (oldX > GameLevel.SIDE_BLOCKS) {
            if (oldX - this.paddleSpeed > GameLevel.SIDE_BLOCKS) {
                double newX = oldX - this.paddleSpeed;
                this.paddle.setUpperLeft(new Point(newX, this.paddle.getUpperLeft().getY()));
            } else {
                double newX = GameLevel.SIDE_BLOCKS;
                this.paddle.setUpperLeft(new Point(newX, this.paddle.getUpperLeft().getY()));
            }
        }
    }

    /**
     * Move the paddle right.
     */
    public void moveRight() {
        double oldX = this.paddle.getUpperLeft().getX();
        if (oldX + this.paddle.getWidth() < GameLevel.WIDTH - GameLevel.SIDE_BLOCKS) {
            if (oldX + this.paddleSpeed + this.paddle.getWidth() < GameLevel.WIDTH - GameLevel.SIDE_BLOCKS) {
                double newX = oldX + this.paddleSpeed;
                this.paddle.setUpperLeft(new Point(newX, this.paddle.getUpperLeft().getY()));
            } else {
                double temp1 = GameLevel.WIDTH - GameLevel.SIDE_BLOCKS - (oldX + this.paddleSpeed);
                double temp2 = this.paddle.getWidth() - temp1;
                double newX = (oldX + this.paddleSpeed) - temp2;
                this.paddle.setUpperLeft(new Point(newX, this.paddle.getUpperLeft().getY()));
            }
        }
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
//            System.out.println("the 'left arrow' key is pressed");
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
//            System.out.println("the 'right arrow' key is pressed");
            this.moveRight();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        int upperLeftX = (int) this.paddle.getUpperLeft().getX();
        int upperLeftY = (int) this.paddle.getUpperLeft().getY();
        int width = (int) this.paddle.getWidth();
        int height = (int) this.paddle.getHeight();
        d.setColor(this.color);
        d.fillRectangle(upperLeftX, upperLeftY, width, height);
        d.drawRectangle(upperLeftX, upperLeftY, width, height);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double division = this.paddle.getWidth() / 5;
        double leftXPaddle = this.paddle.getUpperLeft().getX();
        double part1 = leftXPaddle + division;
        double part2 = leftXPaddle + (2 * division);
        double part3 = leftXPaddle + (3 * division);
        double part4 = leftXPaddle + (4 * division);
        double part5 = leftXPaddle + (5 * division);
        double colXPoint = collisionPoint.getX();
        double speed = currentVelocity.getSpeed();
        Velocity newV = currentVelocity;
        double dx1 = Math.cos(Math.toRadians(PART_1_ANGLE)) * speed;
        double dy1 = -Math.sin(Math.toRadians(PART_1_ANGLE)) * speed;
        double dx2 = Math.cos(Math.toRadians(PART_2_ANGLE)) * speed;
        double dy2 = -Math.sin(Math.toRadians(PART_2_ANGLE)) * speed;
        double dx3 = Math.cos(Math.toRadians(PART_5_ANGLE)) * speed;
        double dy3 = -Math.sin(Math.toRadians(PART_5_ANGLE)) * speed;
        double dx4 = Math.cos(Math.toRadians(PART_4_ANGLE)) * speed;
        double dy4 = -Math.sin(Math.toRadians(PART_4_ANGLE)) * speed;
        if (colXPoint >= leftXPaddle && colXPoint <= part1) {
            newV = new Velocity(dx1, dy1);
        }
        if (colXPoint >= part1 && colXPoint <= part2) {
            newV = new Velocity(dx2, dy2);
        }
        if (colXPoint >= part2 && colXPoint <= part3) {
            newV.setDy(currentVelocity.getDy() * (-1));
        }
        if (colXPoint >= part3 && colXPoint <= part4) {
            newV = new Velocity(dx3, dy3);
        }
        if (colXPoint >= part4 && colXPoint <= part5) {
            newV = new Velocity(dx4, dy4);
        }
        return newV;
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Set color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Set paddle speed.
     *
     * @param paddleSpeed the paddle speed
     */
    public void setPaddleSpeed(int paddleSpeed) {
        this.paddleSpeed = paddleSpeed;
    }
}