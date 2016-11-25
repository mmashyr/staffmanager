package com.mmashyr.staffmanager.model;



import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark
 */

@Entity
@Table(name = "workers")
public class Worker extends BaseModel {

    @Column(name = "first_name")
    @NotNull
    @Size(min = 2, max = 15, message = "First name must be between {min} and {max}")

    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Size(min = 2, max = 15, message = "Second name must be between {min} and {max}")
    private String lastName;


    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH},
            fetch = FetchType.EAGER)
    @JoinTable(name = "worker_task",
            joinColumns = {@JoinColumn(name = "worker_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")})
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Task> tasks = new ArrayList<>();

    public Worker() {
    }

    public Worker(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    @JsonProperty(value = "taskIds")
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return tasks.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (firstName != null ? !firstName.equals(worker.firstName) : worker.firstName != null) return false;
        if (lastName != null ? !lastName.equals(worker.lastName) : worker.lastName != null) return false;
        return tasks != null ? tasks.equals(worker.tasks) : worker.tasks == null;

    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        return result;
    }
}