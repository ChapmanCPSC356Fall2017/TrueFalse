package edu.chapman.cpsc356.truefalse.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.chapman.cpsc356.truefalse.R;
import edu.chapman.cpsc356.truefalse.models.QuestionModel;

public class MainActivity extends AppCompatActivity
{
    private List<QuestionModel> questions = new ArrayList<>();

    private int index = 0;
    private QuestionModel currentQuestion;

    private TextView questionTextView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.questionTextView = (TextView) findViewById(R.id.tv_question);
        this.resultTextView = (TextView) findViewById(R.id.tv_result);

        this.questions.add(new QuestionModel("The live band outside is done", false));
        this.questions.add(new QuestionModel("The sky is blue", true));
        this.questions.add(new QuestionModel("There are 27 people in this class", false));
        this.questions.add(new QuestionModel("We're at Chapman University", true));

        showCurrentQuestion();
    }

    public void onClickTrue(View view)
    {
        //noinspection PointlessBooleanExpression
        if (this.currentQuestion.getAnswer() == true)
        {
            this.resultTextView.setText(R.string.correct_display);
        }
        else
        {
            this.resultTextView.setText(R.string.incorrect_display);
        }

        this.resultTextView.setVisibility(View.VISIBLE);
    }

    public void onClickFalse(View view)
    {
        //noinspection PointlessBooleanExpression
        if (this.currentQuestion.getAnswer() == false)
        {
            this.resultTextView.setText(R.string.correct_display);
        }
        else
        {
            this.resultTextView.setText(R.string.incorrect_display);
        }

        this.resultTextView.setVisibility(View.VISIBLE);
    }

    private void showCurrentQuestion()
    {
        // Start over at the beginning if past the end
        if (this.index >= this.questions.size())
        {
            this.index = 0;
        }

        this.currentQuestion = this.questions.get(this.index);
        this.questionTextView.setText(this.currentQuestion.getText());

        this.index++;
    }

    public void onClickNext(View view)
    {
        showCurrentQuestion();
        this.resultTextView.setVisibility(View.GONE);
    }
}
