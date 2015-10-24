package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckDel extends ActionBarActivity implements View.OnClickListener {
    EditText pass;
    Button bSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        pass = (EditText) findViewById(R.id.pass);
        bSubmit = (Button) findViewById(R.id.bSubmit);

        bSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        EditText password = (EditText) findViewById(R.id.pass);
        switch (v.getId()) {
            case R.id.bSubmit:

                String mypass = password.getText().toString();

                if (mypass.equals("gymapp")) {
                    Intent registerIntent = new Intent(CheckDel.this, DeleteUser.class);
                    startActivity(registerIntent);

                } else {
                    Toast.makeText(CheckDel.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
