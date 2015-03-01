package br.com.kontrola.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import br.com.kontrola.application.persistence.DuplicatedEntityException;
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

	@Test
	public void createDuplicatedProjectTest() {
		String projectIdentifier = "PROJECT_01";
		projectRestService.createNewProject(projectIdentifier, "Project description");

		Response duplicatedProject = projectRestService.createNewProject(projectIdentifier, "Other description");
		assertEquals(Status.INTERNAL_SERVER_ERROR.getStatusCode(), duplicatedProject.getStatus());
	}

	@Test
	public void returnNonExistentProjectInfo() throws Exception {
		Response response = projectRestService.returnProjectInfo("NOT_FOUND");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

	@Test
	public void addNewIssueToProject() throws DuplicatedEntityException {
		String projectIdentifier = "PROJECT_WITH_ISSUE";
		Project newProject = (Project) projectRestService.createNewProject(projectIdentifier, "Project description")
				.getEntity();
		assertEquals(0, newProject.getIssues().size());

		projectRestService.addNewIssueToProject(projectIdentifier, "New issue").getEntity();
		Project projectWithIssue = (Project) projectRestService.returnProjectInfo(projectIdentifier).getEntity();
		assertEquals(1, projectWithIssue.getIssues().size());

		projectRestService.addNewIssueToProject(projectIdentifier, "Second issue").getEntity();
		projectWithIssue = (Project) projectRestService.returnProjectInfo(projectIdentifier).getEntity();
		assertEquals(2, projectWithIssue.getIssues().size());
	}

	@Test
	public void addNewIssueToNonExistentProject() throws DuplicatedEntityException {
		Response response = projectRestService.addNewIssueToProject("NOT_FOUND", "New issue");
		assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
	}

}
