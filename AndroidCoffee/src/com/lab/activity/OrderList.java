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
	
	// ��ƳB�z�A��
	private IDataAccessService das;
	
	public OrderList() {
		// �إ߸�ƳB�z�A�ȹ���
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
		
		// �]�w��ListActivity���e���ťծɩҭn��ܪ���T
        getListView().setEmptyView(findViewById(R.id.emptyView));
        
		// �N��ƶפJ��ListActivity
        importDataToList();
	}
	
	// �פJ��ƨ�ListActivity
    private void importDataToList() {
    	
    	
    	int total = 0;
    	//getListView().removeAllViewsInLayout();
    	// ��Ʈe��:�Ψӵ� ListAdapter ��Ʊ��f�ϥ�
    	ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();   
    	
    	// ���o��l���
    	List<Order> list = das.queryOrder();
    	int lens = list.size();
        
    	// �N���o����l��Ʃ�J��Ʈe��(listItem)��
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
    	
    	// ListAdapter ��Ʊ��f
        SimpleAdapter listItemAdapter = new SimpleAdapter(
        		this, // �]�w���f����
        		listItem, // �`�J��Ʈe�����
        		R.layout.main_rows_layout, // ��Ƥ��e UI xml
        		new String[] {"oId","ItemTitle", "ItemPrice", "ItemImageId"}, // ��Ƽ��D
        		new int[] {R.id.ItemId, R.id.ItemTitle, R.id.ItemPrice, R.id.ItemImage} // ��Ƥ��eUI�榡
        );  
    	
    	// �p�p
        TextView totalView = (TextView)findViewById(R.id.totalView);
        totalView.setText("�p�p:" + total);
        
        // �N��Ʊ��f�`�J��ListActivity
        getListView().setAdapter(listItemAdapter); 
        
        // ���UListActivity���Uitem��ť��
        getListView().setOnItemClickListener(new MyOnItemClickListener());
    }
    
 // �b ListActivity ���U Item ��ť��
    private class MyOnItemClickListener implements OnItemClickListener {

		@SuppressWarnings("rawtypes")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// �ھ�position�Ө��o�I�蠟���item����
			HashMap key = (HashMap)parent.getItemAtPosition(position);
			final String productName = key.get("ItemTitle").toString();
			final String oId   = key.get("oId").toString();
			
			Builder builder = new Builder(OrderList.this);
			builder.setTitle("�R���T�{");
			builder.setMessage("�O�_�R�� [ " + productName + " ] ?");
			
			// ���V�� Yes
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int id) {
	        		
	        		das.deleteOrderById(Integer.parseInt(oId));
	        		importDataToList();
	        		
	        	}
	        });
			
			// �t�V�� No
	        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int id) {
	        		
	        	}
	        });
	        // ���builder
			builder.show();
		}
    	
    }
}
