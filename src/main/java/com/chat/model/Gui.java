package com.chat.model;

public class Gui {

	private Button button;

	public Gui() {

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

	public Button getSignoutButton() {
		button = new Button();
		button.setButtonClass("btn btn-warning margin-10");
		button.setButtonType("submit");
		button.setButtonText("Signout");
		return button;
	}

	public Button getSendMessageButton() {
		button = new Button();
		button.setButtonClass("btn btn-lg btn-success btn-block");
		button.setButtonType("submit");
		button.setButtonText("Send");
		return button;
	}
}
