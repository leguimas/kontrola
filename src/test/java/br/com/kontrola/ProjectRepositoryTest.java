package br.com.kontrola;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.kontrola.application.persistence.DuplicatedEntityException;
import br.com.kontrola.test.BaseIntegrationTest;

public class ProjectRepositoryTest extends BaseIntegrationTest {

	private ProjectRepository repository = new ProjectRepository();

	@Test
	public void createNewProject() throws DuplicatedEntityException {
		Project newProject = new Project("IDENTIFIER", "Project description");
		Project persistedProject = repository.save(newProject);

		assertNotNull(persistedProject.getKey());

		persistedProject = repository.loadByIdentifier(persistedProject.getIdentifier());
		assertEquals(newProject.getIdentifier(), persistedProject.getIdentifier());
		assertEquals(newProject.getDescription(), persistedProject.getDescription());
	}

	@Test(expected = DuplicatedEntityException.class)
	public void createDuplicatedProject() throws DuplicatedEntityException {
		Project newProject = new Project("IDENTIFIER", "Project description");
		newProject = repository.save(newProject);

		Project duplicatedProject = new Project(newProject.getIdentifier(), "Other description");
		repository.save(duplicatedProject);
	}

}
