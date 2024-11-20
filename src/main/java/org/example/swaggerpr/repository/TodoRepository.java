package org.example.swaggerpr.repository;

import org.example.swaggerpr.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
}
