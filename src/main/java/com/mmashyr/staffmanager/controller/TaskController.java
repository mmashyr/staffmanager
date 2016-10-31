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
import java.util.List;

/**
 * Created by Mark
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {
    public static final String ROOT_TASK_CONTROLLER_URL = "/tasks";
    private TaskService taskService;
    private WorkerService workerService;

    @Autowired
    @Qualifier("taskService")
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    @Qualifier(value = "workerService")
    public void setWorkerService(WorkerService workerService) {
        this.workerService = workerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllTasks(Model model) {
        model.addAttribute("tasksList", taskService.getAll());

        return "taskpages/taskslist";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String removeTask(@PathVariable("id") long id) {

        List<Worker> workers = workerService.getAll();
        Task task = taskService.getById(id);

        if (task == null) {
            return "redirect:" + ROOT_TASK_CONTROLLER_URL;
        }

        for (Worker worker : workers) {
            worker.getTasks().remove(task);
            workerService.update(worker);
        }
        taskService.delete(id);

        return "redirect:" + ROOT_TASK_CONTROLLER_URL;
    }

    @RequestMapping(value = "/{id}/workers", method = RequestMethod.GET)
    public String getTask(@PathVariable("id") long id, Model model) {
        Task task = taskService.getById(id);
        if (task == null) {
            return "redirect:" + ROOT_TASK_CONTROLLER_URL;
        }

        model.addAttribute("task", task);

        return "taskpages/taskinfo";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);

        return "taskpages/newtaskform";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTask(@Valid @ModelAttribute("task") Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "taskpages/newtaskform";
        }
        taskService.add(task);

        return "redirect:" + ROOT_TASK_CONTROLLER_URL;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editWorkerForm(@PathVariable int id, Model model) {
        Task task = taskService.getById(id);
        if (task == null) {
            return "redirect:" + ROOT_TASK_CONTROLLER_URL;
        }
        model.addAttribute("task", task);

        return "taskpages/edittaskform";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editWorker(@PathVariable int id, @Valid @ModelAttribute("task") Task task,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "taskpages/edittaskform";
        }
        task.setWorkers(taskService.getById(id).getWorkers());
        taskService.update(task);

        return "redirect:" + ROOT_TASK_CONTROLLER_URL;
    }

}
