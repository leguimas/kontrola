package br.com.kontrola;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Project {

	@Id
	private String key;

	@Index
	private String identifier;

	private String description;

	public Project(String identifier, String description) {
		this.identifier = identifier.trim().toLowerCase();
		this.description = description.trim();
	}

	public String getKey() {
		return key;
	}

	public String getIdentifier() {
		return identifier;
	}

	public String getDescription() {
		return description;
	}

	protected void defineKey(String key) {
		this.key = key;
	}

}
