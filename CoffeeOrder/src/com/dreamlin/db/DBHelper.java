package com.dreamlin.db;

import java.util.ArrayList;

import com.dreamlin.model.DrinkInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.dreams.DreamService;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "CoffeeOrder";
	private static final int DATABASE_VERSION = 1;
	private static final String TABLE_NAME = "DrinkTable";
	// «Ø¥ß¸ê®Æªí
	private static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME
			+ "(_id integer , name text,price text, type integer)";

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SQL);
//		db.close();
		
//		db = getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("_id", 0);
		cv.put("name", "¿@ÁY©@°Ø");
		cv.put("price", "50");
		cv.put("type", 0);
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 1);
		cv.put("name", "¶Â©@°Ø");
		cv.put("price", "40");
		cv.put("type", 0);
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 2);
		cv.put("name", "¥¤­»©@°Ø");
		cv.put("price", "50");
		cv.put("type", 0);
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 3);
		cv.put("name", "¥d¥¬©_¿Õ");
		cv.put("price", "60");
		cv.put("type", 0);
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 4);
		cv.put("name", "®³ÅK");
		cv.put("price", "60");
		cv.put("type", 0);
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 5);
		cv.put("name", "­»¯ó®³ÅK");
		cv.put("price", "70");
		cv.put("type", 0);
		db.insert(TABLE_NAME, null, cv);
		
		cv.put("_id", 0);
		cv.put("name", "¦B©@°Ø");
		cv.put("price", "50");
		cv.put("type", 1);
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 1);
		cv.put("name", "¦B¬õ¯ù");
		cv.put("price", "40");
		cv.put("type", 1);
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 2);
		cv.put("name", "µâ´¶ªá¯ù");
		cv.put("price", "30");
		cv.put("type", 1);
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 3);
		cv.put("name", "ªá¯ù");
		cv.put("price", "20");
		cv.put("type", 1);
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 4);
		cv.put("name", "¦B¬h¾í¥Ä");
		cv.put("price", "20");
		cv.put("type", 1);
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 5);
		cv.put("name", "¿üÄõ¥¤¯ù");
		cv.put("price", "35");
		cv.put("type", 1);
		db.insert(TABLE_NAME, null, cv);

//		db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);

	}

	public void insert(int id, String name, String price) {
		// String sql = "insert into " + TABLE_NAME + "(_id,name,price)values("
		// + id + ",'" + name + "'" + ",'" + price + "')";
//		SQLiteDatabase db = getWritableDatabase();

		// String sql = "insert into " + TABLE_NAME
		// + "(_id,name,price)values(0,'©@°Ø','100')";
		// database.execSQL(sql);
		// sql = "insert into " + TABLE_NAME
		// + "(_id,name,price)values(1,'¬õ¯ù','120')";
		// database.execSQL(sql);
		// sql = "insert into " + TABLE_NAME
		// + "(_id,name,price)values(2,'¥¤¯ù','150')";
		// database.execSQL(sql);

//		ContentValues cv = new ContentValues();
//		cv.put("_id", 0);
//		cv.put("name", "¶Â©@°Ø");
//		cv.put("price", "100");
//		db.insert(TABLE_NAME, null, cv);
//		cv.put("_id", 2);
//		cv.put("name", "¦B¬õ¯ù");
//		cv.put("price", "150");
//		db.insert(TABLE_NAME, null, cv);
//		cv.put("_id", 1);
//		cv.put("name", "¼ö¥¤¯ù");
//		cv.put("price", "120");
//		db.insert(TABLE_NAME, null, cv);
//
//		db.close();
	}

	public ArrayList<DrinkInfo> query() {
		SQLiteDatabase db = getReadableDatabase();
		String sql = "SELECT * FROM " + TABLE_NAME;
		Cursor c = db.rawQuery(sql, null);
		ArrayList<DrinkInfo> DI = new ArrayList<DrinkInfo>();
		// while (c.moveToNext()) {

		if (c.moveToFirst()) {
			for (int i = 0; i < c.getCount(); i++) {
				c.moveToPosition(i);
				int id = c.getInt(0);
				String name = c.getString(1);
				String price = c.getString(2);
				int type = c.getInt(3);
				DrinkInfo DIs = new DrinkInfo(name, price,type);
				DI.add(DIs);

			}
		}
		
		// }

		// Cursor c = getWritableDatabase().query(TABLE_NAME, null, null, null,
		// null, null, null);
		//
		// ArrayList<DrinkInfo> DI = new ArrayList<>();
		// if (c.moveToFirst()) {
		// for (int i = 0; i < 3; i++) {
		// c.move(i);
		// int id = c.getInt(0);
		// String name = c.getString(1);
		// String price = c.getString(2);
		// DrinkInfo DIs = new DrinkInfo(name, price);
		// DI.add(DIs);
		// }
		// }
		return DI;
	}
}
