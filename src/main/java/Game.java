import javafx.util.Pair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Bowling game
 */
public class Game {
    private final Scanner scanner;
    private final List<Frame> frames = new ArrayList<>();

    /**
     * @param srcIn - Source of input for the game.
     */
    public Game(InputStream srcIn) {
        this.scanner = new Scanner(srcIn);
    }

    public List<Frame> getFrames() {
        return this.frames;
    }

    /**
     * Start the game.
     */
    public void run() {
        for (int i = 0; i <= 9; i++) {
            final Frame frame = new Frame();
            frames.add(frame);
            if (i == 9) {
                frame.setLast(true);
            }
            Integer firstRoll = getInput(i + 1, 1);
            updateFrame(frame, firstRoll, 1);
            if(frame.isLast() || !frame.isStrike()) {
                Integer secondRoll = getInput(i + 1, 2);
                //validate roll
                updateFrame(frame, secondRoll, 2);
            }
            if(frame.isLast()) { //last frame
                if (frame.getFirstRoll() == Constants.NUM_PINS) { //get 2 more rolls
                    Integer thirdRoll = getInput(i + 1, 3);
                    if (frame.getSecondRoll() != Constants.NUM_PINS) {
                        //validate roll
                        if (thirdRoll > (Constants.NUM_PINS - frame.getSecondRoll())) {
                            System.out.println("Invalid second roll. Marking third roll as 0.");
                            thirdRoll = 0;
                        }
                    }
                    frame.setThirdRoll(thirdRoll);
                } else if (frame.getFirstRoll() + frame.getSecondRoll() == Constants.NUM_PINS) {
                    //add third roll
                    Integer thirdRoll = getInput(i + 1, 3);
                    frame.setThirdRoll(thirdRoll);
                }
            }
            calculateScore();
            Utils.updateFrameStr(frame);
            Utils.printFormattedScore(frames);
        }
    }

    /**
     * Helper to update the frame properties.
     *
     * @param frame      - Current frame
     * @param roll       - Number of pins rolled
     * @param rollNumber - Current roll number within the frame
     */
    public void updateFrame(Frame frame, int roll, int rollNumber) {
        if (rollNumber == 1) {
            frame.setFirstRoll(roll);
            if (!frame.isLast() && frame.getFirstRoll() == Constants.NUM_PINS) {
                frame.setStrike(true);
            }
        } else if (rollNumber == 2) {
            if (!frame.isLast() && roll > Constants.NUM_PINS - frame.getFirstRoll()) {
                System.out.println("Invalid second roll. Assuming the roll as 0");
                frame.setSecondRoll(0);
            } else {
                frame.setSecondRoll(roll);
            }
            if (!frame.isLast() && frame.getFirstRoll() + frame.getSecondRoll() == Constants.NUM_PINS) {
                frame.setSpare(true);
            }
        } else {
            frame.setThirdRoll(roll);
        }
    }

    /**
     * Calculates the current score of the game
     */
    public void calculateScore() {
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            if (frame.getScore() != null) continue;
            int prevScore = 0;
            if (i > 0) {
                if (frames.get(i - 1).getScore() != null) {
                    prevScore = frames.get(i - 1).getScore();
                } else {
                    break;
                }
            }
            if (i == 9) {
                int score = prevScore + frame.getFirstRoll() + frame.getSecondRoll() + (frame.getThirdRoll() != null ? frame.getThirdRoll() : 0);
                frame.setScore(score);
            } else {
                if (frame.isStrike()) {
                    Pair<Integer, Integer> nextTwoRolls = getNextTwoRolls(i);
                    if (nextTwoRolls == null) {
                        break;
                    }
                    int score = frame.getFirstRoll() + nextTwoRolls.getKey() + nextTwoRolls.getValue();
                    frame.setScore(prevScore + score);
                } else if (frame.isSpare()) {
                    Integer nextRoll = getNextRoll(i);
                    if (nextRoll != null) {
                        frame.setScore(prevScore + frame.getFirstRoll() + frame.getSecondRoll() + nextRoll);
                    } else {
                        break;
                    }
                } else {
                    frame.setScore(prevScore + frame.getFirstRoll() + frame.getSecondRoll());
                }
            }
        }

    }

    /**
     * Helper to get input from the console.
     *
     * @param frame - The current frame.
     * @param turn  - The roll number within the frame
     */
    public Integer getInput(int frame, int turn) {
        String inputStr = String.format("Enter roll for frame: %s, turn: %s : ", frame, turn);
        System.out.println(inputStr);
        try {
            String rollStr = scanner.next();
            int roll = Integer.parseInt(rollStr);
            if (roll >= 0 && roll <= Constants.NUM_PINS) {
                return roll;
            }
            System.out.println("Invalid input. Assuming a roll of 0...");
            return 0;
        } catch (Exception ex) {
            System.out.println("Invalid input. Assuming a roll of 0...");
            return 0;
        }
    }

    /**
     * Get the following roll. This is used in case of a spare to calculate the score.
     *
     * @param frameIdx Index of frame that was a spare
     */
    public Integer getNextRoll(int frameIdx) {
        if (frames.size() > frameIdx + 1) {
            return frames.get(frameIdx + 1).getFirstRoll();
        }
        return null;
    }

    /**
     * Get the following two rolls. This is used in case of a strike to calculate the score.
     *
     * @param frameIdx Index of frame that was a strike
     */
    public Pair<Integer, Integer> getNextTwoRolls(int frameIdx) {
        Integer firstRoll = null;
        Integer secondRoll = null;
        if (frames.size() > frameIdx + 1) {
            if (frameIdx == 8) {
                firstRoll = frames.get(frameIdx + 1).getFirstRoll();
                secondRoll = frames.get(frameIdx + 1).getSecondRoll();
            } else {
                firstRoll = frames.get(frameIdx + 1).getFirstRoll();
                if (!frames.get(frameIdx + 1).isStrike()) {
                    secondRoll = frames.get(frameIdx + 1).getSecondRoll();
                } else if (frames.size() > frameIdx + 2) {
                    secondRoll = frames.get(frameIdx + 2).getFirstRoll();
                }
            }
        }
        if (firstRoll != null && secondRoll != null) {
            return new Pair<>(firstRoll, secondRoll);
        }
        return null;
    }

}
