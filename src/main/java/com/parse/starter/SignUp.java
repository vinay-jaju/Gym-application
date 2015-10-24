package com.parse.starter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class SignUp extends ActionBarActivity implements View.OnClickListener {
    EditText etName, etJoinDate,etEndDate,etAmount;
    Button bRegister;
    RadioGroup rg1,rg2,rg3;
    int selectedId1,selectedId2,selectedId3;
    String selected1,selected2,selected3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etName = (EditText) findViewById(R.id.etName);
        etJoinDate = (EditText) findViewById(R.id.etJoinDate);
        etEndDate = (EditText) findViewById(R.id.etEndDate);
        rg1= (RadioGroup) findViewById(R.id.radioGroup1);
        rg2= (RadioGroup) findViewById(R.id.radioGroup2);
        rg3= (RadioGroup) findViewById(R.id.radioGroup3);
        etAmount= (EditText) findViewById(R.id.etAmtPaid);
        bRegister = (Button) findViewById(R.id.bRegister);
        bRegister.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bRegister:
                selectedId1=rg1.getCheckedRadioButtonId();
                selectedId2=rg2.getCheckedRadioButtonId();
                selectedId3=rg3.getCheckedRadioButtonId();
                if(selectedId1==R.id.rb_yes){
                    selected1="Yes";
                }
                else{
                    selected1="No";
                }
                if(selectedId2==R.id.rb_gym){
                    selected2="Gym";
                }
                else if(selectedId2==R.id.rb_cardio){
                    selected2="Cardio";
                }
                else{
                    selected2="Gym + Cardio";
                }

                if(selectedId3==R.id.rb_paid){
                    selected3="Paid";
                }
                else if(selectedId3==R.id.rb_halfpaid){
                    selected3="Half Paid";
                }
                else{
                    selected3="Unpaid";
                }

                if(isEmpty(etName)||isEmpty(etJoinDate)||isEmpty(etEndDate)||isEmpty(etAmount)){
                    Toast.makeText(SignUp.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                }
                else{//new code of radio buttons
                    //set up a progress dialog
                    final ProgressDialog dlg=new ProgressDialog(SignUp.this);
                    dlg.setTitle("Please Wait...");
                    dlg.setMessage("Signing up. Please wait.");
                    dlg.show();
                    //set up a new parse object
                    ParseObject user_details=new ParseObject("UserDetails");

                    ParseACL acl=new ParseACL();
                    user_details.put("name",etName.getText().toString());
                    user_details.put("joindate",etJoinDate.getText().toString());
                    user_details.put("enddate",etEndDate.getText().toString());
                    user_details.put("personaltrainer",selected1);
                    user_details.put("type",selected2);
                    user_details.put("fees",selected3);
                    user_details.put("days","0");
                    user_details.put("amount",etAmount.getText().toString());
                    acl.setPublicReadAccess(true);
                    acl.setPublicWriteAccess(true);
                    user_details.setACL(acl);

                    user_details.saveInBackground();
                    Toast.makeText(this,"Registered successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,MainActivity.class));
                }
        }

    }

    private boolean isEmpty(EditText etText) {
        if(etText.getText().toString().trim().length()>0)
            return false;
        else
            return true;

    }
}
