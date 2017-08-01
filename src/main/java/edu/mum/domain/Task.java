/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "task", schema = "ea_project")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskid;

    @ManyToOne(optional = false)
    @JoinColumn(name = "projectid")
    private Project project;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startdate;

    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date enddate;

    @Column(name = "taskname")
    private String taskname;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private Set<Resource> resource = new HashSet<>();

    public Integer getTaskid() {
        return taskid;
    }

    public void addResource(Resource resource){
        this.resource.add(resource);
    }
    
    public void addResources(List<Resource> resources){
        this.resource.addAll(resources);
    }
    
    public Task(String taskname, Project project, Status status, Date startdate, Date enddate) {
        this.project = project;
        this.taskname = taskname;
        this.status = status;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public Task() {
    }

    public String getTaskname() {
        return taskname;
    }

    public Project getProject() {
        return project;
    }

    public Status getStatus() {
        return status;
    }

    public Date getStartdate() {
        return (Date) startdate.clone();
    }

    public Date getEnddate() {
        return (Date) enddate.clone();
    }

    public Set<Resource> getResource() {
        return Collections.unmodifiableSet(resource);
    }

    @Override
    public String toString() {
        return "Task{" + "taskid=" + taskid + ", status=" + status + ", startdate=" + startdate + ", enddate=" + enddate + ", taskname=" + taskname + '}';
    }
}
