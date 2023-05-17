package com.travelease.nitant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.travelease.nitant.Budget.BalanceModel;
import com.travelease.nitant.Budget.ExpenseModel;
import com.travelease.nitant.ui.Trip.ActivityModel;

import java.util.ArrayList;

public class BudgetDBHelper extends SQLiteOpenHelper {

    private static Context context  ;
    private static final String DBNAME = "budget.db";
    private static final String TBLNAMEB = "budget";
    private static final String TBLNAMEE = "expense";
    private static final String ID = "id";
    private static final String UID ="uid";
    private static final String ACTIVITY="activity";
    private static final String EXPENSE="expense";
    private static final String BALANCE="balance";
    private static final int VERSION =1 ;

    public BudgetDBHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE " + TBLNAMEB +" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+UID+" TEXT, "+BALANCE+" INTEGER)";
        String sql2 = "CREATE TABLE " + TBLNAMEE +" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+UID+" TEXT, "+ACTIVITY+" TEXT, "+EXPENSE+" INTEGER)";

        db.execSQL(sql1);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertFunds(BalanceModel balanceModel){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(UID,balanceModel.getUid());
        cv.put(BALANCE,balanceModel.getBalance());
        db.insert(TBLNAMEB,ID,cv);
        db.close();
    }

    public ArrayList<BalanceModel> getBalance(int unique)
    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<BalanceModel> balanceModelArrayList = new ArrayList<>();
        Cursor c =db.rawQuery( "select * from "+TBLNAMEB+" where "+UID+" = "+ unique, null );

        while (c.moveToNext())
        {
            int id = c.getInt(0);
            String uid = c.getString(1);
            int balance = c.getInt(2);

            BalanceModel balanceModel = new BalanceModel();
            balanceModel.setId(id);
            balanceModel.setUid(uid);
            balanceModel.setBalance(balance);

            balanceModelArrayList.add(balanceModel);
        }
        return balanceModelArrayList;
    }

    public void insertExpense(ExpenseModel expenseModel){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(UID,expenseModel.getUid());
        cv.put(EXPENSE,expenseModel.getExpense());
        cv.put(ACTIVITY,expenseModel.getActivity());
        db.insert(TBLNAMEE,ID,cv);
        db.close();
    }

}
