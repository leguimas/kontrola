package br.com.kontrola.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import br.com.kontrola.server.TestServer;

public class BaseIntegrationTest {

	private static TestServer server;

	@BeforeClass
	public static void startEnvironment() throws Exception {
		server = new TestServer();
		server.enableWebApp();
		server.start();
	}

	@AfterClass
	public static void shutdownEnvironment() throws Exception {
		server.stop();
	}

}
