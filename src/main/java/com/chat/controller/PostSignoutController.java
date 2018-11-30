package com.chat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.chat.model.Button;
import com.chat.service.PlayerService;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateViewRoute;

public class PostSignoutController implements TemplateViewRoute {

	static final String TITLE = "List App";
	static final String TITLE_ATTRIBUTE = "title";
	static final String BUTTON_CLASS = "buttonClass";
	static final String BUTTON_TYPE = "buttonType";
	static final String BUTTON_TEXT = "buttonText";
	static final String LOGIN_STATUS = "loginFail";
	static final String SIGNUP_STATUS = "signupFail";
	static final String LOGIN_MESSAGE = "message";
	static final String LOGIN_PAGE = "signinPage";
	static final String NEW_USER = "newUserSignup";
	static final String SIGNUP_MESSAGE = "SignUpMessage";
	private GuiController guiController;
	@SuppressWarnings("unused")
	private PlayerService playerService;

	public PostSignoutController() {
		try {
			playerService = new PlayerService();
		} catch (IOException e) {
			e.printStackTrace();
		}
		guiController = new GuiController();
	}

	@Override
	public ModelAndView handle(Request request, Response response) {
		Session session = request.session();
		// Account account = session.attribute("user");
		session.attribute("user", null);
		Map<String, Object> vm = new HashMap<>();
		Button button = guiController.getHomeSigninButton();
		vm.put(BUTTON_CLASS, button.getButtonClass());
		vm.put(BUTTON_TYPE, button.getButtonType());
		vm.put(BUTTON_TEXT, button.getButtonText());
		vm.put(TITLE_ATTRIBUTE, TITLE);
		vm.put(LOGIN_STATUS, false);
		vm.put(SIGNUP_STATUS, false);
		vm.put(LOGIN_MESSAGE, HomeController.TITLE);
		vm.put(LOGIN_PAGE, true);
		vm.put(NEW_USER, false);
		vm.put(SIGNUP_MESSAGE, false);
		return new ModelAndView(vm, HomeController.HOME_VIEW_NAME);
	}
}