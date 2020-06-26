package com.axon.todoist.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.axon.todoist.command.CreateToDoCommand;
import com.axon.todoist.command.UpdateToDoCommand;
import com.axon.todoist.dto.ToDoCreationDTO;
import com.axon.todoist.dto.ToDoUpdateDTO;
import com.axon.todoist.entity.ToDo;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ToDoCommandService {

    private final CommandGateway commandGateway;

    public CompletableFuture<ToDo> createToDo(ToDoCreationDTO toDoCreationDTO) {
        return this.commandGateway.send(new CreateToDoCommand(
            UUID.randomUUID(),
            toDoCreationDTO.getDescription(),
            toDoCreationDTO.getStatus()
        ));
    }

    public CompletableFuture<ToDo> updateToDo(UUID id, ToDoUpdateDTO toDoUpdateDTO) {
        return this.commandGateway.send(new UpdateToDoCommand(
            id,
            toDoUpdateDTO.getDescription(),
            toDoUpdateDTO.getStatus()
        ));
    }
}