package com.chat.controller;

import com.chat.dao.MessageDaoImpl;
import com.chat.model.*;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateViewRoute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetMessageController implements TemplateViewRoute {

	/**
	 * Static members
	 */
	final String CHAT_VIEW_NAME = "chat.ftl";
	final String LOGIN_VIEW_NAME = "home.ftl";
	static final String USER_NAME = "inputUsername";
	static final String PASSWORD = "inputPassword";
	static final String INVALID_LOGIN_MESSAGE = "You must be signed in to continue";
	static final String ADMIN = "admin";
	static final String MESSAGES = "messages";
	
	private MessageDaoImpl messageDaoImpl;
	public ArrayList<Message> messages = new ArrayList<Message>();
	private Gui gui;
	private String viewName;

	/**
	 * Constructor initializes
	 * message dao to persist
	 * messages
	 */
	public GetMessageController() {
		try {
			messageDaoImpl = new MessageDaoImpl();
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
		Session session = request.session();
		final User user = session.attribute("user");
		
		boolean admin = false;
		
		if (user != null) {
			if (user.getAccountType().equals("admin")) {
				admin = true;
			}
			messages = messageDaoImpl.getMessages();
			Button button = gui.getSendMessageButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
			vm.put(MESSAGES, messages);
			vm.put(ADMIN, admin);
			viewName = CHAT_VIEW_NAME;
		} else {
			Button button = gui.getHomeSigninButton();
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