package br.com.kontrola.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class TestServer {

	public static final int DEFAULT_WEB_PORT = 8080;

	private Server server;

    private LocalServiceTestHelper dataStore;

	public TestServer enableWebApp() {
		return this.enableWebApp(DEFAULT_WEB_PORT);
	}

	public TestServer enableWebApp(int port) {
		this.server = new Server(port);

		WebAppContext webApp = new WebAppContext();

		webApp.setDescriptor("src/main/webapp/WEB-INF/web.xml");
		webApp.setResourceBase("/");
		webApp.setParentLoaderPriority(true);

		server.setHandler(webApp);
		return this;
	}

	public TestServer enableDataStore() {
		this.dataStore = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
		return this;
	}

	public void start() throws Exception {
		if (server != null) {
			server.start();
		}

		if (dataStore != null) {
			dataStore.setUp();
		}
	}

	public void stop() throws Exception {
		if (server != null) {
			server.stop();
		}

		if (dataStore != null) {
			dataStore.tearDown();
		}
	}

}
