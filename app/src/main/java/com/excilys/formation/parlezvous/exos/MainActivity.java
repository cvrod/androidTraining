package com.excilys.formation.parlezvous.exos;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
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
    public static final String PREFS_NAME = "PrefsFile";

    private EditText usernameField;
    private EditText passwordField;

    private Button flushButton;
    private Button sendButton;

    private ProgressBar progressBar;

    private boolean identificationSuccess = false;

    private SMSReceiver receiver;

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

        //Setting user/password from SharedPreferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String user = settings.getString("user", "");
        String password = settings.getString("password", "");

        usernameField.setText(user);
        passwordField.setText(password);

        //SMS Receiver
        IntentFilter filter1 = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        receiver = new SMSReceiver();
        registerReceiver(receiver, filter1);
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
        unregisterReceiver(receiver);
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
        boolean result = false;
        try {
            result = (boolean) task.get();
            Log.i(TAG, "Result from ParlezVousTask : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //if ParlezVousTask return true -> login exist
        if(result) {
            //Putting user/password in Preferences
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("user", this.getUsername());
            editor.putString("password", this.getPassword());
            editor.commit();

            Intent intent = new Intent(MainActivity.this, ConnectedActivity.class);
            intent.putExtra("user", usernameField.getText().toString());
            startActivity(intent);
        }

    }
}