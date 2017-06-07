package main.java;

/**
 * Created by collinm on 6/6/17.
 */
public class Game {

    private Frame[] frames;
    private int total;
    private final int numPins = 10;
    private final int numFrames = 10;

    public Game(String raw) {
        parse(raw);
    }

    private void parse(String input) {
        Frame[] result = new Frame[this.numFrames];
        String stringFrame = "";
        int currentFrame = 0;

        for (int i = 0; i < input.length(); i++) {
            // Build string for a single frame
            if (input.charAt(i) == 'X' && i + 2 < input.length() - 1) {
                // Non-tenth frame strikes are single-shot frames
                result[currentFrame] = new Frame("X", this.numPins);
                currentFrame++;

                continue;
            } else {
                stringFrame += input.charAt(i);
            }

            // Make the frame
            if (stringFrame.length() >= 2) {
                if (i + 2 == input.length()) {
                    // 10th frame, 3rd shot checking
                    stringFrame += input.charAt(i + 1);
                    i++; // Make this iteration the last
                }
                result[currentFrame] = new Frame(stringFrame, this.numPins);
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
                // All the pins down in the first shot? STRIKE!
                result += this.numPins + this.calcStrikeBonus(i);
            } else if (frameRawTotal == this.numPins) {
                // All the pins down in the sum of two shots? SPARE.
                result += this.numPins + this.calcSpareBonus(i);
            } else {
                result += frameRawTotal;
            }
        }

        this.total = result;
    }

    // Bonus calculations return only the extra value earned from future frames
    private int calcStrikeBonus(int frameOfStrike) {
        // Note to self: THIS IS VALID FOR BASE-0 COUNTING ONLY
        int numFrames = this.frames.length - 1;

        if (frameOfStrike <= numFrames - 2) {
            // Calculates bonuses that do not require the last frame to calculate
            if (this.frames[frameOfStrike + 1].getBalls()[0] == this.numPins) {
                // If the next frame is also a strike, we need to get a shot from 2 frames ahead
                return this.numPins + this.frames[frameOfStrike + 2].getBalls()[0];
            } else {
                // The next frame is inclusive of 2 shots
                return this.frames[frameOfStrike + 1].getRawTotal();
            }
        } else if (frameOfStrike == numFrames - 1) {
            // Second to last frame calculations
            // (remember: last frame is guaranteed at least 2 ball throws)
            int[] tenthFrameBalls = this.frames[numFrames].getBalls();
            return tenthFrameBalls[0] + tenthFrameBalls[1];
        } else {
            // If we're in the last frame, get the total of the 2 following shots only
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
            result += f.toString();
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
