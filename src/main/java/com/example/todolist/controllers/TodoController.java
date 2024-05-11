package com.example.todolist.controllers;


import com.example.todolist.models.Todo;
import com.example.todolist.services.TodoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
public class TodoController {

    TodoServiceImpl todoService;
    public TodoController(  TodoServiceImpl todoService){
        this.todoService = todoService;
    }

    @PostMapping()
   public Todo createTodo(@RequestBody Todo requestTodo){
       System.out.println("post request controller");
       return todoService.createTodo(requestTodo);
   }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Long id, @RequestBody Todo newTodo) {
        Optional<Todo> oldTodoOptional = todoService.getTodoById(id);
        if (oldTodoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo does not exist");
        }

        Todo existingTodo = oldTodoOptional.get();
        if (newTodo.getTitle() != null) {
            existingTodo.setTitle(newTodo.getTitle());
        }
        if (newTodo.getDescription() != null) {
            existingTodo.setDescription(newTodo.getDescription());
        }
        if (newTodo.getDueDate() != null) {
            existingTodo.setDueDate(newTodo.getDueDate());
        }
        if (newTodo.getIsCompleted() != null) {
            existingTodo.setIsCompleted(newTodo.getIsCompleted());
        }

        Todo savedTodo = todoService.updateTodo(existingTodo);
        return ResponseEntity.ok(savedTodo);
    }

   @DeleteMapping("/{id}")
   public String deleteTodo(@PathVariable Long id){
        return todoService.deleteTodo(id);
   }

   @GetMapping()
   public List<Todo> getAllTodo(){
        return todoService.getAll();
   }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Long id) {
        Optional<Todo> todoOptional = todoService.getTodoById(id);
        if (todoOptional.isPresent()) {
            return ResponseEntity.ok(todoOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Todo does not exist");
        }
    }
}
