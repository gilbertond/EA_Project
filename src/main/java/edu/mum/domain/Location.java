/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * 
 * @author gilberto
 */
@Embeddable
public class Location implements Serializable {

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "street")
    private String street;

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public Location(String city, String state, String street) {
        this.city = city;
        this.state = state;
        this.street = street;
    }

    public Location() {
    }

    @Override
    public String toString() {
        return "Location{" + "city=" + city + ", state=" + state + ", street=" + street + '}';
    }        
}
