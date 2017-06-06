package main.java;

/**
 * Created by collinm on 6/6/17.
 */
public class Frame {

    private int ballOne;
    private int ballTwo;
    private int totalBalls;
    private String flag;

    public Frame(String flag, int ballOne, int ballTwo) {
        this.flag = flag;
        this.ballOne = ballOne;
        this.ballTwo = ballTwo;
        this.totalBalls = ballOne + ballTwo;
    }

    // A single string input indicates an input for parse
    public Frame(String parseMe) {
        // Parse logic separated in case of future changes to this type of constructor
        this.parse(parseMe);
    }

    private void parse(String parseMe) {
        if (parseMe == "X") {
            this.flag = "strike";
            this.ballOne = 10;
            this.ballTwo = 0;
        } else {
            boolean first = true;
            for (char c: parseMe) {
                if (c == '-') {
                    // Well then... the way the balls are represented does not work for this situation =c
                }
            }
        }
    }

    public int getBallOne() {
        return ballOne;
    }

    public void setBallOne(int ballOne) {
        this.ballOne = ballOne;
    }

    public int getBallTwo() {
        return ballTwo;
    }

    public void setBallTwo(int ballTwo) {
        this.ballTwo = ballTwo;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
