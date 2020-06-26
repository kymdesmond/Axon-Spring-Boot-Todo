package com.axon.todoist.repository;

import java.util.UUID;

import com.axon.todoist.entity.ToDo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, UUID>{
    
}