// ofir goldberg, 315141325
package Geometry;
import Levels.GameLevel;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private java.awt.Color color;
    private List<HitListener> hitListeners;
    private boolean deathRegionBlock;
    private boolean isFrameBlock;

    /**
     * Constructor of a new Block.
     *
     * @param rectangle the rectangle
     */
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
        this.color = Color.black;
        this.hitListeners = new ArrayList<>();
        this.deathRegionBlock = false;
        this.isFrameBlock = false;
    }

    /**
     * Constructor of new Block.
     *
     * @param rectangle  the rectangle
     * @param color the color
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double width = this.getCollisionRectangle().getWidth();
        double height = this.getCollisionRectangle().getHeight();
        double xLeftUp = this.getCollisionRectangle().getUpperLeft().getX();
        double yLeftUp = this.getCollisionRectangle().getUpperLeft().getY();
        if (x == xLeftUp || x == xLeftUp + width) {
            dx = dx * (-1);
        }
        if (y == yLeftUp || y == yLeftUp + height) {
            dy = dy * (-1);
        }
        notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Function that draw the block on the given DrawSurface.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        int upperLeftX = (int) this.rectangle.getUpperLeft().getX();
        int upperLeftY = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        surface.fillRectangle(upperLeftX, upperLeftY, width, height);
        surface.setColor(Color.black);
        surface.drawRectangle(upperLeftX, upperLeftY, width, height);
    }

    /**
     * Set block as a death region block.
     *
     * @param val the val
     */
    public void setAsDeathRegionBlock(boolean val) {
        this.deathRegionBlock = val;
    }

    /**
     * Set block as a frame block.
     *
     * @param val the val
     */
    public void setAsFrameBlock(boolean val) {
        this.isFrameBlock = val;
    }

    /**
     * Get if block is a death region block.
     *
     * @return the val
     */
    public boolean getIfDeathRegionBlock() {
        return this.deathRegionBlock;
    }

    /**
     * Get if block is a frame block.
     *
     * @return the val
     */
    public boolean getIfFrameBlock() {
        return this.isFrameBlock;
    }


    @Override
    public void timePassed() {
//        currently, do nothing
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
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
}
