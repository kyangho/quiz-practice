/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Cao tam kien
 */
public class QuizViewModel {

    String quizName;
    String subjectName;
    String subCategoryName;
    int views;
    String dateCreated ;

    public QuizViewModel(String quizName, String subjectName, String subCategoryName, int views, String dateCreated) {
        this.quizName = quizName;
        this.subjectName = subjectName;
        this.subCategoryName = subCategoryName;
        this.views = views;
        this.dateCreated = dateCreated;
    }
    
    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
   



   
    
}
