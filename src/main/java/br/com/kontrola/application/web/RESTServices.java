package br.com.kontrola.application.web;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.com.kontrola.project.ProjectRS;

public class RESTServices extends Application {

	private Set<Class<?>> resources = new HashSet<Class<?>>();

	public RESTServices() {
		resources.add(ProjectRS.class);
		resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return resources;
	}

}
