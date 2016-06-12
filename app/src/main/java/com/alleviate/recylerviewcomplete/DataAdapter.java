package com.alleviate.recylerviewcomplete;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by felix on 9/6/16.
 * Created at Alleviate.
 * shirishkadam.com
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    ArrayList mcu_movies;
    Context context;

    public DataAdapter(Context context, ArrayList mcu_movies) {
        this.mcu_movies = mcu_movies;
        this.context = context;

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, final int position) {
        ((TextView) holder.itemView.findViewById(R.id.textView)).setText(mcu_movies.get(position).toString());

        ((RelativeLayout) holder.itemView.findViewById(R.id.relative_layout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(mcu_movies.get(position).toString());
            }
        });

        ((RelativeLayout) holder.itemView.findViewById(R.id.relative_layout)).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                remove(mcu_movies.get(position).toString());
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mcu_movies.size();
    }

    // Remove a RecyclerView item containing a specified Data object
    public void remove(String movie) {
        int position = mcu_movies.indexOf(movie);
        mcu_movies.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public void update(String movie) {
        int position = mcu_movies.indexOf(movie);
        mcu_movies.set(position,"Movie Liked");
        notifyItemChanged(position);
    }

    public void insert(int position) {
        mcu_movies.add(position, "New Movie");
        notifyItemInserted(position);
        //getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
