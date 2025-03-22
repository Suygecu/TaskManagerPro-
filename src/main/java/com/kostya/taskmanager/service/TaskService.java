package com.kostya.taskmanager.service;

import com.kostya.taskmanager.dto.taskdto.TaskPatchDto;
import com.kostya.taskmanager.dto.taskdto.TaskRequestDto;
import com.kostya.taskmanager.dto.taskdto.TaskResponseDto;
import com.kostya.taskmanager.dto.taskdto.TaskUpdateDto;
import com.kostya.taskmanager.mapper.TaskMapper;
import com.kostya.taskmanager.model.Task;
import com.kostya.taskmanager.repository.TaskRepository;
import com.kostya.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
    }

    public TaskResponseDto patchTask(Long id, TaskPatchDto taskPatchDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        taskMapper.patchTaskFromDto(taskPatchDto, task);
        Task saved = taskRepository.save(task);

        return mapToResponse(saved);
    }

    private TaskResponseDto mapToResponse(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getAssignee() != null ? task.getAssignee().getId() : null,
                task.getDueDate() != null ? task.getDueDate().toString() : null,
                task.getStartDate() != null ? task.getStartDate().toString() : null,
                task.getEndDate() != null ? task.getEndDate().toString() : null,
                task.getCreatedDate() != null ? task.getCreatedDate().toString() : null,
                task.getUpdatedDate() != null ? task.getUpdatedDate().toString() : null
        );
    }
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Task task = new Task();
        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setStatus(taskRequestDto.getStatus());
        task.setPriority(taskRequestDto.getPriority());
        task.setAssignee(userRepository.findById(taskRequestDto.getAssigneeId())
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
        task.setDueDate(LocalDate.parse(taskRequestDto.getDueDate()));
        task.setStartDate(LocalDate.parse(taskRequestDto.getStartDate()));
        task.setEndDate(LocalDate.parse(taskRequestDto.getEndDate()));
        task.setCreatedDate(LocalDate.now());
        task.setUpdatedDate(LocalDate.now());

        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TaskResponseDto updateTask(Long id, TaskUpdateDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setAssignee(userRepository.findById(dto.getAssigneeId())
                .orElseThrow(() -> new IllegalArgumentException("User not found")));
        task.setDueDate(LocalDate.parse(dto.getDueDate()));
        task.setStartDate(LocalDate.parse(dto.getStartDate()));
        task.setEndDate(LocalDate.parse(dto.getEndDate()));
        task.setUpdatedDate(LocalDate.now());

        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        taskRepository.delete(task);
    }



}
