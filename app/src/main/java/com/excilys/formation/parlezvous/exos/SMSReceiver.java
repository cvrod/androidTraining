package com.excilys.formation.parlezvous.exos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    private final String ACTION_RECEIVE_SMS = "android.provider.Telephony.SMS_RECEIVED";

    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_RECEIVE_SMS)){
            Toast.makeText(context, "Message Reçu !", Toast.LENGTH_SHORT).show();
        }
    }
}
