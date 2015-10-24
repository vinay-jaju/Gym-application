package com.parse.starter;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
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

public class Members extends ActionBarActivity implements SearchView.OnQueryTextListener {

    private memberAdapter adapter;
    private RecyclerView recyclerView;
    TextView title23;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    String titles1[]=new String[1000];
    String subtitles1[]=new String[1000];
   // String[] titles={"Home","Hangout","Timetables","Faculty"};
    //String[] subtitles={"Home","Hangout","Timetables","Faculty"};
    public static List<member_info> getData(String[] titles, String[] subtitles){
        List<member_info> data=new ArrayList<>();
        //   int[] icons= {R.drawable.ic_account_balance_black_24dp,R.drawable.ic_alarm_on_black_24dp,R.drawable.ic_assignment_ind_black_24dp,R.drawable.ic_book_black_24dp};
        // String[] titles={"Home","Hangout","Timetables","Faculty"};
        for(int i=0;i<titles.length&&i<subtitles.length;i++){
            member_info current=new member_info();
            current.title=titles[i];
            current.subTitle=subtitles[i];
            data.add(current);
        }
        return data;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
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
                        recyclerView = (RecyclerView) findViewById(R.id.lab_recycler_view);
                        adapter = new memberAdapter(getApplicationContext(), getData(titles, subtitles));
                        recyclerView.setAdapter(adapter);
                        final LinearLayoutManager mLayoutManager;
                        mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);

                        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                                visibleItemCount = mLayoutManager.getChildCount();
                                totalItemCount = mLayoutManager.getItemCount();
                                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                                if (loading) {
                                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                        loading = false;

                                        Log.v("...", "Last Item Wow !");
                                    }
                                }
                            }
                        });

                        adapter.notifyDataSetChanged();
                        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                title23 = (TextView) view.findViewById(R.id.lab_title);
                                // TODO Handle item click


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
                                                Intent intent = new Intent(Members.this, LoginDetails.class);
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
                                                Toast.makeText(Members.this, "Error", Toast.LENGTH_SHORT).show();


                                            }
                                        } else if (objects == null) {
                                            Toast.makeText(Members.this, "Invalid data", Toast.LENGTH_SHORT).show();
                                        }


                                    }


                                });


                            }
                        }
                        ));
                    }
                    catch (IndexOutOfBoundsException i) {
                        Toast.makeText(Members.this, "Error", Toast.LENGTH_SHORT).show();


                    }
                } else if (objects == null) {
                    Toast.makeText(Members.this, "Invalid data", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        final List<member_info> filteredModelList = filter(getData(titles1,subtitles1), query);
        adapter.animateTo(filteredModelList);
        recyclerView.scrollToPosition(0);
        return true;

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
}




