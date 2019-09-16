package za.co.twitter.models;

import java.util.HashSet;

public class User {
	
	private String id;
	private String name;
	private HashSet<String> followers;
	private HashSet<String> followings;

	public User () {
	}
	
	public User (String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashSet<String> getFollowers() {
		return followers;
	}

	public void setFollowers(HashSet<String> followers) {
		this.followers = followers;
	}

	public HashSet<String> getFollowings() {
		return followings;
	}

	public void setFollowings(HashSet<String> followings) {
		this.followings = followings;
	}

}
