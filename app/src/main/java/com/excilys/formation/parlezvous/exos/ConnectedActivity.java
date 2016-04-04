package com.excilys.formation.parlezvous.exos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ConnectedActivity extends AppCompatActivity {
    TextView userLoginField;
    String userLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected);
        userLoginField = (TextView) findViewById(R.id.userLogin);

        Intent i = getIntent();
        userLogin = i.getStringExtra("user");

        userLoginField.setText(userLogin);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);//Menu Resource, Menu
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.deconnexion:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
