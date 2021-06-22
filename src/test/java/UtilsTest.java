import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {
    @Test
    public void testUpdateFrameStr1() {
        Frame frame1 = new Frame();
        frame1.setFirstRoll(5);
        frame1.setSecondRoll(3);

        Utils.updateFrameStr(frame1);
        assertEquals("5|3", frame1.getFrameString());

        Frame frame2 = new Frame();
        frame2.setFirstRoll(10);
        frame2.setStrike(true);
        Utils.updateFrameStr(frame2);
        assertEquals("X", frame2.getFrameString());

        Frame frame3 = new Frame();
        frame3.setFirstRoll(8);
        frame3.setSecondRoll(2);
        frame3.setSpare(true);
        Utils.updateFrameStr(frame3);
        assertEquals("8|/", frame3.getFrameString());

        Frame frame4 = new Frame();
        frame4.setLast(true);
        frame4.setFirstRoll(10);
        frame4.setSecondRoll(10);
        frame4.setThirdRoll(10);
        Utils.updateFrameStr(frame4);
        assertEquals("X|X|X", frame4.getFrameString());

        Frame frame5 = new Frame();
        frame5.setLast(true);
        frame5.setFirstRoll(6);
        frame5.setSecondRoll(4);
        frame5.setThirdRoll(10);
        Utils.updateFrameStr(frame5);
        assertEquals("6|/|X", frame5.getFrameString());

        Frame frame6 = new Frame();
        frame6.setLast(true);
        frame6.setFirstRoll(6);
        frame6.setSecondRoll(3);
        Utils.updateFrameStr(frame6);
        assertEquals("6|3|-", frame6.getFrameString());

        Frame frame7 = new Frame();
        frame7.setLast(true);
        frame7.setFirstRoll(10);
        frame7.setSecondRoll(3);
        frame7.setThirdRoll(7);
        Utils.updateFrameStr(frame7);
        assertEquals("X|3|/", frame7.getFrameString());

        Frame frame8 = new Frame();
        frame8.setLast(true);
        frame8.setFirstRoll(10);
        frame8.setSecondRoll(3);
        frame8.setThirdRoll(6);
        Utils.updateFrameStr(frame8);
        assertEquals("X|3|6", frame8.getFrameString());


    }
}
