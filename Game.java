package main.java;

/**
 * Created by collinm on 6/6/17.
 */
public class Game {

    private Frame[] frames;
    private int total;

    public Game(String raw) {
        parse(raw);
    }

    private void parse(String input) {
        Frame[] result = new Frame[10]; // Out of scope task #3 assumption of 10 frames per string
        String stringFrame = "";
        int currentFrame = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X') {
                result[currentFrame] = new Frame("X");
                currentFrame++;
            } else {
                stringFrame += input.charAt(i);
            }

            // Check for 10th frame
            if (i + 1 == input.length() - 1) {
                stringFrame += input.charAt(i + 1);
                i++;
            }
            // Make the frame
            if (stringFrame.length() >= 2) {
                result[currentFrame] = new Frame(stringFrame);
                currentFrame++;
                stringFrame = "";
            }
        }

        this.frames = result;
    }

}
