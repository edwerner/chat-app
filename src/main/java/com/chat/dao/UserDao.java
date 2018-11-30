package com.chat.dao;

import com.chat.model.Account;

public interface UserDao {
	Account findPlayerByUsername(String username);
	boolean passwordsMatch(Account user);
	void saveUser(Account user);
}