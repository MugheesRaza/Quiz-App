package com.example.mughees.onlinequizapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    private QuestionBank mQuestionLibrary = new QuestionBank();
    MyDataBaseHelper myDB  = new MyDataBaseHelper(this);
    MyDataBaseHelper2 myDB2= new MyDataBaseHelper2(this);
    private TextView mScoreView;   // view for current total score
    TextView txtScore ;
    private TextView mQuestionView;  //current question to answer
    private Button mButtonChoice1; // multiple choice 1 for mQuestionView
    private Button mButtonChoice2; // multiple choice 2 for mQuestionView
    private Button mButtonChoice3; // multiple choice 3 for mQuestionView
    private Button mButtonChoice4; // multiple choice 4 for mQuestionView
    private String mAnswer;  // correct answer for question in mQuestionView
    private int mScore = 0;  // current total score
    private int mQuestionNumber = 0; // current question number
    String nameMain,cnicMain,phoneMain,professionMain;
    public static final String nameExtra = "com.example.mughees.onlinequizapp.nameExtra";
    public static final String cnicExtra = "com.example.mughees.onlinequizapp.cnicExtra";
    public static final String poneExtra = "com.example.mughees.onlinequizapp.poneExtra";
    public static final String professionExtra = "com.example.mughees.onlinequizapp.professionExtra";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtScore = (TextView) findViewById(R.id.textScore);
        mScoreView = (TextView) findViewById(R.id.score);
        mQuestionView = (TextView) findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.choice4);

        mQuestionLibrary.initQuestions(getApplicationContext());
        updateQuestion();
        // show current total score for the user
        updateScore(mScore);

//        Intent intent = getIntent();
//        nameMain = intent.getStringExtra(RegActivity.nameExtra);
//        cnicMain = intent.getStringExtra(RegActivity.cnicExtra);
//        phoneMain = intent.getStringExtra(RegActivity.poneExtra);
//        professionMain = intent.getStringExtra(RegActivity.professionExtra);


    }



    private void updateQuestion(){
        // check if we are not outside array bounds for questions
        if(mQuestionNumber<mQuestionLibrary.getLength() ){
            // set the text for new question,
            // and new 4 alternative to answer on four buttons
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice(mQuestionNumber, 1));
            mButtonChoice2.setText(mQuestionLibrary.getChoice(mQuestionNumber, 2));
            mButtonChoice3.setText(mQuestionLibrary.getChoice(mQuestionNumber, 3));
            mButtonChoice4.setText(mQuestionLibrary.getChoice(mQuestionNumber,4));
            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        }
        else {
            Toast.makeText(MainActivity.this, "It was the last question!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, heigh_score.class);
            i.putExtra("score", mScore); // pass the current score to the second screen
            i.putExtra(nameExtra,nameMain);
            i.putExtra(cnicExtra,cnicMain);
            i.putExtra(poneExtra,phoneMain);
            i.putExtra(professionExtra,professionMain);
            startActivity(i);
        }
    }
    private void updateScore(int point) {
        mScoreView.setText(""+mScore+"/"+mQuestionLibrary.getLength());
    }
    public void onClick(View view) {
        //all logic for all answers buttons in one method
        Button answer = (Button) view;
        // if the answer is correct, increase the score
        if (answer.getText().equals(mAnswer)){
            mScore = mScore + 1;
            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
        // show current total score for the user
        updateScore(mScore);
        // once user answer the question, we move on to the next one, if any
        updateQuestion();
    }
}
