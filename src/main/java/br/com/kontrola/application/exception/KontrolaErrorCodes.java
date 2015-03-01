package br.com.kontrola.application.exception;

import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

public enum KontrolaErrorCodes {

	DUPLICATED_ENTITY("001", "You are trying to persist a duplicated entity.");

	private String code;

	private String message;

	KontrolaErrorCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String toJson() {
		JsonNodeFactory nodeFactory = JsonNodeFactory.instance;

		ObjectNode node = nodeFactory.objectNode();
		node.put("code", code);
		node.put("message", message);

		return node.toString();
	}

}
