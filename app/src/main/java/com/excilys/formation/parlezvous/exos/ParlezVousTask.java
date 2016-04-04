package com.excilys.formation.parlezvous.exos;

import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ParlezVousTask extends android.os.AsyncTask {
    MainActivity activity;
    String user;
    String password;

    public ParlezVousTask(MainActivity act){
        this.activity = act;
    }

    protected void onPreExecute(){
        super.onPreExecute();
        ProgressBar bar = activity.getProgressBar();
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        user = activity.getUsername();
        password = activity.getPassword();

        System.out.println("User : "+user);
        System.out.println("Password : "+password);

        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("http://www.android.com/");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String response = InputStreamToString.convert(in);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }

        return null;
    }

    protected void onPostExecute(Object result) {
        super.onPostExecute(result);
        ProgressBar bar = activity.getProgressBar();
        bar.setVisibility(View.INVISIBLE);
    }
}
