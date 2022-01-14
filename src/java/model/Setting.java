/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.*;

/**
 *
 * @author ducky
 */
@XmlRootElement(name = "setting")
@XmlAccessorType (XmlAccessType.FIELD)
public class Setting {
    private int id;
    private String name;
    @XmlElement
    private String type;
    private String description;
    private String value;
    private String status;

    public Setting() {
    }

    public Setting(int id, String name, String type, String description, String value, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.value = value;
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name + ", type=" + type + ", description=" + description + ", value=" + value + ", status=" + status;
    }
    
    
}
