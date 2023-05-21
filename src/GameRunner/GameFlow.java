// Ofir Goldberg 315141325
package GameRunner;

import Helpers.Counter;
import Interfaces.LevelInformation;
import Levels.GameLevel;
import Listeners.ScoreIndicator;
import Listeners.ScoreTrackingListener;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/**
 * The class Game flow.
 */
public class GameFlow {
    public static final int MAX_LEVEL = 5;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private int levelNumber;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;


    /**
     * Constructor of a new Game flow.
     *
     * @param ar  the ar
     * @param ks  the ks
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.levelNumber = 0;
        this.score = new Counter();
        this.scoreTrackingListener = new ScoreTrackingListener(this.score);
        this.scoreIndicator = new ScoreIndicator(this.score, this);
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (int i = 0; i < levels.size(); i++) {
            this.levelNumber = i + 1;
            GameLevel level = new GameLevel(levels.get(i), this.keyboardSensor, this.animationRunner, gui,
                    this.score, this.scoreTrackingListener, this.scoreIndicator);
            level.setFinalLevel(levels.get(levels.size() - 1).getLevelName());
            this.scoreIndicator.setGameLevel(level);
            level.initialize();
            while (level.getBallRemover().getRemainingBalls().getValue() != 0
                    && level.getBlockRemover().getRemainingBlocks().getValue() != 0) {
                System.out.println("check");
                level.run();
            }
            this.scoreIndicator.addScore(100);
        }
    }

    /**
     * Gets level number.
     *
     * @return the level number
     */
    public int getLevelNumber() {
        return this.levelNumber;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return this.score.getValue();
    }

    /**
     * Sets level number.
     *
     * @param levelNumber the level number
     */
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score.setValue(score);
    }

}