// ofir goldberg, 315141325
package Levels;
import Helpers.Counter;
import Geometry.SpriteCollection;
import Geometry.Velocity;
import GameRunner.GameEnvironment;
import GameRunner.EndScreen;
import GameRunner.AnimationRunner;
import GameRunner.CountdownAnimation;
import GameRunner.PauseScreen;
import GameRunner.KeyPressStoppableAnimation;
import Interfaces.Animation;
import Interfaces.Collidable;
import Interfaces.LevelInformation;
import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.ScoreIndicator;
import Listeners.ScoreTrackingListener;
import Geometry.Ball;
import Geometry.Block;
import Geometry.Paddle;
import Geometry.Rectangle;
import Geometry.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Interfaces.Sprite;

/**
 * The class Game.
 */
public class GameLevel implements Animation {
    public static final int WIDTH = 800;               // The constant WIDTH.
    public static final int HEIGHT = 600;              // The constant HEIGHT.
    public static final int BLOCK_WIDTH = 40;          // The constant BLOCK_WIDTH.
    public static final int BLOCK_HEIGHT = 20;         // The constant BLOCK_HEIGHT.
    public static final int RADIUS = 7;                // The constant RADIUS.
    public static final int SIDE_BLOCKS = 15;          // The constant SIDE_BLOCKS.
    public static final int PADDLE_HEIGHT = 15;        // The constant PADDLE_HEIGHT.
    public static final int PADDLE_DISTANCE = 50;      // The constant PADDLE_HEIGHT.
    public static final String IN_GAME = "inGame";     // The constant IN_GAME.
    public static final String WINNER = "winner";      // The constant WINNER.
    public static final String LOSER = "loser";        // The constant LOSER.
    public static final int NUM_OF_SECONDS = 3;        // The constant of NUM_OF_SECONDS.
    public static final int COUNT_FROM = 3;            // The constant of  COUNT_FROM.
    public static final int FRAME_PER_SECOND = 60;     // The constant of FRAME_PER_SECOND.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    private GUI gui;
    private ArrayList<Ball> ballsList;
    private List<Block> blocksList;
    private Counter numOfBlockes;
    private Counter numOfBalls;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private EndScreen endScreen;
    private String situation;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private boolean running;
    private int indexForMotion;
    private boolean flagForOneHundredBonusPoints;
    private int framePerSecond;
    private CountdownAnimation countDown;
    private LevelInformation levelInformation;
    private String finalLevel;

    /**
     * Constructor of a new Game.
     *
     * @param levelInformation      the level information
     * @param keyboard              the keyboard
     * @param runner                the runner
     * @param gui                   the gui
     * @param score                 the score
     * @param scoreTrackingListener the score tracking listener
     * @param scoreIndicator        the score indicator
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner runner, GUI gui,
                     Counter score, ScoreTrackingListener scoreTrackingListener, ScoreIndicator scoreIndicator) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.ballsList = new ArrayList<Ball>();
        this.blocksList = new ArrayList<Block>();
        this.numOfBlockes = new Counter();
        this.blockRemover = new BlockRemover(this, this.numOfBlockes);
        this.numOfBalls = new Counter();
        this.ballRemover = new BallRemover(this, this.numOfBalls);
        this.score = score;
        this.scoreTrackingListener = scoreTrackingListener;
        this.scoreIndicator = scoreIndicator;
        this.endScreen = new EndScreen(this);
        this.situation = IN_GAME;
        this.gui = gui;
        this.keyboard = keyboard;
        this.framePerSecond = FRAME_PER_SECOND;
        this.runner = runner;
        this.running = false;
        this.indexForMotion = -5;
        this.flagForOneHundredBonusPoints = false;
        this.countDown = new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, this.sprites, this);
        this.levelInformation = levelInformation;
        this.finalLevel = "";
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function that initialize a new game: create the Blocks, Balls, Paddle and add them to the game.
     */
    public void initialize() {
        this.sprites.addSprite(this.levelInformation.getBackground());
        this.createPaddle();
        this.createBall();
        this.createBlocks();
        this.createScoreTable();
    }

