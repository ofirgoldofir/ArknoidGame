// Ofir Goldberg 315141325
package GameRunner;

import Geometry.SpriteCollection;
import Interfaces.Animation;
import Levels.GameLevel;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class Countdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private GameLevel gameLevel;

    /**
     * Constructor of a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     * @param game         the game
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, GameLevel game) {
         this.numOfSeconds = numOfSeconds;
         this.countFrom = countFrom;
         this.gameScreen = gameScreen;
         this.gameLevel = game;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        if (this.gameLevel.getLevelInformation().levelName().equals("Space Invaders")
                || this.gameLevel.getLevelInformation().levelName().equals("Direct Hit")
                || this.gameLevel.getLevelInformation().levelName().equals("Spider Man")) {
            d.setColor(Color.WHITE);
        }
        if (countFrom == 3) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "3", 60);
        }
        if (countFrom == 2) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "2", 60);
        }
        if (countFrom == 1) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "1", 60);
        }
        if (countFrom == 0) {
            d.drawText((d.getWidth() / 2) - 120, d.getHeight() / 2, "Let's Go!", 60);
        }
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        return this.countFrom < 0;
    }

    /**
     * Gets count from.
     *
     * @return the count from
     */
    public int getCountFrom() {
        return this.countFrom;
    }

    /**
     * Gets num of seconds.
     *
     * @return the num of seconds
     */
    public double getNumOfSeconds() {
        return this.numOfSeconds;
    }
}
