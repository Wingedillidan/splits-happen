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

    // A single string input indicates an input for parse
    public Frame(String parseMe) {
        // Parse logic separated in case of future changes to this type of constructor
        this.parse(parseMe);
        this.calcTotal();
    }

    private void parse(String parseMe) {
        if (parseMe.equals("X")) {
            this.balls = new int[]{10};
        } else {
            int[] result = new int[parseMe.length()];

            for (int i = 0; i < parseMe.length(); i++) {
                if (parseMe.charAt(i) == '-') {
                    result[i] = 0;
                } else if (parseMe.charAt(i) == '/') {
                    // Luckily I don't have to worry about validation ;)
                    result[i] = 10 - result[i-1];
                } else {
                    // Same thing!
                    String ballString = Character.toString(parseMe.charAt(i));
                    result[i] = Integer.parseInt(ballString);
                }
            }

            this.balls = result;
        }
    }

    public void calcTotal() {
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
