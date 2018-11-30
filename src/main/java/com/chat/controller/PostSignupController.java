package com.chat.controller;

import com.chat.model.User;
import com.chat.model.Button;
import com.chat.model.Gui;
import com.chat.model.Account;
import com.chat.service.UserService;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateViewRoute;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PostSignupController implements TemplateViewRoute {

	static final String LOGIN_VIEW_NAME = "home.ftl";
	static final String USER_NAME = "inputUsername";
	static final String PASSWORD = "inputPassword";
	static final String EMAIL = "inputEmail";
	static final String FIRST_NAME = "inputFirstName";
	static final String LAST_NAME = "inputLastName";
	static final String SIGNUP_SUCCESS_MESSAGE = "You have successfully signed up.";
	static final String SIGNUP_FAILURE_MESSAGE = "Username is taken. Try again.";
	static final String SIGNUP_FAIL_MESSAGE = "Error while signing up.";
	private Gui gui;
	private UserService playerService;

	public PostSignupController() {
		try {
			playerService = new UserService();
		} catch (IOException e) {
			e.printStackTrace();
		}
		gui = new Gui();
	}

	@Override
	public ModelAndView handle(Request request, Response response) {
		Session session = request.session();
		session.attribute("user", null);

		Map<String, Object> vm = new HashMap<>();
		try {
			playerService = new UserService();
		} catch (IOException e) {
			e.printStackTrace();
		}

		final String username = request.queryParams(USER_NAME);
		final String password = request.queryParams(PASSWORD);
		String signupMessage;
		boolean signupStatus;
		boolean newUserSignup;
		boolean signInPage;

		Account user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setAccountType("user");
		Account existingUser = playerService.findPlayer(user);

		if (existingUser == null) {
			playerService.savePlayer(user);
			signupStatus = false;
			newUserSignup = true;
			signInPage = true;
			signupMessage = SIGNUP_SUCCESS_MESSAGE;
		} else {
			signupStatus = true;
			newUserSignup = false;
			signInPage = false;
			signupMessage = SIGNUP_FAILURE_MESSAGE;
		}

		Button button = gui.getHomeSigninButton();
		vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
		vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
		vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
		vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
		vm.put(HomeController.LOGIN_STATUS, false);
		vm.put(HomeController.LOGIN_PAGE, signInPage);
		vm.put(HomeController.LOGIN_MESSAGE, signupMessage);
		vm.put(HomeController.NEW_USER, newUserSignup);
		vm.put(HomeController.SIGNUP_STATUS, signupStatus);
		vm.put(HomeController.SIGNUP_MESSAGE, signupMessage);
		return new ModelAndView(vm, LOGIN_VIEW_NAME);
	}
}