package main.java;

/**
 * Created by collinm on 6/6/17.
 */
public class Frame {

    // Each array position represents one ball down the lane
    private int[] balls;
    private int rawTotal; // The sum of the instantiated frame only, strikes and spares return 10
    private int numPins;

    public Frame(int[] balls, int numPins) {
        this.balls = balls;
        this.numPins = numPins;
        this.calcTotal();
    }

    // String input indicates parse requirement
    public Frame(String parseMe, int numPins) {
        // Parse logic separated in case of future changes to this type of constructor
        this.numPins = numPins;
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
                result[i] = this.numPins;
            } else if (parseMe.charAt(i) == '/') {
                // Check spare
                result[i] = this.numPins - result[i-1]; // Out of scope #1 assumption
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

        this.rawTotal = total;
    }

    public String toString() {
        String result = "[ ";

        for (int i: this.getBalls()) {
            result += i + " ";
        }

        return result + "]";
    }

    public int[] getBalls() {
        return balls;
    }

    public void setBalls(int[] balls) {
        this.balls = balls;
    }

    public int getRawTotal() {
        return rawTotal;
    }

    public void setRawTotal(int rawTotal) {
        this.rawTotal = rawTotal;
    }

    public int getNumPins() {
        return numPins;
    }

    public void setNumPins(int numPins) {
        this.numPins = numPins;
    }
}
