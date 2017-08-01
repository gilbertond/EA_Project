/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author gilberto
 */
@Entity 
@Table(name = "beneficiary", schema = "ea_project")
public class Beneficiary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer beneficiaryid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    @Embedded
    private Location location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project")
    private Project project;

    public Integer getBeneficiaryid() {
        return beneficiaryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Project getProject() {
        return project;
    }

    public Beneficiary(Project project, String name, String description, Location location) {
        this.project = project;
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public Beneficiary() {
    }

    @Override
    public String toString() {
        return "Beneficiary{" + "beneficiaryid=" + beneficiaryid + ", name=" + name + ", description=" + description + ", location=" + location + '}';
    }
}
