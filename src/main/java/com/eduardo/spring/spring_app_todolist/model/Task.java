package com.eduardo.spring.spring_app_todolist.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    private Boolean isCompleted = false;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    // Create TimeStamp
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}

