package com.chat.controller;

import com.chat.model.Button;

public class GuiController {

	private Button button;

	public GuiController() {

	}

	public Button getHomeSignupButton() {
		button = new Button();
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Signup");
		return button;
	}

	public Button getHomeSigninButton() {
		button = new Button();
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Signin");
		return button;
	}

	public Button getGameSignoutButton() {
		button = new Button();
		button.setButtonClass("btn btn-warning margin-10");
		button.setButtonType("submit");
		button.setButtonText("Signout");
		return button;
	}

	public Button getSelectButton() {
		button = new Button();
		button.setButtonClass("btn btn-lg btn-primary btn-block");
		button.setButtonType("submit");
		button.setButtonText("Select");
		return button;
	}
}
