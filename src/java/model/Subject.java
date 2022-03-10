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
    private Account subject_Author;
    private String subject_status;

    public Subject() {
    }

    public Subject(String subject_title, String subject_status) {
        this.subject_title = subject_title;
        this.subject_status = subject_status;
    }

    public Subject(int subject_id, String subject_title, String subject_status) {
        this.subject_id = subject_id;
        this.subject_title = subject_title;
        this.subject_status = subject_status;
    }
    
     public Subject(int subject_id, String subject_title) {
        this.subject_id = subject_id;
        this.subject_title = subject_title;
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

    public Account getSubject_Author() {
        return subject_Author;
    }

    public void setSubject_Author(Account subject_Author) {
        this.subject_Author = subject_Author;
    }

    public String getSubject_status() {
        return subject_status;
    }

    public void setSubject_status(String subject_status) {
        this.subject_status = subject_status;
    }

}
