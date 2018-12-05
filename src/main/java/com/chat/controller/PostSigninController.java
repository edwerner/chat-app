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

	/**
	 * Static members
	 */
	final String CHAT_VIEW_NAME = "chat.ftl";
	final String LOGIN_VIEW_NAME = "home.ftl";
	static final String USER_NAME = "inputUsername";
	static final String ADMIN = "admin";
	static final String PASSWORD = "inputPassword";
	static String INVALID_LOGIN_MESSAGE = "Incorrect Username/Password";
	static final String MESSAGES = "messages";
	
	private MessageDaoImpl messageDaoImpl;
	public ArrayList<Message> messages = new ArrayList<Message>();
	private UserService userService;
	private Gui gui;
	private String viewName;

	/**
	 * Constructor instantiates
	 * message dao implementation
	 * to persist messages and
	 * user service to persist
	 * user data
	 */
	public PostSigninController() {
		try {
			messageDaoImpl = new MessageDaoImpl();
			userService = new UserService();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gui = new Gui();
	}
	
	/**
	 * Model and view handler
	 * 
	 * @param request
	 * @param response
	 * @return new model and view
	 */
	@Override
	public ModelAndView handle(Request request, Response response) {

		Map<String, Object> vm = new HashMap<>();

		final String username = request.queryParams(USER_NAME);
		final String password = request.queryParams(PASSWORD);

		Account user = new User();
		user.setUsername(username);
		user.setPassword(password);
		boolean admin = false;
		boolean loggedIn = false;
		Account existingUser = null;
		
		if (username != null) {
			loggedIn = userService.authenticate(user);
			existingUser = userService.findUser(user);
		} else {
			INVALID_LOGIN_MESSAGE = "You muse be signed in to continue";
		}
		
		if (loggedIn) {
			if (existingUser.getAccountType().equals("admin")) {
				admin = true;
			}
			messages = messageDaoImpl.getMessages();
			Session session = request.session();
			user.setAccountType(existingUser.getAccountType());
			session.attribute("user", user);
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