package com.chat.service;

import java.io.IOException;

import com.chat.dao.UserDaoImpl;
import com.chat.model.Account;

public class UserService {

	private UserDaoImpl userDaoImpl;

	public UserService() throws IOException {
		userDaoImpl = new UserDaoImpl();
	}

	public void savePlayer(Account player) {
		userDaoImpl.savePlayer(player);
	}

	public Account findPlayer(Account player) {
		Account existingPlayer = userDaoImpl.findPlayerByUsername(player.getUsername());
		return existingPlayer;
	}

	public boolean authenticate(Account player) {
		if (player.getUsername().isEmpty() || player.getPassword().isEmpty()) {
			return false;
		}

		Account pl = userDaoImpl.findPlayerByUsername(player.getUsername());
		if (pl == null) {
			return false;
		}
		boolean passwordsMatch = userDaoImpl.passwordsMatch(player);
		return passwordsMatch;
	}
}