package com.spencerstudios.jsonformatter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import spencerstudios.com.bungeelib.Bungee;

public class OutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        TextView tv = (TextView) findViewById(R.id.et_ouput);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        Intent intent = getIntent();
        String beautified = intent.getStringExtra("string");
        tv.setText(ssb(beautified));
    }

    private SpannableStringBuilder ssb(String in) {

        String[] symbols = {"\\{", "\\}", ":", "\\[", "\\]", ","};
        SpannableStringBuilder sb = new SpannableStringBuilder(in);

        Pattern[] patterns = new Pattern[symbols.length];
        Matcher[] matchers = new Matcher[symbols.length];

        for (int i = 0; i < symbols.length; i++) {
            patterns[i] = Pattern.compile(symbols[i], Pattern.CASE_INSENSITIVE);
            matchers[i] = patterns[i].matcher(in);
            while (matchers[i].find()) {
                sb.setSpan(new ForegroundColorSpan(Color.parseColor("#989898")), matchers[i].start(), matchers[i].end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            }
        }

        String quotes = "(\"([^\"]*)\")";
        Pattern p1 = Pattern.compile(quotes, Pattern.CASE_INSENSITIVE);
        Matcher m1 = p1.matcher(in);

        while (m1.find()) {
            sb.setSpan(new ForegroundColorSpan(Color.parseColor("#673ab7")), m1.start(), m1.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }

        return sb;
    }

    public void click(View v) {
        int id = v.getId();

        if (id == R.id.btn_copy) {

        } else {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Bungee.zoom(OutputActivity.this);
    }
}
