package com.nuance.speechkitsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.VoicemailContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nuance.speechkit.Audio;
import com.nuance.speechkit.Language;
import com.nuance.speechkit.Session;
import com.nuance.speechkit.Transaction;
import com.nuance.speechkit.Voice;

/**
 * Initial screen.
 *
 * Copyright (c) 2015 Nuance Communications. All rights reserved.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    View asrButton = null;
    View nluButton = null;
    View textNluButton = null;
    View ttsButton = null;
    View phase2 = null;
    private Session speechSession = null;

    View audioButton = null;

    View configButton = null;
    View aboutButton = null;
    View [] questionButtons = null;
    public static String [] questions = null;

    public final static int QUESTION_NUM = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        speechSession = Session.Factory.session(this, Configuration.SERVER_URI, Configuration.APP_KEY);
        LinearLayout mainContent = (LinearLayout) findViewById(R.id.main_content);
        LinearLayout coreTech = inflateCategoryView("Questions: ", mainContent);

        //creating questions
        questionButtons = new View[QUESTION_NUM];
        questions = new String[QUESTION_NUM];
        questions[0] = "Tell me about yourself!";
        questions[1] = "Cool! any work experiences?";
        questions[2] = "Any freetime baby? What do you like doing?";
        questions[3] = "Do you have a future? tell me about that!";
        questions[4] = "Do you want to chat?";

        for(int i=0; i<QUESTION_NUM; i++)
            this.questionButtons[i] = inflateRowView("Question " + (i+1) , questions[i], coreTech);

        LinearLayout query = inflateCategoryView("Query:", mainContent);
        phase2 = inflateRowView("Phase2", "Ask me anything" , mainContent);

        LinearLayout misc = inflateCategoryView("Misc:", mainContent);

        configButton = inflateRowView("Configuration", "Host URL, App ID, etc", misc);
        aboutButton = inflateRowView("About", "Learn more about SpeechKit", misc);

    }

    @Override
    public void onClick(View v) {
        Transaction.Options options = new Transaction.Options();
        options.setLanguage(new Language(Configuration.LANGUAGE));

        Intent intent = null;
        if(v == asrButton) {
            intent = new Intent(this, ASRActivity.class);
        } else if (v == phase2) {
            intent = new Intent(this, NLUActivity.class);
            intent.putExtra("QUESTION_STRING", "Ask me Anything");
            intent.putExtra("QUESTION_ID", -3);
        } else if(v == nluButton) {
            intent = new Intent(this, NLUActivity.class);
        } else if(v == textNluButton) {
            intent = new Intent(this, TextNLUActivity.class);
        } else if(v == ttsButton) {
            intent = new Intent(this, TTSActivity.class);
        } else if(v == audioButton)  {
            intent = new Intent(this, AudioActivity.class);
        } else if(v == configButton) {
            intent = new Intent(this, ConfigActivity.class);
        } else if(v == aboutButton) {
            intent = new Intent(this, AboutActivity.class);
        } else {
            for(int i=0; i<QUESTION_NUM; i++) {

                if(v == this.questionButtons[i]) {
                    intent = new Intent(this, NLUActivity.class);
                    intent.putExtra("QUESTION_STRING", this.questions[i]);
                    intent.putExtra("QUESTION_ID", i);
                }
            }
        }

        if(intent != null) {
            startActivity(intent);
            overridePendingTransition(R.anim.enter_left, R.anim.exit_left);
        }
    }

    private LinearLayout inflateCategoryView(String title, LinearLayout parent) {
        View v = (View) getLayoutInflater().inflate(R.layout.activity_main_category, null);
        ((TextView)v.findViewById(R.id.title)).setText(title);
        parent.addView(v);
        return ((LinearLayout)v.findViewById(R.id.list));
    }

    private View inflateRowView(String mainText, String subText, LinearLayout parent) {
        View v = (View) getLayoutInflater().inflate(R.layout.activity_main_row, null);
        ((TextView)v.findViewById(R.id.mainText)).setText(mainText);
        ((TextView)v.findViewById(R.id.subText)).setText(subText);
        parent.addView(v);
        v.setOnClickListener(this);
        return v;
    }
}
