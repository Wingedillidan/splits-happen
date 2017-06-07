package main.java;

import main.java.tests.GameTest;

/**
 * Created by collinm on 6/6/17.
 */
public class SplitsHappen {

    public static void main(String[] args) {
        Game game;
        String[] test = new String[]{"XXXXXXXXXXXX", "9-9-9-9-9-9-9-9-9-9-", "5/5/5/5/5/5/5/5/5/5/5", "X7/9-X-88/-6XXX81"};

        for (String s: test) {
            game = new Game(s);
            game.calcGame();
            System.out.println(game.toString());
            System.out.println("Total: " + game.getTotal());
        }

        GameTest.testAll();
    }

}
