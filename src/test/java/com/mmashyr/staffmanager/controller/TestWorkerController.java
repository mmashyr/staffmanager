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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Mark
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestAppConfig.class)
public class TestWorkerController {

    public static final String ROOT_WORKER_CONTROLLER_URL = "/workers";

    GlobalControllerExceptionHandler exceptionHandler = new GlobalControllerExceptionHandler();
    @InjectMocks
    WorkerController controller;
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
    public void whenInvokingAddNewWorkerByGetMethodShouldAddNewWorkerToModelAndReturnFormView() throws Exception {
        Worker worker = new Worker();

        mockMvc.perform(get("/workers/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("workerpages/newworkerform"))
                .andExpect(model().attribute("worker", worker));

    }

    @Test
    public void whenSubmittingValidWorkerByPostMethodShouldAddItOnServiceLayerAndRedirectToTheRootController() throws Exception {

        mockMvc.perform(post("/workers/add")
                .param("firstName", "firstName")
                .param("lastName", "lastName"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(ROOT_WORKER_CONTROLLER_URL));

        verify(workerService, times(1)).add(new Worker("firstName", "lastName"));

    }

    @Test
    public void whenInvokingInvalidWorkerByPostMethodShouldBeRedirectedToFormWithErrorParameters() throws Exception {

        mockMvc.perform(post("/workers/add")
                .param("firstName", "1")
                .param("lastName", "lastName"))
                .andExpect(view().name("workerpages/newworkerform"))
                .andExpect(model().attributeHasFieldErrors("worker", "firstName"));

        verifyNoMoreInteractions(workerService);

    }

    @Test
    public void getAllWorkersShouldAddToModelAllWorkersFromServiceAndForwardToWorkersListPage() throws Exception {
        when(workerService.getAll()).thenReturn(Collections.EMPTY_LIST);

        this.mockMvc.perform(get("/workers"))
                .andExpect(status().isOk())
                .andExpect(view().name("workerpages/workerslist"))
                .andExpect(model().attribute("workersList", workerService.getAll()));

    }

    @Test
    public void whenWorkerWithGivenIdExistsShouldAddThisWorkerAndTasksHeDoesntHaveToTheModel() throws Exception {
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);
        Task task3 = mock(Task.class);
        Worker worker = mock(Worker.class);

        when(taskService.getAll()).thenReturn(Arrays.asList(task1, task2, task3));
        when(worker.getTasks()).thenReturn(Arrays.asList(task1));
        when(workerService.getById(1)).thenReturn(worker);

        List<Task> tasksWorkerDoesntHave = Arrays.asList(task2, task3);

        this.mockMvc.perform(get("/workers/1/tasks"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("worker", worker))
                .andExpect(model().attribute("tasksList", tasksWorkerDoesntHave))
                .andExpect(view().name("workerpages/workerinfo"));
    }

    @Test
    public void whenWorkerWithGivenIdDoesNotExistsShouldRedirectToTheRootController() throws Exception {
        when(workerService.getById(1)).thenReturn(null);

        this.mockMvc.perform(get("/workers/1/tasks"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(ROOT_WORKER_CONTROLLER_URL));
    }

    @Test
    public void whenIdInPathCannotBeParsedShouldRedirectToErrorPage() throws Exception {
        this.mockMvc.perform(get("/workers/notInt/tasks"))
                .andExpect(forwardedUrl("error"));
    }

    @Test
    public void removingTaskFromWorkerShouldDeleteThisTaskAndInvokeUpdateOnTheServiceAndRedirectToTheListOfAllWorkersTasks() throws Exception {
        Worker worker = new Worker();
        Task task = new Task();
        worker.getTasks().add(task);

        when(taskService.getById(1)).thenReturn(task);
        when(workerService.getById(1)).thenReturn(worker);

        this.mockMvc.perform(delete("/workers/1/tasks/1"))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(ROOT_WORKER_CONTROLLER_URL + "/1/tasks"));

        worker.getTasks().remove(task);
        verify(workerService, times(1)).update(worker);
    }

    @Test
    public void deleteUserShouldInvokeDeleteUserOnServiceAndRedirectToTheRootController() throws Exception {
        when(workerService.getById(1)).thenReturn(mock(Worker.class));

        this.mockMvc.perform(delete("/workers/1"))
                .andExpect(redirectedUrl(ROOT_WORKER_CONTROLLER_URL));

        verify(workerService, times(1)).delete(1);
    }

    @Test
    public void editWorkerShouldAddThisWorkerToTheModelAndReturnEditWorkerForm() throws Exception {
        Worker worker = new Worker("firstName", "lastName");
        when(workerService.getById(1)).thenReturn(worker);

        this.mockMvc.perform(get("/workers/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("workerpages/editworkerform"))
                .andExpect(model().attribute("worker", worker));

        verify(workerService, times(1)).getById(1);
    }

    @Test
    public void validWorkerFromEditWorkerFormShouldBeUpdatedOnServiceAndRedirectedToTheRootController() throws Exception {
        Worker worker = mock(Worker.class);
        Worker updatedWorker = new Worker("editedFirstName", "editedLastName");
        List<Task> newTasks = Arrays.asList(new Task("Hello", "Hello"));
        updatedWorker.setTasks(newTasks);

        when(worker.getFirstName()).thenReturn("firstName");
        when(worker.getLastName()).thenReturn("lastName");
        when(workerService.getById(1)).thenReturn(worker);
        when(workerService.getById(1).getTasks()).thenReturn(newTasks);

        this.mockMvc.perform(post("/workers/edit/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", "editedFirstName")
                .param("lastName", "editedLastName"))
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl(ROOT_WORKER_CONTROLLER_URL));

        verify(workerService, times(1)).update(updatedWorker);

    }

    @Test
    public void WhenWorkerFromEditWorkerFormHasErrorsShouldBeRedirectedToFormWithErrorParameters() throws Exception {
        Worker worker1 = new Worker("q", "lkihj");
        when(workerService.getById(1)).thenReturn(worker1);

        this.mockMvc.perform(post("/workers/edit/1").
                contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("lastName", worker1.getLastName())
                .param("firstName", worker1.getFirstName()))
                .andExpect(view().name("workerpages/editworkerform"))
                .andExpect(model().attributeHasFieldErrors("worker", "firstName"));

        verifyNoMoreInteractions(workerService);
    }


}
