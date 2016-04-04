package com.excilys.formation.parlezvous.exos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Getting class name
    private final String TAG = MainActivity.class.getSimpleName();
    private EditText usernameField;
    private EditText passwordField;
    private Button flushButton;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate !");
        usernameField = (EditText) findViewById(R.id.user);
        passwordField = (EditText) findViewById(R.id.password);

        flushButton = (Button) findViewById(R.id.viderButton);
        sendButton = (Button) findViewById(R.id.envoyerButton);
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy !");
    }

    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause !");
    }

    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume !");
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState !");
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState !");
    }

    //Reset user/password textfield
    public void flushButtonMethod(View view) {
        usernameField.setText("");
        passwordField.setText("");
    }
    
    public void sendButtonMethod(View view) {
        Toast.makeText(this, "Toast !", Toast.LENGTH_SHORT).show();
    }
}