// ofir goldberg, 315141325
package Geometry;
import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * The class Sprite collection.
 */
public class SpriteCollection {

    private ArrayList<Sprite> spriteList;

    /**
     * Constructor of a new Sprite collection.
     *
     * @param spriteList the sprite list
     */
    public SpriteCollection(ArrayList<Sprite> spriteList) {
        this.spriteList = spriteList;
    }

    /**
     * Constructor of a new Sprite collection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * Add sprite.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * Function that call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed();
        }
    }

    /**
     * Function that call drawOn(d) on all sprites.
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : this.spriteList) {
            sprite.drawOn(d);
        }
    }
}