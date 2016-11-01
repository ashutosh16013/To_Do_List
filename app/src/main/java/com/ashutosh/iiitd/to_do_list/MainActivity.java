package com.ashutosh.iiitd.to_do_list;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import helperClasses.DBManager;
import helperClasses.ListItem;

public class MainActivity extends AppCompatActivity {
    private Button mButton_for_todo;
    private Button mButton_to_view;

    private ArrayList<ListItem> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton_for_todo = (Button)findViewById(R.id.btn_add_todo);
        mButton_for_todo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent_for_add = new Intent(MainActivity.this,Activity_for_details.class);
                startActivity(intent_for_add);
                overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
            }
        });


        //Begin recycler view
        recyclerView = (RecyclerView) findViewById(R.id.recycler_for_list);

        mAdapter = new ListAdapter(itemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ListItem item = itemList.get(position);
                Intent intent_to_display = new Intent(MainActivity.this,Display_Details.class);
                startActivity(intent_to_display);
                overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareListData();


    }

    void prepareListData(){
        DBManager db = new DBManager(getApplicationContext());
        ArrayList<String[]> items = db.getAllData();
        int i=0;
        Iterator it = items.iterator();
        if(db.numberOfRows()>0)
        {
            while(it.hasNext()){

                String temp[] = (String [])it.next();
                String title = temp[0];
                String details = temp[1];
                ListItem temp_obj=  new ListItem(title,details);
                itemList.add(temp_obj);
                i++;
            }
            mAdapter.notifyDataSetChanged();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"No items to display",Toast.LENGTH_SHORT).show();
        }
    }
}
