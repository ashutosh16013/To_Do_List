package helperClasses;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Ashutosh on 31-10-2016.
 */

public class DBManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "To_Do_List";
    private static final String TABLE_NAME = "Tasks";
    private static final String TASKS_COLUMN_TITLE = "Title";
    private static final String TASKS_COLUMN_DETAILS = "Details";
    private static final String TASKS_COLUMN_TIME = "Timestamp";

    public DBManager(Context context)
    {
        super(context, DB_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " +TABLE_NAME+" ("+TASKS_COLUMN_TITLE+" text, "+TASKS_COLUMN_DETAILS+" text, "+TASKS_COLUMN_TIME
        +" DATETIME);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertToDo (String title, String details)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASKS_COLUMN_TITLE, title);
        contentValues.put(TASKS_COLUMN_DETAILS, details);
        /*
        Inserting date
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "insert into grievance(id, name, filename, inserted_on) values('" + id + "', '" + name+ "', '" + filename + "', '" + d +"')";
        db.execSQL(query);
         */
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String d = dateFormat.format(date);
        contentValues.put(TASKS_COLUMN_TIME, d);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db,TABLE_NAME);
        return numRows;
    }

    public ArrayList<String[]> getAllData()
    {
        ArrayList<String[]> array_list = new ArrayList<String[]>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();
        String []temp;
        while(res.isAfterLast() == false){
            temp = new String[2];
            temp[0] = res.getString(res.getColumnIndex(TASKS_COLUMN_TITLE));
            temp[1] = res.getString(res.getColumnIndex(TASKS_COLUMN_DETAILS));;
            array_list.add(temp);
            res.moveToNext();
        }
        return array_list;
    }




}
