package org.example.swaggerpr.controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.swaggerpr.EntityNotFoundExeption;
import org.example.swaggerpr.entity.Comment;
import org.example.swaggerpr.repository.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Integer id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment Not Found With ID: " + id));
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/")
    public ResponseEntity<List<Comment>> getAllComment() {
        List<Comment> comments = commentRepository.findAll();
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundExeption("Comment Not Found With ID: " + id));
        commentRepository.delete(comment);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentRepository.save(comment));
    }
}