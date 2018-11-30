package com.chat.controller;

import com.chat.model.*;
import com.chat.service.PlayerService;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateViewRoute;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostSigninController implements TemplateViewRoute {

	final String CHAT_VIEW_NAME = "chat.ftl";
	final String LOGIN_VIEW_NAME = "home.ftl";
	static final String USER_NAME = "inputUsername";
	static final String PASSWORD = "inputPassword";
	static final String INVALID_LOGIN_MESSAGE = "Incorrect Username/Password";

	private PlayerService playerService;
	private GuiController guiController;
	private String viewName;

	public PostSigninController() {
		try {
			playerService = new PlayerService();
		} catch (IOException e) {
			e.printStackTrace();
		}
		guiController = new GuiController();
	}

	@Override
	public ModelAndView handle(Request request, Response response) {

		Map<String, Object> vm = new HashMap<>();

		final String username = request.queryParams(USER_NAME);
		final String password = request.queryParams(PASSWORD);

		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);

		final boolean loggedIn = playerService.authenticate(account);

		if (loggedIn) {
			Session session = request.session();
			session.attribute("account", account);
			Button button = guiController.getSelectButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
			viewName = CHAT_VIEW_NAME;
		} else {
			Button button = new GuiController().getHomeSigninButton();
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