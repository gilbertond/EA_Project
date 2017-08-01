/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author gilberto
 */
@Entity 
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer resourceid;

    @Column(name = "resourcename")
    private String resourcename;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task")
    private Task task;

    public Resource(Task task, String resourcename, String description) {
        this.task = task;
        this.resourcename = resourcename;
        this.description = description;
    }

    public Resource() {
    }

    public Task getTask() {
        return task;
    }

    public Integer getResourceid() {
        return resourceid;
    }

    public String getResourcename() {
        return resourcename;
    }

    public String getDescription() {
        return description;
    }
}
