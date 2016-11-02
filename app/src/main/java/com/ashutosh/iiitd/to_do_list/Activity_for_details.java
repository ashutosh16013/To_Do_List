package com.ashutosh.iiitd.to_do_list;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

import helperClasses.DBManager;

public class Activity_for_details extends AppCompatActivity  {

    private Button mButton_for_db;
    private EditText mText_for_title;
    private EditText mText_for_details;
    private TextView mText_for_date;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_details);
        mButton_for_db = (Button)findViewById(R.id.button_for_add);
        mText_for_title = (EditText)findViewById(R.id.input_title);
        mText_for_details = (EditText)findViewById(R.id.input_detail);
        mText_for_date = (TextView)findViewById(R.id.dateView);
        mButton_for_db.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                String title = mText_for_title.getText().toString();
                String details = mText_for_details.getText().toString();
                String date = mText_for_date.getText().toString();
                if(!title.equals("")&&!details.equals("")&&!date.equals(""))
                {
                    //Enter value into database
                    DBManager db = new DBManager(getApplicationContext());
                    db.insertToDo(title,details,date);
                    Intent intent = new Intent(Activity_for_details.this,MainActivity.class);
                    Toast.makeText(getApplicationContext(),"Value successfully entered",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please fill in all the fields",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("The values are yet to save. Do you wish to exit now ?");

        alertDialogBuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(Add_medicines_2.this,"You clicked yes button",Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                overridePendingTransition  (R.anim.right_slide_in, R.anim.right_slide_out);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        mText_for_date.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
}
