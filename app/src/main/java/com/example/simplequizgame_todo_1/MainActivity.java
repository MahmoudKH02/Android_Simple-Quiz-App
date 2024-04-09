package com.example.simplequizgame_todo_1;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {
    private static final int MAX_QUESTIONS = 5;

    private int score = 0;
    private int qCount = 0;
    private Question currQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText scoreText = findViewById(R.id.scoreText);
        EditText timeText = findViewById(R.id.timeText);
        EditText questionText = findViewById(R.id.questionText);

        Button a = findViewById(R.id.b1);
        Button b = findViewById(R.id.b2);
        Button c = findViewById(R.id.b3);
        Button d = findViewById(R.id.b4);

        createQuestions();

        scoreText.setText( (CharSequence) "Score: 0" );
        timeText.setText( (CharSequence) "Seconds Remaining: 10" );

        Collections.shuffle(Question.questionList);
        currQuestion = Question.questionList.get(qCount);

        // shuffle the choices
        String answer = currQuestion.getChoices()[currQuestion.getAnswer()];
        Collections.shuffle(Arrays.asList(currQuestion.getChoices()));
        currQuestion.setAnswer(Arrays.asList(currQuestion.getChoices()).indexOf(answer));

        questionText.setText(String.format("%s) %s", qCount+1, currQuestion.getQuestion()));

        a.setText(String.format("A. %s", currQuestion.getChoices()[0]));
        b.setText(String.format("B. %s", currQuestion.getChoices()[1]));
        c.setText(String.format("C. %s", currQuestion.getChoices()[2]));
        d.setText(String.format("D. %s", currQuestion.getChoices()[3]));

        CountDownTimer timer = new CountDownTimer(10_000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeText.setText( (CharSequence) ("Seconds Remaining: " + (millisUntilFinished / 1000 + 1)) );
            }

            public void onFinish() {
                timeText.setText( (CharSequence) "Seconds Remaining: 0" );
                qCount++;

                if (qCount == MAX_QUESTIONS) {
                    questionText.setText( (CharSequence) "Finished!!!!!" );
                    Question.questionList.clear();

                    Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class)
                            .putExtra("score", score);
                    startActivity(resultIntent);
                } else {
                    nextQuestion(questionText, a, b, c, d, this);
                }
            }
        };

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedButton = (Button) v;
                String buttonText = clickedButton.getText().toString().substring(3);

                if (buttonText.equalsIgnoreCase(currQuestion.getChoices()[currQuestion.getAnswer()])) {
                    score++;
                    scoreText.setText(String.format("Score: %s", score));
                }
                qCount++;

                if (qCount >= MAX_QUESTIONS) {
                    timer.cancel();

                    questionText.setText( (CharSequence) "Finished!!!!!" );
                    Question.questionList.clear();

                    Intent resultIntent = new Intent(MainActivity.this, ResultActivity.class)
                            .putExtra("score", score);
                    startActivity(resultIntent);
                } else {
                    nextQuestion(questionText, a, b, c, d, timer);
                }
            }
        };

        a.setOnClickListener(answerButtonClickListener);
        b.setOnClickListener(answerButtonClickListener);
        c.setOnClickListener(answerButtonClickListener);
        d.setOnClickListener(answerButtonClickListener);

        timer.start();

    } // end onCreate

    private static void createQuestions () {
        Question q1 = new Question(
                "What is the difference between \"==\" and \"===\" in JavaScript?",
                new String[] { // choices
                        "\"==\" performs type coercion, while \"===\" does not.",
                        "\"==\" checks both value and type, while \"===\" only checks value.",
                        "\"==\" checks value only, while \"===\" checks value and type.",
                        "\"==\" is used for strict comparison, while \"===\" is for loose comparison."},
                2
        );
        Question q2 = new Question(
                "How do you declare a variable in Python?",
                new String[] { // choices
                        "var x = 5.",
                        "let x = 5.",
                        "x = 5.",
                        "declare x = 5."},
                2
        );
        Question q3 = new Question(
                "What does \"HTML\" stand for?",
                new String[] { // choices
                        "Hyper Text Markup Language.",
                        "High Tech Modern Language.",
                        "Hyperlinking Textual Markup Language.",
                        "Home Tool Markup Language."},
                0
        );
        Question q4 = new Question(
                "What does the term \"API\" stand for?",
                new String[] { // choices
                        "Application Programming Interface.",
                        "Automated Programming Interface.",
                        "Advanced Program Integration.",
                        "Application Process Integration."},
                0
        );
        Question q5 = new Question(
                "Which of the following is a valid variable name in Python?",
                new String[] { // choices
                        "3dogs.",
                        "_cat.",
                        "try.",
                        "class-name."},
                1
        );

        Question.questionList.add(q1);
        Question.questionList.add(q2);
        Question.questionList.add(q3);
        Question.questionList.add(q4);
        Question.questionList.add(q5);
    }

    private void nextQuestion (EditText qText,
                               Button a, Button b, Button c, Button d,
                               CountDownTimer timer)
    {
        timer.cancel();

        currQuestion = Question.questionList.get(qCount);

        // shuffle choices
        String answer = currQuestion.getChoices()[currQuestion.getAnswer()];
        Collections.shuffle(Arrays.asList(currQuestion.getChoices()));
        currQuestion.setAnswer(Arrays.asList(currQuestion.getChoices()).indexOf(answer));

        qText.setText(String.format("%s) %s", qCount+1, currQuestion.getQuestion()));
        a.setText(String.format("A. %s", currQuestion.getChoices()[0]));
        b.setText(String.format("B. %s", currQuestion.getChoices()[1]));
        c.setText(String.format("C. %s", currQuestion.getChoices()[2]));
        d.setText(String.format("D. %s", currQuestion.getChoices()[3]));

        timer.start();
    }

} // end Activity