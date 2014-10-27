package com.lab.service.util;

import com.lab.R;

// �ѼƸ귽
public interface Args {
	
	// �@��Ѽ�
	public static interface Common {
		int NON_SELECTED = -1;
	}
	
	// ��Ʈw�Ѽ�
	public static interface DBSchema {
		String DATABASE_NAME           = "lab.store";
		int    DATABASE_VERSION        = 2;
		String COFFEE_TABLE_NAME       = "coffee";
		String ORDER_TABLE_NAME        = "order2";
		String CREATE_TABLE_COFFEE_SQL = "CREATE TABLE coffee (itemId INTEGER PRIMARY KEY, itemTitle TEXT, itemPrice NUMERIC, itemImageId NUMERIC, itemImage BLOB)";
		String DROP_TABLE_COFFEE_SQL   = "DROP TABLE IF EXISTS coffee";
		String CREATE_TABLE_ORDER_SQL  = "CREATE TABLE order2 (oId INTEGER PRIMARY KEY, oItemId INTEGER)";
		String DROP_TABLE_ORDER_SQL    = "DROP TABLE IF EXISTS order";
	}
	 
	// �Ϲ��귽�Ѽ�
	public static interface ImageResPath {
		// �]�w�Ϲ��ɮװ}�C
		Integer[] IMAGE_ID = { 
			R.drawable.icon_hot_latte, 
			R.drawable.icon_hot_mocha,
			R.drawable.icon_hot_cappuccino, 
			R.drawable.icon_hot_macchiato
		};
	}
}
