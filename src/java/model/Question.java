/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author conmu
 */
public class Question {
    private int id;
    private String content;
    private String correctAnswer;
    private ArrayList<Answer> answers = new ArrayList<>();
    
    public  Question(){
        
    }

    public Question(int id, String content, String correctAnswer) {
        this.id = id;
        this.content = content;
        this.correctAnswer = correctAnswer;
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
