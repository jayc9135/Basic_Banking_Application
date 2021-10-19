package com.jayc.banking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_info";
    private String TABLE_NAME1 = "transfers_table";

    public Database(@Nullable Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY ,NAME TEXT,PHONE_NO VARCHAR, EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR,BALANCE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,TRANSACTION_ID VARCHAR, DATE VARCHAR,SENDER_ID VARCHAR,RECEIVER_ID VARCHAR,AMOUNT VARCHAR,STATUS TEXT)");

        db.execSQL("insert into user_info values(1, 'Jagan', 'jagan123@gmail.com','8235147520', '656456027979', 'ABHY0065306','19000.00')");
        db.execSQL("insert into user_info values(2, 'Rahul', '2001rahul@outlook.com', '7562001458','743641364921', 'PYTM0123456','6000.00')");
        db.execSQL("insert into user_info values(3, 'Naman', 'naman456@aol.com', '9783805672','971485807552', 'UTIB0SYDC04','5400.00')");
        db.execSQL("insert into user_info values(4, 'Jeet','321jeet@gmail.com', '4845812580','793768854960', 'ICIC0003893','25000.55')");
        db.execSQL("insert into user_info values(5, 'Vikram', 'vik567@gmail.com', '8151086138','541532801422', 'UTIB0004673','100000.00')");
        db.execSQL("insert into user_info values(6, 'Samay', 'samay234@hotmail.com', '1262240977','920203212024', 'ICIC0003353','85000.00')");
        db.execSQL("insert into user_info values(7, 'Tanmay', 'tanmay008@yahoo.com', '3531510160','191220744457', 'UTIB0004673','1500.23')");
        db.execSQL("insert into user_info values(8, 'Keval', 'keval333@aol.com','4201826041', '866963745307', 'SBIN0061517','41326.45')");
        db.execSQL("insert into user_info values(9, 'Ravikant', 'ravikant@rediffmail.com', '7155054473','713275078538', 'SBIN0061692','7812.36')");
        db.execSQL("insert into user_info values(10, 'Laxman', '4567laxman@gmail.com', '8210560611','403793407490', 'SBIN0040705','562146.00')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_info", null);
        return cursor;
    }

    public Cursor readparticulardata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_info where id = " + id, null);
        return cursor;
    }

    public Cursor readselectuserdata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_info except select * from user_info where id = " + id, null);
        return cursor;
    }

    public void updateAmount(String id, Double amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_info set balance = " + amount + " where id = " + id);
    }

    public Cursor readtransferdata(String transactionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table where TRANSACTION_ID = " + transactionId, null);
        return cursor;
    }


    public Cursor readperticulartransferdata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table where SENDER_ID = " + id + " OR RECEIVER_ID = " + id + " ORDER BY id DESC ", null);
        return cursor;
    }


    public boolean insertTransferData(String Transaction_id, String date, String SenderID, String ReceiverID, String amount, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TRANSACTION_ID", Transaction_id);
        contentValues.put("DATE", date);
        contentValues.put("SENDER_ID", SenderID);
        contentValues.put("RECEIVER_ID", ReceiverID);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
