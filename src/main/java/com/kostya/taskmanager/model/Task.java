package com.kostya.taskmanager.model;



import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private String priority;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User assignee;



    private LocalDate dueDate;
    private LocalDate startDate;
    private LocalDate endDate;

    private LocalDate createdDate;
    private LocalDate updatedDate;



}
