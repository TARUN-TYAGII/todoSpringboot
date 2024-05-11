package com.example.todolist.services;


import com.example.todolist.models.Todo;

import java.util.List;

public interface TodoService {

    Todo createTodo(Todo todoRequest);
    Todo updateTodo(Long id, Todo todoRequest);
    String deleteTodo(Long id);
    Object getTodoById(Long id);

    List<Todo> getAll();

//     List<Todo> getAllTodos();


}
