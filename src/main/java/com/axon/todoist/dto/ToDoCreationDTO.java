package com.axon.todoist.dto;

import lombok.Value;

@Value
public class ToDoCreationDTO {
    private final String description;
    private final Boolean status;
}