package com.example.taskmanager.spec;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.TaskStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {

    public static Specification<Task> withFilter(String search, TaskStatus status, String userEmail) {
        return (root, query, cb) -> {
            Predicate predicate =  cb.conjunction();

            if (userEmail != null) {
                predicate = cb.and(predicate, cb.equal(root.get("user").get("email"), userEmail));
            }

            if (search != null && !search.isBlank()) {
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("title")), "%" + search.toLowerCase() + "%"));
            }

            if (status != null) {
                predicate = cb.and(predicate, cb.equal(root.get("status"), status));
            }

            return predicate;
        };
    }
}
