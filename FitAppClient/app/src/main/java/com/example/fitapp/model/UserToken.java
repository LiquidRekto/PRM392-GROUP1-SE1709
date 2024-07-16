package com.example.fitapp.model;

public class UserToken {
    private String id;
    private String name;
    private String roleName;

    public UserToken() {
    }

    public UserToken(String id, String name, String roleName) {
        this.id = id;
        this.name = name;
        this.roleName = roleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
