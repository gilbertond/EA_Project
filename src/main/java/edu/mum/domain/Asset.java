/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author gilberto
 */
@Entity
public class Asset extends Resource {

    @Column(name = "category")
    private String category;

    @Column(name = "assetnumber")
    private int assetnumber;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setAssetnumber(int assetnumber) {
        this.assetnumber = assetnumber;
    }

    public int getAssetnumber() {
        return assetnumber;
    }

    public Asset(Task task, String category, int assetnumber, String resourcename, String description) {
        super(task, resourcename, description);
        this.category = category;
        this.assetnumber = assetnumber;
    } 

    public Asset() {
        super();
    }
}
