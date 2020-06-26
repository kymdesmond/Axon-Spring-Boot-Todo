package com.axon.todoist.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import com.axon.todoist.entity.ToDo;
import com.axon.todoist.service.ToDoQueryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api")
@Api(value = "ToDo Queries", description = "ToDo Queries Controller")
@AllArgsConstructor
public class ToDoQueryController {

    private final ToDoQueryService toDoQueryService;

    @GetMapping(value = "/todo/{id}")
    public CompletableFuture<ToDo> findById(@PathVariable UUID id) {
        return this.toDoQueryService.findById(id);
    }

    @GetMapping(value = "/todo/{id}/events")
    public List<Object> listEventsForToDo(@PathVariable UUID id) {
        return this.toDoQueryService.listEventsForToDo(id);
    }
}