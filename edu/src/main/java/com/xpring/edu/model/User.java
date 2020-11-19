package com.xpring.edu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;


@Entity
@Table(name="user")
public class User {

    @Id
    private String username;
    private String email;
    private String role;
    private String password;   
    @ColumnDefault("false")
    private Boolean enabled;

    
	public String getUsername() {
		return username;
	}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public User() {

    }

    public User(String username, String email, String role, String password, Boolean enabled) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.password = password;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User [email=" + email + ", enabled=" + enabled + ", password=" + password + ", role=" + role
                + ", username=" + username + "]";
    }

    
    
}
