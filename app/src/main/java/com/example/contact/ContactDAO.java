package com.example.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contact.db";

    public ContactDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE contact (id INTEGER PRIMARY KEY, first_Name TEXT NOT NULL,last_Name TEXT NOT NULL,job TEXT NOT NULL,phone TEXT NOT NULL,email TEXT NOT NULL);");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contact;");
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public List<Contact> getAll(){
        List<Contact> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from contact",null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new Contact(cursor.getLong(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("first_Name")),cursor.getString(cursor.getColumnIndex("last_Name")),cursor.getString(cursor.getColumnIndex("job")),cursor.getString(cursor.getColumnIndex("phone")),cursor.getString(cursor.getColumnIndex("email"))));
                cursor.moveToNext();
            }
        }
        return list;
    }
    public List<Contact> getByKey(String key){
        List<Contact> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from contact WHERE last_name like ? OR first_name like ?",new String[]{"%"+key+"%","%"+key+"%"});
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(new Contact(cursor.getLong(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("first_Name")),cursor.getString(cursor.getColumnIndex("last_Name")),cursor.getString(cursor.getColumnIndex("job")),cursor.getString(cursor.getColumnIndex("phone")),cursor.getString(cursor.getColumnIndex("email"))));
                cursor.moveToNext();
            }
        }

        return list;
    }
    public Contact getByID(Long id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from contact WHERE id = ?",new String[]{Long.toString(id)});
        if (cursor.moveToFirst()) {
            return new Contact(cursor.getLong(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("first_Name")),cursor.getString(cursor.getColumnIndex("last_Name")),cursor.getString(cursor.getColumnIndex("job")),cursor.getString(cursor.getColumnIndex("phone")),cursor.getString(cursor.getColumnIndex("email")));

        }
        return null;
    }
    public void update(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("first_Name",contact.getFirst_Name());
        cv.put("last_Name",contact.getLast_Name());
        cv.put("job",contact.getJob());
        cv.put("phone",contact.getPhone());
        cv.put("email",contact.getEmail());
        db.update("contact",cv,"id=?",new String[]{Long.toString(contact.getId())});
    }
    public void delet(long id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("contact","id=?",new String[]{Long.toString(id)});
    }
    public void add(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("first_Name",contact.getFirst_Name());
        cv.put("last_Name",contact.getLast_Name());
        cv.put("job",contact.getJob());
        cv.put("phone",contact.getPhone());
        cv.put("email",contact.getEmail());
        db.insert("contact",null,cv);
    }
}
