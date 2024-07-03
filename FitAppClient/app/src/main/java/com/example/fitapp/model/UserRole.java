package com.example.fitapp.model;

public class UserRole {
    public int userRoleId;
    public String roleName;

    public UserRole() {
    }

    public UserRole(int userRoleId, String roleName) {
        this.userRoleId = userRoleId;
        this.roleName = roleName;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
