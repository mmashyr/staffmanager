package com.mmashyr.staffmanager.controller.rest;

import com.mmashyr.staffmanager.model.Task;
import com.mmashyr.staffmanager.model.Worker;
import com.mmashyr.staffmanager.services.TaskService;
import com.mmashyr.staffmanager.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    private ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAll();
        if (tasks == null || tasks.isEmpty()) {
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    private ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
        Task task = taskService.getById(id);
        if (task == null) {
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    private void createTask(@Valid @RequestBody Task task){
        taskService.add(task);
    }

    @RequestMapping(value = "/tasks/{taskId}/tasks/{workerId}", method = RequestMethod.PUT)
    private ResponseEntity<Void> assignWorkerToTask(@PathVariable("taskId") long taskId,
                                                 @PathVariable("workerId") long workerId) {

        Task task = taskService.getById(taskId);
        Worker worker = workerService.getById(workerId);
        if (worker == null || task == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        task.getWorkers().add(worker);
        taskService.update(task);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}


