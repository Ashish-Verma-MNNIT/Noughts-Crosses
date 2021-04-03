package com.ashish.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE ="-4" ;
    public static final String EXTRA_MESSAGE2 ="-5" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openPlayground(View view) {
        Intent intent = new Intent(this, Playground.class);
        EditText editText = (EditText) findViewById(R.id.Player1);
        String message = editText.getText().toString();
        if(message!=null&&!message.isEmpty())
            intent.putExtra(EXTRA_MESSAGE, message);
        else
            intent.putExtra(EXTRA_MESSAGE,"Player 1");
        EditText editText2 = (EditText) findViewById(R.id.Player2);
        String message2 = editText2.getText().toString();
        if(message2!=null&&!message2.isEmpty())
            intent.putExtra(EXTRA_MESSAGE2, message2);
        else
            intent.putExtra(EXTRA_MESSAGE2,"Player 2");
        startActivity(intent);
    }
}