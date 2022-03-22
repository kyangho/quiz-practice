/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.post;

import java.sql.Blob;

/**
 *
 * @author ducky
 */
public class PostFile {
    private int id;
    private String name;
    private String type;
    private Blob fileBlob;

    public PostFile() {
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

    public Blob getFileBlob() {
        return fileBlob;
    }

    public void setFileBlob(Blob fileBlob) {
        this.fileBlob = fileBlob;
    }
    
    
}
