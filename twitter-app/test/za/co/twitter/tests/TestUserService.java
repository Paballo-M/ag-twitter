package za.co.twitter.tests;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;
import za.co.twitter.models.User;
import za.co.twitter.services.UserService;

public class TestUserService  extends TestCase {
	
	String userFilename = null;
	Map<String, User> appUsers = null;
	UserService userService = null;
	
	protected void setUp() {
		userFilename = "user.txt";
		appUsers = new HashMap<String, User>();
		userService = new UserService();
	}
	
	public void testReadUsersFromFile() {
		
		userService.readUsersFromFile(userFilename);
		appUsers = userService.getAppUsers();
		assertNotNull(appUsers);
		assertEquals(3, appUsers.size());
	}
	
}
