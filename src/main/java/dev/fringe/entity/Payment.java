package dev.fringe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    
}
