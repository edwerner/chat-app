package com.chat;

import static spark.Spark.*;

import com.chat.controller.GetMessageController;
import com.chat.controller.HomeController;
import com.chat.controller.PostMessageController;
import com.chat.controller.PostRemoveMessageController;
import com.chat.controller.PostSigninController;
import com.chat.controller.PostSignoutController;
import com.chat.controller.PostSignupController;
import com.chat.controller.SignupController;

import spark.TemplateEngine;

public class WebServer {
	
	/**
	 * Public routes for Spark views
	 */
	public static final String GET_HOME_URL = "/";
	public static final String POST_SIGNIN_URL = "/chatroom";
	public static final String GET_SIGNIN_URL = "/chatroom";
	public static final String GET_SIGNUP_URL = "/signup";
	public static final String POST_SIGNUP_URL = "/signup";
	public static final String POST_SIGNOUT_URL = "/";
	public static final String POST_MESSAGE_URL = "/chat";
	public static final String GET_MESSAGE_URL = "/chat";
	public static final String POST_REMOVE_MESSAGE_URL = "/remove";

	private final TemplateEngine templateEngine;

	public WebServer(final TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
	
	/**
	 * Initializes routes and binds
	 * them to controllers
	 */
	public void initialize() {
		staticFileLocation("/public");
		get(GET_HOME_URL, new HomeController(), templateEngine);
		post(POST_SIGNIN_URL, new PostSigninController(), templateEngine);
		get(GET_SIGNIN_URL, new PostSigninController(), templateEngine);
		get(GET_SIGNUP_URL, new SignupController(), templateEngine);
		post(POST_SIGNUP_URL, new PostSignupController(), templateEngine);
		post(POST_SIGNOUT_URL, new PostSignoutController(), templateEngine);
		post(POST_MESSAGE_URL, new PostMessageController(), templateEngine);
		get(GET_MESSAGE_URL, new GetMessageController(), templateEngine);
		post(POST_REMOVE_MESSAGE_URL, new PostRemoveMessageController().postRemoveMessage(), new JsonTransformer());
	}
}