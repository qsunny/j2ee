package com.aaron.winkwebapp.api.exception;

import com.aaron.winkwebapp.api.bean.User;

@SuppressWarnings("serial")
public class UserException extends Exception {
	
	private User user;
	
	public UserException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserException(User user) {
		super();
		this.user = user;
	}

	public UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	public User getUser() {
		return user;
	}
	
	
}
