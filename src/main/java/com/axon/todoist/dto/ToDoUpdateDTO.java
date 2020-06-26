package com.axon.todoist.dto;

import lombok.Value;

@Value
public class ToDoUpdateDTO {

    private final String description;
    private final Boolean status;
}