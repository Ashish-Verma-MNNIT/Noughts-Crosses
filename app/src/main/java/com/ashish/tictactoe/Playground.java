package com.ashish.tictactoe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class Playground extends AppCompatActivity implements View.OnClickListener{
    private TextView PlayerOneScore, PlayerTwoScore, PlayerStatus;
    private Button[] buttons=new Button[9];
    private Button ResetGame;

    int PlayerOneScoreCount, PlayerTwoScoreCount, RoundCount;
    boolean ActivePlayer;

    int [] GameState={2,2,2,2,2,2,2,2,2};
    int[][] WinningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};




    @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_playground);
        Intent intent = getIntent();
        String player1 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String player2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        TextView textView= findViewById(R.id.Player1);
        textView.setText(player1);
        TextView textView2= findViewById(R.id.Player2);
        textView2.setText(player2);
        PlayerOneScore= findViewById(R.id.PlayerOneScore);
        PlayerTwoScore= findViewById(R.id.PlayerTwoScore);
        PlayerStatus= findViewById(R.id.PlayerStatus);
        ResetGame= findViewById(R.id.ResetGame);
        for (int i=0;i<buttons.length;i++){
            String buttonID= "btn_"+i;
            int resourceID=getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i]= findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }
        RoundCount=0;
        PlayerOneScoreCount=0;
        PlayerTwoScoreCount=0;
        ActivePlayer=true;
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        Intent intent = getIntent();
        String player1 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String player2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        String buttonID =v.getResources().getResourceEntryName(v.getId());
        int GameStatePointer= Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));
        if(ActivePlayer){
            ((Button) v).setText("X");
            ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
            GameState[GameStatePointer]=0;
        }
        else{
            ((Button) v).setText("0");
            ((Button) v).setTextColor(Color.parseColor("#70FFEA"));
            GameState[GameStatePointer]=1;

        }
        RoundCount++;
        if(check()){
            if(ActivePlayer){
                PlayerOneScoreCount++;
                updateScore();
                Toast.makeText(this,player1+" Won", Toast.LENGTH_LONG).show();
                playAgain();
            }
            else{
                PlayerTwoScoreCount++;
                updateScore();
                Toast.makeText(this,player2+" Won", Toast.LENGTH_SHORT).show();
                playAgain();
            }
        }else if(RoundCount==9){
            playAgain();
            Toast.makeText(this,"NO winner", Toast.LENGTH_SHORT).show();
        }else{
            ActivePlayer=!ActivePlayer;
        }
        if(PlayerOneScoreCount>PlayerTwoScoreCount){
            PlayerStatus.setText(String.format("%s is winning..", player1));
        }else if(PlayerTwoScoreCount>PlayerOneScoreCount){
            PlayerStatus.setText(String.format("%s is winning..", player2));
        }else
            PlayerStatus.setText("");
        ResetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAgain();
                PlayerOneScoreCount=0;
                PlayerTwoScoreCount=0;
                PlayerStatus.setText("");
                updateScore();
            }
        });
    }
    public boolean check(){
        boolean winnerResult=false;

        for(int [] WinningPosition : WinningPositions){
            if(GameState[WinningPosition[0]]==GameState[WinningPosition[1]]&&GameState[WinningPosition[1]]==GameState[WinningPosition[2]] && GameState[WinningPosition[0]]!=2){
                winnerResult=true;
            }
        }
        return winnerResult;
    }
    public void updateScore(){
        PlayerOneScore.setText(Integer.toString(PlayerOneScoreCount));
        PlayerTwoScore.setText(Integer.toString(PlayerTwoScoreCount));
    }
    public void playAgain(){
        RoundCount=0;
        ActivePlayer=true;
        for(int i=0;i<buttons.length;i++){
            GameState[i]=2;
            buttons[i].setText("");
        }
    }
}