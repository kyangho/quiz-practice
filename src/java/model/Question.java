/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Blob;
import java.util.ArrayList;

/**
 *
 * @author conmu
 */
public class Question {
    private int id;
    private String content;
    private String correctAnswer;
    private Subject subject;
    private Category category;
    private Subcategory subCategory;
    private String level;
    private String status;
    private Blob media;
    private String mediaName;
    private ArrayList<Answer> answers = new ArrayList<>();
    
    public  Question(){
        
    }

    public Question(int id, String content, String correctAnswer) {
        this.id = id;
        this.content = content;
        this.correctAnswer = correctAnswer;
    }

    public Question(int id, String content, String correctAnswer, Subject subject, Category category, Subcategory subCategory) {
        this.id = id;
        this.content = content;
        this.correctAnswer = correctAnswer;
        this.subject = subject;
        this.category = category;
        this.subCategory = subCategory;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }
    
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Blob getMedia() {
        return media;
    }

    public void setMedia(Blob media) {
        this.media = media;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Subcategory subCategory) {
        this.subCategory = subCategory;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
    

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", content=" + content + ", correctAnswer=" + correctAnswer + "}";
    }

    public void display() {
        System.out.print("Question{" + "id=" + id + ", content=" + content + ", correctAnswer=" + correctAnswer + ", ");
        for (Answer answer : answers) {
            System.out.print(answer.toString());
        }
        System.out.println();
    }
}
