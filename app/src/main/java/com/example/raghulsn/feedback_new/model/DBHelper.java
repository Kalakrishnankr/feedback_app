package com.example.raghulsn.feedback_new.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user on 29/6/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    //Table One customer
    public static final String DATABASE_NAME = "Mkdb.db";
    public static final String TABLE_CUSTOMER = "customer";
    public static final String CUSTOMER_COLUMN_ID = "id";
    public static final String CUSTOMER_COLUMN_SEX = "sex";
    public static final String CUSTOMER_COLUMN_NAME = "name";
    public static final String CUSTOMER_COLUMN_AGE = "age";
    public static final String CUSTOMER_COLUMN_EMAILID= "email";
    public static final String CUSTOMER_COLUMN_PHONENO = "phone";
    public static final String CUSTOMER_COLUMN_DATE = "date";

    //Table two login
    public static final String TABLE_NAME_2 = "login";
    public static final String LOGIN_COLUMN_USER_ID = "u_id";
    public static final String LOGIN_COLUMN_USER_TYPE = "type";
    public static final String LOGIN_COLUMN_USERNAME = "uname";
    public static final String LOGIN_COLUMN_PASSWORD = "password";

    //Table Three Questions
    public static final String TABLE_QN = "question";
    public static final String QUES_COLUMN_ID = "id";
    public static final String QUESTION = "ques";


    public static final String TABLE_RATING = "rating";
    public static final String RATING_COLUMN_ID = "id";
    public static final String CUSTOMER_ID = "cid";
    public static final String QUES_ID = "qid";
    public static final String RATING = "rate";


    private HashMap hp;
    private ArrayList<Question> allQuestions;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table customer " +
                        "(id integer primary key, name text,age text,sex text,email text,phone text,date text)"
        );
        db.execSQL(
                "create table login " +
                        "(u_id integer primary key,type text,uname text,password text)"
        );

        db.execSQL(
                "create table question " +
                        "(id integer primary key,ques text)"
        );

        db.execSQL(
                "create table rating " +
                        "(id integer primary key,cid integer,qid integer,rate integer)"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS customer");
       // db.execSQL("DROP TABLE IF EXISTS film_details");
       // db.execSQL("DROP TABLE IF EXISTS film_atndlist");
        onCreate(db);
    }

    public boolean customer(String name, String age, String sex, String mail,String phone, String date) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("age", age);
            contentValues.put("sex", sex);
            contentValues.put("email", mail);
            contentValues.put("phone", phone);
            contentValues.put("date", date);
            long flag = db.insert("customer", null, contentValues);
            Log.e("Task", "Success" + flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

   /* public boolean login(Integer id,String type, String uname,String password) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put("fid", id);
            contentValues.put("rating", rate);
            contentValues.put("desc", describ);

            long flag = db.insert("film_details", null, contentValues);
            Log.e("", "" + flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }*/

    /*public long insert(int fid,String dat) {
        long flag=0;
        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("fid", fid);
            contentValues.put("date", dat);
             flag = db.insert("film_atndlist", null, contentValues);
            Log.e("", "" + flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
*/



    /*public Cursor getData() {
        Cursor res = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("select * from film", null);


        } catch (Exception e) {
            e.printStackTrace();name
        }
        return res;
    }
    public Cursor getDetails(Integer fid) {
        Cursor res = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("select * from film_details where fid="+fid+"", null);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Cursor get(String dat) {
        Cursor res = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("select * from film_atndlist", null);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public Cursor geteditDetails(Integer id) {
        Cursor res = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("select * from film_details where id="+id+"", null);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public List<String> getAllLabels() {
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT name FROM register";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }


    public Cursor searchData(String nm) {
        Cursor res = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("select * from film where name LIKE '" + nm + "%'", null);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


  *//*  public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase(); public long insertRating(String id,String cid,String qid,String rating){
        long rowId=0;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(CUSTOMER_ID, cid);
            values.put(QUES_ID, qid);
            values.put(RATING, rating);
            rowId = db.insert(TABLE_RATING, null, values);
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rowId;.ques,rating.rate
    }
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FILM_TABLE_NAME_1);
        return numRows;
    }*//*

    public boolean updatecomments(Integer id, String rate, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rating", rate);
        contentValues.put("desc", desc);
        db.update("film_details", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public boolean deleteContact(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("film_details",
                "id = ? ",
                new String[]{Integer.toString(id)});
        return true;
    }

    public ArrayList<String> getAllCotacts() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from contacts", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(FILM_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }*/

    public Cursor getQuestnDetails() {
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

    /*public Cursor getRatingList() {
        int result=0;
        Cursor cursor =null;
        try {
            String countQuery1 ="select  rating.qid,question.ques,rating.SUM(rate) from rating JOIN question on question.id=rating.qid GROUP BY rating.qid";
            //select question.ques,rating.rate from rating JOIIN question on question.id=rating.qid
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(countQuery1, null);
            result = cursor.getCount();
            // cursor.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return cursor;
    }
*/
    public int getTotalRating(Integer qid) {

        int sum=0;
        Cursor cursor =null;
        try {
//            String countQuery2 ="SELECT SUM(rate) AS Totalrating FROM rating where qid="+qid+"";
            String countQuery2 ="SELECT SUM(rate) FROM rating where qid="+qid+"";
            //select question.ques,rating.rate from rating JOIN question on question.id=rating.qid
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(countQuery2, null);
            if(cursor != null)
            {
                cursor.moveToFirst();
                sum = cursor.getInt(0);
            }
//            sum=cursor.getColumnCount();

            // cursor.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sum;
    }


    public int getRating() {

        int result=0;
        Cursor cursor =null;
        try {
            String countQuery = "select * from rating";
            SQLiteDatabase db = this.getReadableDatabase();

            cursor = db.rawQuery(countQuery, null);
            result = cursor.getCount();
            // cursor.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public int getTotalcustmr(Integer qid) {

        int count=0;
        Cursor cursor =null;
        try {
//            String countQuery2 ="SELECT SUM(rate) AS Totalrating FROM rating where qid="+qid+"";
            String countQuery2 ="SELECT rate FROM rating where qid="+qid+"";
            //select question.ques,rating.rate from rating JOIN question on question.id=rating.qid
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(countQuery2, null);
            count = cursor.getCount();
            /*if(cursor != null)
            {
                cursor.moveToFirst();
                sum = cursor.getInt(0);
            }*/
//            sum=cursor.getColumnCount();

            // cursor.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return count;
    }





    public long insertQNDetails(String columnId, String qn) {
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


    public long insertCustDetails(String name, String age, String sex, String mail,String phone, String date) {

            long flag=0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("age", age);
            contentValues.put("sex", sex);
            contentValues.put("email", mail);
            contentValues.put("phone", phone);
            contentValues.put("date", date);
             flag = db.insert(TABLE_CUSTOMER, null, contentValues);
            Log.e("Task", "Success" + flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public long insertRating(long cid,Integer qid,Integer rt){
        long rowId=0;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(CUSTOMER_ID, cid);
            values.put(QUES_ID, qid);
            values.put(RATING, rt);
            rowId = db.insert(TABLE_RATING, null, values);
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rowId;
    }



    public ArrayList<Question> getAllQuestions() {

        ArrayList<Question> allQuestions = new ArrayList<Question>();
        Cursor cursor = this.getQuestnDetails();
        if (cursor.moveToFirst()) {

            do {

                allQuestions.add(new Question(cursor.getInt(0), cursor.getString(1)));


            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {

            cursor.close();
        }
        return allQuestions;
    }
}