    /**
     * Function that run the game - start the animation loop.
     */
    public void run() {
        this.runner.setFramesPerSecond(this.countDown.getCountFrom() / (int) this.countDown.getNumOfSeconds());
        this.runner.run(this.countDown);
        this.runner.setFramesPerSecond(this.framePerSecond);
        this.running = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard, this.sprites)));
        } else if (this.blockRemover.getRemainingBlocks().getValue() == 0
                && levelInformation.levelName().equals(finalLevel)) {
            this.situation = WINNER;
            this.indexForMotion += 5;
            this.endScreen.winner(d, this.indexForMotion);
            if (this.indexForMotion == WIDTH) {
                this.indexForMotion = -600;
            }
            if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                gui.close();
            }
        } else if (this.ballRemover.getRemainingBalls().getValue() == 0) {
            this.situation = LOSER;
            this.indexForMotion += 5;
            this.endScreen.gameOver(d, this.indexForMotion);
            if (this.indexForMotion == WIDTH) {
                this.indexForMotion = -600;
            }
            if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                gui.close();
            }
        } else {
            if (this.blockRemover.getRemainingBlocks().getValue() == 0
                    || this.ballRemover.getRemainingBalls().getValue() == 0) {
                this.running = false;
            }
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Create paddle.
     */
    public void createPaddle() {
        int paddleWidth = this.levelInformation.paddleWidth();
        int paddleSpeed = this.levelInformation.paddleSpeed();
        Rectangle rect = new Rectangle(new Point((WIDTH / 2) - (paddleWidth / 2),
                HEIGHT - 30), paddleWidth, PADDLE_HEIGHT);
        this.paddle = new Paddle(this.keyboard, rect);
        this.paddle.setPaddleSpeed(paddleSpeed);
        this.paddle.setColor(this.levelInformation.getPaddleColor());
        this.paddle.addToGame(this);
    }

    /**
     * Create ball.
     */
    public void createBall() {
        int numOfBalls = this.levelInformation.numberOfBalls();
        List<Velocity> velocities = this.levelInformation.initialBallVelocities();
        for (int i = 0; i < numOfBalls; i++) {
            Ball ball = new Ball(new Point(this.paddle.getCollisionRectangle().getUpperLeft().getX()
                    + (this.paddle.getCollisionRectangle().getWidth() / 2),
                    this.paddle.getCollisionRectangle().getUpperLeft().getY() - SIDE_BLOCKS), RADIUS);
            ball.setColor(this.levelInformation.getBallColor());
            this.ballsList.add(ball);
        }
        for (int i = 0; i < numOfBalls; i++) {
            this.ballsList.get(i).setVelocity(velocities.get(i));
            this.ballsList.get(i).setGame(this.environment);
            this.ballsList.get(i).addToGame(this);
        }
        this.numOfBalls.setValue(levelInformation.numberOfBalls());
        ballRemover.setRemainingBalls(this.numOfBalls);
    }

    /**
     * Create blocks.
     */
    public void createBlocks() {
        this.blocksList = this.levelInformation.blocks();
        this.numOfBlockes.setValue(this.levelInformation.numberOfBlocksToRemove());
        blockRemover.setRemainingBlocks(this.numOfBlockes);
        for (int i = 0; i < this.blocksList.size(); i++) {
            if (this.blocksList.get(i).getIfDeathRegionBlock()) {
                this.blocksList.get(i).addHitListener(this.ballRemover);
            }
            this.blocksList.get(i).addToGame(this);
            if (!this.blocksList.get(i).getIfFrameBlock()) {
                this.blocksList.get(i).addHitListener(blockRemover);
                this.blocksList.get(i).addHitListener(scoreTrackingListener);
            }
        }
    }

    /**
     * Create score table.
     */
    public void createScoreTable() {
        this.scoreIndicator.addToGame(this);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Add collidable.
     *
     * @param c the Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    /**
     * Remove game.
     *
     * @param d the surface
     */
    public void removeGame(DrawSurface d) {
        for (int j = 0; j < this.blocksList.size(); j++) {
            this.blocksList.get(j).removeFromGame(this);
        }
        for (int j = 0; j < this.ballsList.size(); j++) {
            this.ballsList.get(j).removeFromGame(this);
        }
        this.sprites.removeSprite(this.levelInformation.getBackground());
        this.sprites.removeSprite(this.paddle);
        this.blocksList.removeAll(this.blocksList);
        this.ballsList.removeAll(this.ballsList);
        this.numOfBalls.setValue(0);
        this.ballRemover.setRemainingBalls(this.numOfBalls);
    }

    /**
     * Recreate game.
     *
     * @param d the d
     */
    public void reCreateGame(DrawSurface d) {
        this.sprites.addSprite(this.levelInformation.getBackground());
        this.createPaddle();
        this.createBall();
        this.createBlocks();
        this.createScoreTable();
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // getters
    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Gets paddle.
     *
     * @return the paddle
     */
    public Paddle getPaddle() {
        return this.paddle;
    }

    /**
     * Gets gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * Gets balls list.
     *
     * @return the balls list
     */
    public ArrayList<Ball> getBallsList() {
        return this.ballsList;
    }

    /**
     * Gets blocks list.
     *
     * @return the blocks list
     */
    public List<Block> getBlocksList() {
        return this.blocksList;
    }

    /**
     * Gets num of blockes.
     *
     * @return the num of blockes
     */
    public Counter getNumOfBlockes() {
        return this.numOfBlockes;
    }

    /**
     * Gets num of balls.
     *
     * @return the num of balls
     */
    public Counter getNumOfBalls() {
        return this.numOfBalls;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return this.score;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public EndScreen getEndScreen() {
        return this.endScreen;
    }

    /**
     * Gets situation.
     *
     * @return the situation
     */
    public String getSituation() {
        return this.situation;
    }

    /**
     * Gets keyboard.
     *
     * @return the keyboard
     */
    public KeyboardSensor getKeyboard() {
        return this.keyboard;
    }

    /**
     * Gets BlockRemover.
     *
     * @return the BlockRemover
     */
    public BlockRemover getBlockRemover() {
        return this.blockRemover;
    }

    /**
     * Gets BallRemover.
     *
     * @return the BallRemover
     */
    public BallRemover getBallRemover() {
        return this.ballRemover;
    }

    /**
     * Gets frame per second.
     *
     * @return the frame per second
     */
    public int getFramePerSecond() {
        return this.framePerSecond;
    }

    /**
     * Gets index for motion.
     *
     * @return the index for motion
     */
    public int getIndexForMotion() {
        return this.indexForMotion;
    }

    /**
     * Gets flag for one hundred bonus points.
     *
     * @return the flag for one hundred bonus points
     */
    public boolean getFlagForOneHundredBonusPoints() {
        return this.flagForOneHundredBonusPoints;
    }

    /**
     * Gets level information.
     *
     * @return the level information
     */
    public LevelInformation getLevelInformation() {
        return this.levelInformation;
    }
    /**
     * Gets final level.
     *
     * @return the final level
     */
    public String getFinalLevel() {
        return this.finalLevel;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // setters
    /**
     * Set random color to the paddle.
     */
    private void setPaddleRandomColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        this.paddle.setColor(new Color(r, g, b));
    }

    /**
     * Sets situation.
     *
     * @param situation the situation
     */
    public void setSituation(String situation) {
        this.situation = situation;
    }

    /**
     * Sets index for motion.
     *
     * @param index the index
     */
    public void setIndexForMotion(int index) {
        this.indexForMotion = index;
    }

    /**
     * Increase score.
     *
     * @param score the score
     */
    public void increaseScore(int score) {
        this.score.increase(score);
    }

    /**
     * Sets flag for one hundred bonus points.
     *
     * @param flag the flag
     */
    public void setFlagForOneHundredBonusPoints(boolean flag) {
        this.flagForOneHundredBonusPoints = flag;
    }

     /**
     * Sets final level.
     *
     * @param finalLevel the final level
     */
    public void setFinalLevel(String finalLevel) {
        this.finalLevel = finalLevel;
    }
}