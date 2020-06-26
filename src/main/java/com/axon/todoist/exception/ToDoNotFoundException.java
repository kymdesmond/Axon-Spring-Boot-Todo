package com.axon.todoist.exception;

import java.util.UUID;

public class ToDoNotFoundException extends Throwable {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ToDoNotFoundException(UUID id) {
        super("Cannot find ToDo " + id);
    }
    
}