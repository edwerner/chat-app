package com.chat.dao;

import com.chat.model.User;

public interface UserDao {
	User findPlayerByUsername(String username);
	void savePlayer(User player);
	boolean passwordsMatch(User player);
}