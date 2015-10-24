package com.parse.starter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class LoginDetails extends ActionBarActivity implements View.OnClickListener {
Button btLogout;
    TextView flname,fljoin,flend,flfees,fltrainer,fltype,days,amountpaid;
    Button add,sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_details);
        btLogout= (Button) findViewById(R.id.btLogout);
        btLogout.setOnClickListener(this);
        flname= (TextView) findViewById(R.id.ftname);
        fljoin= (TextView) findViewById(R.id.ftjoin);
        flend= (TextView) findViewById(R.id.ftend);
        flfees= (TextView) findViewById(R.id.fees_info);
        fltrainer= (TextView) findViewById(R.id.ptrainer);
        fltype= (TextView) findViewById(R.id.memtype);
        days= (TextView) findViewById(R.id.count);
        amountpaid= (TextView) findViewById(R.id.amount_paid);
        add= (Button) findViewById(R.id.count2);
        sub= (Button) findViewById(R.id.count1);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);

        Intent intent=getIntent();
        String fsname=intent.getStringExtra("name");
        String fsjoin=intent.getStringExtra("joindate");
        String fsend=intent.getStringExtra("enddate");
        String p_trainer=intent.getStringExtra("personaltrainer");
        String type=intent.getStringExtra("type");
        String fees=intent.getStringExtra("fees");
        String count=intent.getStringExtra("days");
        String amount=intent.getStringExtra("amount");
        flfees.setText(fees);
        fltype.setText(type);
        fltrainer.setText(p_trainer);
        flname.setText(fsname);
        fljoin.setText(fsjoin);
        flend.setText(fsend);
        days.setText(count);
        amountpaid.setText(amount);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogout:
                flname.setText("");
                fljoin.setText("");
                flend.setText("");
                flfees.setText("");
                fltype.setText("");
                fltrainer.setText("");
                amountpaid.setText("00");
                days.setText("0");
                startActivity(new Intent(this,MainActivity.class));
                break;


            case R.id.count1:
               String count1=  days.getText().toString();
                Integer count2=Integer.valueOf(count1);
                if(count2<=0){
                    Toast.makeText(this,"Cannot decrease",Toast.LENGTH_SHORT).show();
                }
                else{
                    count2=count2-1;
                    days.setText(String.valueOf(count2));
                }
                //code for saving the count to update the user

                ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");

                query.whereEqualTo("name", flname.getText().toString());

                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null && objects != null) {
                            try {

                                ParseObject p = objects.get(0);
                                p.put("days", days.getText().toString());
                                p.saveInBackground();

                            } catch (IndexOutOfBoundsException i) {
                                Toast.makeText(LoginDetails.this, "Error", Toast.LENGTH_SHORT).show();

                            }
                        } else if (objects == null) {
                            Toast.makeText(LoginDetails.this, "Invalid data", Toast.LENGTH_SHORT).show();
                        }


                    }


                });
                break;



            case R.id.count2:
                String count3= (String) days.getText();
                Integer count4=Integer.valueOf(count3);
                if(count4>=730){
                    Toast.makeText(this,"2 years completed. Create new account",Toast.LENGTH_SHORT).show();
                }
                else{
                    count4=count4+1;
                    days.setText(String.valueOf(count4));
                }
                //code for saving the count to update the user

                ParseQuery<ParseObject> query1 = ParseQuery.getQuery("UserDetails");

                query1.whereEqualTo("name", flname.getText().toString());

                query1.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null && objects!=null) {
                            try {

                                ParseObject p = objects.get(0);
                                p.put("days",days.getText().toString());
                                p.saveInBackground();

                            }
                            catch(IndexOutOfBoundsException i){
                                Toast.makeText(LoginDetails.this,"Error",Toast.LENGTH_SHORT).show();

                            }
                        } else if(objects==null) {
                            Toast.makeText(LoginDetails.this, "Invalid data", Toast.LENGTH_SHORT).show();
                        }


                    }


                });
                break;


        }
    }
}