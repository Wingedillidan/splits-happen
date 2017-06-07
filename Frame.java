package main.java;

/**
 * Created by collinm on 6/6/17.
 */
public class Frame {

    // Each array position represents one ball down the lane
    private int[] balls;
    private int totalBalls;

    public Frame(int[] balls) {
        this.balls = balls;
        this.calcTotal();
    }

    // Just a string indicates an input for parse
    public Frame(String parseMe) {
        // Parse logic separated in case of future changes to this type of constructor
        this.parse(parseMe);
        this.calcTotal();
    }

    private void parse(String parseMe) {
        int[] result = new int[parseMe.length()];

        for (int i = 0; i < parseMe.length(); i++) {
            if (parseMe.charAt(i) == '-') {
                // Check miss
                result[i] = 0;
            } else if (parseMe.charAt(i) == 'X') {
                // Check strike
                result[i] = 10;
            } else if (parseMe.charAt(i) == '/') {
                // Check spare
                result[i] = 10 - result[i-1]; // Out of scope #1 assumption
            } else {
                String ballString = Character.toString(parseMe.charAt(i)); // Out of scope #1 assumption
                result[i] = Integer.parseInt(ballString);
            }
        }

        this.balls = result;
    }

    private void calcTotal() {
        int total = 0;

        for (int i: this.balls) {
            total += i;
        }

        this.totalBalls = total;
    }

    public int[] getBalls() {
        return balls;
    }

    public void setBalls(int[] balls) {
        this.balls = balls;
    }

    public int getTotalBalls() {
        return totalBalls;
    }

    public void setTotalBalls(int totalBalls) {
        this.totalBalls = totalBalls;
    }

}
