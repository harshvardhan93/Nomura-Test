import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.StringJoiner;

public class GameTest {

    @Test
    public void testRegularGame() {
        Game game = getGameWithInput("/testRegularGame.txt");
        game.run();
        List<Frame> frames = game.getFrames();
        Assert.assertEquals(10, frames.size());
        Assert.assertEquals(141, frames.get(9).getScore().intValue());
        Assert.assertEquals("9|/,8|/,9|0,7|/,X,X,6|3,7|2,5|1,5|1|-", getFrameScoreString(frames));
    }

    @Test
    public void testRegularGame2() {
        Game game = getGameWithInput("/testRegularGame2.txt");
        game.run();
        List<Frame> frames = game.getFrames();
        Assert.assertEquals(10, frames.size());
        Assert.assertEquals(155, frames.get(9).getScore().intValue());
        Assert.assertEquals("9|/,8|/,9|0,7|/,X,X,6|3,7|2,5|1,X|8|/", getFrameScoreString(frames));
    }

    @Test
    public void testWithAllStrikes() {
        Game game = getGameWithInput("/testAllStrikes.txt");
        game.run();
        List<Frame> frames = game.getFrames();
        Assert.assertEquals(10, frames.size());
        Assert.assertEquals(300, frames.get(9).getScore().intValue());
        Assert.assertEquals("X,X,X,X,X,X,X,X,X,X|X|X", getFrameScoreString(frames));
    }

    @Test
    public void testWithAllSpares() {
        Game game = getGameWithInput("/testAllSpares.txt");
        game.run();
        List<Frame> frames = game.getFrames();
        Assert.assertEquals(10, frames.size());
        Assert.assertEquals(185, frames.get(9).getScore().intValue());
        Assert.assertEquals("7|/,8|/,9|/,9|/,9|/,9|/,8|/,9|/,6|/,9|/|9", getFrameScoreString(frames));
    }

    @Test
    public void testWithInvalidInputs() {
        Game game = getGameWithInput("/testWithInvalidInputs.txt");
        game.run();
        List<Frame> frames = game.getFrames();
        Assert.assertEquals(10, frames.size());
        Assert.assertEquals(56, frames.get(9).getScore().intValue());
        Assert.assertEquals("7|0,0|0,8|/,9|0,1|1,6|0,0|2,0|0,0|0,X|1|0", getFrameScoreString(frames));
    }

    public Game getGameWithInput(String inputFile) {
        InputStream in = GameTest.class.getResourceAsStream(inputFile);
        return new Game(in);
    }

    public String getFrameScoreString(List<Frame> frames) {
        StringJoiner joiner = new StringJoiner(",");
        frames.forEach(f -> joiner.add(f.getFrameString()));
        return joiner.toString();
    }
}
