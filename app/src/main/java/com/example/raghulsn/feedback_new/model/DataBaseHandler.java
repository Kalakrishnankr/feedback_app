/*
package com.example.raghulsn.feedback_new.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

*/
/**
 * Created by raghul.sn on 18/10/16.
 *//*


public class DataBaseHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Mkdb.db";
    public static final String TABLE_NAME_CUSTOMER = "customer";
    public static final String CUSTOMER_COLUMN_ID = "id";
    public static final String CUSTOMER_COLUMN_RATING = "rating";
    public static final String CUSTOMER_COLUMN_SEX = "sex";
    public static final String CUSTOMER_COLUMN_NAME = "name";
    public static final String CUSTOMER_COLUMN_AGE = "age";
    public static final String CUSTOMER_COLUMN_EMAILID= "email";
    public static final String CUSTOMER_COLUMN_PHONENO = "phone";
    public static final String CUSTOMER_COLUMN_DATE = "date";
    public static final String CUSTOMER_COLUMN_TIME = "time";

    public static final String TABLE_QN = "question";
    public static final String QUES_COLUMN_ID = "id";
    public static final String QUESTION = "ques";


    public static final String TABLE_NAME_3 = "rating";
    public static final String RATING_COLUMN_ID = "id";
    public static final String CUSTOMER_ID = "cid";
    public static final String QUES_ID = "qid";
    public static final String RATING = "rating";

    public DataBaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_NAME_CUSTOMER + "("
                + CUSTOMER_COLUMN_ID + " INTEGER PRIMARY KEY," + CUSTOMER_COLUMN_RATING + " TEXT," + CUSTOMER_COLUMN_SEX + " TEXT,"
                + CUSTOMER_COLUMN_NAME + " TEXT," + CUSTOMER_COLUMN_AGE + " TEXT,"
                + CUSTOMER_COLUMN_EMAILID + " TEXT," + CUSTOMER_COLUMN_PHONENO + " TEXT,"
                + CUSTOMER_COLUMN_DATE + " TEXT," + CUSTOMER_COLUMN_TIME + " TEXT"
                + ")";
        db.execSQL(CREATE_CUSTOMER_TABLE);

        String CREATE_QN_TABLE = "CREATE TABLE " + TABLE_QN + "("
                + QUES_COLUMN_ID + " INTEGER PRIMARY KEY," + QUESTION + " TEXT"+ ")";

        db.execSQL(CREATE_QN_TABLE);




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CUSTOMER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QN);

    }
    long insertRatingDetails(CacheSingleEntity entity) {
        long rowId = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CUSTOMER_COLUMN_ID, entity.columnId);
            values.put(CUSTOMER_COLUMN_RATING, entity.rating);
            values.put(CUSTOMER_COLUMN_SEX, entity.sex);
            values.put(CUSTOMER_COLUMN_NAME, entity.name);
            values.put(CUSTOMER_COLUMN_AGE, entity.age);
            values.put(CUSTOMER_COLUMN_EMAILID, entity.email);
            values.put(CUSTOMER_COLUMN_PHONENO, entity.phnnumber);
            values.put(CUSTOMER_COLUMN_DATE, entity.date);
            values.put(CUSTOMER_COLUMN_TIME, entity.time);
            // Inserting Rows
            rowId = db.insert(TABLE_NAME_CUSTOMER, null, values);
            db.close(); // Closing database connection
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowId;
    }
    public int getPRatingDetails(String id,String user) {
        int result=0;
        try {
            String countQuery = "select * from customer where id = '" + id + "' and user = '" + user + "'";
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            result = cursor.getCount();
            cursor.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public Cursor getRatingDetails() {
        int result=0;
        Cursor cursor =null;
        try {
            String countQuery = "select * from question";
            SQLiteDatabase db = this.getReadableDatabase();

            cursor = db.rawQuery(countQuery, null);
            result = cursor.getCount();
           // cursor.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cursor;
    }
    long insertQNDetails( String columnId,String qn ) {
        long rowId = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(QUES_COLUMN_ID, columnId);
            values.put(QUESTION, qn);
            // Inserting Rows
            rowId = db.insert(TABLE_QN, null, values);
            db.close(); // Closing database connection
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowId;
    }
}
*/
