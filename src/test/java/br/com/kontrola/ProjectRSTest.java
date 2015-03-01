package br.com.kontrola;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import br.com.kontrola.test.BaseIntegrationTest;

public class ProjectRSTest extends BaseIntegrationTest {

	ProjectRS projectRestService = new ProjectRS();

	@Test
	public void createNewProjectTest() throws Exception {
		String projectIdentifier = "NEW_PROJECT";
		Project projectCreated = (Project) projectRestService
				.createNewProject(projectIdentifier, "Project description").getEntity();
		assertNotNull(projectCreated.getKey());

		Project projectFounded = (Project) projectRestService.returnProjectInfo(projectIdentifier).getEntity();
		assertEquals(projectCreated.getKey(), projectFounded.getKey());
		assertEquals(projectCreated.getIdentifier(), projectFounded.getIdentifier());
	}

//	@Test
//	public void createDuplicatedProjectTest() throws Exception {
//		String projectIdentifier = "PROJECT_01";
//		projectRestService.createNewProject(projectIdentifier, "Project description");
//
//		Response response = projectRestService.createNewProject(projectIdentifier, "Other description");
//		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
//	}

	@Test
	public void returnNonExistentProjectInfo() throws Exception {
		Response projectFounded = projectRestService.returnProjectInfo("NOT_FOUND");
		assertEquals(Status.NOT_FOUND.getStatusCode(), projectFounded.getStatus());
	}

}
