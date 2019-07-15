package com.example.mughees.onlinequizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class heigh_score extends AppCompatActivity {
    TextView txtScore;

    MyDataBaseHelper2 myDB2 = new MyDataBaseHelper2(this);
    int score;
    String data;
    String  finalname1 ;
    String finalCnic1 ;
    String finalPhone1 ;
    String finalprofessional;
    String text1,text2,text3,text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heigh_score);


         txtScore = (TextView) findViewById(R.id.textScore);
        TextView txtHighScore = (TextView) findViewById(R.id.textHighScore);
        Intent i = getIntent();

        // receive the score from last activity by Intent

        finalname1 = i.getStringExtra(MainActivity.nameExtra);
        finalPhone1 = i.getStringExtra(MainActivity.cnicExtra);
        finalCnic1 = i.getStringExtra(MainActivity.cnicExtra);
        finalprofessional = i.getStringExtra(MainActivity.professionExtra);
        score = i.getIntExtra("score", 0);
        // display current score
        txtScore.setText("Your score: " + score);

        // use Shared preferences to save the best score


        SharedPreferences mypref = getPreferences(MODE_PRIVATE);
        int highscore = mypref.getInt("highscore", 0);
        if (highscore >= score)
            txtHighScore.setText("High score: " + highscore);
        else {
            txtHighScore.setText("New highscore: " + score);
            SharedPreferences.Editor editor = mypref.edit();
            editor.putInt("highscore", score);
            editor.commit();
        }



    }

    public void onRepeatClick(View view) {
        Intent intent = new Intent(heigh_score.this, MainActivity.class);
        startActivity(intent);
    }


}
