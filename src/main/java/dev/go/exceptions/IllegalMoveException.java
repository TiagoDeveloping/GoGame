package dev.go.exceptions;

public class IllegalMoveException extends RuntimeException {
	private static final long serialVersionUID = -2253704369613425984L;

	public IllegalMoveException(String msg) {
		super(msg);
	}
}
