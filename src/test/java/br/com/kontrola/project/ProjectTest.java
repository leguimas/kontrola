package br.com.kontrola.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ProjectTest {

	@Test
	public void testConstructor() {
		Project project = new Project("IDENTIFIER WITH SPACES", "Description");
		assertEquals("identifier-with-spaces", project.getIdentifier());
	}

	@Test
	public void testEquals() {
		Project projectA = new Project("A", "Description");
		Project projectA2 = new Project("A", "Different description");
		Project projectB = new Project("B", "Description");

		assertTrue(projectA.equals(projectA2));
		assertFalse(projectA.equals(projectB));
	}

	@Test
	public void addNewIssue() {
		Project project = new Project("Issues", "Project with issues");
		assertEquals(0, project.getIssues().size());

		project.addNewIssue("ISSUE 01");
		assertEquals(1, project.getIssues().size());

		project.addNewIssue("ISSUE 02");
		assertEquals(2, project.getIssues().size());

		project.addNewIssue("issue-01");
		assertEquals(2, project.getIssues().size());
	}

}
