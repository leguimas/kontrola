package br.com.kontrola.project;

import java.util.ArrayList;
import java.util.List;

import br.com.kontrola.application.persistence.BaseEntity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Project extends BaseEntity {

	@Index
	private String identifier;

	private String description;

	private List<Issue> issues;

	@SuppressWarnings("unused")
	private Project() {
	}

	public Project(String identifier, String description) {
		this.identifier = identifier.trim().toLowerCase().replace(" ", "-");
		this.description = description.trim();

		this.issues = new ArrayList<Issue>();
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getDescription() {
		return description;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public Project addNewIssue(String issueName) {
		if (issues == null) {
			issues = new ArrayList<Issue>();
		}

		Issue newIssue = new Issue(issueName.trim().toLowerCase());
		if (!issues.contains(newIssue)) {
			issues.add(newIssue);
		}

		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}

}
