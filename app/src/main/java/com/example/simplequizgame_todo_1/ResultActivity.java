package com.example.simplequizgame_todo_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        EditText feedbackText = (EditText) findViewById(R.id.feedbackText);
        EditText scoreText = (EditText) findViewById(R.id.finalScoreText);
        Button reset = (Button) findViewById(R.id.button);

        Intent i = getIntent();
        int score = i.getIntExtra("score", 0);

        scoreText.setText(String.format("Score: %s/5", score));

        if (score >= 4) {
            feedbackText.setText( (CharSequence) "You Won!!" );
        } else {
            feedbackText.setText( (CharSequence) "You Lost!!!" );
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    } // end onCreate
} // end Activity