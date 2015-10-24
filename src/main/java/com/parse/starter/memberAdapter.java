package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collections;
import java.util.List;

/**
 * Created by vinay on 10/12/2015.
 */
public class memberAdapter extends RecyclerView.Adapter<memberAdapter.myViewHolder> {
    private LayoutInflater inflater;
    private Context context;

    List<member_info> data = Collections.emptyList();

    public memberAdapter(Context context,List<member_info> data) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= inflater.from(parent.getContext())
                .inflate(R.layout.member_row, parent, false);
        myViewHolder holder=new myViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        member_info current=data.get(position);

        holder.title.setText(current.title);
        holder.subTitle.setText(current.subTitle);

    }



    @Override
    public int getItemCount() {

        return data.size();
    }


    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;

        TextView subTitle;


        public myViewHolder(View itemView) {

            super(itemView);

            title = (TextView) itemView.findViewById(R.id.lab_title);

            subTitle = (TextView) itemView.findViewById(R.id.lab_subtitle);
        }



        @Override
        public void onClick(View v) {


  /*          ParseQuery<ParseObject> query = ParseQuery.getQuery("UserDetails");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null && objects != null) {
                        try {
                            int n=getAdapterPosition();
                            ParseObject p = objects.get(n);
                            String fsname = p.getString("name");
                            String fsjoin = p.getString("joindate");
                            String fsend = p.getString("enddate");

                            Intent intent = new Intent(context, LoginDetails.class);
                            intent.putExtra("name", fsname);
                            intent.putExtra("joindate", fsjoin);
                            intent.putExtra("enddate", fsend);
                            context.startActivity(intent);


                        } catch (IndexOutOfBoundsException i) {
                            Toast.makeText(context, "No such username", Toast.LENGTH_SHORT).show();


                        }
                    } else if (objects == null) {
                        Toast.makeText(context, "Invalid data", Toast.LENGTH_SHORT).show();
                    }


                }


            });

        }*/
    }
    }


//applying animations

    public void animateTo(List<member_info> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }
    private void applyAndAnimateRemovals(List<member_info> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final member_info model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }


    private void applyAndAnimateAdditions(List<member_info> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final member_info model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }


    private void applyAndAnimateMovedItems(List<member_info> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final member_info model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }
/*Implementing the functions for searching*/
public member_info removeItem(int position){
    final member_info model=data.remove(position);
    notifyItemRemoved(position);
    return model;
}

    public String getName(member_info model){
        String my_title=model.title;
        return my_title;
    }

    public void addItem(int position, member_info model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final member_info model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

}
