package br.com.kontrola.project;

import java.util.Date;

public class Issue {

	public enum Status {
		RED, YELLOW, GREEN;
	}

	private String name;

	private Status status;

	private Date lastUpdate;

	@SuppressWarnings("unused")
	private Issue() {
	}

	public Issue(String name) {
		this.name = name.trim().toLowerCase().replace(" ", "-");
		this.status = Status.RED;
		this.lastUpdate = new Date();
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

	public Issue updateStatus(Status status) {
		this.status = status;
		this.lastUpdate = new Date();

		return this;
	}
}
