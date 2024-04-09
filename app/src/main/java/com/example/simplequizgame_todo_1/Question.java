package com.example.simplequizgame_todo_1;

import java.util.ArrayList;

public class Question {
    private String question;
    private String[] choices;
    private int answer;
    static final ArrayList<Question> questionList = new ArrayList<>(5);

    public Question () {}

    public Question(String question, String[] choices, int answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

} // end class
