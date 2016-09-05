package com.example.hp.scarnesdice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;
import java.util.Timer;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int rollValue=0,score,imageValue,cscore;
    Integer is;
    boolean userTurn=true;
    MainActivity()
    {
        rollValue=0;
        imageValue=0;
        score=0;
        is=0;
        cscore=0;
    }


    Handler h=new Handler();
    Runnable r=new Runnable() {
        @Override
        public void run() {
            computerTurn();
        }
    };

    public void rollDice( View view) {
        if(userTurn==true)
        {
            setRandom();
            if (rollValue!=1) {
                updatScore();
            }
            setImage();
            userTurn=false;
            TextView ycs=(TextView)findViewById(R.id.YCS);
            ycs.setText(Integer.toString(rollValue));
            TextView ccs=(TextView)findViewById(R.id.CCS);
            ccs.setText(Integer.toString(0));
            h.postDelayed(r,2000);
            Log.d("MainActivity","User turn");

        }

    }

    private void setRandom() {
        Random r=new Random();
        rollValue=r.nextInt(6)+1;
    }

    private void computerTurn() {
        Button btnRoll = (Button) findViewById(R.id.btnRoll);
        btnRoll.setEnabled(false);
        if (userTurn == false) {
            setRandom();
            if (rollValue != 1) {
                updatScoreCS();
            }
            setImage();
            userTurn = true;
            TextView ccs = (TextView) findViewById(R.id.CCS);
            ccs.setText(Integer.toString(rollValue));
            TextView ycs = (TextView) findViewById(R.id.YCS);
            ycs.setText(Integer.toString(0));
            btnRoll.setEnabled(true);
            Log.d("MainActivity", "In Computers turn");
        }
    }


    private void updatScoreCS() {
        cscore=cscore+rollValue;
        //Log.d("MainActivity","In CUpadateScore");
        TextView cs=(TextView)findViewById(R.id.computerScore);
        cs.setText(Integer.toString(cscore));
    }


    private void updatScore() {

        score=score+rollValue;
        TextView ys=(TextView)findViewById(R.id.yourScore);
        ys.setText(Integer.toString(score));

    }

    private void setImage() {
        Integer p[]={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,
                R.drawable.dice6};
        is=rollValue-1;
        ImageView iv=(ImageView)findViewById(R.id.image1);
        iv.setImageResource(p[is]);
    }

    public void holdDice(final View view) {
        if (userTurn=false)
        {
            rollDice(view);
        }
    }

    public void resetDice(View view) {
        TextView ys=(TextView)findViewById(R.id.yourScore);
        ys.setText(Integer.toString(0));
        TextView cs=(TextView)findViewById(R.id.computerScore);
        cs.setText(Integer.toString(0));
        ImageView iv=(ImageView)findViewById(R.id.image1);
        Integer p[]={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,
                R.drawable.dice6};
        iv.setImageResource(p[0]);

        TextView ycs=(TextView)findViewById(R.id.YCS);
        ycs.setText(Integer.toString(0));

        TextView ccs=(TextView)findViewById(R.id.CCS);
        ccs.setText(Integer.toString(0));

        Button btnRoll=(Button)findViewById(R.id.btnRoll);
        btnRoll.setEnabled(true);
        score=0;
        cscore=0;
    }
}
