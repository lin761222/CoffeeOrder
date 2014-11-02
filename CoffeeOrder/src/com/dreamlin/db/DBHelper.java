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
			+ "(_id integer , name text,price text)";

	public DBHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_SQL);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);

	}

	public void insert(int id, String name, String price) {
		// String sql = "insert into " + TABLE_NAME + "(_id,name,price)values("
		// + id + ",'" + name + "'" + ",'" + price + "')";
		SQLiteDatabase db = getWritableDatabase();

		// String sql = "insert into " + TABLE_NAME
		// + "(_id,name,price)values(0,'©@°Ø','100')";
		// database.execSQL(sql);
		// sql = "insert into " + TABLE_NAME
		// + "(_id,name,price)values(1,'¬õ¯ù','120')";
		// database.execSQL(sql);
		// sql = "insert into " + TABLE_NAME
		// + "(_id,name,price)values(2,'¥¤¯ù','150')";
		// database.execSQL(sql);

		ContentValues cv = new ContentValues();
		cv.put("_id", 0);
		cv.put("name", "¶Â©@°Ø");
		cv.put("price", "100");
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 2);
		cv.put("name", "¦B¬õ¯ù");
		cv.put("price", "150");
		db.insert(TABLE_NAME, null, cv);
		cv.put("_id", 1);
		cv.put("name", "¼ö¥¤¯ù");
		cv.put("price", "120");
		db.insert(TABLE_NAME, null, cv);

		db.close();
	}

	public ArrayList<DrinkInfo> query() {
		SQLiteDatabase db = getReadableDatabase();
		String sql = "SELECT * FROM " + TABLE_NAME;
		Cursor c = db.rawQuery(sql, null);
		ArrayList<DrinkInfo> DI = new ArrayList<DrinkInfo>();
		while (c.moveToNext()) {
			for (int i = 0; i < 3; i++) {
				c.move(i);
				int id = c.getInt(0);
				String name = c.getString(1);
				String price = c.getString(2);
				DrinkInfo DIs = new DrinkInfo(name, price);
				DI.add(DIs);
			}
		}

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
