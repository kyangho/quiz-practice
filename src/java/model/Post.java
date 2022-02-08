/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author ducky
 */
public class Post {
    private int id;
    private Blob thumbnail;
    private String title;
    private String content;
    private String category;
    private String author;
    private Date dateCreated;
    private Date dateModified;
    private boolean featuring;
    private String status;
    private ArrayList<PostFile> postFiles;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Blob getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Blob thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public boolean isFeaturing() {
        return featuring;
    }

    public void setFeaturing(boolean featuring) {
        this.featuring = featuring;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<PostFile> getPostFiles() {
        return postFiles;
    }

    public void setPostFiles(ArrayList<PostFile> postFiles) {
        this.postFiles = postFiles;
    }
    
    
}
