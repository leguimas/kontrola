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
}
