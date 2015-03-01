package br.com.kontrola.application.persistence;

import br.com.kontrola.application.exception.KontrolaErrorCodes;
import br.com.kontrola.application.exception.KontrolaException;

public class DuplicatedEntityException extends KontrolaException {

	public DuplicatedEntityException() {
		super(KontrolaErrorCodes.DUPLICATED_ENTITY);
	}

	protected DuplicatedEntityException(KontrolaErrorCodes errorCode) {
		super(errorCode);
	}

	private static final long serialVersionUID = 2886371204122987400L;

}
