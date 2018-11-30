package com.chat.service;

import java.io.IOException;

import com.chat.dao.UserDaoImpl;
import com.chat.model.User;

public class PlayerService {

	private UserDaoImpl userDaoImpl;

	public PlayerService() throws IOException {
		userDaoImpl = new UserDaoImpl();
	}

	public void savePlayer(User player) {
		userDaoImpl.savePlayer(player);
	}

	public User findPlayer(User player) {
		User existingPlayer = userDaoImpl.findPlayerByUsername(player.getUsername());
		return existingPlayer;
	}

	public boolean authenticate(User player) {
		if (player.getUsername().isEmpty() || player.getPassword().isEmpty()) {
			return false;
		}

		User pl = userDaoImpl.findPlayerByUsername(player.getUsername());
		if (pl == null) {
			return false;
		}
		boolean passwordsMatch = userDaoImpl.passwordsMatch(player);
		return passwordsMatch;
	}
}