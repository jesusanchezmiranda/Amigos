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
    private static String tlf;

    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){

            tlf = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

        }else if(state.equals(TelephonyManager.EXTRA_STATE_IDLE)){

            repository = new Repository(context);
            repository.selectIdFromLlamadaEntrante(tlf);
        }
    }


}



