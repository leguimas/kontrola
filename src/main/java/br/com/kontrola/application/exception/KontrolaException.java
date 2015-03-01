package br.com.kontrola.application.exception;

public class KontrolaException extends Exception {

	private static final long serialVersionUID = -8043500028978644279L;

	private KontrolaErrorCodes error;

	protected KontrolaException(KontrolaErrorCodes error) {
		this.error = error;
	}

	public KontrolaErrorCodes getError() {
		return error;
	}

}
