package com.example.todolist.repositories;

import com.example.todolist.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    Todo save(Todo todo);
}
