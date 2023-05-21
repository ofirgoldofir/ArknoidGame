// ofir goldberg, 315141325
package Interfaces;
import Levels.GameLevel;
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
    /**
     * Function that draw the sprite on a given surface.
     *
     * @param d the draw surface
     */
    void drawOn(DrawSurface d);

    /**
     * Function that notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * Function that add the sprite to a given game.
     *
     * @param g the game
     */
    void addToGame(GameLevel g);
}