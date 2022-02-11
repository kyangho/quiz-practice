/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Yankee
 */
public class Subject {

    private int subject_id;
    private String subject_title;
    private String subject_author;
    private String subject_status;

    public Subject() {
    }

    public Subject(int subject_id, String subject_title, String subject_author, String subject_status) {
        this.subject_id = subject_id;
        this.subject_title = subject_title;
        this.subject_author = subject_author;
        this.subject_status = subject_status;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_title() {
        return subject_title;
    }

    public void setSubject_title(String subject_title) {
        this.subject_title = subject_title;
    }

    public String getSubject_author() {
        return subject_author;
    }

    public void setSubject_author(String subject_author) {
        this.subject_author = subject_author;
    }

    public String getSubject_status() {
        return subject_status;
    }

    public void setSubject_status(String subject_status) {
        this.subject_status = subject_status;
    }



}
