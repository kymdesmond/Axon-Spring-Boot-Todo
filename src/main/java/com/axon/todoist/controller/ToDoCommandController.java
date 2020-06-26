package com.axon.todoist.controller;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.axon.todoist.dto.ToDoCreationDTO;
import com.axon.todoist.dto.ToDoUpdateDTO;
import com.axon.todoist.entity.ToDo;
import com.axon.todoist.service.ToDoCommandService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api")
@Api(value = "ToDo Commands", description = "ToDo Commands API")
@AllArgsConstructor
public class ToDoCommandController {
    
    private final ToDoCommandService toDoCommandService;

    @PostMapping(value="/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<ToDo> createToDo(@RequestBody ToDoCreationDTO toDoCreationDTO) {
        return this.toDoCommandService.createToDo(toDoCreationDTO);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CompletableFuture<ToDo> updateToDo(@PathVariable UUID id, @RequestBody ToDoUpdateDTO toDoUpdateDTO) {
        return this.toDoCommandService.updateToDo(id, toDoUpdateDTO);
    }
    
}