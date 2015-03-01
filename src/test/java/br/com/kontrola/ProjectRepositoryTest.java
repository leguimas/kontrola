package br.com.kontrola;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.kontrola.test.BasePersistenceTest;

public class ProjectRepositoryTest extends BasePersistenceTest {

	private ProjectRepository repository = new ProjectRepository();

	@Test
	public void createNewRepository() {
		Project newProject = new Project("IDENTIFIER", "Project description");
		Project persistedProject = repository.save(newProject);

		assertNotNull(persistedProject.getKey());

		persistedProject = repository.load(persistedProject.getIdentifier());
		assertEquals(newProject.getIdentifier(), persistedProject.getIdentifier());
		assertEquals(newProject.getDescription(), persistedProject.getDescription());
	}

}
