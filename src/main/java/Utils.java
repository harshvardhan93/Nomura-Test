import java.util.ArrayList;
import java.util.List;

public class Utils {

    /**
     * Updates the frame and sets the string that will display the rolls
     *
     * @param frame - Frame whose string needs to be set
     */
    public static void updateFrameStr(Frame frame) {
        if (!frame.isLast()) {
            if (frame.isStrike()) {
                frame.setFrameString(Constants.STRIKE);
            } else if (frame.isSpare()) {
                frame.setFrameString(frame.getFirstRoll() + "|/");
            } else {
                frame.setFrameString(frame.getFirstRoll() + Constants.ROLL_SEPARATOR + frame.getSecondRoll());
            }
        } else {
            StringBuilder builder = new StringBuilder();
            if (frame.getFirstRoll() == Constants.NUM_PINS) {
                builder.append(Constants.STRIKE);
                builder.append(Constants.ROLL_SEPARATOR);
                //check if second roll is strike
                if (frame.getSecondRoll() == Constants.NUM_PINS) {
                    builder.append(Constants.STRIKE);
                    builder.append(Constants.ROLL_SEPARATOR);
                    if (frame.getThirdRoll() == Constants.NUM_PINS) {
                        builder.append(Constants.STRIKE);
                    } else {
                        builder.append(frame.getThirdRoll());
                    }
                } else {
                    builder.append(frame.getSecondRoll());
                    builder.append(Constants.ROLL_SEPARATOR);
                    if (frame.getSecondRoll() + frame.getThirdRoll() == Constants.NUM_PINS) {
                        builder.append(Constants.SPARE);
                    } else {
                        builder.append(frame.getThirdRoll());
                    }
                }
            } else {
                builder.append(frame.getFirstRoll());
                builder.append(Constants.ROLL_SEPARATOR);
                if (frame.getFirstRoll() + frame.getSecondRoll() == Constants.NUM_PINS) {
                    builder.append(Constants.SPARE);
                    builder.append(Constants.ROLL_SEPARATOR);
                    if (frame.getThirdRoll() == Constants.NUM_PINS) {
                        builder.append(Constants.STRIKE);
                    } else {
                        builder.append(frame.getThirdRoll());
                    }
                } else {
                    builder.append(frame.getSecondRoll());
                    builder.append(Constants.ROLL_SEPARATOR);
                    builder.append("-");
                }
            }
            frame.setFrameString(builder.toString());
        }
    }

    /**
     * Prints a formatted score
     * X -> indicates a strike
     * / -> indicates a spare
     * - -> indicates no roll (used for the third roll in last frame)
     */
    public static void printFormattedScore(List<Frame> frames) {
        List<String> frameScores = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();
        for (Frame frame : frames) {
            if (frame.getScore() != null) {
                scores.add(frame.getScore());
            }
            if (frame.getFrameString() != null) {
                frameScores.add(frame.getFrameString());
            }
        }
        System.out.println(frameScores.toString());
        System.out.println(scores.toString());
    }
}
