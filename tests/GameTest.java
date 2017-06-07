package main.java.tests;

import main.java.Frame;
import main.java.Game;

import java.lang.reflect.Array;

/**
 * Run tests with the -ea java tag
 * Created by collinm on 6/7/17.
 */
public class GameTest {

    public static void testAll() {
        testParse();
        testCalcGame();
    }

    public static void testParse() {
        // Tests the conversion of the string input and it's resulting array of frames
        Game game;

        // Test 1
        String test1 = "XXXXXXXXXXXX";
        int[][] test1Expected = new int[][]{
                new int[]{10},
                new int[]{10},
                new int[]{10},
                new int[]{10},
                new int[]{10},
                new int[]{10},
                new int[]{10},
                new int[]{10},
                new int[]{10},
                new int[]{10, 10, 10},
        };

        game = new Game(test1);
        compareGameFrames(game, test1Expected);

        // Test 2
        String test2 = "9-9-9-9-9-9-9-9-9-9-";
        int[][] test2Expected = new int[][]{
                new int[]{9, 0},
                new int[]{9, 0},
                new int[]{9, 0},
                new int[]{9, 0},
                new int[]{9, 0},
                new int[]{9, 0},
                new int[]{9, 0},
                new int[]{9, 0},
                new int[]{9, 0},
                new int[]{9, 0},
        };

        game = new Game(test2);
        compareGameFrames(game, test2Expected);

        // Test 3
        String test3 = "5/5/5/5/5/5/5/5/5/5/5";
        int[][] test3Expected = new int[][]{
                new int[]{5, 5},
                new int[]{5, 5},
                new int[]{5, 5},
                new int[]{5, 5},
                new int[]{5, 5},
                new int[]{5, 5},
                new int[]{5, 5},
                new int[]{5, 5},
                new int[]{5, 5},
                new int[]{5, 5, 5},
        };

        game = new Game(test3);
        compareGameFrames(game, test3Expected);

        // Test 4
        String test4 = "X7/9-X-88/-6XXX81";
        int[][] test4Expected = new int[][]{
                new int[]{10},
                new int[]{7, 3},
                new int[]{9, 0},
                new int[]{10},
                new int[]{0, 8},
                new int[]{8, 2},
                new int[]{0, 6},
                new int[]{10},
                new int[]{10},
                new int[]{10, 8, 1},
        };

        game = new Game(test4);
        compareGameFrames(game, test4Expected);
    }

    public static void testCalcGame() {
        Game game;

        // Test 1
        String test1 = "XXXXXXXXXXXX";
        game = new Game(test1);
        game.calcGame();

        assert game.getTotal() == 300;

        // Test 2
        String test2 = "9-9-9-9-9-9-9-9-9-9-";
        game = new Game(test2);
        game.calcGame();

        assert game.getTotal() == 90;

        // Test 3
        String test3 = "5/5/5/5/5/5/5/5/5/5/5";
        game = new Game(test3);
        game.calcGame();

        assert game.getTotal() == 150;

        // Test 4
        String test4 = "X7/9-X-88/-6XXX81";
        game = new Game(test4);
        game.calcGame();

        assert game.getTotal() == 167;
    }

    private static void compareGameFrames(Game g, int[][] a) {
        Frame[] frame = g.getFrames();
        for (int i = 0; i < frame.length; i++) {
            int[] shot = frame[i].getBalls();

            for (int j = 0; j < shot.length; j++) {
                assert shot[j] == a[i][j];
            }

            assert shot.length == a[i].length;
        }

        assert frame.length == a.length;
    }
}
