package br.com.kontrola.project;

import static br.com.kontrola.application.persistence.EncapsulatedObjectifyService.ofy;

import java.util.UUID;

import br.com.kontrola.application.persistence.DuplicatedEntityException;

public class ProjectRepository {

	public Project save(Project newProject) throws DuplicatedEntityException {

		if (existsProjectWithTheSameIdentifier(newProject)) {
			throw new DuplicatedEntityException();
		}

		return persist(newProject);
	}

	private Project persist(Project project) {
		if (!project.isPersisted()) {
			project.defineKey(UUID.randomUUID().toString());
		}

		ofy().save().entity(project).now();
		return project;
	}

	private boolean existsProjectWithTheSameIdentifier(Project newProject) {
		return !newProject.isPersisted() && this.loadByIdentifier(newProject.getIdentifier()) != null;
	}

	public Project loadByIdentifier(String identifier) {
		return ofy().load().type(Project.class).filter("identifier ==", identifier.trim().toLowerCase()).first().now();
	}

}
