package edu.chapman.cpsc356.truefalse.models;

public class QuestionModel
{
    private String text;
    private boolean answer;

    public QuestionModel(String text, boolean answer)
    {
        this.text = text;
        this.answer = answer;
    }

    public String getText()
    {
        return this.text;
    }

    public boolean getAnswer()
    {
        return this.answer;
    }
}
