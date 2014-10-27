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
	
	// ������U�I�諸itemId
	private int currentItemId; 
	
	// ��ƳB�z�A��
	private IDataAccessService das;
	
	public ProductList() {
		// ������U�I�諸itemId(-1:��ܥ��I�����󶵥�)
		currentItemId = Args.Common.NON_SELECTED;
		// �إ߸�ƳB�z�A�ȹ���
        das = new DataAccessService(ProductList.this);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        
        // ���UListActivity���Uitem��ť��
        getListView().setOnItemClickListener(new MyOnItemClickListener());
        
        // ���UListActivity��������ť��
        getListView().setOnCreateContextMenuListener(new MyOnCreateContextMenuListener());
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
    	//getListView().removeAllViewsInLayout();
    	// ��Ʈe��:�Ψӵ� ListAdapter ��Ʊ��f�ϥ�
    	ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();   
    	
    	// ���o��l���
    	List<Product> list = das.queryProduct();
    	int lens = list.size();
    	
    	// �N���o����l��Ʃ�J��Ʈe��(listItem)��
    	for(int x=0;x<lens;++x) {
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("ItemId"     , list.get(x).getItemId());   
            map.put("ItemTitle"  , list.get(x).getItemTitle());   
            map.put("ItemPrice"  , list.get(x).getItemPrice()); 
            map.put("ItemImageId", list.get(x).getItemImageId());
            listItem.add(map);
        }
    	
    	// ListAdapter ��Ʊ��f
        SimpleAdapter listItemAdapter = new SimpleAdapter(
        		this, // �]�w���f����
        		listItem, // �`�J��Ʈe�����
        		R.layout.main_rows_layout, // ��Ƥ��e UI xml
        		new String[] {"ItemId","ItemTitle", "ItemPrice", "ItemImageId"}, // ��Ƽ��D
        		new int[] {R.id.ItemId, R.id.ItemTitle, R.id.ItemPrice, R.id.ItemImage} // ��Ƥ��eUI�榡
        );   
        
        // �N��Ʊ��f�`�J��ListActivity
        getListView().setAdapter(listItemAdapter);
        
        
    }
    
    // �b ListActivity ���U Item ��ť��
    private class MyOnItemClickListener implements OnItemClickListener {

		@SuppressWarnings("rawtypes")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// �ھ�position�Ө��o�I�蠟���item����
			HashMap key = (HashMap)parent.getItemAtPosition(position);
			final String productName = key.get("ItemTitle").toString();
			final String productId   = key.get("ItemId").toString();
			
			Builder builder = new Builder(ProductList.this);
			builder.setTitle("�����I��T�{");
			builder.setMessage("�O�_�n�I [ " + productName + " ] �@�M?");
			
			// ���V�� Yes
			builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	        	public void onClick(DialogInterface dialog, int id) {
	        		
	        		Order order = new Order();
	        		order.setoItemId(Integer.parseInt(productId));
	        		das.appendOrder(order);
	        		
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
    
    // �۫�Long-Click���(�������)��ť��
    private class MyOnCreateContextMenuListener implements OnCreateContextMenuListener {

		@SuppressWarnings("rawtypes")
		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {  
			// �إߪ���������
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			// ���o������m
			int position = info.position;
			// ���o������m���e
			HashMap key = (HashMap)((ListView)v).getItemAtPosition(position);
			// �]�w���������D
			menu.setHeaderTitle(key.get("ItemTitle").toString());
			// �]�w��UitemId
			currentItemId = Integer.parseInt(key.get("ItemId").toString());
			// �]�w������椺�e
			menu.add(0, 0, 0, "�ק����"); 
			menu.add(0, 1, 0, "�R������");
			menu.add(0, 2, 0, "�������");
		}
    	
    }
    
    // ���������U Item ���^�I�禡
    @Override  
    public boolean onContextItemSelected(MenuItem item) {
    	// ���o����m id
    	int itemId = item.getItemId();
		
    	switch(itemId) {
	    	case 0: // �ק����
	    		/*
	    		 * LayoutInflater : �N�ۤv�]�pxml��Layout�নView
	    		 * inflate : ��LayoutInflater���@���
	    		 * inflate�u�|��Layout�Φ��@�ӥHview����{������H�C��ɭY���ݭn�ɦA�� setContentView(view)�⥦�K�W�C
	    		*/	    		
	    		LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    		
	    		// �N R.layout.popup_main.xml�ন view
	    		View layout = inflater.inflate(R.layout.update_price_layout, null);
	    		
	    		Button submitBtn = (Button)layout.findViewById(R.id.SubmitBtn);
	    		final EditText priceEditText = (EditText)layout.findViewById(R.id.PriceEditText);
	    		// ���o����I��ӫ~������
	    		priceEditText.setText(String.valueOf(das.getProductById(currentItemId).getItemPrice()));
	    		
	    		// �w�q  Dialog ��ܵ���
	    		final Dialog dialog = new Dialog(ProductList.this);
	    		dialog.setContentView(layout);
	    		dialog.setTitle("�@�@�@�@�ק����@�@�@�@");
	    		// ���dialog
	    		dialog.show();
	    		submitBtn.setOnClickListener(new OnClickListener() {
	    			@Override
	    			public void onClick(View view) {
	    				int price = Integer.parseInt(priceEditText.getText().toString());
	    				// �ܧ� item price
	    				das.updateProductItemPriceById(currentItemId, price);
	    				importDataToList();
	    				// ���� dialog
	    				dialog.dismiss();
	    				Toast.makeText(ProductList.this, "��ƭק粒��!", Toast.LENGTH_SHORT).show();
	    			}
	    		});
	    		break;
	    	case 1: // �R������
	    		// �w�q  Builder ��ܵ���
	    		Builder builder = new Builder(ProductList.this);
				builder.setTitle("�R�����");
				builder.setMessage("�O�_�n�R��[" + das.getProductById(currentItemId).getItemTitle() + "]?");
				
				// ���V�� Yes
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
		        	public void onClick(DialogInterface dialog, int id) {
		        		das.deleteProductById(currentItemId);
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
	    		
	    		break;
	    	case 2: // �������
	    		currentItemId = -1;
	    		break;	
	    }    
	    return super.onContextItemSelected(item);   
    }
    
    // �إ߿ﶵ���
    @Override    
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
    	menu.add(0, 0, Menu.NONE, "���}�t��");
    	return true;
    }
    
    // �ﶵ�����U Item ���^�I�禡
    @Override    
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(item.getGroupId() == 0) {
    		switch(item.getItemId()) {
    			case 0: // ������}�t��
    				finish();
    				break;
    		}
    		
    	} else {
    		return super.onOptionsItemSelected(item);
    	}
    	return true;
    }
}