package com.chat.service;

import java.io.IOException;

import com.chat.dao.UserDaoImpl;
import com.chat.model.Account;

public class UserService {

	private UserDaoImpl userDaoImpl;

	/**
	 * Constructor instantiates
	 * new user dao implementation
	 * 
	 * @throws IOException
	 */
	public UserService() throws IOException {
		userDaoImpl = new UserDaoImpl();
	}

	public void savePlayer(Account user) {
		userDaoImpl.saveUser(user);
	}

	public Account findUser(Account user) {
		Account existingPlayer = userDaoImpl.findUserByUsername(user.getUsername());
		return existingPlayer;
	}

	public boolean authenticate(Account user) {
		if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			return false;
		}

		Account pl = userDaoImpl.findUserByUsername(user.getUsername());
		if (pl == null) {
			return false;
		}
		boolean passwordsMatch = userDaoImpl.passwordsMatch(user);
		return passwordsMatch;
	}
}