package com.hn.hnstudio.hnquizgame.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hn.hnstudio.hnquizgame.R;
import com.hn.hnstudio.hnquizgame.model.Question;
import com.hn.hnstudio.hnquizgame.model.QuestionBank;

import java.util.Arrays;

public class Play extends AppCompatActivity implements View.OnClickListener {
    private TextView question;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    private Button resume;
    private Button score,number;

    private Button returnMainMenu;
    private Dialog pausemenu;

    private int nb =1;

    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;

    private int mScore;
    private int mNumberOfQuestions;
    MediaPlayer mediaPlayer;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";

    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        System.out.println("GameActivity::onCreate()");


        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tears);

        mediaPlayer.start();







        mQuestionBank = this.generateQuestions();

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
            mNumberOfQuestions = 6;
        }

        mEnableTouchEvents = true;

        // Wire widgets
        question = (TextView) findViewById(R.id.question);
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);
        score = (Button) findViewById(R.id.score);

        number = (Button) findViewById(R.id.number);

        // Use the tag property to 'name' the buttons
        answer1.setTag(0);
        answer2.setTag(1);
        answer3.setTag(2);
        answer4.setTag(3);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }


    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            // Good answer
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;

        } else {
            // Wrong answer
            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;

                // If this is the last question, ends the game.
                // Else, display the next question.
                if (--mNumberOfQuestions == 0) {
                    // End the game
                    endGame();
                } else {
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                   nb++;
                    number.setText(""+nb);
                }
            }
        }, 2000); // LENGTH_SHORT is usually 2 second long
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("Your score is " + mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
/*
    private void pauseMenu(){
        pausemenu=new Dialog(Play.this);
        pausemenu.setContentView(R.layout.pause_menu);
        pausemenu.setTitle("Game Paused");
        returnMainMenu=(Button)pausemenu.findViewById(R.id.mainMenu);
        resume=(Button)pausemenu.findViewById(R.id.returngame);
        resume.setEnabled(true);
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausemenu.cancel();
            }
        });
returnMainMenu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent gameAct=new Intent(Play.this,MainMenu.class);
        startActivity(gameAct);
    }
});
    pausemenu.show();



    }

*/

    private void displayQuestion(final Question question1) {
        question.setText(question1.getQuestion());
        answer1.setText(question1.getChoiceList().get(0));
        answer2.setText(question1.getChoiceList().get(1));
        answer3.setText(question1.getChoiceList().get(2));
        answer4.setText(question1.getChoiceList().get(3));
        score.setText("Score : " +mScore);
    }

    private QuestionBank generateQuestions() {
        Question question1 = new Question("What is the name of the network of computers from which the Internet has emerged?",
                Arrays.asList("Arpanet", "NSFNET", "FidoNetc", "Bitnet"),
                0);

        Question question2 = new Question("Which address type is not supported by IPv6?",
                Arrays.asList("Multicast", "Broadcast", "Anycast", "Unicast"),
                1);

        Question question3 = new Question("What are the correct pinouts for a crossover cable?",
                Arrays.asList("Pin 2 to pin 3", "Pin 1 to pin 6", "Pin 2 to pin 6", "Pin 4 to pin 8"),
                2);

        Question question4 = new Question("A computer assisted method for the recording and analyzing of existing or hypothetical systems is",
                Arrays.asList("Data transmission", "Data flow", "Data capture", "Data processing"),
                1);

        Question question5 = new Question("The brain of any computer system is ?",
                Arrays.asList("ALU", "Memory", "CPU", "Control unit"),
                2);

        Question question6 = new Question("What difference does the 5th generation computer have from other generation computers?",
                Arrays.asList("Technological advancement", "Scientific code", "Object Oriented Programming", "All of the above"),
               0);

        Question question7 = new Question("\t\n" +
                "Which of the following computer language is used for artificial intelligence?",
                Arrays.asList("FORTRAN", "PROLOG", "C", "COBOL"),
                1);

        Question question8 = new Question("\t\n" +
                "As compared to diskettes, the hard disks are",
                Arrays.asList("more expensive", "more portable", "less rigid", "slowly accessed"),
                0);

        Question question9 = new Question("Who is considered the 'father' of the minicomputer and one of the founder fathers of the modern computer industry world-wide?",
                Arrays.asList("George Tate", "Kenneth H. Olsen", "Seymour Cray", "Basic Pascal"),
                1);
        Question question10 = new Question("\t\n" +
                "The first microprocessors produced by Intel Corpn. and Texas Instruments were used primarily to control small",
                Arrays.asList("microwave ovens", "washing machines", "calculators", "personal computers"),
                2);
        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10));
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
        mediaPlayer.release();
        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("GameActivity::onDestroy()");
    }
}
