package dev.fringe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import dev.fringe.entity.support.Page;

/**
 * @author dev-frine
 */
@Entity
public class User extends Page{

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String email;
    private String password;
    private String role;

    public User() {
    	super();
    	this.name  = "";
    	this.role = "";
    }

    public User(String name) {
    	this();
        this.name = name;
    }
    public User(String name,String email, String password) {
    	this(name);
    	this.email = email;
    	this.password = password;
    }
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
