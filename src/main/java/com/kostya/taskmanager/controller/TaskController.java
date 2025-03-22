package com.kostya.taskmanager.controller;


import com.kostya.taskmanager.dto.taskdto.TaskPatchDto;
import com.kostya.taskmanager.dto.taskdto.TaskRequestDto;
import com.kostya.taskmanager.dto.taskdto.TaskResponseDto;
import com.kostya.taskmanager.dto.taskdto.TaskUpdateDto;
import com.kostya.taskmanager.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/tasks")
public class TaskController {

    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto created = taskService.createTask(taskRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public void updateTask(@PathVariable Long id, @RequestBody TaskUpdateDto taskUpdateDto) {
        taskService.updateTask(id, taskUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }


    @PatchMapping("/{id}")
    public void patchTask(@PathVariable Long id, @RequestBody TaskPatchDto taskPatchDto) {
        taskService.patchTask(id, taskPatchDto);
    }


}
