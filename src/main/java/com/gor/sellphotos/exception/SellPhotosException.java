package com.gor.sellphotos.exception;

/**
 *
 */
public class SellPhotosException extends RuntimeException {

	public SellPhotosException(final String message) {
		super(message);
	}

	public SellPhotosException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
