package com.lab.service;

import java.util.ArrayList;
import java.util.List;

import com.lab.service.util.Args;
import com.lab.service.util.DBHelper;
import com.lab.vo.Product;
import com.lab.vo.Order;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataAccessService implements IDataAccessService {
	private List<Product> productList; // cache Product 資料表資訊
	private static DBHelper dbHelper; 
		
	public DataAccessService(Context context) {
		dbHelper = new DBHelper(context);
		productList = new ArrayList<Product>();		
	}
	
	// 取得所有資料
	@Override
	public List<Product> queryProduct() {
		productList = null;
		productList = new ArrayList<Product>();
		
		// 使用[唯讀]模式開啟資料庫
		SQLiteDatabase db = dbHelper.getReadableDatabase();	
		String sql = "SELECT itemId, itemTitle, itemPrice, itemImageId, itemImage FROM " + Args.DBSchema.COFFEE_TABLE_NAME;
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		for(int i=0;i<cursor.getCount();++i) {
			Product p = new Product();
			p.setItemId(cursor.getInt(0));
			p.setItemTitle(cursor.getString(1));
			p.setItemPrice(cursor.getInt(2));
			p.setItemImageId(cursor.getInt(3));
			p.setItemImage(cursor.getBlob(4));
			productList.add(p);
    		cursor.moveToNext();
    	}
    	cursor.close();
    	db.close();
		return productList;
	}	
    
	@Override
	public boolean deleteProductById(int id) {
		// 使用[可寫入]模式開啟資料庫
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		db.delete(Args.DBSchema.COFFEE_TABLE_NAME, "itemId=?", new String[]{String.valueOf(id)});
		db.close();
		return true;
	}
	
	@Override
	public boolean updateProductItemPriceById(int id, int price) {
		// 使用[可寫入]模式開啟資料庫
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("itemPrice", price);
		db.update(Args.DBSchema.COFFEE_TABLE_NAME, values, "itemId=?", new String[]{String.valueOf(id)});
		
		db.close();
		return true;
	}
	
	@Override
	public boolean appendProduct(Product product) {
		// 使用[可寫入]模式開啟資料庫
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("itemTitle", product.getItemTitle());
		values.put("itemPrice", product.getItemPrice());
		values.put("itemImageId", product.getItemImageId());
		values.put("itemImage", product.getItemImage());
		db.insert(Args.DBSchema.COFFEE_TABLE_NAME, null, values);
		
		db.close();
		return true;
	}
	
	@Override
	public Product getProductById(int id) {
		Product product = null;
		for(Product vo : productList) {
			if(vo.getItemId() == id) {
				product = vo;
				break;
			}
		}
		return product;
	}
	
	// 取得多筆 Order
	@Override
	public List<Order> queryOrder() {
		
		List<Order> orderList = new ArrayList<Order>(); // Order 訂單資訊
		
		// 使用[唯讀]模式開啟資料庫
		SQLiteDatabase db = dbHelper.getReadableDatabase();	
		//String sql = "SELECT oId, oItemId FROM " + Args.DBSchema.ORDER_TABLE_NAME;
		String sql = "select oId, oItemId, itemId, itemTitle, itemPrice, itemImageId, itemImage " +
					 "from " + Args.DBSchema.COFFEE_TABLE_NAME + ", " + Args.DBSchema.ORDER_TABLE_NAME + " " +
					 "where oItemID = itemId ";
		
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		for(int i=0;i<cursor.getCount();++i) {
			
			Order order = new Order();
			order.setoId(cursor.getInt(0));
			order.setoItemId(cursor.getInt(1));
			
			Product product = new Product();
			product.setItemId(cursor.getInt(2));
			product.setItemTitle(cursor.getString(3));
			product.setItemPrice(cursor.getInt(4));
			product.setItemImageId(cursor.getInt(5));
			
			order.setProduct(product);
			
			orderList.add(order);
    		cursor.moveToNext();
    	}
    	cursor.close();
    	db.close();
		return orderList;
	}
	
	// 刪除 Order, 根據傳入ID
	@Override
	public boolean deleteOrderById(int id) {
		// 使用[可寫入]模式開啟資料庫
		SQLiteDatabase db = dbHelper.getWritableDatabase();		
		db.delete(Args.DBSchema.ORDER_TABLE_NAME, "oId=?", new String[]{String.valueOf(id)});
		db.close();
		return true;
	}	
	
	// 新增 Order
	@Override
	public boolean appendOrder(Order order) {
		// 使用[可寫入]模式開啟資料庫
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("oItemId", order.getoItemId());
		db.insert(Args.DBSchema.ORDER_TABLE_NAME, null, values);
		db.close();
		return true;
	}
	
	
	
}
