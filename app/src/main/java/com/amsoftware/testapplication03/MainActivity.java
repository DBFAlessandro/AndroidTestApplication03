package com.amsoftware.testapplication03;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements MessageManager
{
    //PER VELOCIZZARE LE OPERAZIONI, SALVO LE REFERENCES
    //ONDE EVITARE FIND A RUNTIME
    Button btnAct2;
    //STESSA COSA PER TESTO
    EditText txtMessage;
    //References all'ascoltatore, la classe base è astratta ascoltatore generico
    View.OnClickListener btnListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Istanzio l'ascoltatore agli eventi click bottoni
        btnListener = new UIButtonsManager(this);
        //PRENDO UNA REFERENZA AL PRIMO BOTTONE
        btnAct2    = findViewById(Protocol.SEND_REQUEST_ECHO);
        //PRENDO UNA REFERENZA ALLA PRIMA EDIT
        txtMessage = findViewById(Protocol.TEXT_1);
        //ASSEGNO UN'ASCOLTATORE
        btnAct2.setOnClickListener(btnListener);

    }

     @Override
     public String sendMessage()
     {
         return "S-1:" + txtMessage.getText().toString();
     }

    @Override
    public void onReceiveMessage(String message)
    {
        txtMessage.setText("R-1:" + message);

    }

    @Override
     public Activity getActivity()
     {
         return this;
     }

     //piu oo meno come il create per la visualizzione di intent , ma qui la classe è giò creata
    //e riiceve un messaggio da un altra classe come risposta.
     @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data)
     {
         //delego il listener a gestire l'evento result
         ((UIButtonsManager)btnListener).onActivityProtocolSolver(requestCode,resultCode,data);
     }

}
