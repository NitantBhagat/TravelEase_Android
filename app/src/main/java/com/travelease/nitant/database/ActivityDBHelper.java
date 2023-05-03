package com.travelease.nitant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.travelease.nitant.ui.Trip.ActivityModel;

public class ActivityDBHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "activitydb";
    private static final String TBLNAME ="activity";
    private static final String ID ="id";
//    uid IS FOR SETTING DATA FOR SPECIFIC TRIP WHICH IS GETTING FROM SHOW ACTIVITY OF TRIPS
    private static final String UID ="uid";
    private static final String DATE ="date";
    private static final String ACTIVITY="activity";
    private static final int VERSION = 1 ;


    public ActivityDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE "+TBLNAME+"(" +ID+" INTEGER PRIMARY KEY AUTOINCREMENT," + UID + " TEXT," + ACTIVITY + " TEXT," + DATE + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertActivity(ActivityModel activityModel){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ACTIVITY,activityModel.getActivity());
        cv.put(UID,activityModel.getId());
        db.insert(TBLNAME,ID,cv);
        db.close();
    }
}
