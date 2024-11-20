package org.example.swaggerpr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.swaggerpr.entity.Comment;
import org.example.swaggerpr.entity.Post;
import org.example.swaggerpr.entity.Todo;
import org.example.swaggerpr.repository.CommentRepository;
import org.example.swaggerpr.repository.PostRepository;
import org.example.swaggerpr.repository.TodoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@SpringBootApplication
public class SwaggerprApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerprApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            ObjectMapper objectMapper,
            TodoRepository todoRepository,
            PostRepository postRepository,
            CommentRepository commentRepository) throws IOException {
        return (args ->{
            List<Todo> todos = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/todos"), new TypeReference<List<Todo>>() {});
            todoRepository.saveAll(todos);

            List<Post> posts = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/posts"), new TypeReference<List<Post>>() {});
            postRepository.saveAll(posts);

            List<Comment> comments = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/comments"), new TypeReference<List<Comment>>() {});
            commentRepository.saveAll(comments);

        });
    }

//    @Bean
//    public AuditorAware<Long> auditorProvider(UserSession userSession) {
//        return () -> Optional.of(userSession.requireUserId());
//    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*");
            }
        };
    }


}
