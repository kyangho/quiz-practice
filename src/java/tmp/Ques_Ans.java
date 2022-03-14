/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tmp;

import java.util.ArrayList;
import model.Answer;
import model.Question;

/**
 *
 * @author Vu Duc Tien
 */
public class Ques_Ans {
    private Question question;
    private String answer;
    public Ques_Ans() {
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
