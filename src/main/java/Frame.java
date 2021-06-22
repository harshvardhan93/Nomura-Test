/**
 * A bowling frame
 */
public class Frame {
    /**
     * Number of pins rolled in the first roll
     */
    private Integer firstRoll = null;

    /**
     * Number of pins rolled in the second roll
     */
    private Integer secondRoll = null;

    /**
     * Number of pins rolled in the third roll. This is only for the last frame
     */
    private Integer thirdRoll = null;

    /**
     * Total score after finishing the rolls in the frame.
     */
    private Integer score = null;

    /**
     * Indicates if this is the last frame
     */
    private boolean last = false;

    /**
     * Indicates if the frame was a strike. Only used for the first 9 frames.
     */
    private boolean strike = false;

    /**
     * Indicates if the frame was a spare. Only used for the first 9 frames.
     */
    private boolean spare = false;

    /**
     * String representation of the frame
     */
    private String frameString;

    public void setFirstRoll(int value) {
        this.firstRoll = value;
    }

    public void setSecondRoll(int value) {
        this.secondRoll = value;
    }

    public void setThirdRoll(int value) {
        this.thirdRoll = value;
    }

    public void setScore(int value) {
        this.score = value;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public void setSpare(boolean spare) {
        this.spare = spare;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isStrike() {
        return this.strike;
    }

    public boolean isSpare() {
        return this.spare;
    }

    public boolean isLast() {
        return this.last;
    }

    public Integer getFirstRoll() {
        return this.firstRoll;
    }

    public Integer getSecondRoll() {
        return this.secondRoll;
    }

    public Integer getThirdRoll() {
        return this.thirdRoll;
    }

    public Integer getScore() {
        return this.score;
    }

    public String getFrameString() {
        return frameString;
    }

    public void setFrameString(String frameString) {
        this.frameString = frameString;
    }
}