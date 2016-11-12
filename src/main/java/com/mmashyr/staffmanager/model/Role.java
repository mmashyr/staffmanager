package com.mmashyr.staffmanager.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mark on 11.11.2016.
 */
@Entity
@Table(name = "role")
public class Role extends BaseModel {

    @Enumerated(EnumType.STRING)
    private UserRoleType type;

    public Role() {
        super();
    }

    public Role(long id) {
        super(id);
    }

    public UserRoleType getType() {
        return type;
    }

    public void setRole(UserRoleType role) {
        this.type = type;
    }
}
