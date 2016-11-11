package com.mmashyr.staffmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Mark on 11.11.2016.
 */
@Entity
@Table(name = "role")
public class Role extends BaseModel {

    @Column(name = "role", unique = true, nullable = false)
    private String role = Roles.USER.getRole();

    public Role(){
        super();
    }
    public Role(long id){
        super(id);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
