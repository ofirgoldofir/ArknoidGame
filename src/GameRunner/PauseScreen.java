// Ofir Goldberg 315141325
package GameRunner;

import Geometry.SpriteCollection;
import Interfaces.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The class Pause screen.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private SpriteCollection gameScreen;

    /**
     * Constructor a new Pause screen.
     *
     * @param k          the k
     * @param gameScreen the game screen
     */
    public PauseScreen(KeyboardSensor k, SpriteCollection gameScreen) {
        this.keyboard = k;
        this.stop = false;
        this.gameScreen = gameScreen;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.WHITE);
        d.drawText(170, d.getHeight() / 2, "paused -- press space to continue", 32);
        d.setColor(Color.BLACK);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}