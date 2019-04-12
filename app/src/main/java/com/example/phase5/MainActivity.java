package com.example.phase5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.stringprocessormodule.IStringProcessorProxy;

public class MainActivity extends AppCompatActivity {

    private TextView mDisplayMessage;
    private EditText mIP;
    private EditText mPort;
    private EditText mTextToChange;
    private ToggleButton mToggleButton;

    private String ip;
    private String port;
    private String textToChange;
    private String response;
    private IStringProcessorProxy mServerProxy;

    private Button mToLower;
    private Button mTrim;
    private Button mDoubleParse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mIP = findViewById(R.id.ip_address);
        mPort = findViewById(R.id.port);
        mTextToChange = findViewById(R.id.text_to_change);
        mDisplayMessage = findViewById(R.id.displayText);
        mServerProxy = StringProcessorProxy.getInstance();

        mToLower = findViewById(R.id.button_tolowercase);
        mTrim = findViewById(R.id.button_trim);
        mDoubleParse = findViewById(R.id.button_parseDouble);
        mToggleButton = findViewById(R.id.toggle_button);

        mIP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ip = s.toString();

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ip = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                ip = s.toString();
            }
        });

        mPort.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
               port = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                port = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

                port = s.toString();
            }
        });


        mTextToChange.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                textToChange = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textToChange = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {
                textToChange = s.toString();

            }
        });

    }
// Tjhis is just a test
    //Well that did not seem to work
    //Another test
//This is a test from my laprtop

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendToLower(View view) {
        StringProcessorProxy.setIPandPort(mToggleButton.isChecked(), mIP.getText().toString(), mPort.getText().toString(), "/toLower");

        new ToLowerTask().execute();

    }

    public void sendTrim(View view) {
        StringProcessorProxy.setIPandPort(mToggleButton.isChecked(), mIP.getText().toString(), mPort.getText().toString(), "/trim");
        new TrimTask().execute();

    }

    public void sendParseDouble(View view) {

        StringProcessorProxy.setIPandPort(mToggleButton.isChecked(), mIP.getText().toString(), mPort.getText().toString(), "/parseDouble");
        new ParseDoubleTask().execute();
    }

    public void displayMessage(View view) {
        mDisplayMessage.setText(response);
    }


    public class ToLowerTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids) {
            response = mServerProxy.toLower(textToChange);
            return response;
        }
    }

    public class TrimTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids){
            response = mServerProxy.trim(textToChange);
            return response;
        }
    }

    public class ParseDoubleTask extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids){
            response = mServerProxy.parseDouble(textToChange);
            return response;
        }
    }

}
