package br.com.kontrola.project;

import java.util.Date;

import br.com.kontrola.application.persistence.BaseEntity;
import br.com.kontrola.project.Issue.Status;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

@Entity
public class IssueChangeLog extends BaseEntity {

	@Index
	private String issue;

	private Status status;

	private String reason;

	private String user;

	private Date date;

	public IssueChangeLog(String issue, Status status, String reason, String user) {
		this.issue = issue;
		this.status = status;
		this.reason = reason.trim();
		this.user = user.trim();
		this.date = new Date();
	}

	public String getIssue() {
		return issue;
	}

	public Status getStatus() {
		return status;
	}

	public String getReason() {
		return reason;
	}

	public String getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

}
