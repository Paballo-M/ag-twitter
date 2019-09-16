package za.co.twitter.tests;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import za.co.twitter.models.Tweet;

import za.co.twitter.services.TweetService;

public class TestTweetService extends TestCase {

	String tweetFilename = null;
	Map<Integer, Tweet> appTweets = null;
	TweetService tweetService = null;
	
	protected void setUp() {
		tweetFilename = "tweet.txt";
		appTweets = new HashMap<Integer, Tweet>();
		tweetService = new TweetService();
	}
	
	public void testReadUsersFromFile() {
		
		tweetService.readAppTweetsFromFile(tweetFilename);
		appTweets = tweetService.getAppTweets();
		assertNotNull(appTweets);
		assertEquals(3, appTweets.size());
	}
}
