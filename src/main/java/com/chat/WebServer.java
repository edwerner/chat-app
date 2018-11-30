package com.chat;

import static spark.Spark.*;

import com.chat.controller.HomeController;
import com.chat.controller.PostSigninController;
import com.chat.controller.PostSignoutController;
import com.chat.controller.PostSignupController;
import com.chat.controller.SignupController;

import spark.TemplateEngine;

public class WebServer {
	public static final String GET_HOME_URL = "/";
	public static final String POST_SIGNIN_URL = "/list";
	public static final String GET_SIGNUP_URL = "/signup";
	public static final String POST_SIGNUP_URL = "/signup";
	public static final String POST_SIGNOUT_URL = "/";

	private final TemplateEngine templateEngine;

	public WebServer(final TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public void initialize() {
		staticFileLocation("/public");
		get(GET_HOME_URL, new HomeController(), templateEngine);
		post(POST_SIGNIN_URL, new PostSigninController(), templateEngine);
		get(GET_SIGNUP_URL, new SignupController(), templateEngine);
		post(POST_SIGNUP_URL, new PostSignupController(), templateEngine);
		post(POST_SIGNOUT_URL, new PostSignoutController(), templateEngine);
	}
}