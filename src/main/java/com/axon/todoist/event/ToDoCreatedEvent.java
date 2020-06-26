package com.axon.todoist.event;

import java.util.UUID;

import lombok.Data;

@Data
public class ToDoCreatedEvent {
    
    private final UUID id;
    private final String description;
    private final Boolean status;
}