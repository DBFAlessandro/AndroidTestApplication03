package com.amsoftware.testapplication03;

import android.app.Activity;

public interface MessageManager {
    String sendMessage();
    void onReceiveMessage(String message);
    Activity getActivity();
}
