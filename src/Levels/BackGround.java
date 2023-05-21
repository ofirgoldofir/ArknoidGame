// Ofir Goldberg 315141325
package Levels;

import Interfaces.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.Polygon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The class Back ground.
 */
public class BackGround implements Sprite {
    private int levelNumber;
    private int motionCloud1;
    private int motionCloud2;
    private int motionCloud3;
    private int motionCloud4;
    private int cloudStartPosition1;
    private int cloudStartPosition2;
    private int cloudStartPosition3;
    private int cloudStartPosition4;
    private boolean cloudFlag;

    /**
     * Constructor of a new Back ground.
     *
     * @param levelNumber the level number
     */
    public BackGround(int levelNumber) {
        this.levelNumber = levelNumber;
        this.motionCloud1 = 0;
        this.motionCloud2 = 0;
        this.motionCloud3 = 0;
        this.motionCloud4 = 0;
        this.cloudStartPosition1 = 100;
        this.cloudStartPosition2 = 250;
        this.cloudStartPosition3 = 400;
        this.cloudStartPosition4 = 650;
        this.cloudFlag = false;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.levelNumber == 1) {
            houseBackground(d);
        } else if (this.levelNumber == 2) {
            rainbow(d);
        } else if (this.levelNumber == 3) {
            spaceBackground(d);
            drawImageFromFile(d, "spaceinvaders.txt");
        } else if (this.levelNumber == 4) {
            snowJangel(d);
        } else if (this.levelNumber == 5) {
            spiderman(d);
        }
    }

    @Override
    public void timePassed() {
        // do nothing
    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

//    /**
//     * Picture background.
//     *
//     * @param d the d
//     */
//    private void pictureBackground(DrawSurface d) {
//        d.setColor(Color.WHITE);
//        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
//        ImageIcon img = new ImageIcon("src/Images/testPic.jpg");
//        d.drawImage(0, 0, img.getImage());
//        d.setColor(Color.WHITE);
//        if (this.levelNumber.getValue() == 1 && this.game.getSituation().equals(Game.IN_GAME)) {
//            d.fillRectangle(375, 0, 425, 250);
//            d.fillRectangle(0, 250, 800, 500);
//        } else if (this.levelNumber.getValue() == 2 && this.game.getSituation().equals(Game.IN_GAME)) {
//            d.fillRectangle(375, 0, 425, 250);
//            d.fillRectangle(0, 250, 800, 150);
//            d.fillRectangle(0, 400, 425, 250);
//        } else if (this.levelNumber.getValue() == 3 && this.game.getSituation().equals(Game.IN_GAME)) {
//            d.fillRectangle(375, 0, 50, 700);
//            d.fillRectangle(0, 250, 800, 150);
//            d.fillRectangle(0, 400, 425, 250);
//        } else if (this.levelNumber.getValue() == 4 && this.game.getSituation().equals(Game.IN_GAME)) {
//            d.setColor(Color.WHITE);
//            d.fillRectangle(375, 0, 50, 700);
//            d.fillRectangle(0, 250, 800, 150);
//        } else if (this.levelNumber.getValue() == 5 && this.game.getSituation().equals(Game.IN_GAME)) {
//            d.fillRectangle(200, 250, 400, 150);
//        }
//    }

    /**
     * draw direct hit background.
     *
     * @param d the d
     */
    public void rainbow(DrawSurface d) {
        d.setColor(new Color(51, 204, 255)); // light blue
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        d.setColor(Color.red); // red
        d.fillCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT, GameLevel.WIDTH / 2);
        d.setColor(new Color(0xEC7D2A)); // orange
        d.fillCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT, GameLevel.WIDTH / 2 - 20);
        d.setColor(Color.yellow); // yellow
        d.fillCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT, GameLevel.WIDTH / 2 - 40);
        d.setColor(Color.green); // green
        d.fillCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT, GameLevel.WIDTH / 2 - 60);
        d.setColor(new Color(0x26D4EC)); // Very light blue
        d.fillCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT, GameLevel.WIDTH / 2 - 80);
        d.setColor(new Color(0x2333DE)); // dark blue
        d.fillCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT, GameLevel.WIDTH / 2 - 100);
        d.setColor(new Color(0xA66EE3)); // purple
        d.fillCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT, GameLevel.WIDTH / 2 - 120);
        d.setColor(new Color(51, 204, 255)); // light blue
        d.fillCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT, GameLevel.WIDTH / 2 - 140);
        this.drawClouds(d, 0, 550, 40);
        this.drawClouds(d, GameLevel.WIDTH - 150, 550, 40);

        this.drawClouds(d, this.cloudStartPosition1 + this.motionCloud1, 100, 20);
        this.drawClouds(d, this.cloudStartPosition2 + this.motionCloud2, 200, 15);
        this.drawClouds(d, this.cloudStartPosition3 + this.motionCloud3, 150, 25);
        this.drawClouds(d, this.cloudStartPosition4 + this.motionCloud4, 175, 18);

        this.motionCloud1 += 2;
        this.motionCloud2 += 2;
        this.motionCloud3 += 2;
        this.motionCloud4 += 2;

        if (this.cloudStartPosition1 + this.motionCloud1 > GameLevel.WIDTH) {
            this.motionCloud1 = -this.cloudStartPosition1 - 100;
        }
        if (this.cloudStartPosition2 + this.motionCloud2 > GameLevel.WIDTH) {
            this.motionCloud2 = -this.cloudStartPosition2 - 100;
        }
        if (this.cloudStartPosition3 + this.motionCloud3 > GameLevel.WIDTH) {
            this.motionCloud3 = -this.cloudStartPosition3 - 100;
        }
        if (this.cloudStartPosition4 + this.motionCloud4 > GameLevel.WIDTH) {
            this.motionCloud4 = -this.cloudStartPosition4 - 100;
        }
    }

    /**
     * draw clouds.
     *
     * @param d    the d
     * @param x    the x
     * @param y    the y
     * @param size the size
     * @Param size the size
     */
    public void drawClouds(DrawSurface d, int x, int y, int size) {
        d.setColor(new Color(222, 232, 222));
        d.fillCircle(x, y, size);
        d.fillCircle(x + size, y - size / 2, size);
        d.fillCircle(x + size * 2, y - size, size);
        d.fillCircle(x + size * 3, y - (int) (size / 1.3), size);
        d.fillCircle(x + size, y + size / 3, size);
        d.fillCircle(x + size * 2, y + size / 3 + (int) (size / 10), size);
        d.fillCircle(x + size * 3, y + size / 3, size);
        d.fillCircle(x + size * 4, y, size);
    }

    /**
     * draw house and tree background.
     *
     * @param d the surface
     */
    private void houseBackground(DrawSurface d) {
        // background
        d.setColor(new Color(51, 153, 255)); // blue
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        // sun
        d.setColor(new Color(255, 153, 0));
        d.fillOval(30, 30, 150, 100);
        // house
        d.setColor(Color.BLACK);
        d.drawRectangle(50, 300, 200, 400); // house
        d.drawRectangle(80, 350, 40, 60); // window
        d.drawRectangle(180, 350, 40, 60); // window
        d.drawRectangle(117, 480, 67, 200); // door
        // roof
        d.setColor(new Color(102, 51, 0)); // brown
        Polygon p = new Polygon();
        p.addPoint(150, 150);
        p.addPoint(250, 300);
        p.addPoint(50, 300);
        // grass
        d.fillPolygon(p);
        d.setColor(new Color(0, 102, 0)); // green
        int adding = 20, a = 15, b = 25, c = 35;
        for (int i = 0; i < 40; i++) {
            Polygon p1 = new Polygon();
            p1.addPoint(b + (i * adding), 550);
            p1.addPoint(a + (i * adding), GameLevel.HEIGHT);
            p1.addPoint(c + (i * adding), GameLevel.HEIGHT);
            d.fillPolygon(p1);
            p1.addPoint(b + 11 + (i * adding), 550);
            p1.addPoint(a + 11 + (i * adding), GameLevel.HEIGHT);
            p1.addPoint(c + 11 + (i * adding), GameLevel.HEIGHT);
            d.fillPolygon(p1);
        }
        // birds
        d.setColor(Color.BLACK);
        d.drawLine(300, 300, 312, 312);
        d.drawLine(324, 300, 312, 312);
        d.drawLine(500, 250, 512, 262);
        d.drawLine(524, 250, 512, 262);
        // trees
        d.setColor(new Color(102, 51, 0)); // brown
        d.fillRectangle(600, 400, 25, 400);
        d.setColor(new Color(0, 102, 0)); // green

        Polygon tree1Part1 = new Polygon();
        tree1Part1.addPoint(612, 200);
        tree1Part1.addPoint(530, 400);
        tree1Part1.addPoint(690, 400);
        d.fillPolygon(tree1Part1);

        Polygon tree1Part2 = new Polygon();
        tree1Part2.addPoint(612, 175);
        tree1Part2.addPoint(510, 350);
        tree1Part2.addPoint(710, 350);
        d.fillPolygon(tree1Part2);
    }

    /**
     * draw space background.
     *
     * @param d the surface
     */
    private void spaceBackground(DrawSurface d) {
        Random random = new Random();
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        for (int i = 0; i < 15; i++) {
            d.setColor(Color.WHITE);
            int x1 = random.nextInt(GameLevel.WIDTH);
            int y1 = random.nextInt(GameLevel.HEIGHT);
            d.drawLine(x1, y1, x1 + 5, y1 + 5);
        }
        d.setColor(Color.WHITE);
//        for (int i = 0; i < 15; i++) {
//            Polygon starPart1 = new Polygon();
//            Polygon starPart2 = new Polygon();
//            int x2 = random.nextInt(GameLevel.WIDTH);
//            int y2 = random.nextInt(GameLevel.HEIGHT);
//            starPart1.addPoint(x2 + 6, y2 + 8);
//            starPart1.addPoint(x2, y2);
//            starPart1.addPoint(x2 + 9, y2);
//            d.fillPolygon(starPart1);
//            starPart2.addPoint(x2 + 6, y2 - 8);
//            starPart2.addPoint(x2, y2 + 5);
//            starPart2.addPoint(x2 + 9, y2 + 5);
//            d.fillPolygon(starPart2);
//        }
    }

    /**
     * draw Israel flag background.
     *
     * @param d the surface
     */
    private void israelBackground(DrawSurface d) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, GameLevel.SIDE_BLOCKS, GameLevel.WIDTH, (3 * GameLevel.SIDE_BLOCKS));
        d.fillRectangle(0, GameLevel.HEIGHT - (4 * GameLevel.SIDE_BLOCKS), GameLevel.WIDTH,
                (3 * GameLevel.SIDE_BLOCKS));
        d.setColor(Color.BLUE);
        // first Triangular lines
        d.drawLine(200, 450, 400, 100);
        d.drawLine(600, 450, 400, 100);
        d.drawLine(200, 450, 600, 450);
        // second Triangular lines
        d.drawLine(200, 150, 600, 150);
        d.drawLine(400, 500, 600, 150);
        d.drawLine(400, 500, 200, 150);
        // anthem
        d.setColor(Color.BLUE);

    }

    /**
     * draw background.
     *
     * @param d the surface
     */
    private void snowJangel(DrawSurface d) {
        d.setColor(new Color(0, 0, 153)); // blue
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        Random random = new Random();
        for (int i = 0; i < 15; i++) {
            d.setColor(Color.WHITE);
            int x1 = random.nextInt(GameLevel.WIDTH);
            int y1 = random.nextInt(GameLevel.HEIGHT);
            d.fillCircle(x1, y1, 3);
        }
        d.fillOval(-100, 550, 190, 150);
        d.fillOval(0, 550, 190, 150);
        d.fillOval(100, 550, 190, 150);
        d.fillOval(200, 550, 190, 150);
        d.fillOval(300, 550, 190, 150);
        d.fillOval(400, 550, 190, 150);
        d.fillOval(500, 550, 190, 150);
        d.fillOval(600, 550, 190, 150);
        d.fillOval(700, 550, 190, 150);
        // trees
        int adding = 135, lower;
        for (int i = 1; i < 7; i++) {
            if (i % 2 == 0) {
                lower = 50;
            } else {
                lower = 0;
            }
            d.setColor(new Color(102, 51, 0)); // brown
            d.fillRectangle(-70 + (i * adding), 480, 25, 400);
            d.setColor(new Color(0, 102, 0)); // green
            Polygon tree1Part1 = new Polygon();
            tree1Part1.addPoint(-61 + (i * adding), 280 + lower);
            tree1Part1.addPoint(-111 + (i * adding), 480 + lower);
            tree1Part1.addPoint(-10 + (i * adding), 480 + lower);
            d.fillPolygon(tree1Part1);
            Polygon tree1Part2 = new Polygon();
            tree1Part2.addPoint(-61 + (i * adding), 255 + lower);
            tree1Part2.addPoint(-130 + (i * adding), 430 + lower);
            tree1Part2.addPoint(10 + (i * adding), 430 + lower);
            d.fillPolygon(tree1Part2);
            Polygon tree1Part3 = new Polygon();
            tree1Part3.addPoint(-61 + (i * adding), 175 + lower);
            tree1Part3.addPoint(-111 + (i * adding), 345 + lower);
            tree1Part3.addPoint(-10 + (i * adding), 345 + lower);
            d.fillPolygon(tree1Part3);
        }
        // moon
        d.setColor(Color.WHITE);
        d.fillOval(70, 30, 180, 130);
        d.setColor(new Color(102, 102, 102));
    }

    /**
     * draw Spider-Man background.
     *
     * @param d the surface
     */
    public void spiderman(DrawSurface d) {
        d.setColor(new Color(0x1E27D0));
        d.fillRectangle(0, 0, GameLevel.WIDTH, GameLevel.HEIGHT);
        d.setColor(Color.BLACK);
        d.fillCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, 5);
        d.drawCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, 50);
        d.drawCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, 100);
        d.drawCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, 150);
        d.drawCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, 200);
        d.drawCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, 250);
        d.drawCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, 300);
        d.drawCircle(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, 350);
        d.drawLine(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, GameLevel.WIDTH, GameLevel.HEIGHT);
        d.drawLine(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, 0, 0);
        d.drawLine(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, GameLevel.WIDTH, 0);
        d.drawLine(GameLevel.WIDTH / 2, GameLevel.HEIGHT / 2, 0, GameLevel.HEIGHT);
        d.drawLine(GameLevel.WIDTH / 2, 0, GameLevel.WIDTH / 2, GameLevel.HEIGHT);
        d.drawLine(0, GameLevel.HEIGHT / 2, GameLevel.WIDTH, GameLevel.HEIGHT / 2);
    }

    /**
     * Draw image from file.
     *
     * @param d        the d
     * @param fileName the file name
     */
    public void drawImageFromFile(DrawSurface d, String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            File myObj = new File("src/Levels/LevelsImages/" + fileName);
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
                if (fileName.equals("spaceinvaders.txt")) {
                    int blockWidth = 9;
                    int blockHeight = 15;
                    if (c == '%') {
                        d.setColor(new Color(0x53EF47));
                        d.fillRectangle((j * blockWidth) + 17, (i * blockHeight + 45), blockWidth, blockHeight);
                    }
                    if (c == '#') {
                        d.setColor(new Color(0xFFFFFF));
                        d.fillRectangle((j * blockWidth) + 17, (i * blockHeight + 45), blockWidth, blockHeight);
                    }
                } else if (fileName.equals("soccerfield.txt")) {
                    int blockWidth = 7;
                    int blockHeight = 18;
                    if (c == '1') {
                        d.setColor(new Color(0xFFFFFF));
                        d.fillRectangle((j * blockWidth) + 3, (i * blockHeight + 35), blockWidth, blockHeight);
                    }
                    if (c == '0') {
                        d.setColor(new Color(0x3C7500));
                        d.fillRectangle((j * blockWidth) + 3, (i * blockHeight + 35), blockWidth, blockHeight);
                    }
                    if (c == '?') {
                        d.setColor(new Color(0xFAB07F));
                        d.fillRectangle((j * blockWidth) + 3, (i * blockHeight + 35), blockWidth, blockHeight);
                    }
                    if (c == '!') {
                        d.setColor(new Color(0x1F171F));
                        d.fillRectangle((j * blockWidth) + 3, (i * blockHeight + 35), blockWidth, blockHeight);
                    }
                }
            }
        }
    }
}
