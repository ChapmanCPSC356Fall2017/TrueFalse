package edu.chapman.cpsc356.truefalse.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.chapman.cpsc356.truefalse.R;
import edu.chapman.cpsc356.truefalse.models.QuestionModel;

public class MainActivity extends AppCompatActivity
{
    private final String LOGTAG = "MainActivity";
    private final String PREV_INDEX = "previous_index";

    private List<QuestionModel> questions = new ArrayList<>();

    private int index = 0;
    private QuestionModel currentQuestion;

    private TextView questionTextView;
    private TextView resultTextView;

    private boolean currentQuestionCheated = false;

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

        if (savedInstanceState != null)
        {
            this.index = savedInstanceState.getInt(PREV_INDEX);
        }

        showCurrentQuestion();

        Log.d(LOGTAG, "onCreate()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        // Save current index to bundle
        outState.putInt(PREV_INDEX, this.index - 1);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        Log.d(LOGTAG, "onStart()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        Log.d(LOGTAG, "onResume()");

    }

    @Override
    protected void onPause()
    {
        super.onPause();

        Log.d(LOGTAG, "onPause()");

    }

    @Override
    protected void onStop()
    {
        super.onStop();

        Log.d(LOGTAG, "onStop()");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();

        Log.d(LOGTAG, "onRestart()");

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        Log.d(LOGTAG, "onDestroy()");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CheatActivity.REQUEST_CHEATED)
        {
            if (resultCode == RESULT_OK)
            {
                Log.i(LOGTAG, "Cheated");
                this.currentQuestionCheated = true;
            }
            else
            {
                Log.i(LOGTAG, "DID NOT Cheat");
            }
        }
    }

    public void onClickTrue(View view)
    {
        //noinspection PointlessBooleanExpression
        if (this.currentQuestion.getAnswer() == true)
        {
            showCorrect();
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
            showCorrect();
        }
        else
        {
            this.resultTextView.setText(R.string.incorrect_display);
        }

        this.resultTextView.setVisibility(View.VISIBLE);
    }

    private void showCorrect()
    {
        this.resultTextView.setText(R.string.correct_display);

        if (this.currentQuestionCheated)
        {
            Toast.makeText(this, "Yeah ... but you cheated", Toast.LENGTH_SHORT).show();
        }
    }

    private void showCurrentQuestion()
    {
        this.currentQuestionCheated = false;

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

    public void onClickCheat(View view)
    {
        new AlertDialog.Builder(this)
                .setTitle(R.string.are_you_sure)
                .setMessage(R.string.are_you_sure_msg)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent = new Intent(MainActivity.this, CheatActivity.class)
                                .putExtra(CheatActivity.EXTRA_ANSWER, currentQuestion.getAnswer());

                        startActivityForResult(intent, CheatActivity.REQUEST_CHEATED);
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}
