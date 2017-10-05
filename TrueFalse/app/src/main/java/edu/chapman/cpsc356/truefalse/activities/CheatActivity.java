package edu.chapman.cpsc356.truefalse.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import edu.chapman.cpsc356.truefalse.R;

public class CheatActivity extends AppCompatActivity
{
    public static final int REQUEST_CHEATED = 1000;
    public static final String EXTRA_ANSWER = "extra_answer";
    private static final String LOGTAG = "CheatActivity";

    private TextView cheatTextView;
    private boolean answer;
    private boolean cheated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        this.cheatTextView = (TextView) findViewById(R.id.tv_cheat_answer);

        Intent startingIntent = getIntent();
        this.answer = startingIntent.getBooleanExtra(EXTRA_ANSWER, false);

        Log.d(LOGTAG, "Received cheat answer " + answer);

        Log.d(LOGTAG, "onCreate()");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Activity back arrow not handled with onBackPressed() ... hack it to do so
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
            return true; // Return true because we handled the event
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if (this.cheated)
        {
            setResult(RESULT_OK);
        }
        else
        {
            setResult(RESULT_CANCELED);
        }

        super.onBackPressed();
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

    public void onClickNo(View view)
    {
        finish();
    }

    public void onClickYes(View view)
    {
        this.cheated = true;

        View noButton = findViewById(R.id.btn_no);
        noButton.setVisibility(View.GONE);

        this.cheatTextView.setVisibility(View.VISIBLE);

        //noinspection PointlessBooleanExpression
        if (this.answer == true)
        {
            this.cheatTextView.setText(R.string.true_display);
        }
        else
        {
            this.cheatTextView.setText(R.string.false_display);
        }

    }
}
