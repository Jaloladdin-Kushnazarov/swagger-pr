package org.example.swaggerpr.criteria;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ParameterObject
public class TodoCriteria {
    private String title;
    @Parameter(required = true)
    private Type type;
    private boolean completed;
    @Parameter(required = true, example = "2020-02-28 13:00:39")
    private LocalDateTime created;
    @Parameter(required = true, example = "5")
    @Min(4)
    private Integer userId;


    public enum Type {
        EDUCATION, IT,SPORT
    }

}
