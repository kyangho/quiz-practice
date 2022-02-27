    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.post;

import java.sql.Blob;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author ducky
 */
public class Post {
    private int id;
    private Blob thumbnail;
    private String title;
    private String brief;
    private String content;
    private String author;
    private Date dateCreated;
    private Date dateModified;
    private boolean isFeature;
    private String status;
    private ArrayList<PostFile> postFiles;
    private ArrayList<PostCategory> categories;
    public Post() {
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
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

    public boolean isIsFeature() {
        return isFeature;
    }

    public void setIsFeature(boolean isFeature) {
        this.isFeature = isFeature;
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

    public ArrayList<PostCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<PostCategory> categories) {
        this.categories = categories;
    }
    
    
}
