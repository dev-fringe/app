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
    private String name = "";

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

	public String toString() {
		return "User [id=" + id + ", name=" + name + ", getOffset()=" + getOffset() + ", getSize()=" + getSize()
				+ ", getPage()=" + getPage() + ", getNum()=" + getNum() + ", getFrom()=" + getFrom()
				+ ", getRowcount()=" + getRowcount() + ", isFirstPage()=" + isFirstPage() + ", isLastPage()="
				+ isLastPage() + ", getUrls()=" + getUrls() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + "]";
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
	
    
}
