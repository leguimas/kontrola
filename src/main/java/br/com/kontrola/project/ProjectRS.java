package br.com.kontrola.project;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.kontrola.application.persistence.DuplicatedEntityException;

@Path("/projects")
public class ProjectRS {

	private ProjectRepository projectRepository = new ProjectRepository();

	@GET
	@Path("/{identifier}")
	@Produces("application/json;charset=utf-8")
	public Response returnProjectInfo(@PathParam("identifier") String identifier) {
		Project project = projectRepository.loadByIdentifier(identifier);

		if (project != null) {
			return Response.ok(project).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Produces("application/json;charset=utf-8")
	public Response createNewProject(@FormParam("identifier") String identifier,
			@FormParam("description") String description) {
		Project newProject = new Project(identifier, description);

		try {
			newProject = projectRepository.save(newProject);
		} catch (DuplicatedEntityException e) {
			return Response.serverError().entity(e.getError().toJson()).build();
		}

		return Response.ok(newProject).build();
	}

	@POST
	@Path("/{identifier}/issues")
	@Produces("application/json;charset=utf-8")
	public Response addNewIssueToProject(@PathParam("identifier") String projectIdentifier,
			@FormParam("name") String issueName) throws DuplicatedEntityException {

		Project project = projectRepository.loadByIdentifier(projectIdentifier);
		if (project == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		project.addNewIssue(issueName);
		project = projectRepository.save(project);

		return Response.ok(project).build();
	}

}
