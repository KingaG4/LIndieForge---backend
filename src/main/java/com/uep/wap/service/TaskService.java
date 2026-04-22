package com.uep.wap.service;

import com.uep.wap.model.Task;
import com.uep.wap.repository.TaskRepository;
import com.uep.wap.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setType(taskDTO.getType());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        task.setDeadline(taskDTO.getDeadline());
        taskRepository.save(task);
        System.out.println("Task added");
    }

    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}