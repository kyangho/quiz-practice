/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author conmu
 */
public class Quiz_Account {

    private Account account;
    private Quiz quiz;
    private Date timeJoin;
    private double rate;
    private double duration;

    public Quiz_Account() {
    }

    public Account getAccount() {
        return account;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getTimeJoin() {
        return timeJoin;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public void setTimeJoin(Date timeJoin) {
        this.timeJoin = timeJoin;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
