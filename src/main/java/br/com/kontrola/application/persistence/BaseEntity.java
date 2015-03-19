package br.com.kontrola.application.persistence;

import java.util.UUID;

import com.googlecode.objectify.annotation.Id;

public abstract class BaseEntity {

	@Id
	protected String key;

	public BaseEntity defineKey() {
		this.key = UUID.randomUUID().toString();
		return this;
	}

	public String getKey() {
		return key;
	}
}
