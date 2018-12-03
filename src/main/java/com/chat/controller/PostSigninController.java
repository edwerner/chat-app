package com.chat.controller;

import com.chat.dao.MessageDaoImpl;
import com.chat.model.*;
import com.chat.service.UserService;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateViewRoute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostSigninController implements TemplateViewRoute {

	final String CHAT_VIEW_NAME = "chat.ftl";
	final String LOGIN_VIEW_NAME = "home.ftl";
	static final String USER_NAME = "inputUsername";
	static final String ADMIN = "admin";
	static final String PASSWORD = "inputPassword";
	static final String INVALID_LOGIN_MESSAGE = "Incorrect Username/Password";
	private MessageDaoImpl messageDaoImpl;
	public ArrayList<Message> messages = new ArrayList<Message>();
	static final String MESSAGES = "messages";
	private UserService playerService;
	private Gui gui;
	private String viewName;

	public PostSigninController() {
		try {
			messageDaoImpl = new MessageDaoImpl();
			playerService = new UserService();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gui = new Gui();
	}

	@Override
	public ModelAndView handle(Request request, Response response) {

		Map<String, Object> vm = new HashMap<>();

		final String username = request.queryParams(USER_NAME);
		final String password = request.queryParams(PASSWORD);

		Account user = new User();
		user.setUsername(username);
		user.setPassword(password);

		final boolean loggedIn = playerService.authenticate(user);
		boolean admin = false;
		
		final Account existingUser = playerService.findPlayer(user);
		
		if (loggedIn) {
			if (existingUser.getAccountType() == "admin") {
				admin = true;
			}

			System.out.println("ACCOUNT TYPE: " + existingUser.getAccountType());
			messages = messageDaoImpl.getMessages();
			Session session = request.session();
			user.setAccountType(existingUser.getAccountType());
			session.attribute("user", user);
			session.maxInactiveInterval(10);
			Button button = gui.getSendMessageButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
			vm.put(MESSAGES, messages);
			vm.put(ADMIN, admin);
			viewName = CHAT_VIEW_NAME;
		} else {
			Button button = new Gui().getHomeSigninButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
			vm.put(HomeController.LOGIN_STATUS, true);
			vm.put(HomeController.LOGIN_MESSAGE, INVALID_LOGIN_MESSAGE);
			vm.put(HomeController.NEW_USER, false);
			vm.put(HomeController.LOGIN_PAGE, true);
			vm.put(HomeController.SIGNUP_STATUS, false);
			vm.put(HomeController.SIGNUP_MESSAGE, null);
			viewName = LOGIN_VIEW_NAME;
		}
		return new ModelAndView(vm, viewName);
	}
}