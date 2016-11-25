package com.mmashyr.staffmanager.controller.rest;

import com.mmashyr.staffmanager.model.Task;
import com.mmashyr.staffmanager.services.TaskService;
import com.mmashyr.staffmanager.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mark on 25.11.2016.
 */
@RestController
@RequestMapping(value = "/rest")
public class RestTaskController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/tasks")
    private List<Task> getAllTasks() {
        List<Task> tasks = taskService.getAll();

        return tasks;
    }
}


