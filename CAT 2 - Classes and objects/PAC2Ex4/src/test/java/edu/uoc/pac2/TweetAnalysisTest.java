package edu.uoc.pac2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TweetAnalysisTest {

    TweetAnalysis tweetAnalysis;

    @BeforeEach
    private void initializeTweetAnalysis() {
        tweetAnalysis = new TweetAnalysis();
    }

    @Test
    public void testCountHashtags() {
        assertEquals(0, tweetAnalysis.countHashtags(""));
        assertEquals(0, tweetAnalysis.countHashtags("This is a tweet with no hashtags"));
        assertEquals(1, tweetAnalysis.countHashtags("Now we have one #hashtag!"));
        assertEquals(2, tweetAnalysis.countHashtags("#hashtags are typically used more than #once"));
        assertEquals(5, tweetAnalysis.countHashtags("You may have a tweet full of hashtags #twitter " +
                "#hashtag #socialnetwork #followme #like"));
        assertEquals(10, tweetAnalysis.countHashtags("Although we might write them like this ##########"));
        assertEquals(2, tweetAnalysis.countHashtags("And if we separate them like #this" +
                System.lineSeparator() + "It should not be a #problem"));
        assertEquals(4, tweetAnalysis.countHashtags("#Or#Even#Like" + System.lineSeparator() + "#This"));
    }

    @Test
    public void testHighlightUsernames() {

        assertEquals("It seems like @DonaldTrump and @JoeBiden will both run for president in 2024. God help us all.",
                tweetAnalysis.highlightUsernames("It seems like DonaldTrump and JoeBiden will both run for " +
                        "president in 2024. God help us all.", new String[]{"DonaldTrump", "JoeBiden"}));
        assertEquals("Last USA presidents include: @JoeBiden, @DonaldTrump, @BarackObama and @GeorgeBush",
                tweetAnalysis.highlightUsernames("Last USA presidents include: JoeBiden, DonaldTrump, " +
                        "BarackObama and GeorgeBush", new String[]{"DonaldTrump", "JoeBiden", "GeorgeBush", "BarackObama"}));
        assertEquals("@JoeBiden-@DonaldTrump/@BarackObama.@GeorgeBush,@JoeBiden@JoeBiden,@BarackObama",
                tweetAnalysis.highlightUsernames("JoeBiden-DonaldTrump/BarackObama.GeorgeBush,JoeBidenJoeBiden,BarackObama",
                        new String[]{"DonaldTrump", "JoeBiden", "GeorgeBush", "BarackObama"}));
    }

    @Test
    public void testTweetHistory() {
        assertEquals("User @DonaldTrump tweeted: 'I will be president'",
                tweetAnalysis.getTweetHistory(new String[]{"DonaldTrump:I will be president"}));
        assertEquals("User @DonaldTrump tweeted: 'I will be president'" + System.lineSeparator() +
                        "User @JoeBiden tweeted: 'Yeah you wish!'",
                tweetAnalysis.getTweetHistory(new String[]{"DonaldTrump:I will be president",
                "JoeBiden:Yeah you wish!"}));
        assertEquals("User @DonaldTrump tweeted: 'I will be president'" + System.lineSeparator() +
                        "User @JoeBiden tweeted: 'Yeah you wish!'" + System.lineSeparator() +
                        "User @GeorgeBush tweeted: 'If I could, you can'" + System.lineSeparator() +
                        "User @BarackObama tweeted: 'Don't make me talk'",
                tweetAnalysis.getTweetHistory(new String[]{"DonaldTrump:I will be president",
                        "JoeBiden:Yeah you wish!",
                        "GeorgeBush:If I could, you can",
                        "BarackObama:Don't make me talk"}));
        assertEquals("User @DonaldTrump tweeted: 'I will be president: believe me'",
                tweetAnalysis.getTweetHistory(new String[]{"DonaldTrump:I will be president: believe me"}));

        assertEquals("User @DonaldTrump tweeted: 'I will be president: believe me. And soon enough: be sure.'",
                tweetAnalysis.getTweetHistory(new String[]{"DonaldTrump:I will be president: believe me. And soon enough: be sure."}));
        assertEquals("User @DonaldTrump tweeted: 'I will be: president'" + System.lineSeparator() +
                        "User @JoeBiden tweeted: 'Yeah, you wish!'" + System.lineSeparator() +
                        "User @GeorgeBush tweeted: 'If I could: you can'" + System.lineSeparator() +
                        "User @BarackObama tweeted: 'Don't make me talk... Please: leave'",
                tweetAnalysis.getTweetHistory(new String[]{"DonaldTrump:I will be: president",
                        "JoeBiden:Yeah, you wish!",
                        "GeorgeBush:If I could: you can",
                        "BarackObama:Don't make me talk... Please: leave"}));
    }

    @Test
    public void testBotTweet() {
        assertEquals("",tweetAnalysis.botTweet(new String[]{}, 1));
        assertEquals("",tweetAnalysis.botTweet(new String[]{"Hi"}, 1));
        assertEquals("Hi",tweetAnalysis.botTweet(new String[]{"Hi"}, 3));
        assertEquals("Hi Hi Hi Hi Hi Hi Hi",tweetAnalysis.botTweet(new String[]{"Hi"}, 20));
        assertEquals("Hi there",tweetAnalysis.botTweet(new String[]{"Hi", "there"}, 8));
        assertEquals("Hi",tweetAnalysis.botTweet(new String[]{"Hi", "there"}, 7));
        assertEquals("Hi",tweetAnalysis.botTweet(new String[]{"Hi", "there", "this", "is", "a","bot"}, 7));
        assertEquals("Hi there",tweetAnalysis.botTweet(new String[]{"Hi", "there", "this", "is", "a","bot"}, 8));
        assertEquals("Hi there",tweetAnalysis.botTweet(new String[]{"Hi", "there", "this", "is", "a","bot"}, 11));
        assertEquals("Hi there this is a bot",tweetAnalysis.botTweet(new String[]{"Hi", "there", "this", "is", "a","bot"}, 22));
        assertEquals("Hi there this is a bot Hi there this is a",tweetAnalysis.botTweet(new String[]{"Hi", "there", "this", "is", "a","bot"}, 44));
        assertEquals("Hi there this is a bot Hi there this is a bot",tweetAnalysis.botTweet(new String[]{"Hi", "there", "this", "is", "a","bot"}, 45));

    }

}
