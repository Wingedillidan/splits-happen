package main.java;

/**
 * Created by collinm on 6/6/17.
 */
public class Game {

    private Frame[] frames;
    private int total;
    private int numPins = 10;
    private int numFrames = 10;

    public Game(String raw) {
        parse(raw);
    }

    private void parse(String input) {
        Frame[] result = new Frame[this.numFrames];
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

            if (frameBalls[0] == this.numPins) {
                // STRIKE!
                result += this.numPins + this.calcStrikeBonus(i);
            } else if (frameRawTotal == this.numPins) {
                // SPARE.
                result += this.numPins + this.calcSpareBonus(i);
            } else {
                result += frameRawTotal;
            }
        }

        this.total = result;
    }

    private int calcStrikeBonus(int frameOfStrike) {
        // Note to self: THIS IS VALID FOR BASE-0 COUNTING ONLY
        int numFrames = this.frames.length - 1;

        if (frameOfStrike < numFrames - 1) {
            // Calculates bonuses that do not include the last frame
            if (this.frames[frameOfStrike + 1].getBalls()[0] == this.numPins) {
                // Checks if the next frame is also a strike
                return this.numPins + this.frames[frameOfStrike + 2].getBalls()[0];
            } else {
                return this.frames[frameOfStrike + 1].getRawTotal();
            }
        } else if (frameOfStrike == numFrames - 1) {
            // Second to last frame calculations
            // (remember: last frame is guaranteed at least 2 ball throws)
            int[] tenthFrameBalls = this.frames[numFrames].getBalls();
            return tenthFrameBalls[0] + tenthFrameBalls[1];
        } else {
            // For if the last frame starts with a strike
            // Subtracts total numPins as this calculates the bonus only
            return this.frames[frameOfStrike].getRawTotal() - this.numPins;
        }
    }

    private int calcSpareBonus(int frameOfSpare) {
        if (frameOfSpare < this.frames.length - 1) {
            // Calculates bonuses that do not include the last frame
            return this.frames[frameOfSpare + 1].getBalls()[0];
        } else {
            // Last frame spares always have a 3rd shot
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

    public int getNumPins() {
        return numPins;
    }

    public void setNumPins(int numPins) {
        this.numPins = numPins;
    }

    public int getNumFrames() {
        return numFrames;
    }

    public void setNumFrames(int numFrames) {
        this.numFrames = numFrames;
    }
}
