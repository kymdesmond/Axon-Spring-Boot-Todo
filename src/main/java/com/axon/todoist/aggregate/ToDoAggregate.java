package com.axon.todoist.aggregate;

import java.util.UUID;

import com.axon.todoist.command.CreateToDoCommand;
import com.axon.todoist.command.UpdateToDoCommand;
import com.axon.todoist.event.ToDoCreatedEvent;
import com.axon.todoist.event.ToDoUpdatedEvent;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Aggregate
public class ToDoAggregate {
    
    @AggregateIdentifier
    private UUID id;
    private String description;
    private Boolean status;

    @CommandHandler
    public ToDoAggregate(CreateToDoCommand command) {
        AggregateLifecycle.apply(
            new ToDoCreatedEvent(command.getId(), command.getDescription(), command.getStatus())
        );
    }

    @EventSourcingHandler
    public void on(ToDoCreatedEvent event) {
        this.id = event.getId();
        this.description = event.getDescription();
        this.status = event.getStatus();
    }

    @CommandHandler
    public void handle(UpdateToDoCommand command) {
        AggregateLifecycle.apply(
            new ToDoUpdatedEvent(command.getId(), command.getDescription(), command.getStatus())
        );
    }

    @EventSourcingHandler
    public void on(ToDoUpdatedEvent event) {
        this.description = event.getDescription();
        this.status = event.getStatus();
    }
}