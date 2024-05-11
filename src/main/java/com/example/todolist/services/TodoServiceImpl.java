package com.example.todolist.services;

import com.example.todolist.models.Todo;
import com.example.todolist.repositories.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    private TodoRepository todoRepository;
    public TodoServiceImpl(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }


    @Override
    public Todo createTodo(Todo todoRequest) {
        System.out.println("inside post services");
        Todo newTodo = new Todo();
        newTodo.setTitle(todoRequest.getTitle());
        newTodo.setDescription(todoRequest.getDescription());
        newTodo.setDueDate(new Date());
        newTodo.setIsCompleted(todoRequest.getIsCompleted());
        return todoRepository.save(newTodo);
    }

    @Override
    public Todo updateTodo(Long id, Todo todoRequest) {
        return null;
    }


    public Todo updateTodo(Todo todoRequest) {
        return todoRepository.save(todoRequest);
    }

    @Override
    public String deleteTodo(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()) {
            return "Todo with ID " + id + " does not exist";
        }

        todoRepository.deleteById(id);
        return "Todo with ID " + id + " deleted successfully";

    }

    @Override
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

//    @Override
//    public List<Todo> getAllTodos() {
//        return null;
//    }


}
