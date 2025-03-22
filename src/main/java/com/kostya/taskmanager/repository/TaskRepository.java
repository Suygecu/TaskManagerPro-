package com.kostya.taskmanager.repository;

import com.kostya.taskmanager.enums.TaskPriority;
import com.kostya.taskmanager.enums.TaskStatus;
import com.kostya.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;



public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssigneeId(Long userId);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByPriority(TaskPriority priority);
    List<Task> findByAssigneeIdAndStatus(Long userId, TaskStatus status);
    List<Task> findByDueDateBefore(LocalDate date);
    List<Task> findByCreatedDateBetween(LocalDate start, LocalDate end);
}
