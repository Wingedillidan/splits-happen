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
        Frame[] result = new Frame[10]; // Out of scope task #3 assumes 10 frames per string
        String stringFrame = "";
        int currentFrame = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X' && i + 2 < input.length() - 1) {
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

    public void calcGame() {
        int result = 0;

        for (int i = 0; i < this.frames.length; i++) {
            int[] frameBalls = this.frames[i].getBalls();
            int frameRawTotal = this.frames[i].getRawTotal();

            if (frameBalls[0] == 10) {
                result += 10 + this.calcStrikeBonus(i);
            } else if (frameRawTotal == 10) {
                result += 10 + this.calcSpareBonus(i);
            } else {
                result += frameRawTotal;
            }
        }

        this.total = result;
    }

    private int calcStrikeBonus(int frameOfStrike) {
        if (frameOfStrike < 9) {
            if (this.frames[frameOfStrike + 1].getBalls()[0] == 10) {
                return 10 + this.frames[frameOfStrike + 2].getBalls()[1];
            } else {
                return this.frames[frameOfStrike + 1].getRawTotal();
            }
        } else if (frameOfStrike == 9) {
            int[] tenthFrameBalls = this.frames[10].getBalls();
            return tenthFrameBalls[0] + tenthFrameBalls[1];
        } else {
            return this.frames[frameOfStrike].getRawTotal() - 10;
        }
    }

    private int calcSpareBonus(int frameOfSpare) {
        if (frameOfSpare < 10) {
            return this.frames[frameOfSpare].getBalls()[0];
        } else {
            return this.frames[frameOfSpare].getBalls()[2];
        }
    }

    public String toString() {
        String result = "Bowling Game with Frames: ";
        for (Frame f: this.frames) {
            result += "[ ";
            for (int i: f.getBalls()) {
                result += i + " ";
            }
            result += "]";
        }

        return result;
    }

    public Frame[] getFrames() {
        return frames;
    }

    public void setFrames(Frame[] frames) {
        this.frames = frames;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
