// ofir goldberg, 315141325

import GameRunner.AnimationRunner;
import GameRunner.GameFlow;
import Interfaces.LevelInformation;
import Levels.GameLevel;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.Level4;
import Levels.Level5;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Ass 3 game.
 */
public class runGame {
    /**
     * Main function.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(runner, keyboard, gui);
        List<LevelInformation> levels = new ArrayList<>();
        for (String s : args) {
            switch (s) {
                case "1":
                    levels.add(new Level1());
                    break;
                case "2":
                    levels.add(new Level2());
                    break;
                case "3":
                    levels.add(new Level3());
                    break;
                case "4":
                    levels.add(new Level4());
                    break;
                case "5":
                    levels.add(new Level5());
                    break;
                default:
                    break;
            }
        }
        if (levels.size() == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
            levels.add(new Level5());
        }
        gameFlow.runLevels(levels);



















//        List<LevelInformation> levels = new ArrayList<LevelInformation>();
//        LevelInformation levelInformation1 = new Level1();
//        LevelInformation levelInformation2 = new Level2();
//        LevelInformation levelInformation3 = new Level3();
//        LevelInformation levelInformation4 = new Level4();
//        LevelInformation levelInformation5 = new Level5();
//        levels.add(levelInformation1);
//        levels.add(levelInformation2);
//        levels.add(levelInformation3);
//        levels.add(levelInformation4);
//        levels.add(levelInformation5);
//
//
//
//
//
//        GameLevel game = new GameLevel(levelInformation5, keyboard, runner, gui); // creating new game.
//        game.initialize(); // initialize blocks, balls and paddle.
//        game.run(); // run the game.
    }
}
