package com.travelease.nitant.database;

import static com.travelease.nitant.ui.Trip.TripManageActivity.refreshActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.travelease.nitant.ui.Trip.ActivityModel;
import com.travelease.nitant.ui.Trip.TripItemModel;

import java.util.ArrayList;
import java.util.List;

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
        cv.put(UID,activityModel.getUid());
        cv.put(DATE,activityModel.getDate());
        db.insert(TBLNAME,ID,cv);
        db.close();
    }

    public List<ActivityModel> showActivity(String unique)
    {
        SQLiteDatabase db = getReadableDatabase();
//        String column[]={ID,UID,ACTIVITY,DATE};
//        Cursor c =db.query(TBLNAME,column,null,null,null,null,null);
        Cursor c =db.rawQuery( "select * from "+TBLNAME+" where "+UID+" = "+ unique, null );
        ArrayList<ActivityModel> activityModelArrayList = new ArrayList<>();

        while (c.moveToNext())
        {
            int id = c.getInt(0);
            String uid = c.getString(1);
            String activity = c.getString(2);
            String date = c.getString(3);

            ActivityModel activityModel = new ActivityModel();
            activityModel.setId(id);
            activityModel.setUid(uid);
            activityModel.setActivity(activity);
            activityModel.setDate(date);

            activityModelArrayList.add(activityModel);
        }


        return activityModelArrayList;
    }

    public void deleteActivity(Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = ID+"="+id;
        db.delete(TBLNAME,where,null);
        db.close();
    }
}
