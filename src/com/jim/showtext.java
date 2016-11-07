package com.jim;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
import java.util.Random;
import java.io.InputStream;


public class showtext extends Activity
{
    /** Called when the activity is first created. */
    private static final String TAG = "Wordscreen";

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showtext);
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        TextView textview = (TextView) findViewById(R.id.text);
        textview.setText(text);
    }

    public void startOver(View view) {
        final Context context = this;
        Intent intent = new Intent(context, wordscreen.class);
        startActivity(intent);
        finish();
    }
}
