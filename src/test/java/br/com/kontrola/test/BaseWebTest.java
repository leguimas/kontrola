package br.com.kontrola.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import br.com.kontrola.server.TestServer;


public class BaseWebTest {

	protected static TestServer server = new TestServer();

	@BeforeClass
	public static void startEnvironment() throws Exception {
		server.enableDataStore();
		server.enableWebApp(TestServer.DEFAULT_WEB_PORT);
		server.start();
	}

	@AfterClass
	public static void shutdownEnvironment() throws Exception {
		server.stop();
	}

}
