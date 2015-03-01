package br.com.kontrola;

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
			@FormParam("description") String description) throws DuplicatedEntityException {
		Project newProject = new Project(identifier, description);
		newProject = projectRepository.save(newProject);

		return Response.ok(newProject).build();
	}

}
