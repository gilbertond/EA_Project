/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.domain;

import java.util.Collections;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

/**
 *
 * @author gilberto
 */
@Entity
public class Volunteer extends Resource{
    @Column(name = "skill")
    @ElementCollection
    private List<String> skills; 
    
    @Column(name = "voluteernumber")
    private int volunteernumber;
    
    public List<String> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    public int getVolunteernumber() {
        return volunteernumber;
    }
    
    public void addSkill(String skill){
        skills.add(skill);
    }
    
    public void removeSkill(String skill){
        skills.remove(skill);
    }
    
    public void offerService(Task task){
        task.getResource().add(this);
    }

    public Volunteer(Task task, List<String> skills, int volunteernumber, String resourcename, String description) {
        super(task, resourcename, description);
        this.skills = skills;
        this.volunteernumber = volunteernumber;
    }

    public Volunteer() {
        super();
    }
}
