/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Cao tam kien
 */
public class QuestionViewModel {
    String questionName;
    String subjectName;
    String subjectCategoryName;
    int level ;

    public QuestionViewModel(String questionName, String subjectName, String subjectCategoryName, int level) {
        this.questionName = questionName;
        this.subjectName = subjectName;
        this.subjectCategoryName = subjectCategoryName;
        this.level = level;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCategoryName() {
        return subjectCategoryName;
    }

    public void setSubjectCategoryName(String subjectCategoryName) {
        this.subjectCategoryName = subjectCategoryName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    
    
}
