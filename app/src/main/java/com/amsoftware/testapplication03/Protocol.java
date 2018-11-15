package com.amsoftware.testapplication03;

public final class Protocol
{
    //FAST RE MAPPING on compile time created class R
    //ONLY NEEDED SUBSET OF STUFFS
    public static final int SEND_REQUEST_ECHO    = R.id.button;
    public static final int SEND_RESPONSE_ECHO   = R.id.button2;
    public static final int TEXT_1               = R.id.editText;
    public static final int LBL_1                = R.id.textView;

    //PROTOCOLLO CHIAMATA RISPOSTA
    public static final int    REQ_ECHO = 1;
    //IDENTIFICATORI DI PARAMTRI NEL PROTOCOLLO
    public static final String REQ_ECHO_STR = "REQ_ECHO";
    public static final String RES_ECHO_STR = "RES_ECHO";

    public static final Class REQ_ECHO_TARGET_EXPLICIT = MainActivity2.class;
}
