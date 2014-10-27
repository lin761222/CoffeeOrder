package com.lab.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lab.service.IDataAccessService;
import com.lab.service.DataAccessService;
import com.lab.service.util.Args;
import com.lab.vo.Order;
import com.lab.vo.Product;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import com.lab.R;
public class ProductList extends ListActivity {
	
	// 紀錄當下點選的itemId
	private int currentItemId; 
	
	// 資料處理服務
	private IDataAccessService das;
	
	public ProductList() {
		// 紀錄當下點選的itemId(-1:表示未點選到任何項目)
		currentItemId = Args.Common.NON_SELECTED;
		// 建立資料處理服務實體
        das = new DataAccessService(ProductList.this);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        
        // 註冊ListActivity按下item監聽器
        getListView().setOnItemClickListener(new MyOnItemClickListener());
        
        // 註冊ListActivity長按選單監聽器
        getListView().setOnCreateContextMenuListener(new MyOnCreateContextMenuListener());
    }
    
    @Override
    public void onResume() {
		super.onResume();
		
		// 設定當ListActivity內容為空白時所要顯示的資訊
        getListView().setEmptyView(findViewById(R.id.emptyView));
        
		// 將資料匯入到ListActivity
        
        importDataToList();
        
	}
    
    // 匯入資料到ListActivity
    private void importDataToList() {
    	//getListView().removeAllViewsInLayout();
    	// 資料容器:用來給 ListAdapter 資料接口使用
    	ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();   
    	
    	// 取得原始資料
    	List<Product> list = das.queryProduct();
    	int lens = list.size();
    	
    	// 將取得的原始資料放入資料容器(listItem)內
    	for(int x=0;x<lens;++x) {
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("ItemId"     , list.get(x).getItemId());   
            map.put("ItemTitle"  , list.get(x).getItemTitle());   
            map.put("ItemPrice"  , list.get(x).getItemPrice()); 
            map.put("ItemImageId", list.get(x).getItemImageId());
            listItem.add(map);
        }
    	
    	// ListAdapter 資料接口
        SimpleAdapter listItemAdapter = new SimpleAdapter(
        		this, // 設定接口環境
        		listItem, // 注入資料容器資料
        		R.layout.main_rows_layout, // 資料內容 UI xml
        		new String[] {"ItemId","ItemTitle", "ItemPrice", "ItemImageId"}, // 資料標題
        		new int[] {R.id.ItemId, R.id.ItemTitle, R.id.ItemPrice, R.id.ItemImage} // 資料內容UI格式
        );   
        
        // 將資料接口注入到ListActivity
        getListView().setAdapter(listItemAdapter);
        
        
    }
    
    // 在 ListActivity 按下 Item 監聽器
    private class MyOnItemClickListener implements OnItemClickListener {

		@SuppressWarnings("rawtypes")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 根據position來取得點選之選單item實體
			HashMap key = (HashMap)parent.getItemAtPosition(position);
			final String productName = key.get("ItemTitle").toString();
			final String productId   = key.get("ItemId").toString();
			
			Builder builder = new Builder(ProductList.this);
			builder.setTitle("飲料點選確認");
			builder.setMessage("是否要點 [ " + productName + " ] 一杯?");
			
			// 正向鍵 Yes
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int id) {
	        		
	        		Order order = new Order();
	        		order.setoItemId(Integer.parseInt(productId));
	        		das.appendOrder(order);
	        		
	        	}
	        });
			
			// 負向鍵 No
	        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int id) {
	        		
	        	}
	        });
	        // 顯示builder
			builder.show();
		}
    	
    }
    
    // 自建Long-Click選單(長按選單)監聽器
    private class MyOnCreateContextMenuListener implements OnCreateContextMenuListener {

		@SuppressWarnings("rawtypes")
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
			// 建立長按選單實體
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			// 取得紀錄位置
			int position = info.position;
			// 取得紀錄位置內容
			HashMap key = (HashMap)((ListView)v).getItemAtPosition(position);
			// 設定長按選單標題
			menu.setHeaderTitle(key.get("ItemTitle").toString());
			// 設定當下itemId
			currentItemId = Integer.parseInt(key.get("ItemId").toString());
			// 設定長按選單內容
			menu.add(0, 0, 0, "修改價格"); 
			menu.add(0, 1, 0, "刪除紀錄");
			menu.add(0, 2, 0, "關閉選單");
		}
    	
    }
    
    // 長按選單按下 Item 之回呼函式
    @Override  
    public boolean onContextItemSelected(MenuItem item) {
    	// 取得選單位置 id
    	int itemId = item.getItemId();
		
    	switch(itemId) {
	    	case 0: // 修改價格
	    		/*
	    		 * LayoutInflater : 將自己設計xml的Layout轉成View
	    		 * inflate : 用LayoutInflater做一件事
	    		 * inflate只會把Layout形成一個以view類實現成的對象。到時若有需要時再用 setContentView(view)把它貼上。
	    		*/	    		
	    		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    		
	    		// 將 R.layout.popup_main.xml轉成 view
	    		View layout = inflater.inflate(R.layout.update_price_layout, null);
	    		
	    		Button submitBtn = (Button)layout.findViewById(R.id.SubmitBtn);
	    		final EditText priceEditText = (EditText)layout.findViewById(R.id.PriceEditText);
	    		// 取得選單點選商品之價格
	    		priceEditText.setText(String.valueOf(das.getProductById(currentItemId).getItemPrice()));
	    		
	    		// 定義  Dialog 對話視窗
	    		final Dialog dialog = new Dialog(ProductList.this);
	    		dialog.setContentView(layout);
	    		dialog.setTitle("　　　　修改價格　　　　");
	    		// 顯示dialog
	    		dialog.show();
	    		submitBtn.setOnClickListener(new OnClickListener() {
	    			@Override
	    			public void onClick(View view) {
	    				int price = Integer.parseInt(priceEditText.getText().toString());
	    				// 變更 item price
	    				das.updateProductItemPriceById(currentItemId, price);
	    				importDataToList();
	    				// 關閉 dialog
	    				dialog.dismiss();
	    				Toast.makeText(ProductList.this, "資料修改完畢!", Toast.LENGTH_SHORT).show();
	    			}
	    		});
	    		break;
	    	case 1: // 刪除紀錄
	    		// 定義  Builder 對話視窗
	    		Builder builder = new Builder(ProductList.this);
				builder.setTitle("刪除資料");
				builder.setMessage("是否要刪除[" + das.getProductById(currentItemId).getItemTitle() + "]?");
				
				// 正向鍵 Yes
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		        	public void onClick(DialogInterface dialog, int id) {
		        		das.deleteProductById(currentItemId);
		        		importDataToList();	        		
		        	}
		        });
				
				// 負向鍵 No
		        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
		        	public void onClick(DialogInterface dialog, int id) {
		        		
		        	}
		        });
		        // 顯示builder
				builder.show();
	    		
	    		break;
	    	case 2: // 關閉選單
	    		currentItemId = -1;
	    		break;	
	    }    
	    return super.onContextItemSelected(item);   
    }
    
    // 建立選項選單
    @Override    
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	menu.add(0, 0, Menu.NONE, "離開系統");
    	return true;
    }
    
    // 選項選單按下 Item 之回呼函式
    @Override    
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getGroupId() == 0) {
    		switch(item.getItemId()) {
    			case 0: // 選到離開系統
    				finish();
    				break;
    		}
    		
    	} else {
    		return super.onOptionsItemSelected(item);
    	}
    	return true;
    }
}