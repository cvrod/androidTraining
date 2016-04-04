package com.excilys.formation.parlezvous.exos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    //Getting class name
    private final String TAG = MainActivity.class.getSimpleName();

    private EditText usernameField;
    private EditText passwordField;

    private Button flushButton;
    private Button sendButton;

    private ProgressBar progressBar;

    private boolean identificationSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate !");
        usernameField = (EditText) findViewById(R.id.user);
        passwordField = (EditText) findViewById(R.id.password);

        flushButton = (Button) findViewById(R.id.viderButton);
        sendButton = (Button) findViewById(R.id.envoyerButton);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    //Getters
    public ProgressBar getProgressBar(){
        return this.progressBar;
    }

    public String getUsername(){
        return this.usernameField.getText().toString();
    }

    public String getPassword(){
        return this.passwordField.getText().toString();
    }

    //Setters
    public void setIdentificationSuccess(boolean b){
        this.identificationSuccess = b;
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

    //Sending logs
    public void sendButtonMethod(View view) {
        Toast.makeText(this, "Toast !", Toast.LENGTH_SHORT).show();
        ParlezVousTask task = new ParlezVousTask(this);
        task.execute();
        try {
            boolean result = (boolean) task.get();
            Log.i(TAG, "Result from ParlezVousTask : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(MainActivity.this, ConnectedActivity.class);
        intent.putExtra("user", usernameField.getText().toString());
        startActivity(intent);

    }
}