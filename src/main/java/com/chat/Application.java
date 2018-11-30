package com.chat;

import java.util.logging.Logger;

import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

public final class Application {
	private static final Logger LOG = Logger.getLogger(Application.class.getName());
	private final WebServer webServer;

	public static void main(String[] args) {
		final TemplateEngine templateEngine = new FreeMarkerEngine();
		final WebServer webServer = new WebServer(templateEngine);
		final Application application = new Application(webServer);
		application.initialize();
	}

	private Application(final WebServer webServer) {
		this.webServer = webServer;
	}

	private void initialize() {
		LOG.fine("Authentication app is initializing.");
		webServer.initialize();
		LOG.fine("Authentication app initialization complete.");
	}

}