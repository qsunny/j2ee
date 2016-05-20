package com.aaron.search.exception;

import java.io.Serializable;

/**
 * Search Exception
 * @author aaron.qiu
 * @since 2016-05-19
 */
@SuppressWarnings("serial")
public class SearchException extends Exception implements Serializable {

	public SearchException() {
		super();
	}

	public SearchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SearchException(String message, Throwable cause) {
		super(message, cause);
	}

	public SearchException(String message) {
		super(message);
	}

	public SearchException(Throwable cause) {
		super(cause);
	}

	
	
}
