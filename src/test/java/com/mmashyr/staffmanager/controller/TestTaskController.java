package com.mmashyr.staffmanager.controller;

import com.mmashyr.staffmanager.Config.TestAppConfig;
import com.mmashyr.staffmanager.model.Task;
import com.mmashyr.staffmanager.model.Worker;
import com.mmashyr.staffmanager.services.impl.TaskServiceImpl;
import com.mmashyr.staffmanager.services.impl.WorkerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Mark on 31.10.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestAppConfig.class)
public class TestTaskController {

    public static final String ROOT_TASK_CONTROLLER_URL = "/tasks";

    GlobalControllerExceptionHandler exceptionHandler = new GlobalControllerExceptionHandler();
    @InjectMocks
    TaskController controller;
    @Mock
    WorkerServiceImpl workerService;
    @Mock
    TaskServiceImpl taskService;
    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Before
    public void initializeWorkerServiceMock() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(exceptionHandler).build();
    }

    @Test
    public void getAllTasksShouldAddToModelAllTasksFromServiceAndForwardToTasksListPage() throws Exception {
        when(taskService.getAll()).thenReturn(Collections.EMPTY_LIST);

        this.mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("taskpages/taskslist"))
                .andExpect(model().attribute("tasksList", taskService.getAll()));
    }

    @Test
    public void deletingTaskShouldDeleteItOnAllWorkers() throws Exception {
        Worker worker1 = new Worker("firstName","lastName");
        Worker worker2 = new Worker("firstName2","lastName2");
        Task task1 = new Task("title", "description");
        Task task2 = new Task("title2", "description2");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        worker1.setTasks(tasks);
        worker2.setTasks(tasks);
        List<Worker> workers = Arrays.asList(worker1, worker2);

        when(taskService.getById(1)).thenReturn(task1);
        when(workerService.getAll()).thenReturn(workers);

        this.mockMvc.perform(delete("/tasks/1"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(ROOT_TASK_CONTROLLER_URL));

        assertThat(worker1.getTasks(),is(equalTo(Arrays.asList(task2))));
        assertThat(worker2.getTasks(),is(equalTo(Arrays.asList(task2))));
        verify(workerService, times(1)).update(worker1);
        verify(workerService, times(1)).update(worker2);

    }

    @Test
    public void whenInvokingAddNewTaskByGetMethodShouldAddNewTaskToModelAndReturnFormView() throws Exception {
        Task task = new Task();

        mockMvc.perform(get("/tasks/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("taskpages/newtaskform"))
                .andExpect(model().attribute("task", task));
    }

    @Test
    public void whenSubmittingValidTaskByPostMethodShouldAddItOnServiceLayerAndRedirectToTheRootController() throws Exception {
        Task task = new Task("title", "description");
        task.setDeadline(new Date(2016, 12, 31));

        mockMvc.perform(post("/tasks/add")
                .param("title", "title")
                .param("description", "description")
                .param("deadline", "2016-12-31"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(ROOT_TASK_CONTROLLER_URL));

        verify(taskService, times(1)).add(task);
    }

    @Test
    public void whenSubmittingInvalidTaskByPostMethodShouldRedirectToTheFormWithErrorAttrs() throws Exception {
        mockMvc.perform(post("/tasks/add")
                .param("title", "title")
                .param("description", "description")
                .param("deadline", "2016/12/31"))
                .andExpect(view().name("taskpages/newtaskform"))
                .andExpect(model().hasErrors());

        verifyNoMoreInteractions(taskService);
    }

    @Test
    public void whenSubmittingTaskWithDateNotInFutureByPostMethodShouldRedirectToTheFormWithErrorAttrs() throws Exception {
        mockMvc.perform(post("/tasks/add")
                .param("title", "title")
                .param("description", "description")
                .param("deadline", "2015-12-31"))
                .andExpect(view().name("taskpages/newtaskform"))
                .andExpect(model().hasErrors());

        verifyNoMoreInteractions(taskService);
    }

    @Test
    public void whenTaskWithGivenIdExistsShouldAddThisTaskToTheModel() throws Exception {
        Task task = mock(Task.class);

        when(taskService.getById(1)).thenReturn(task);

        this.mockMvc.perform(get("/tasks/1/workers"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("task", task))
                .andExpect(view().name("taskpages/taskinfo"));
    }

    @Test
    public void whenTaskWithGivenIdDoesNotExistsShouldRedirectToTheRootController() throws Exception {
        when(taskService.getById(1)).thenReturn(null);

        this.mockMvc.perform(get("/tasks/1/workers"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(ROOT_TASK_CONTROLLER_URL));
    }

    @Test
    public void whenIdInPathCannotBeParsedShouldRedirectToErrorPage() throws Exception {
        this.mockMvc.perform(get("/tasks/notInt/workers"))
                .andExpect(forwardedUrl("error"));
    }

}
