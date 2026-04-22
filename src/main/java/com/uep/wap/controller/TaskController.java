package com.uep.wap.controller;

import com.uep.wap.dto.TaskDTO;
import com.uep.wap.model.Task;
import com.uep.wap.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "/tasks")
    public Iterable<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping(path = "/tasks")
    public String addTask(@RequestBody TaskDTO taskDTO) {
        taskService.addTask(taskDTO);
        return "Task added";
    }
}