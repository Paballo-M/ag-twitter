package za.co.twitter.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import za.co.twitter.constants.AppConstants;
import za.co.twitter.models.Tweet;
import za.co.twitter.models.User;
import za.co.twitter.services.TweetService;
import za.co.twitter.services.UserService;

public class App {

	public static void main(String[] args) {

		if (args == null) {
			System.out.println("Please provide the name of the files to read data from...");
		} else if (args.length != 2) {
			System.out.println("Please provide the correct number of files to read data from...");
		} else if (args[0].lastIndexOf(".") == -1 || args[1].lastIndexOf(".") == -1) {
			System.out.println("Please provide the file extension of the files to read data from...");
		} else if (!args[0].substring(args[0].lastIndexOf(".")).equalsIgnoreCase(AppConstants.FILE_EXTENSION)
				|| !args[1].substring(args[1].lastIndexOf(".")).equalsIgnoreCase(AppConstants.FILE_EXTENSION)) {
			System.out.println("Please provide the correct file extesion of the files to read data from...");
		} else if (!args[0].equalsIgnoreCase(AppConstants.USER_FILENAME) && !args[1].equalsIgnoreCase(AppConstants.TWEET_FILENAME)) {
			System.out.println("Please provide the correct filenames of the files to read data from...");
		}  else {

			UserService userService = new UserService();
			userService.readUsersFromFile(args[0]);
			Map<String, User> appUsers = userService.sortAppUsers(userService.getAppUsers(), true);

			TweetService tweetService = new TweetService();
			tweetService.readAppTweetsFromFile(args[1]);
			Map<Integer, Tweet> appTweets = tweetService.getAppTweets();

			for (User appUser : appUsers.values()) {
				List<String> users = new ArrayList<String>();
				System.out.println(appUser.getName());
				users.add(appUser.getId());
				if (appUser.getFollowings() != null) {
					for (String userFollowings : appUser.getFollowings()) {
						users.add(userFollowings);
					}
				}

				for (Tweet tweet : appTweets.values()) {
					if (users.contains(tweet.getUserId())) {
						System.out.println("\t @" + tweet.getUserId() + ": " + tweet.getMessage());
					}
				}
			}
		}
	}
}
