package br.com.kontrola.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.kontrola.application.persistence.BaseEntity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Issue extends BaseEntity {

	public enum Status {
		RED, YELLOW, GREEN;
	}

	@Index
	private String name;

	private Status status;

	private Date lastUpdate;

	@Index
	private String project;

	@Ignore
	private List<IssueChangeLog> changeLog;

	@SuppressWarnings("unused")
	private Issue() {
	}

	public Issue(String project, String name) {
		this.name = name.trim().toLowerCase().replace(" ", "-");
		this.status = Status.RED;
		this.lastUpdate = new Date();
		this.project = project;

		this.changeLog = new ArrayList<IssueChangeLog>();
	}

	public String getName() {
		return name;
	}

	public Status getStatus() {
		return status;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public String getProject() {
		return project;
	}

	public List<IssueChangeLog> getChangeLog() {
		return changeLog;
	}

	public Issue updateStatus(Status status, String reason, String user) {
		this.status = status;
		lastUpdate = new Date();

		IssueChangeLog changeLog = new IssueChangeLog(this.key, status, reason, user);
		this.changeLog.add(changeLog);

		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
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
		Issue other = (Issue) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		return true;
	}

}
