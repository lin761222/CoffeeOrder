package com.lab.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lab.service.DataAccessService;
import com.lab.service.IDataAccessService;
import com.lab.vo.Order;

import android.app.ListActivity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.lab.R;
public class OrderList extends ListActivity  {
	
	// 資料處理服務
	private IDataAccessService das;
	
	public OrderList() {
		// 建立資料處理服務實體
        das = new DataAccessService(OrderList.this);
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);
        
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
    	
    	
    	int total = 0;
    	//getListView().removeAllViewsInLayout();
    	// 資料容器:用來給 ListAdapter 資料接口使用
    	ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();   
    	
    	// 取得原始資料
    	List<Order> list = das.queryOrder();
    	int lens = list.size();
        
    	// 將取得的原始資料放入資料容器(listItem)內
    	for(int x=0;x<lens;++x) {
    		int itemPrice = list.get(x).getProduct().getItemPrice();
    		total += itemPrice;
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("oId"     , list.get(x).getoId());   
        	map.put("ItemTitle"  , list.get(x).getProduct().getItemTitle());   
        	map.put("ItemPrice"  , itemPrice);   
        	map.put("ItemImageId"  , list.get(x).getProduct().getItemImageId());   
            listItem.add(map);
        }
    	
    	// ListAdapter 資料接口
        SimpleAdapter listItemAdapter = new SimpleAdapter(
        		this, // 設定接口環境
        		listItem, // 注入資料容器資料
        		R.layout.main_rows_layout, // 資料內容 UI xml
        		new String[] {"oId","ItemTitle", "ItemPrice", "ItemImageId"}, // 資料標題
        		new int[] {R.id.ItemId, R.id.ItemTitle, R.id.ItemPrice, R.id.ItemImage} // 資料內容UI格式
        );  
    	
    	// 小計
        TextView totalView = (TextView)findViewById(R.id.totalView);
        totalView.setText("小計:" + total);
        
        // 將資料接口注入到ListActivity
        getListView().setAdapter(listItemAdapter); 
        
        // 註冊ListActivity按下item監聽器
        getListView().setOnItemClickListener(new MyOnItemClickListener());
    }
    
 // 在 ListActivity 按下 Item 監聽器
    private class MyOnItemClickListener implements OnItemClickListener {

		@SuppressWarnings("rawtypes")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// 根據position來取得點選之選單item實體
			HashMap key = (HashMap)parent.getItemAtPosition(position);
			final String productName = key.get("ItemTitle").toString();
			final String oId   = key.get("oId").toString();
			
			Builder builder = new Builder(OrderList.this);
			builder.setTitle("刪除確認");
			builder.setMessage("是否刪除 [ " + productName + " ] ?");
			
			// 正向鍵 Yes
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int id) {
	        		
	        		das.deleteOrderById(Integer.parseInt(oId));
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
		}
    	
    }
}
