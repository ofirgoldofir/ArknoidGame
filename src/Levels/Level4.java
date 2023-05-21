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
 * The class Level 4.
 */
public class Level4 implements LevelInformation {
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
     * Constructor of a new Level 4.
     */
    public Level4() {
        this.numberOfBalls = 3;
        this.ballsVelocities = new ArrayList<>();
        this.paddleSpeed = 20;
        this.paddleWidth = 150;
        this.levelName = "Snowy forest";
        this.levelNumber = 4;
        this.background = new BackGround(this.levelNumber);
        this.blocks = new ArrayList<>();
        this.numberOfBlocksToRemove = 0;
        this.ballColor = new Color(0xDE952A);
        this.paddleColor = Color.BLACK;
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        Random rand = new Random();
        int angle = 60;
        for (int i = 0; i < this.numberOfBalls; i++) {
            this.ballsVelocities.add(Velocity.fromAngleAndSpeed(GameLevel.RADIUS, angle));
            if (i == 1) {
                angle = 90;
            } else {
                angle += 180;
            }
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
        int blockWidth = 22;
        int blockHeight = 15;
        Color color;
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                color = new Color(0xFFB954);
            } else if (i == 1) {
                color = new Color(0xA2690F);
            } else if (i == 2) {
                color = new Color(0x7C500F);
            } else if (i == 3) {
                color = new Color(0x674005);
            } else if (i == 4) {
                color = new Color(0x5B3A0A);
            } else {
                color = new Color(0x3D2707);
            }
            for (int j = 0; j < 35; j++) {
                Block block = new Block(new Rectangle(new Point(GameLevel.SIDE_BLOCKS + (blockWidth * j),
                        (10 * GameLevel.SIDE_BLOCKS) + (blockHeight * i)),
                        blockWidth, blockHeight), color);
                this.blocks.add(block);
                this.numberOfBlocksToRemove++;
            }
        }
//        drawImageFromFile();
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
     * draw Image From File.
     */
    public void drawImageFromFile() {
        int blockWidth = 17;
        int blockHeight = 17;
        List<String> lines = new ArrayList<>();
        try {
            File myObj = new File("src/Levels/LevelsImages/deer.txt");
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
                Rectangle rect = new Rectangle(new Point((j * blockWidth) + 80,
                        i * blockHeight + 50), blockWidth, blockHeight);
                if (c == '@') {
                    Block block = new Block(rect, Color.black);
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == '#') {
                    Block block = new Block(rect, new Color(0xEF071A));
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == '(') {
                    Block block = new Block(rect, new Color(0x36B1EE));
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == '!') {
                    Block block = new Block(rect, new Color(0x944AE3));
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == '?') {
                    Block block = new Block(rect, new Color(0xEC7D2A));
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == '%') {
                    Block block = new Block(rect, new Color(0x62400B));
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == '.') {
                    Block block = new Block(rect, new Color(0xF1C483));
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == '/') {
                    Block block = new Block(rect, new Color(0xFDE604));
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == '*') {
                    Block block = new Block(rect, new Color(0x807D80));
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
                if (c == 'w') {
                    Block block = new Block(rect, new Color(0xFFFFFF));
                    this.blocks.add(block);
                    this.numberOfBlocksToRemove++;
                }
            }
        }
    }

    /**
     * get Level Name.
     *
     * @return level name.
     */
    public String getLevelName() {
        return this.levelName;
    }
}
