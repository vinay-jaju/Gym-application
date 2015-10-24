package com.parse.starter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Update extends ActionBarActivity implements View.OnClickListener {
    EditText etName, etJoinDate, etEndDate, days,paidamount;
    Button bUpdate;
    RadioGroup rg1, rg2, rg3;
    int selectedId1, selectedId2, selectedId3;
    String selected1, selected2, selected3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        etName = (EditText) findViewById(R.id.etName);
        etJoinDate = (EditText) findViewById(R.id.etJoinDate);
        etEndDate = (EditText) findViewById(R.id.etEndDate);
        paidamount= (EditText) findViewById(R.id.up_amount);
        rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
        rg2 = (RadioGroup) findViewById(R.id.radioGroup2);
        rg3 = (RadioGroup) findViewById(R.id.radioGroup3);
        days = (EditText) findViewById(R.id.days_count);
        bUpdate = (Button) findViewById(R.id.bUpdate);
        bUpdate.setOnClickListener(this);

        //getting the intent
        Intent intent = getIntent();
        String fsname = intent.getStringExtra("name");
        String fsjoin = intent.getStringExtra("joindate");
        String fsend = intent.getStringExtra("enddate");
        String p_trainer = intent.getStringExtra("personaltrainer");
        String type_mem = intent.getStringExtra("type");
        String fees = intent.getStringExtra("fees");
        String count = intent.getStringExtra("days");
        String amount_pay=intent.getStringExtra("amount");


        etName.setText(fsname);
        etJoinDate.setText(fsjoin);
        etEndDate.setText(fsend);
        days.setText(count);
        paidamount.setText(amount_pay);
        if (p_trainer.equals("Yes") ) {
            rg1.check(R.id.rb_yes);
        } else {
            rg1.check(R.id.rb_no);
        }
        if (type_mem.equals("Gym")) {
            rg2.check(R.id.rb_gym);
        } else if (type_mem.equals("Cardio")) {
            rg2.check(R.id.rb_cardio);
        } else {
            rg2.check(R.id.rb_gymcardio);
        }
        if (fees.equals("Paid") ) {
            rg3.check(R.id.rb_paid);
        }
        else if(fees.equals("Half Paid")){
            rg3.check(R.id.rb_halfpaid);
        }
        else {
            rg3.check(R.id.rb_unpaid);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bUpdate:
                Intent intent = getIntent();
                String name = intent.getStringExtra("name");
                selectedId1 = rg1.getCheckedRadioButtonId();
                selectedId2 = rg2.getCheckedRadioButtonId();
                selectedId3 = rg3.getCheckedRadioButtonId();
                if (selectedId1 == R.id.rb_yes) {
                    selected1 = "Yes";
                } else {
                    selected1 = "No";
                }
                if (selectedId2 == R.id.rb_gym) {
                    selected2 = "Gym";
                } else if (selectedId2 == R.id.rb_cardio) {
                    selected2 = "Cardio";
                } else {
                    selected2 = "Gym + Cardio";
                }

                if (selectedId3 == R.id.rb_paid) {
                    selected3 = "Paid";
                }
                else if(selectedId3==R.id.rb_halfpaid){
                    selected3="Half Paid";
                }
                else {
                    selected3 = "Unpaid";
                }

                if (etName.getText().toString().equals("")||paidamount.getText().toString().equals("")|| etJoinDate.getText().toString().equals("") ||etEndDate.getText().toString().equals("")||days.getText().toString().equals("")) {
                    Toast.makeText(Update.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                } else {
                    final ProgressDialog dlg = new ProgressDialog(Update.this);
                    dlg.setTitle("Please Wait...");
                    dlg.setMessage("Updating user. Please wait.");
                    dlg.show();
                    //set up a new parse object
                    ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");

                    query.whereEqualTo("name", name);

                    query.findInBackground(new FindCallback<ParseObject>() {
                        @Override
                        public void done(List<ParseObject> objects, ParseException e) {
                            if (e == null && objects != null) {
                                try {

                                    ParseObject p = objects.get(0);
                                    p.put("name", etName.getText().toString());
                                    p.put("joindate", etJoinDate.getText().toString());
                                    p.put("enddate", etEndDate.getText().toString());
                                    p.put("personaltrainer",selected1);
                                    p.put("type",selected2);
                                    p.put("fees",selected3);
                                    p.put("days",days.getText().toString());
                                    p.put("amount",paidamount.getText().toString());
                                    p.saveInBackground();
                                    startActivity(new Intent(Update.this,MainActivity.class));
                               /* String fsname = p.getString("name");
                                String fsjoin = p.getString("joindate");
                                String fsend = p.getString("enddate");
                                Intent intent = new Intent(Update.this, LoginDetails.class);
                                intent.putExtra("name", fsname);
                                intent.putExtra("joindate", fsjoin);
                                intent.putExtra("enddate", fsend);
                                startActivity(intent);*/
                                } catch (IndexOutOfBoundsException i) {
                                    Toast.makeText(Update.this, "No such username", Toast.LENGTH_SHORT).show();
                                    dlg.cancel();

                                }
                            } else if (objects == null) {
                                Toast.makeText(Update.this, "Invalid data", Toast.LENGTH_SHORT).show();
                            }


                        }


                    });
                    break;
                }

        }
    }
}