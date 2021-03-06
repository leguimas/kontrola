package br.com.kontrola.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import br.com.kontrola.application.persistence.DuplicatedEntityException;
import br.com.kontrola.test.BaseIntegrationTest;

public class IssueRepositoryTest extends BaseIntegrationTest {

	private IssueRepository repository = new IssueRepository();

	@Test
	public void createNewIssue() throws DuplicatedEntityException {
		Issue newIssue = new Issue("PROJECT A", "ISSUE A");
		Issue persistedIssue = repository.save(newIssue);

		assertNotNull(persistedIssue.getKey());

		persistedIssue = repository.loadByNameAndProject(persistedIssue.getName(), persistedIssue.getProject());
		assertEquals(newIssue.getKey(), persistedIssue.getKey());
		assertEquals(newIssue.getName(), persistedIssue.getName());
		assertEquals(newIssue.getProject(), persistedIssue.getProject());
	}

	@Test(expected = DuplicatedEntityException.class)
	public void createDuplicatedIssue() throws DuplicatedEntityException {
		Issue newIssue = new Issue("PROJECT A", "ISSUE A");
		newIssue = repository.save(newIssue);

		Issue duplicatedIssue = new Issue(newIssue.getName().toLowerCase(), newIssue.getProject());
		repository.save(duplicatedIssue);
	}

	@Test
	public void findIssueNotFound() {
		Issue issue = repository.loadByNameAndProject("UNEXISTENT NAME", "UNEXISTENT PROJECT");
		assertNull(issue);
	}

	public void findIssueByProject() throws DuplicatedEntityException {
		String project = "FILTER BY PROJECT";

		repository.save(new Issue(project, "NAME 01"));
		repository.save(new Issue(project, "NAME 02"));

		List<Issue> issuesByProject = repository.loadByProject(project);
		assertEquals(2, issuesByProject.size());

		issuesByProject = repository.loadByProject("PROJECT WITHOUT ISSUE");
		assertEquals(0, issuesByProject.size());
	}
}
