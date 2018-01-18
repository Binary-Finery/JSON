package com.spencerstudios.jsonformatter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import spencerstudios.com.bungeelib.Bungee;

public class MainActivity extends AppCompatActivity {

    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!=null)getSupportActionBar().hide();

        input = (EditText) findViewById(R.id.et_input);

    }

    public void click(View v){
        int id = v.getId();

        if (id==R.id.btn_clear){
            input.setText("");
        }else{
            formatJSon();
        }
    }

    private void formatJSon(){

        try {
            String js = input.getText().toString().trim();
            String json = String.valueOf(js.startsWith("{") ? new JSONObject(js) : new JSONArray(js));

            String pretty = new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(new JsonParser().parse(json));

            Intent intent = new Intent(MainActivity.this, OutputActivity.class);
            intent.putExtra("string", pretty);
            startActivity(intent);
            Bungee.card(MainActivity.this);

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }
}
