package com.mycompany.demoapp;

public class GameItem {
    private String task;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String rightAnswer;

    public GameItem() {
        this.task = "0";
        this.answer1 = "0";
        this.answer2 = "0";
        this.answer3 = "0";
        this.answer4 = "0";
        this.rightAnswer = "0";
    }

    public GameItem(String input) {
        getNewTask(input);
    }

    public GameItem(String task, String answer1, String answer2, String answer3,
                    String answer4, String rightAnswer) {
        this.createGameItem(task, answer1, answer2, answer3, answer4, rightAnswer);
    }

    public void getNewTask(String input) {
        //split the string that looks like
        //100+2#102#103#100#120#102
        if (input.contains("#")) {
            String[] items = input.split("#");
            this.task = items[0];
            this.answer1 = items[1];
            this.answer2 = items[2];
            this.answer3 = items[3];
            this.answer4 = items[4];
            this.rightAnswer = items[5];
        }
        else {
            throw new IllegalArgumentException("String " + input + " does not contain #");
        }
    }

    public String getTask() {
        return task;
    }
    public String getAnswer1() {
        return answer1;
    }
    public String getAnswer2() {
        return answer2;
    }
    public String getAnswer3() {
        return answer3;
    }
    public String getAnswer4() {
        return answer4;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setTask(String task) {
        this.task = task;
    }
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public void createGameItem(String task, String answer1, String answer2, String answer3,
                               String answer4, String rightAnswer) {
        this.setTask(task);
        this.setAnswer1(answer1);
        this.setAnswer2(answer2);
        this.setAnswer3(answer3);
        this.setAnswer4(answer4);
        this.setRightAnswer(rightAnswer);
    }
}
