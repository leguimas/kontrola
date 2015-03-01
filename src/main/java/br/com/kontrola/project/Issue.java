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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		return true;
	}

}
