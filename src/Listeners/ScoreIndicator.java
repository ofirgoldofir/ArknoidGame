// Ofir Goldberg 315141325
package Listeners;
import Helpers.Counter;
import GameRunner.GameFlow;
import Levels.GameLevel;
import Interfaces.Sprite;
import biuoop.DrawSurface;

/**
 * The class Score indicator.
 */
public class ScoreIndicator implements Sprite {

    private Counter score;
    private GameFlow game;
    private GameLevel gameLevel;

    /**
     * Constructor a new Score indicator.
     *
     * @param score the score
     * @param game  the game
     */
    public ScoreIndicator(Counter score, GameFlow game) {
        this.score = score;
        this.game = game;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText(GameLevel.WIDTH / 2, GameLevel.SIDE_BLOCKS + 5, "score: " + this.score.getValue(), 18);
        d.drawText(GameLevel.WIDTH / 2 + 300, GameLevel.SIDE_BLOCKS + 5, "level: " + this.game.getLevelNumber(), 18);
        d.drawText(GameLevel.WIDTH / 2 - 365, GameLevel.SIDE_BLOCKS + 5, "level name: "
                + this.gameLevel.getLevelInformation().getLevelName(), 18);
    }
    @Override
    public void timePassed() {
        // currently does nothing.
    }
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Add score.
     *
     * @param i the
     */
    public void addScore(int i) {
        this.score.increase(i);
    }

    /**
     * Sets game level.
     * @param gameLevel the game level.
     */
    public void setGameLevel(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }
}
