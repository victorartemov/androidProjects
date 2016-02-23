package com.mycompany.demoapp;

public class Game {
    private String task;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String rightAnswer;

    public Game()
    {
        this.task = "0";
        this.answer1 = "0";
        this.answer2 = "0";
        this.answer3 = "0";
        this.answer4 = "0";
        this.rightAnswer = "0";
    }

    public Game(String input)
    {
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

        } else {
            throw new IllegalArgumentException("String " + input + " does not contain #");
        }


    }

    public void getNewTask(String input)
    {
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

        } else {
            throw new IllegalArgumentException("String " + input + " does not contain #");
        }
    }

    public String getTask()
    {
        return task;
    }

    public String getAnswer1()
    {
        return answer1;
    }

    public String getAnswer2()
    {
        return answer2;
    }

    public String getAnswer3()
    {
        return answer3;
    }

    public String getAnswer4()
    {
        return answer4;
    }

    public String getRightAnswer()
    {
        return rightAnswer;
    }

}
