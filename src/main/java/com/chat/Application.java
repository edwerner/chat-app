package com.chat;

import java.io.IOException;
import java.util.logging.Logger;

import com.chat.model.Account;
import com.chat.model.User;
import com.chat.service.UserService;

import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

public final class Application {
	private static final Logger LOG = Logger.getLogger(Application.class.getName());
	private final WebServer webServer;

	/**
	 * Main method instantiates
	 * application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final TemplateEngine templateEngine = new FreeMarkerEngine();
		final WebServer webServer = new WebServer(templateEngine);
		final Application application = new Application(webServer);
		application.initialize();
	}

	/**
	 * Instantiates we server and
	 * creates admin account
	 * 
	 * @param webServer
	 */
	private Application(final WebServer webServer) {
		this.webServer = webServer;
		createAdminAccount();
	}
	
	/**
	 * Creates admin account
	 */
	private void createAdminAccount() {
		UserService userService = null;
		try {
			userService = new UserService();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Account user = new User();
		user.setUsername("admin");
		user.setPassword("admin");
		user.setAccountType("admin");
		userService.savePlayer(user);
	}
	
	/**
	 * Initializes application
	 */
	private void initialize() {
		LOG.fine("Authentication app is initializing.");
		webServer.initialize();
		LOG.fine("Authentication app initialization complete.");
	}

}