package com.chat.controller;

import com.chat.model.Button;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import java.util.HashMap;
import java.util.Map;

public class SignupController implements TemplateViewRoute {

	private GuiController guiController;

	public SignupController() {
		guiController = new GuiController();
	}

	@Override
	public ModelAndView handle(Request request, Response response) {
		Map<String, Object> vm = new HashMap<>();
		Button button = guiController.getHomeSignupButton();
		vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
		vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
		vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
		vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
		vm.put(HomeController.LOGIN_STATUS, false);
		vm.put(HomeController.SIGNUP_STATUS, false);
		vm.put(HomeController.LOGIN_MESSAGE, "Welcome");
		vm.put(HomeController.LOGIN_PAGE, false);
		vm.put(HomeController.NEW_USER, false);
		vm.put(HomeController.SIGNUP_MESSAGE, false);
		return new ModelAndView(vm, HomeController.HOME_VIEW_NAME);
	}
}