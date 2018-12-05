package com.chat.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.chat.dao.MessageDaoImpl;
import com.chat.model.Button;
import com.chat.model.Gui;
import com.chat.model.Message;
import com.chat.model.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import spark.Session;

public class PostMessageController implements TemplateViewRoute {

	static final String CHAT_VIEW_NAME = "chat.ftl";
	static final String TITLE = "title";
	static final String INVALID_ACCESS_MESSAGE = "You must be signed in to continue";
	static final String MESSAGE = "inputMessage";
	static final String INVALID_LOGIN_MESSAGE = "Incorrect Username/Password";
	static final String MESSAGES = "messages";
	static final String ADMIN = "admin";
	private Gui gui;
	private String viewName;
	private MessageDaoImpl messageDaoImpl;
	public ArrayList<Message> messages = new ArrayList<Message>();

	public PostMessageController() {
		this.gui = new Gui();
		try {
			messageDaoImpl = new MessageDaoImpl();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ModelAndView handle(Request request, Response response) {
		Map<String, Object> vm = new HashMap<>();

		Session session = request.session();
		final User user = session.attribute("user");
		boolean admin = false;
		
		if (user == null) {
			Button button = gui.getHomeSigninButton();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
			vm.put(HomeController.LOGIN_STATUS, false);
			vm.put(HomeController.LOGIN_MESSAGE, INVALID_ACCESS_MESSAGE);
			vm.put(HomeController.NEW_USER, false);
			vm.put(HomeController.LOGIN_PAGE, true);
			vm.put(HomeController.SIGNUP_STATUS, false);
			vm.put(HomeController.SIGNUP_MESSAGE, null);
			viewName = HomeController.HOME_VIEW_NAME;
		} else {
			if (user.getAccountType().equals("admin")) {
				admin = true;
			}
			Button button = gui.getSendMessageButton();
			final String messageString = request.queryParams(MESSAGE);
			Message message = new Message();
			message.setMessage(messageString);
			message.setUsername(user.getUsername());
			messageDaoImpl.saveMessage(message);
			messages = messageDaoImpl.getMessages();
			vm.put(HomeController.BUTTON_CLASS, button.getButtonClass());
			vm.put(HomeController.BUTTON_TYPE, button.getButtonType());
			vm.put(HomeController.BUTTON_TEXT, button.getButtonText());
			vm.put(HomeController.TITLE_ATTRIBUTE, HomeController.TITLE);
			vm.put(HomeController.LOGIN_STATUS, true);
			vm.put(HomeController.NEW_USER, false);
			vm.put(HomeController.LOGIN_PAGE, true);
			vm.put(HomeController.SIGNUP_STATUS, false);
			vm.put(HomeController.SIGNUP_MESSAGE, null);
			vm.put(MESSAGES, messages);
			vm.put(ADMIN, admin);
			viewName = CHAT_VIEW_NAME;
		}
		return new ModelAndView(vm, viewName);
	}
}