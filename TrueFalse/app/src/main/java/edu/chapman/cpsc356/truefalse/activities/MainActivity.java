package edu.chapman.cpsc356.truefalse.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.chapman.cpsc356.truefalse.R;
import edu.chapman.cpsc356.truefalse.models.QuestionModel;

public class MainActivity extends AppCompatActivity
{
    private QuestionModel question;

    private TextView questionTextView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.questionTextView = (TextView) findViewById(R.id.tv_question);
        this.resultTextView = (TextView) findViewById(R.id.tv_result);

        this.question = new QuestionModel("The live band outside is done", false);

        this.questionTextView.setText(this.question.getText());
    }

    public void onClickTrue(View view)
    {
        //noinspection PointlessBooleanExpression
        if (this.question.getAnswer() == true)
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
        if (this.question.getAnswer() == false)
        {
            this.resultTextView.setText(R.string.correct_display);
        }
        else
        {
            this.resultTextView.setText(R.string.incorrect_display);
        }

        this.resultTextView.setVisibility(View.VISIBLE);
    }
}
