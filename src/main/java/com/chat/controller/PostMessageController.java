package com.chat.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.chat.model.Button;
import com.chat.model.Gui;
import com.chat.service.UserService;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateViewRoute;

public class PostMessageController implements TemplateViewRoute {

	static final String TITLE = "List App";
	final String CHAT_VIEW_NAME = "chat.ftl";
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
	private Gui gui;
	@SuppressWarnings("unused")
	private UserService playerService;

	public PostMessageController() {
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
		Button button = gui.getHomeSigninButton();
		vm.put(BUTTON_CLASS, button.getButtonClass());
		vm.put(BUTTON_TYPE, button.getButtonType());
		vm.put(BUTTON_TEXT, button.getButtonText());
		vm.put(TITLE_ATTRIBUTE, TITLE);
		return new ModelAndView(vm, CHAT_VIEW_NAME);
	}
}