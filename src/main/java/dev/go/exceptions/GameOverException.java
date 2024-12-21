package dev.go.exceptions;

public class GameOverException extends RuntimeException {

	private static final long serialVersionUID = 5364422662522006704L;
	
	public GameOverException(String msg) {
		super(msg);
	}
	
}
