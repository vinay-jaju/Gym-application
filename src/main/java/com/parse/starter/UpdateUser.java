package com.parse.starter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class UpdateUser extends ActionBarActivity implements SearchView.OnQueryTextListener {
    private memberAdapter adapter;
    private RecyclerView recyclerView;
    String titles1[]=new String[1000];
    String subtitles1[]=new String[1000];
    TextView title23;

    TextView subTitle23;

    // String[] titles={"Home","Hangout","Timetables","Faculty"};
    //String[] subtitles={"Home","Hangout","Timetables","Faculty"};
    public static List<member_info> getData(String[] titles, String[] subtitles){
        List<member_info> data=new ArrayList<>();
        //   int[] icons= {R.drawable.ic_account_balance_black_24dp,R.drawable.ic_alarm_on_black_24dp,R.drawable.ic_assignment_ind_black_24dp,R.drawable.ic_book_black_24dp};
        // String[] titles={"Home","Hangout","Timetables","Faculty"};
        for(int i=0;i<titles.length&&i<subtitles.length;i++){
            member_info current=new member_info();
            current.title=titles[i%titles.length];
            current.subTitle=subtitles[i%subtitles.length];
            data.add(current);
        }
        return data;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
        query.setLimit(1000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null) {
                    try {
                        int n = objects.size();
                        String titles[] = new String[n];
                        String subtitles[] = new String[n];
                        for (int i = 0; i < n; i++) {
                            ParseObject p = objects.get(i);
                            String fsname = p.getString("name");
                            String fsend = p.getString("enddate");
                            titles[i] = fsname;
                            subtitles[i] = fsend;

                        }
                        titles1 = titles;
                        subtitles1 = subtitles;
                        recyclerView = (RecyclerView) findViewById(R.id.delete_recyclerview);
                        adapter = new memberAdapter(getApplicationContext(), getData(titles, subtitles));
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View itemView, int position) {

                                title23 = (TextView) itemView.findViewById(R.id.lab_title);


                                // TODO Handle item click
                                      /*   ParseObject p = objects.get(position);
                                        String fsname = p.getString("name");
                                        String fsjoin = p.getString("joindate");
                                        String fsend = p.getString("enddate");
                                        String fstrainer=p.getString("personaltrainer");
                                        String fsfees=p.getString("fees");
                                        String fstype=p.getString("type");
                                        String fscount=p.getString("days");
                                       Intent intent = new Intent(DeleteUser.this, LoginDetails.class);
                                        intent.putExtra("name", fsname);
                                        intent.putExtra("joindate", fsjoin);
                                        intent.putExtra("enddate", fsend);
                                        intent.putExtra("personaltrainer",fstrainer);
                                        intent.putExtra("type",fstype);
                                        intent.putExtra("fees",fsfees);
                                        intent.putExtra("days",fscount);
                                        startActivity(intent);*/

                                    /*
                                        final ProgressDialog dlg = new ProgressDialog(UpdateUser.this);
                                        dlg.setTitle("Please Wait...");
                                        dlg.setMessage("Getting user. Please wait.");
                                        dlg.show();

                                        ParseObject p = objects.get(position);
                                        String fsname = p.getString("name");
                                        String fsjoin = p.getString("joindate");
                                        String fsend = p.getString("enddate");
                                        String fstrainer = p.getString("personaltrainer");
                                        String fsfees = p.getString("fees");
                                        String fstype = p.getString("type");
                                        String fscount = p.getString("days");
                                        String fsamount = p.getString("amount");
                                        Intent intent = new Intent(UpdateUser.this, Update.class);
                                        intent.putExtra("name", fsname);
                                        intent.putExtra("joindate", fsjoin);
                                        intent.putExtra("enddate", fsend);
                                        intent.putExtra("personaltrainer", fstrainer);
                                        intent.putExtra("type", fstype);
                                        intent.putExtra("fees", fsfees);
                                        intent.putExtra("days", fscount);
                                        intent.putExtra("amount", fsamount);
                                        startActivity(intent);
                                    */




                                ParseQuery<ParseObject> query1 = ParseQuery.getQuery("UserDetails");

                                query1.whereEqualTo("name", title23.getText().toString());

                                query1.findInBackground(new FindCallback<ParseObject>() {
                                    @Override
                                    public void done(List<ParseObject> objects, ParseException e) {
                                        if (e == null && objects != null) {
                                            try {

                                                ParseObject p = objects.get(0);
                                                String fsname = p.getString("name");
                                                String fsjoin = p.getString("joindate");
                                                String fsend = p.getString("enddate");
                                                String fstrainer = p.getString("personaltrainer");
                                                String fsfees = p.getString("fees");
                                                String fstype = p.getString("type");
                                                String fscount = p.getString("days");
                                                String fsamount = p.getString("amount");
                                                Intent intent = new Intent(UpdateUser.this, Update.class);
                                                intent.putExtra("name", fsname);
                                                intent.putExtra("joindate", fsjoin);
                                                intent.putExtra("enddate", fsend);
                                                intent.putExtra("personaltrainer", fstrainer);
                                                intent.putExtra("type", fstype);
                                                intent.putExtra("fees", fsfees);
                                                intent.putExtra("days", fscount);
                                                intent.putExtra("amount", fsamount);
                                                startActivity(intent);



                        /*ParseObject p = objects.get(0);
                        String fsname = p.getString("name");
                        String fsjoin = p.getString("joindate");
                        String fsend = p.getString("enddate");
                        Intent intent = new Intent(Members.this, LoginDetails.class);
                        intent.putExtra("name", fsname);
                        intent.putExtra("joindate", fsjoin);
                        intent.putExtra("enddate", fsend);
                        startActivity(intent);*/
                                            } catch (IndexOutOfBoundsException i) {
                                                Toast.makeText(UpdateUser.this, "Error", Toast.LENGTH_SHORT).show();


                                            }
                                        } else if (objects == null) {
                                            Toast.makeText(UpdateUser.this, "Invalid data", Toast.LENGTH_SHORT).show();
                                        }


                                    }


                                });


                            }
                        }));
                    } catch (IndexOutOfBoundsException i) {
                        Toast.makeText(UpdateUser.this, "Error", Toast.LENGTH_SHORT).show();


                    }
                } else if (objects == null) {
                    Toast.makeText(UpdateUser.this, "Invalid data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_members, menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<member_info> filter(List<member_info> models, String query) {
        query = query.toLowerCase();

        final List<member_info> filteredModelList = new ArrayList<>();
        for (member_info model : models) {
            final String text = model.title.toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<member_info> filteredModelList = filter(getData(titles1,subtitles1), query);
        adapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        return true;
    }



}