package za.co.twitter.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import za.co.twitter.constants.AppConstants;
import za.co.twitter.models.User;

public class UserService {

	private Map<String, User> appUsers = new HashMap<String, User>();
	
	public Map<String, User> getAppUsers() {
		return appUsers;
	}

	public void readUsersFromFile(String filename) {
		
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {
			stream.forEach(item->{

				// GET Follower
				String followerId = item.substring(0, item.indexOf(" "));
				User follower = createUser(followerId);
				if (!appUsers.containsKey(followerId)) {
					appUsers.put(follower.getId(), follower);
				}
				
				// GET Users
				String usersLine = (item.substring(item.indexOf(AppConstants.FOLLOWS) + AppConstants.FOLLOWS.length())).trim();
				String[] users = usersLine.split(",");
				
				for (String user : users) {
					user = user.trim();
					if(!appUsers.containsKey(user)){
						String newUserId = user;
						User newUser = createUser(newUserId);
						addFollower(newUser, follower);
						addFollowing(newUser, follower);
						appUsers.replace(follower.getId(), follower);
						appUsers.put(newUser.getId(), newUser);
						
					} else {
						User existingUser = appUsers.get(user);
						addFollower(existingUser, follower);
						addFollowing(existingUser, follower);
						appUsers.replace(follower.getId(), follower);
						appUsers.replace(existingUser.getId(), existingUser);
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
	
	private void addFollower(User user, User follower) {
		
		if(user.getFollowers() != null){
			user.getFollowers().add(follower.getId());
		} else {
			HashSet<String> userFollowers = new HashSet<String>();
			userFollowers.add(follower.getId());
			user.setFollowers(userFollowers);
		}
		
	}
	
	private void addFollowing(User user, User follower) {
		
		if(follower.getFollowings() != null){
			follower.getFollowings().add(user.getId());
		} else {
			HashSet<String> userFollowings = new HashSet<String>();
			userFollowings.add(user.getId());
			follower.setFollowings(userFollowings);
		}
		
	}
	
	private User createUser(String id) {
		return new User(id, id);
	}
	

    public Map<String, User> sortAppUsers(Map<String, User> unsortedMap, final boolean order)
    {
        List<Entry<String, User>> list = new LinkedList<>(unsortedMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? ((User)(o1.getValue())).getId().compareTo(((User)(o2.getValue())).getId()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : ((User)(o1.getValue())).getId().compareTo(((User)(o2.getValue())).getId()) : ((User)(o2.getValue())).getId().compareTo(((User)(o1.getValue())).getId()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : ((User)(o2.getValue())).getId().compareTo(((User)(o1.getValue())).getId()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }
}
