package br.com.kontrola.project;

import static br.com.kontrola.application.persistence.EncapsulatedObjectifyService.ofy;

import java.util.List;

import br.com.kontrola.application.persistence.BaseEntityRepository;
import br.com.kontrola.application.persistence.DuplicatedEntityException;

public class IssueRepository extends BaseEntityRepository {

	public Issue save(Issue newIssue) throws DuplicatedEntityException {

		if (existsIssueWithTheSameName(newIssue)) {
			throw new DuplicatedEntityException();
		}

		return persist(newIssue);
	}

	private boolean existsIssueWithTheSameName(Issue newIssue) {
		return !newIssue.isPersisted() && this.loadByNameAndProject(newIssue.getName(), newIssue.getProject()) != null;
	}

	public Issue loadByNameAndProject(String name, String project) {
		return ofy().load().type(Issue.class).filter("name ==", name.trim().toLowerCase())
				.filter("project == ", project).first().now();
	}

	public List<Issue> loadByProject(String project) {
		return ofy().load().type(Issue.class).filter("project == ", project).list();
	}

}
