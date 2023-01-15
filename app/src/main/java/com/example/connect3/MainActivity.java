package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    int gameState[]={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    //o=yellow , 1=red
    int activePlayer=0;
boolean gameActive =true;
    public void dropIn(View view){
        ImageView counter=(ImageView) view;
        Log.i("tag",counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    gameActive=false;
                    //someone has won
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "Yellow";

                    } else {
                        winner = "Red";
                    }
                    Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();
                    Button playAgainButton= (Button) findViewById(R.id.button);
                    TextView winnerTextView= (TextView) findViewById(R.id.textView);
                    winnerTextView.setText(winner+" has Won!");
                    playAgainButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playAgainButton= (Button) findViewById(R.id.button);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView winnerTextView= (TextView) findViewById(R.id.textView);
                playAgainButton.setVisibility(View.INVISIBLE);
                winnerTextView.setVisibility(View.INVISIBLE);
                GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
                for(int i=0;i<gridLayout.getChildCount();i++)
                {
                    ImageView counter =(ImageView) gridLayout.getChildAt(i);
                    counter.setImageDrawable(null);
                }
                Arrays.fill(gameState, 2);
                activePlayer=0;
                gameActive =true;
            }
        });
    }
}