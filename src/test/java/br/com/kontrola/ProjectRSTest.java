package br.com.kontrola;

import static org.junit.Assert.assertEquals;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import br.com.kontrola.test.BaseIntegrationTest;

public class ProjectRSTest extends BaseIntegrationTest {

	private static final String KONTROLA_URL = "http://localhost:8080/api";

	@Test
	public void returnProjectInfoTest() throws Exception {
		CloseableHttpClient httpRequest = HttpClients.createDefault();

		try {
			String projectIdentifier = "TEST";
			HttpGet jiraRequest = new HttpGet(KONTROLA_URL + "/projects/" + projectIdentifier);

			CloseableHttpResponse response = httpRequest.execute(jiraRequest);
			try {
				String projectInformation = EntityUtils.toString(response.getEntity());
				assertEquals(projectIdentifier, projectInformation);
			} finally {
				response.close();
			}
		} finally {
			httpRequest.close();
		}
	}

}
