package com.chat.controller;

import java.util.HashMap;
import java.util.Map;

import com.chat.model.Button;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class HomeController implements TemplateViewRoute {

	public static final String HOME_VIEW_NAME = "home.ftl";
	static final String TITLE = "Chat App";
	static final String TITLE_ATTRIBUTE = "title";
	static final String BUTTON_CLASS = "buttonClass";
	static final String BUTTON_TYPE = "buttonType";
	static final String BUTTON_TEXT = "buttonText";
	static final String LOGIN_STATUS = "loginFail";
	static final String SIGNUP_STATUS = "signupFail";
	public static final String LOGIN_MESSAGE = "message";
	static final String LOGIN_PAGE = "signinPage";
	static final String NEW_USER = "newUserSignup";
	static final String SIGNUP_MESSAGE = "SignUpMessage";
	private GuiController guiController;

	public HomeController() {
		guiController = new GuiController();
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
		Button button = guiController.getHomeSigninButton();
		vm.put(BUTTON_CLASS, button.getButtonClass());
		vm.put(BUTTON_TYPE, button.getButtonType());
		vm.put(BUTTON_TEXT, button.getButtonText());
		vm.put(TITLE_ATTRIBUTE, TITLE);
		vm.put(LOGIN_STATUS, false);
		vm.put(SIGNUP_STATUS, false);
		vm.put(LOGIN_MESSAGE, "Welcome");
		vm.put(LOGIN_PAGE, true);
		vm.put(NEW_USER, false);
		vm.put(SIGNUP_MESSAGE, false);
		return new ModelAndView(vm, HOME_VIEW_NAME);
	}
}