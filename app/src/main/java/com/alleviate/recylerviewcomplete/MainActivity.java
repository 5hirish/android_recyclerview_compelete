package com.alleviate.recylerviewcomplete;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RecyclerView.Adapter rvadpter;
    int new_movie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Marvel Universe");

        String movies[] = getResources().getStringArray(R.array.mcu_titles);
        final ArrayList mcu_movies = new ArrayList<String>(Arrays.asList(movies));

        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_view);
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager rvlayoutmanager = new LinearLayoutManager(this);
        rv.setLayoutManager(rvlayoutmanager);

        rvadpter = new DataAdapter(getApplicationContext(), mcu_movies);
        rv.setAdapter(rvadpter);

        rv.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action :"+mcu_movies.size(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                String movies[] = getResources().getStringArray(R.array.mcu_titles_coming);
                int position = mcu_movies.size();
                mcu_movies.add(position, movies[new_movie]);
                rvadpter.notifyItemInserted(position);
                rvadpter.notifyDataSetChanged();

                new_movie ++;
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
