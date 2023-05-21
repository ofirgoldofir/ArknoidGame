// Ofir Goldberg 315141325
package GameRunner;
import Levels.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The class Level.
 */
public class EndScreen {
    private GameLevel game;

    /**
     * Constructor a new Level.
     *
     * @param game the game
     */
    public EndScreen(GameLevel game) {
        this.game = game;
    }

    /**
     * Winner.
     *
     * @param d the d
     * @param i the index
     */
    public void winner(DrawSurface d, int i) {
        for (int j = 0; j < this.game.getBlocksList().size(); j++) {
            if (!this.game.getBlocksList().get(j).getIfFrameBlock()) {
                this.game.getBlocksList().get(j).removeFromGame(this.game);
            }
        }
        for (int j = 0; j < this.game.getBallsList().size(); j++) {
            this.game.getBallsList().get(j).removeFromGame(this.game);
        }
        this.game.getPaddle().removeFromGame(this.game);
        this.game.removeSprite(this.game.getLevelInformation().getBackground());
        this.game.getLevelInformation().getBackground().drawOn(d);
        if (this.game.getLevelInformation().levelName().equals("Space Invaders")) {
            d.setColor(Color.WHITE);
        } else {
            d.setColor(Color.GREEN);
        }
        d.drawText(i, GameLevel.HEIGHT / 2, "You Win!", 100);
        if (this.game.getLevelInformation().levelName().equals("Space Invaders")) {
            d.setColor(Color.WHITE);
        } else {
            d.setColor(Color.black);
        }
        d.drawText(GameLevel.WIDTH / 2 - 130, GameLevel.HEIGHT / 2 + 100, "your score is: "
                + this.game.getScore().getValue(), 30);
        d.drawText(GameLevel.WIDTH / 2 - 80, GameLevel.HEIGHT / 2 + 150, "Press space to exit", 15);
    }

    /**
     * Game over.
     *
     * @param d the d
     * @param i the index
     */
    public void gameOver(DrawSurface d, int i) {
        for (int j = 0; j < this.game.getBlocksList().size(); j++) {
            if (!this.game.getBlocksList().get(j).getIfFrameBlock()) {
                this.game.getBlocksList().get(j).removeFromGame(this.game);
            }
        }
        for (int j = 0; j < this.game.getBallsList().size(); j++) {
            this.game.getBallsList().get(j).removeFromGame(this.game);
        }
        this.game.getPaddle().removeFromGame(this.game);
        this.game.removeSprite(this.game.getLevelInformation().getBackground());
        this.game.getLevelInformation().getBackground().drawOn(d);
        d.setColor(Color.RED);
        d.drawText(i, GameLevel.HEIGHT / 2, "Game Over", 100);
        if (this.game.getLevelInformation().levelName().equals("Space Invaders")) {
            d.setColor(Color.WHITE);
        } else {
            d.setColor(Color.BLACK);
        }
        d.drawText(GameLevel.WIDTH / 2 - 120, GameLevel.HEIGHT / 2 + 100, "your score is: "
                + this.game.getScore().getValue(), 30);
        d.drawText(GameLevel.WIDTH / 2 - 80, GameLevel.HEIGHT / 2 + 150, "Press space to exit", 15);
    }
}
