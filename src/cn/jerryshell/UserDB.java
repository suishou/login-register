package cn.jerryshell;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
	private static UserDB instence = new UserDB();
	private List<User> userList;

	private UserDB() {
		userList = new ArrayList<>();
		userList.add(new User("admin", "admin", "calljerryli@outlook.com"));
		userList.add(new User("JerryTest", "123", "1111###@163.com"));
		userList.add(new User("TomTest", "456", "2222###@qq.com"));
	}

	public static UserDB getInstence() {
		return instence;
	}

	public List<User> getAll() {
		return userList;
	}

	public boolean addUser(User newuser) {
		this.userList.add(newuser);
		return true;
	}

	public User getUser(String username) {
		for (User user : this.userList) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
}
