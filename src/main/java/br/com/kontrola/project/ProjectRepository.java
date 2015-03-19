package br.com.kontrola.project;

import static br.com.kontrola.application.persistence.EncapsulatedObjectifyService.ofy;
import br.com.kontrola.application.persistence.BaseEntityRepository;
import br.com.kontrola.application.persistence.DuplicatedEntityException;

public class ProjectRepository extends BaseEntityRepository {

	public Project save(Project newProject) throws DuplicatedEntityException {

		if (existsProjectWithTheSameIdentifier(newProject)) {
			throw new DuplicatedEntityException();
		}

		return persist(newProject);
	}

	private boolean existsProjectWithTheSameIdentifier(Project newProject) {
		return !newProject.isPersisted() && this.loadByIdentifier(newProject.getIdentifier()) != null;
	}

	public Project loadByIdentifier(String identifier) {
		return ofy().load().type(Project.class).filter("identifier ==", identifier.trim().toLowerCase()).first().now();
	}

}
