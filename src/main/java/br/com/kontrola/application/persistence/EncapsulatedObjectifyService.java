package br.com.kontrola.application.persistence;

import br.com.kontrola.project.Issue;
import br.com.kontrola.project.Project;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class EncapsulatedObjectifyService {

	static {
		factory().register(Project.class);
		factory().register(Issue.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

}
