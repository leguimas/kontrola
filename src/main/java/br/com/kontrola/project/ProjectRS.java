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

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/projects")
@Api(value = "/projects", description = "Operations about project")
public class ProjectRS {

	private ProjectRepository projectRepository = new ProjectRepository();

	private IssueRepository issueRepository = new IssueRepository();

	@GET
	@Path("/{identifier}")
	@Produces("application/json;charset=utf-8")
	@ApiOperation(value = "Get project info by project identifier", notes = "Returns the project info and the project issues.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Project found."),
			@ApiResponse(code = 404, message = "Project not found.") })
	public Response returnProjectInfo(
			@ApiParam(value = "Project identifier that you want to get the information.", required = true) @PathParam("identifier") String identifier) {
		Project project = projectRepository.loadByIdentifier(identifier);

		if (project != null) {
			return Response.ok(project).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Produces("application/json;charset=utf-8")
	@ApiOperation(value = "Create a new project.", notes = "Returns the persisted project info.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Project persisted.") })
	public Response createNewProject(@ApiParam(value = "Project identifier.", required = true)  @FormParam("identifier") String identifier,
			@ApiParam(value = "Project description..", required = true)  @FormParam("description") String description) {
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
	@ApiOperation(value = "Create a new issue to a specific project.", notes = "Returns the project info and the project issues.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Issue persisted."),
			@ApiResponse(code = 404, message = "Project not found.") })
	public Response addNewIssueToProject(@ApiParam(value = "Project identifier.", required = true) @PathParam("identifier") String projectIdentifier,
			@ApiParam(value = "Issue name.", required = true) @FormParam("name") String issueName) {

		Project project = projectRepository.loadByIdentifier(projectIdentifier);
		if (project == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		Issue newIssue = project.addNewIssue(issueName);
		try {
			newIssue = issueRepository.save(newIssue);
		} catch (DuplicatedEntityException e) {
			return Response.serverError().entity(e.getError().toJson()).build();
		}

		return Response.ok(project).build();
	}

}
