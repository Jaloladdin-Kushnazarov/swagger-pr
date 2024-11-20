package org.example.swaggerpr.repository;

import org.example.swaggerpr.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
