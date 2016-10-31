package com.mmashyr.staffmanager.controller;

import com.mmashyr.staffmanager.model.Task;
import com.mmashyr.staffmanager.model.Worker;
import com.mmashyr.staffmanager.services.TaskService;
import com.mmashyr.staffmanager.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark
 */

@Controller
@RequestMapping(value = "/workers")
public class WorkerController {

    public static final String ROOT_WORKER_CONTROLLER_URL = "/workers";
    private WorkerService workerService;
    private TaskService taskService;

    @Autowired
    @Qualifier(value = "workerService")
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }

    @Autowired
    @Qualifier("taskService")
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getAllWorkers(Model model) {
        model.addAttribute("workersList", workerService.getAll());
        return "workerpages/workerslist";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeWorker(@PathVariable("id") long id) {

        if (workerService.getById(id) == null) {
            return "redirect:" + ROOT_WORKER_CONTROLLER_URL;
        }

        workerService.delete(id);

        return "redirect:" + ROOT_WORKER_CONTROLLER_URL;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addWorkerForm(Model model) {
        Worker worker = new Worker();
        model.addAttribute("worker", worker);

        return "workerpages/newworkerform";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addWorker(@Valid @ModelAttribute("worker") Worker worker, BindingResult result) {
        if (result.hasErrors()) {
            return "workerpages/newworkerform";
        }
        workerService.add(worker);

        return "redirect:" + ROOT_WORKER_CONTROLLER_URL;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editWorkerForm(@PathVariable int id, Model model) {
        Worker worker = workerService.getById(id);
        if (worker == null) {
            return "redirect:" + ROOT_WORKER_CONTROLLER_URL;
        }
        model.addAttribute("worker", worker);

        return "workerpages/editworkerform";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editWorker(@PathVariable int id, @Valid @ModelAttribute("worker") Worker worker, BindingResult result) {
        if (result.hasErrors()) {
            return "workerpages/editworkerform";
        }
        worker.setTasks(workerService.getById(id).getTasks());
        workerService.update(worker);

        return "redirect:" + ROOT_WORKER_CONTROLLER_URL;
    }

    @RequestMapping(value = "/{id}/tasks", method = RequestMethod.GET)
    public String getWorker(@PathVariable("id") long id, Model model) {

        Worker worker = workerService.getById(id);
        if (worker == null) {
            return "redirect:" + ROOT_WORKER_CONTROLLER_URL;
        }
        List<Task> currentWorkersTasks = worker.getTasks();
        List<Task> tasksWorkerDoesntHave = new ArrayList<>();

        for (Task task : taskService.getAll()) {
            if (!currentWorkersTasks.contains(task)) {
                tasksWorkerDoesntHave.add(task);
            }
        }
        model.addAttribute("worker", worker);
        model.addAttribute("tasksList", tasksWorkerDoesntHave);
        model.addAttribute("task", new Task());

        return "workerpages/workerinfo";
    }

    @RequestMapping(value = "/{workerId}/tasks/add", method = RequestMethod.POST)
    public String addTaskToWorkerForm(@ModelAttribute("task") Task task, @PathVariable("workerId") long workerId) {

        Worker worker = workerService.getById(workerId);
        if (worker == null) {
            return "redirect:" + ROOT_WORKER_CONTROLLER_URL;
        }
        worker.getTasks().add(taskService.getById(task.getId()));
        workerService.update(worker);

        return "redirect:" + ROOT_WORKER_CONTROLLER_URL + "/" + workerId + "/tasks";
    }

    @RequestMapping(value = "/{workerId}/tasks/{taskId}", method = RequestMethod.DELETE)
    public String removeTaskFromWorker(@PathVariable("workerId") long workerId, @PathVariable("taskId") long taskId) {
        Worker worker = workerService.getById(workerId);
        Task task = taskService.getById(taskId);
        if (worker == null || task == null) {
            return "redirect:" + ROOT_WORKER_CONTROLLER_URL;
        }
        worker.getTasks().remove(task);
        workerService.update(worker);

        return "redirect:" + ROOT_WORKER_CONTROLLER_URL + "/" + workerId + "/tasks";
    }

}
