package dev.fringe.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import dev.fringe.entity.support.Page;
import lombok.Data;


@Entity
@Data
public class Category extends Page implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CATID")
    private String categoryId;

    private String name;

    @Column(name = "DESCN")
    private String description;
    
    public Category() {
		this.categoryId = "";
		this.name = "";
		this.description = "";
	}

}
