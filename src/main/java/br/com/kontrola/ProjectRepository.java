package br.com.kontrola;

import static br.com.kontrola.application.persistence.OfyService.ofy;

import java.util.UUID;

public class ProjectRepository {

	public Project save(Project newProject) {
		newProject.defineKey(UUID.randomUUID().toString());
		ofy().save().entity(newProject).now();
		return newProject;
	}

	public Project load(String identifier) {
		return ofy().load().type(Project.class).filter("identifier ==", identifier.trim().toLowerCase()).first().now();
	}

}
