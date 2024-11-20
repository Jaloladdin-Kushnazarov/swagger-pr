package org.example.swaggerpr.repository;

import org.example.swaggerpr.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
