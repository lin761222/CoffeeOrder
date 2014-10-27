package com.lab.service.util;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context) {
		super(context, Args.DBSchema.DATABASE_NAME, null, Args.DBSchema.DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Args.DBSchema.CREATE_TABLE_COFFEE_SQL);
		db.execSQL(Args.DBSchema.CREATE_TABLE_ORDER_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onDropTable(db);
		onCreate(db);
	}
	
	public void onDropTable(SQLiteDatabase db) {
		db.execSQL(Args.DBSchema.DROP_TABLE_COFFEE_SQL);
		db.execSQL(Args.DBSchema.DROP_TABLE_ORDER_SQL);
	}
	
}
