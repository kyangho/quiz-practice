/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author conmu
 */
public class Quiz {

    private int id;
    private String title;
    private Subject subject;
    private int categoryId;
    private String level;
    private String type;
    private String img;
    private Account author;
    private Date startTime;
    private Date endTime;
    private double rate;
    private String status;
    private boolean hasJoin;
    ArrayList<Question> questions = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(int id, String title, Subject subject, int categoryId, String level,
            String type, String img, Account author, Date startTime, Date endTime, double rate, String status) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.categoryId = categoryId;
        this.level = level;
        this.type = type;
        this.img = img;
        this.author = author;
        this.startTime = startTime;
        this.endTime = endTime;
        this.rate = rate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Quiz{" + "id=" + id + ", title=" + title + ", subject=" + subject 
                + ", categoryId=" + categoryId + ", level=" + level + ", type=" + type 
                + ", img=" + img + ", author=" + author + ", startTime=" + startTime +
                ", endTime=" + endTime + ", rate=" + rate + ", status=" + status + '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isHasJoin() {
        return hasJoin;
    }

    public void setHasJoin(boolean hasJoin) {
        this.hasJoin = hasJoin;
    }
    
    

    public void display() {
        System.out.print("Quiz{" + "id=" + id + ", title=" + title + ", subject=" +
                subject.getSubject_title() + ", categoryId=" + categoryId + ", level=" +
                level + ", type=" + type + ", img=" + img + ", author=" + author.getFullname() 
                + ", startTime=" + startTime + ", endTime=" + endTime + ", rate=" + rate + ", status=" + status + ", ");
        questions.forEach((question) -> {
            question.display();
        });
        System.out.println();
    }

}
