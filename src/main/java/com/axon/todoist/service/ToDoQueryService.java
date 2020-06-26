package com.axon.todoist.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.axon.todoist.entity.ToDo;
import com.axon.todoist.query.FindToDoQuery;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ToDoQueryService {
    
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public CompletableFuture<ToDo> findById(UUID id) {
        return this.queryGateway.query(
            new FindToDoQuery(id),
            ResponseTypes.instanceOf(ToDo.class)
            );
    }

    public List<Object> listEventsForToDo(UUID id) {
        return this.eventStore
                .readEvents(id.toString())
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
}