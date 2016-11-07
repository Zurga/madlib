package com.jim;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.view.View;


public class infoscreen extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void startWordActivity(View view) {
        final Context context = this;

        Intent intent = new Intent(context, wordscreen.class);
        startActivity(intent);
    }
}


