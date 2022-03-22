/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classes;

import java.util.ArrayList;
import model.Account;

/**
 *
 * @author Vu Duc Tien
 */
public class Classes {
    private int classID;
    private String className;
    private String status;
    private String note;
    private ArrayList<Account> users;
    private Account author;

    public Classes() {
    }

    public Classes(int classID, String className, String status, String note, ArrayList<Account> users, Account author) {
        this.classID = classID;
        this.className = className;
        this.status = status;
        this.note = note;
        this.users = users;
        this.author = author;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<Account> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Account> users) {
        this.users = users;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }
}
