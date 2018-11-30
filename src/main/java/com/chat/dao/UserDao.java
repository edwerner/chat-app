package com.chat.dao;

import com.chat.model.Account;

public interface UserDao {
	Account findPlayerByUsername(String username);
	void savePlayer(Account player);
	boolean passwordsMatch(Account player);
}