package com.mmashyr.staffmanager.controller.rest;

import com.mmashyr.staffmanager.model.Task;
import com.mmashyr.staffmanager.model.Worker;
import com.mmashyr.staffmanager.services.TaskService;
import com.mmashyr.staffmanager.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mark on 25.11.2016.
 */
@RestController
@RequestMapping(value = "/rest")
public class RestWorkerController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/workers", method = RequestMethod.GET)
    private ResponseEntity<List<Worker>> getAllWorkers() {
        List<Worker> workers = workerService.getAll();
        if( workers == null || workers.isEmpty()){
            return new ResponseEntity<List<Worker>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Worker>>(workers, HttpStatus.OK);
    }

    @RequestMapping(value = "/workers/{id}", method = RequestMethod.GET)
    private ResponseEntity<Worker> getWorkerById(@PathVariable("id") int id){
        Worker worker = workerService.getById(id);
        if(worker == null){
            return new ResponseEntity<Worker>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Worker>(worker, HttpStatus.OK);
    }
}
