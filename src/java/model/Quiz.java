/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author conmu
 */
public class Quiz {

    private int id;
    private String title;
    private String name;
    private Subject subject;
    private String level;
    private String type;
    private String img;
    private Account author;
    private Category category;
    private Time startTime;
    private Time endTime;
    private String status;
    private double rate;
    ArrayList<Question> questions = new ArrayList<>();
    private boolean hasJoin;

    public Quiz() {
    }

    public Quiz(int id, String title, Subject subject, String level, String type, String img, Account author, Category category, Time startTime, Time endTime, String status, double rate, boolean hasJoin) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.level = level;
        this.type = type;
        this.img = img;
        this.author = author;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.rate = rate;
        this.hasJoin = hasJoin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

    public boolean isHasJoin() {
        return hasJoin;
    }

    public void setHasJoin(boolean hasJoin) {
        this.hasJoin = hasJoin;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
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
        return "Quiz{" + "id=" + id + ", title=" + title + ", subject=" + subject + ", level=" + level + ", type=" + type + ", img=" + img + ", author=" + author + ", startTime=" + startTime + ", endTime=" + endTime + ", rate=" + rate + '}';
    }

    public void display() {
        System.out.print("Quiz{" + "id=" + id + ", title=" + title + ", subject=" + subject + ", level=" + level + ", type=" + type + ", img=" + img + ", author=" + author + ", startTime=" + startTime + ", endTime=" + endTime + ", rate=" + rate + ", ");
        questions.forEach((question) -> {
            System.out.print(question.toString());
        });
        System.out.println();
    }

}
