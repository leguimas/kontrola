package br.com.kontrola.application.persistence;

import br.com.kontrola.Project;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class EncapsulatedObjectifyService {

	static {
		factory().register(Project.class);
	}

	public static Objectify ofy() {
		return ObjectifyService.ofy();
	}

	public static ObjectifyFactory factory() {
		return ObjectifyService.factory();
	}

}
