package com.amsoftware.testapplication03;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class UIButtonsManager implements View.OnClickListener
{
    //LAVORO PER COMPOSIZIONE
    //L'interfaccia MessageManager ha dei contentuti
    MessageManager messaggiatore;

    //decido, per disaccoppiare, di esporre solo l'interfaccia manager
    public UIButtonsManager(MessageManager messageManager)
    {
        messaggiatore = messageManager;
    }

    @Override
    public void onClick(View v)
    {
        int sender = v.getId();

        //test logs
        Log.println(Log.DEBUG,"","RECEIVED " + sender);

        switch(sender)
        {
            case Protocol.SEND_REQUEST_ECHO:
                 //gestisco evento click btn 1
                 onSendRequestEcho();
                 break;
            case Protocol.SEND_RESPONSE_ECHO :
                 //gestisco l'evento click btn 2
                 onSendResponseEcho();
                 break;
            default:
                break;
        }


    }

    private void onSendRequestEcho()
    {

        //PRIMA COSA: sempre un intent
        Intent request = new Intent(messaggiatore.getActivity(),Protocol.REQ_ECHO_TARGET_EXPLICIT);
        //ASSEGNO PARAMETRI EXTRA CON IDENTIFICATORE
        request.putExtra(Protocol.REQ_ECHO_STR, messaggiatore.sendMessage());
        //CREO UNA RICHIESTA IN ATTESA DI RISPOSTA,
        //COME statActivity classico ma in piu
        //DEVO METTERGLI UN IDENTIFICATORE ANCHE QUA, UN INT
        //IN QUESTO CASO E' ESPLICITA
        //il paramatero è visto nel costrutture dell'activity chiamata
        messaggiatore.getActivity().startActivityForResult(request,Protocol.REQ_ECHO);
    }

    private void onSendResponseEcho()
    {
        //PRIMA COSA: sempre un intent
        //in questo caso è vuoto perchè non devo eseguire richieste
        //di risposte implicite(di sistema) o esplicite(altre activity custom)
        //con parametri
        Intent intent = new Intent();
        //ASSEGNO PARAMETRI EXTRA CON IDENTIFICATORE
        intent.putExtra(Protocol.RES_ECHO_STR, messaggiatore.sendMessage());
        //non lancio altre activty come detto prima
        //ma salvo i paramteri di intent come risultato valido di una richiesta
        messaggiatore.
                getActivity().
                setResult(Activity.RESULT_OK,intent);
        //non mi serve piu l'activity, devo chiuderla, il codice di ritorno è il risultato salvato.
        messaggiatore.
                getActivity().
                finish();
    }


    //SA COME INTERPRETARE LE RICHIESTE/RISPOSTE
    void onActivityProtocolSolver(int requestCode, int resultCode, Intent data)
    {
        //se corrisponde ad un protocollo noto
        if(requestCode == Protocol.REQ_ECHO)
        {
            //ACTIVITY APERTA COME DA RICHIESTA
            if(resultCode == Protocol.REQ_ECHO)
            {
                //chiamo l'evento che gestisce l'arrivo di un messaggio REQUEST
                messaggiatore.onReceiveMessage(data.getStringExtra(Protocol.REQ_ECHO_STR));
            }
            else
                //ACTIVITY GIA' APERTA, RICEVE UNA RISPOSTA
                //come se fosse una dialog.. _ok,_cancelled.. _etc...
            if(resultCode == Activity.RESULT_OK)
            {
                //faccio la stessa cosa che facevo per activity 2, nel costruttore xchè era li
                //che ricevevo il messaggio
                //ora lo ricevo ad activity pronta
                messaggiatore.onReceiveMessage(data.getStringExtra(Protocol.RES_ECHO_STR));
            }


        }
    }
}
