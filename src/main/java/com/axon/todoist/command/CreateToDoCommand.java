package com.axon.todoist.command;

import java.util.UUID;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateToDoCommand {
    
    @TargetAggregateIdentifier
    private UUID id;
    private String description;
    private Boolean status;
}