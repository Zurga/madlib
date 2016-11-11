package com.jim;

import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
import java.util.Random;
import java.io.InputStream;


public class wordscreen extends Activity
{
    /** Called when the activity is first created. */
    private Story story;
    private static final String TAG = "Wordscreen";
    private int story_idx;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words);
        Context context = getApplicationContext();
        int[] stories = {
            R.raw.madlib0_simple,
            R.raw.madlib1_tarzan,
            R.raw.madlib2_university,
            R.raw.madlib3_clothes,
            R.raw.madlib4_dance,
        };

        if (savedInstanceState != null){
            story_idx = savedInstanceState.getInt("story_idx");
        }
        else {
            story_idx = new Random().nextInt(stories.length);
        }
        InputStream stream = context.getResources().openRawResource(stories[story_idx]);
        story = new Story(stream);
        /* set up all the things the user needs to see */
        EditText input = (EditText) findViewById(R.id.input);
        input.setHint(story.getNextPlaceholder());
        setRemainingWords(story.getPlaceholderRemainingCount());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("story_idx", story_idx);
    }


    public void setRemainingWords(int numWords) {
        String display = " word(s) left";
        String num_words = String.valueOf(numWords);
        Log.i(TAG, num_words);
        TextView words_left = (TextView) findViewById(R.id.wordsleft);
        words_left.setText("");
        words_left.setText(num_words + display);
    }

    public void nextWord(View view) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        EditText input = (EditText) findViewById(R.id.input);
        String word = input.getText().toString();
        if (!story.isFilledIn()){
            if (word.equals("")) {
                CharSequence text = "Please enter a word...";
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            else {
                story.fillInPlaceholder(word);
                input.setText("");
            }
            if (story.isFilledIn()){
                Intent show_text = new Intent(context, showtext.class);
                show_text.putExtra("text", story.toString());
                startActivity(show_text);
            }
            setRemainingWords(story.getPlaceholderRemainingCount());
            input.setHint(story.getNextPlaceholder());
        }
        else {
            Intent show_text = new Intent(context, showtext.class);
            show_text.putExtra("text", story.toString());
            startActivity(show_text);
        }
    }
}
