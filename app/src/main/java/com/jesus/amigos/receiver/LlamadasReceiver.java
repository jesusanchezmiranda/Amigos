package com.jesus.amigos.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import com.jesus.amigos.model.Repository;
import com.jesus.amigos.model.room.pojo.Llamada;


public class LlamadasReceiver extends BroadcastReceiver {

    private Llamada llamada;
    private Repository repository;
    private String tlf;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            String estadoTlf = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (estadoTlf.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                tlf = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                repository = new Repository(context);
                repository.insertaLlamada(tlf);
            }
        }
    }


}



