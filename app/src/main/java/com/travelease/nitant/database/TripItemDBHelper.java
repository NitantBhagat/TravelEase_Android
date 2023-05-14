package com.travelease.nitant.database;

import static com.travelease.nitant.ui.Trip.TripFragment.refresh;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.travelease.nitant.ui.Trip.TripFragment;
import com.travelease.nitant.ui.Trip.TripItemModel;

import java.util.ArrayList;
import java.util.List;


public class TripItemDBHelper extends SQLiteOpenHelper {

    private static Context context  ;
    private static final String DBNAME = "trip.db";
    private static final String TBLNAME = "trips";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DETAIL = "detail";
    private static final int VERSION =1 ;


    public TripItemDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TBLNAME +" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+TITLE+" TEXT, "+DETAIL+" TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void Insert(TripItemModel trip)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",trip.getTitle());
        cv.put("detail",trip.getDetail());
        long Result = db.insert(TBLNAME,ID,cv);
        if(Result==-1)
        {
            Toast.makeText( context, "Failed!", Toast.LENGTH_SHORT).show();
        }else
        {
            refresh();
            Toast.makeText(context, "Added Succesdully", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    public List<TripItemModel> Show(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TripItemModel> tripdata = new ArrayList<>();

        String[] column = {ID,TITLE,DETAIL};
        Cursor c = db.query(TBLNAME,column,null,null,null,null,null);
        while (c.moveToNext())
        {
            int id = c.getInt(0);
            String title = c.getString(1);
            String detail = c.getString(2);

            TripItemModel tripItemModel = new TripItemModel();
            tripItemModel.setId(id);
            tripItemModel.setTitle(title);
            tripItemModel.setDetail(detail);

            tripdata.add(tripItemModel);
        }


        return tripdata;
    }

    public void Update(TripItemModel trip){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title",trip.getTitle());
        cv.put("detail",trip.getDetail());
        String where = ID+"="+trip.getId();
        long Result = db.update(TBLNAME,cv,where,null);
        if(Result==-1)
        {
            Toast.makeText( context, "Failed!", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(context, "Updated Succesdully", Toast.LENGTH_SHORT).show();

        }
        db.close();
        refresh();
    }

    public void Delete(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        String where = ID+"="+id;
        db.delete(TBLNAME,where,null);
        db.close();
    }
}
