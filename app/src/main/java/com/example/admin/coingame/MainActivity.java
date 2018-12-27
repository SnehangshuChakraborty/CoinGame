package com.example.admin.coingame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int turn = 0;

    int[] gameState = {2,2,2,2,2,2,2,2,2};
    boolean gameActive = true;
    int [][] WinningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
   // public int tappedCounter = Integer.parseInt()

    public void DropIn(View view){
        ImageView Counter = (ImageView)view;
        int TappedCounter = Integer.parseInt(Counter.getTag().toString());
        if(gameState[TappedCounter]==2&&gameActive) {
            gameState[TappedCounter] = turn;
            Counter.setTranslationY(-1500);
            if (turn == 0) {
                Counter.setImageResource(R.drawable.y);
                turn = 1;
            } else {
                Counter.setImageResource(R.drawable.r);
                turn = 0;
            }

            Counter.animate().translationYBy(1500).setDuration(300);

            //WInning Logic
            for (int[] winningPosition : WinningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    Button PlayAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
                    String winner = "";
                    gameActive=false;
                    if (turn == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }
                    winnerText.setText(winner + " has won");
                    PlayAgainButton.setVisibility(view.VISIBLE);
                    winnerText.setVisibility(view.VISIBLE);
                    break;
                }
            }
        }
    }

    public void playAgain(View view){
        Button PlayAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView winnerText = (TextView) findViewById(R.id.winnerTextView);
        PlayAgainButton.setVisibility(view.INVISIBLE);
        winnerText.setVisibility(view.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        turn = 0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
