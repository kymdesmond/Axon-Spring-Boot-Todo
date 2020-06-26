package com.axon.todoist.projection;

import java.util.Optional;

import com.axon.todoist.entity.ToDo;
import com.axon.todoist.event.ToDoCreatedEvent;
import com.axon.todoist.event.ToDoUpdatedEvent;
import com.axon.todoist.exception.ToDoNotFoundException;
import com.axon.todoist.query.FindToDoQuery;
import com.axon.todoist.repository.ToDoRepository;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ToDoProjection {

    private final ToDoRepository toDoRepository;
    
    @EventHandler
    public void on(ToDoCreatedEvent event) {
        log.info("Handling ToDo creation event {}", event.getId());
        ToDo toDo = new ToDo(event.getId(), event.getDescription(), event.getStatus());
        this.toDoRepository.save(toDo);
    }

    @EventHandler
    public void on(ToDoUpdatedEvent event) throws ToDoNotFoundException {
        log.info("Handling ToDo update event {}", event.getId());

        Optional<ToDo> optionalToDo = toDoRepository.findById(event.getId());

        if(optionalToDo.isPresent()) {
            ToDo toDo = optionalToDo.get();
            toDo.setDescription(event.getDescription());
            toDo.setStatus(event.getStatus());
            this.toDoRepository.save(toDo);
        } else {
            throw new ToDoNotFoundException(event.getId());
        }

    }
    
    @QueryHandler
    public ToDo handle(FindToDoQuery findToDoQuery) {
        log.info("Handling find ToDo query: {}", findToDoQuery.getId());
        return this.toDoRepository.findById(findToDoQuery.getId()).orElse(null);
    }
}