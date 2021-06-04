package com.superheros.exception;

/**
 * Exception when the resource not found.
 * @author Victor
 *
 */
public class ResourceNotFoundException extends RuntimeException {
	
	/**
	 * UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * @param id Resource ID.
	 */
	public ResourceNotFoundException(Integer id) {
		super(String.format("Superhero with id %d not found", id));
	}

}
