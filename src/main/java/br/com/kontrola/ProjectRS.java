package br.com.kontrola;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/projects")
public class ProjectRS {

	@GET
	@Path("/{identifier}")
	public Response returnProjectInfo(@PathParam("identifier") String identifier) {
		return Response.ok(identifier).build();
	}

}
