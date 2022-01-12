/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ducky
 */
public class Role {
    private int id;
    private String type;
    private String value;
    private boolean active;
    
    private ArrayList<Function> functions;

    public Role() {
    }

    public Role(int id, String type, String value, boolean status, ArrayList<Function> functions) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.active = status;
        this.functions = functions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(ArrayList<Function> functions) {
        this.functions = functions;
    }
    
    
}
