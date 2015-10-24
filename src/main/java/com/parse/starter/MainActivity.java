/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.ProgressDialog;
import android.content.Intent;
import java.lang.String;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

  TextView fname,fjoin,fend;
  Button bLogin,btDetails;
  EditText etloginName;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //initialize

   /* fname= (TextView) findViewById(R.id.ftname);
    fjoin= (TextView) findViewById(R.id.ftjoin);
    fend= (TextView) findViewById(R.id.ftend);

    // i need to start from here
*/
      bLogin= (Button) findViewById(R.id.btLogin);

      bLogin.setOnClickListener(this);


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      startActivity(new Intent(this, SignUp.class));
      return true;
    }

    else if(id==R.id.delete_list){
      startActivity(new Intent(this, CheckDel.class));
      return true;
    }
    else if(id==R.id.update_list){
      startActivity(new Intent(this, UpdateUser.class));
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onClick(View v) {
    //start here
    switch (v.getId()) {
        case R.id.btLogin:
            startActivity(new Intent(MainActivity.this, Members.class));
            break;
        /*
        case R.id.btgetdetails:
        //parse extracting the user details
        final ProgressDialog dlg=new ProgressDialog(MainActivity.this);
        dlg.setTitle("Please Wait...");
        dlg.setMessage("Signing in. Please wait.");
        dlg.show();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");

        query.whereEqualTo("name", etloginName.getText().toString());

        query.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> objects, ParseException e) {
            if (e == null && objects!=null) {
              try {

                ParseObject p = objects.get(0);
                String fsname = p.getString("name");
                String fsjoin = p.getString("joindate");
                String fsend = p.getString("enddate");
                  String fstrainer=p.getString("personaltrainer");
                  String fsfees=p.getString("fees");
                  String fstype=p.getString("type");
                  String fscount=p.getString("days");
                  String fsamount=p.getString("amount");
                  Intent intent = new Intent(MainActivity.this, LoginDetails.class);
                  intent.putExtra("name", fsname);
                  intent.putExtra("joindate", fsjoin);
                  intent.putExtra("enddate", fsend);
                  intent.putExtra("personaltrainer",fstrainer);
                  intent.putExtra("type",fstype);
                  intent.putExtra("fees",fsfees);
                  intent.putExtra("days",fscount);
                  intent.putExtra("amount", fsamount);
                  startActivity(intent);
              }
              catch(IndexOutOfBoundsException i){
                Toast.makeText(MainActivity.this,"No such username",Toast.LENGTH_SHORT).show();
                dlg.cancel();

              }
            } else if(objects==null) {
              Toast.makeText(MainActivity.this, "Invalid data", Toast.LENGTH_SHORT).show();
            }


          }


        });
            break;
*/
    }

  }
}
