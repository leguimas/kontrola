package br.com.kontrola.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import br.com.kontrola.server.TestServer;

import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

public class BasePersistenceTest {

	protected static TestServer server = new TestServer();

	private static Closeable closeable;

	@BeforeClass
	public static void startEnvironment() throws Exception {
		server.enableDataStore();
		server.start();

		closeable = ObjectifyService.begin();
	}

	@AfterClass
	public static void shutdownEnvironment() throws Exception {
		closeable.close();
		server.stop();
	}

}
