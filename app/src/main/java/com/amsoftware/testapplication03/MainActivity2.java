package com.amsoftware.testapplication03;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity implements MessageManager {

    Button btnEcho;
    TextView lblMessage;
    View.OnClickListener btnListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_main);

        //RIFACCIO I PASSI ESEGUITI PER ACTIVITY 1
        //Istanzio l'ascoltatore agli eventi click bottoni
        btnListener = new UIButtonsManager(this);
        //PRENDO UNA REFERENZA AL PRIMO BOTTONE
        btnEcho     = findViewById(Protocol.SEND_RESPONSE_ECHO);
        //PRENDO UNA REFERENZA ALLA PRIMA EDIT
        lblMessage = findViewById(Protocol.LBL_1);
        //ASSEGNO UN'ASCOLTATORE
        btnEcho.setOnClickListener(btnListener);

        //notifico il gestore di eventi di verificare il messaggio arrivato
        //il lgestore sa come interpretare il messaggio
        ((UIButtonsManager)btnListener).onActivityResult(Protocol.REQ_ECHO,Protocol.REQ_ECHO,getIntent());
    }

    @Override
    public String sendMessage()
    {
        return  "S-2:" + lblMessage.getText().toString();
    }

    @Override
    public void onReceiveMessage(String message)
    {
        lblMessage.setText("R-2:" + message);
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
