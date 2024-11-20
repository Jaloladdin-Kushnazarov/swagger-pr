package org.example.swaggerpr.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.swaggerpr.EntityNotFoundExeption;
import org.example.swaggerpr.criteria.TodoCriteria;
import org.example.swaggerpr.dto.ErrorData;
import org.example.swaggerpr.entity.Todo;
import org.example.swaggerpr.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/todo")
@Tag(name = "Bu Todo Controller", description = "bu todo controller")
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Operation(
            summary = "id bo'yicha topadi",
            description = "bu yerga description yozishingiz mumkin edi!",
            responses = {
                    @ApiResponse(description = "hammasi zo'r ok", responseCode = "200"),
                    @ApiResponse(description = "database bilan bog'liq muammo", responseCode = "500",
                            content = {
                                    @Content(schema = @Schema(implementation = ErrorData.class))
                            }),
                    @ApiResponse(description = "bunaqa manzilli page topilmadi", responseCode = "404",
                            content = {
                                    @Content(schema = @Schema(implementation = ErrorData.class))
                            })

            })
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundExeption("Todo Not Found With ID: " + id));
        return ResponseEntity.ok(todo);
    }

    @GetMapping("/")
    public ResponseEntity<List<Todo>> getAllTodo(TodoCriteria todoCriteria ) {
        System.out.println(todoCriteria);
        List<Todo> todos = todoRepository.findAll();
        return ResponseEntity.ok(todos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundExeption("Todo Not Found With ID: " + id));
        todoRepository.delete(todo);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Todo> saveTodo(@RequestBody Todo todo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoRepository.save(todo));
    }
}