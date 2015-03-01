package br.com.kontrola.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class TestServer {

	public static final int DEFAULT_WEB_PORT = 8080;

	private Server server;

	public TestServer() {
		this(DEFAULT_WEB_PORT);
	}

	public TestServer(int port) {
		this.server = new Server(port);
	}

	public TestServer enableWebApp() {
		WebAppContext webApp = new WebAppContext();

		webApp.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		webApp.setResourceBase("/");
		webApp.setParentLoaderPriority(true);

		server.setHandler(webApp);
		return this;
	}

	public void start() throws Exception {
		server.start();
	}

	public void stop() throws Exception {
		server.stop();
	}
}
