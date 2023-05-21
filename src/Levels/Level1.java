// Ofir Goldberg 315141325
package Levels;
import Geometry.Velocity;
import Interfaces.LevelInformation;
import Interfaces.Sprite;
import Geometry.Block;
import Geometry.Point;
import Geometry.Rectangle;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The class Level 1.
 */
public class Level1 implements LevelInformation {
    private int numberOfBalls;
    private List<Velocity> ballsVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private int levelNumber;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;
    private Color ballColor;
    private Color paddleColor;

    /**
     * Constructor of a new Level 1.
     */
    public Level1() {
        this.numberOfBalls = 1;
        this.ballsVelocities = new ArrayList<>();
        this.paddleSpeed = 20;
        this.paddleWidth = 100;
        this.levelName = "Direct Hit";
        this.levelNumber = 1;
        this.background = new BackGround(this.levelNumber);
        this.blocks = new ArrayList<>();
        this.numberOfBlocksToRemove = 0;
        this.ballColor = Color.black;
        this.paddleColor = Color.black;
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        Random rand = new Random();
        for (int i = 0; i < this.numberOfBalls; i++) {
            this.ballsVelocities.add(Velocity.fromAngleAndSpeed(GameLevel.RADIUS, 90));
        }
        return this.ballsVelocities;
    }
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }
    @Override
    public String levelName() {
        return this.levelName;
    }
    @Override
    public Sprite getBackground() {
        return this.background;
    }
    @Override
    public List<Block> blocks() {
        Block scoreBlock = new Block(new Rectangle(new Point(0, 0),
                GameLevel.WIDTH, 1.5 * GameLevel.SIDE_BLOCKS), Color.WHITE);
        scoreBlock.setAsFrameBlock(true);
        Block upBlock = new Block(new Rectangle(new Point(0, 1.5 * GameLevel.SIDE_BLOCKS),
                GameLevel.WIDTH, GameLevel.SIDE_BLOCKS), Color.GRAY);
        upBlock.setAsFrameBlock(true);
        Block downBlock = new Block(new Rectangle(new Point(0, GameLevel.HEIGHT + GameLevel.RADIUS),
                GameLevel.WIDTH, GameLevel.SIDE_BLOCKS), Color.GRAY);
        downBlock.setAsFrameBlock(true);
        downBlock.setAsDeathRegionBlock(true);
        Block leftBlock = new Block(new Rectangle(new Point(0, 0),
                GameLevel.SIDE_BLOCKS, GameLevel.HEIGHT), Color.GRAY);
        leftBlock.setAsFrameBlock(true);
        Block rightBlock = new Block(new Rectangle(new Point(GameLevel.WIDTH - GameLevel.SIDE_BLOCKS, 0),
                GameLevel.SIDE_BLOCKS, GameLevel.HEIGHT), Color.GRAY);
        rightBlock.setAsFrameBlock(true);
        this.blocks.add(scoreBlock);
        this.blocks.add(upBlock);
        this.blocks.add(downBlock);
        this.blocks.add(leftBlock);
        this.blocks.add(rightBlock);
        int endX = GameLevel.WIDTH - GameLevel.SIDE_BLOCKS - GameLevel.BLOCK_WIDTH;
        int startY = GameLevel.PADDLE_DISTANCE + GameLevel.SIDE_BLOCKS;
        // create the Blocks
        Block block = new Block(new Rectangle(new Point((GameLevel.WIDTH / 2) - 10, GameLevel.HEIGHT / 3),
                GameLevel.BLOCK_WIDTH, GameLevel.BLOCK_HEIGHT), Color.BLACK);
        this.blocks.add(block);
        this.numberOfBlocksToRemove++;
        drawImageFromFile();
        return this.blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
    @Override
    public Color getBallColor() {
        return this.ballColor;
    }
    @Override
    public Color getPaddleColor() {
        return this.paddleColor;
    }

    /**
     * Draw image from file.
     */
    public void drawImageFromFile() {
        int blockWidth = 13;
        int blockHeight = 11;
        List<String> lines = new ArrayList<>();
        try {
            File myObj = new File("src/Levels/LevelsImages/file.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                lines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                Rectangle rect = new Rectangle(new Point((j * blockWidth) - 165,
                        i * blockHeight), blockWidth, blockHeight);
                if (c == '@') {
                    Block block = new Block(rect, Color.BLACK);
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == '%') {
                    Block block = new Block(rect, Color.red);
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == '&') {
                    Block block = new Block(rect, Color.BLUE);
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
            }
        }
    }

    /**
     * Get level name.
     *
     * @return level name
     */
    public String getLevelName() {
        return this.levelName;
    }

}
