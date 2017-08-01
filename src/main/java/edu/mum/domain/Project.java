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
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author gilberto
 */
@Entity
@Table(name = "project", schema = "ea_project")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectid;

    @Column(name = "location")
    @Embedded
    private Location location;

    @Column(name = "description")
    private String description;

    public Project(Integer projectid) {
        this.projectid = projectid;
    }

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Beneficiary> beneficiaries = new HashSet<>();

    public Integer getProjectid() {
        return projectid;
    }

    public Location getLocation() {
        return location;
    }

    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    public String getDescription() {
        return description;
    }

    public String getProjectname() {
        return projectname;
    }

    public Date getStartDate() {
        return (Date) startDate.clone();
    }

    public Date getEndDate() {
        return (Date) endDate.clone();
    }

    public Set<Beneficiary> getBeneficiaries() {
        return Collections.unmodifiableSet(beneficiaries);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public void setBeneficiaries(Set<Beneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    public Project(String name, String description, Location location, Date startDate, Date endDate) {
        this.projectname = name;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Project() {
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void addTasks(Set<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    public void addBeneficiary(Beneficiary beneficiary) {
        this.beneficiaries.add(beneficiary);
    }

    public void addBeneficiaries(Set<Beneficiary> beneficiarys) {
        this.beneficiaries.addAll(beneficiarys);
    }

    @Override
    public String toString() {
        return "Project{" + "projectid=" + projectid + ", location=" + location + ", description=" + description + ", projectname=" + projectname + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }
}
