package za.co.twitter.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import za.co.twitter.models.Tweet;

public class TweetService {

	private Map<Integer, Tweet> appTweets = new HashMap<Integer, Tweet>();
	
	public Map<Integer, Tweet> getAppTweets() {
		return appTweets;
	}
	 
	public void readAppTweetsFromFile(String filename) {
		
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {
			int[] count = {0,1};
			stream.forEach(item->{
				
				//GET tweets
				String id = String.valueOf(count[1]++);
				String userId = item.substring(0, item.indexOf(">"));
				String message = (item.substring(item.indexOf(">") + 1, item.length())).trim();
				message = message.length() > 140 ? message.substring(0, 139) : message;
				Tweet tweet = new Tweet(id, userId, message);

				appTweets.put(Integer.parseInt(id), tweet);
				
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

}
