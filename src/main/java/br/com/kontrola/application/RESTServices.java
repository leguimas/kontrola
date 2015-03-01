package br.com.kontrola.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.com.kontrola.ProjectRS;

public class RESTServices extends Application {

	private Set<Object> singletons = new HashSet<Object>();

	public RESTServices() {
		singletons.add(new ProjectRS());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
