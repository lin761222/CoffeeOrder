package com.lab.activity;

import java.io.ByteArrayOutputStream;

import com.lab.activity.sub.GridImageView;
import com.lab.service.DataAccessService;
import com.lab.service.util.Args;
import com.lab.vo.Product;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.lab.R;
public class AppendProduct extends Activity {
	
	private Spinner spinner01;
	private EditText editText01;
	private ImageView imageView01;
	private Button button01;
	private int position; // 圖片index
	
	// 資料處理服務
	DataAccessService das;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.append_layout);
        das = new DataAccessService(AppendProduct.this);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.coffeeList, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner01 = (Spinner)findViewById(R.id.Spinner01);
        editText01 = (EditText)findViewById(R.id.EditText01);
        imageView01 = (ImageView)findViewById(R.id.ImageView01);
		button01 = (Button)findViewById(R.id.Button01);
		
		spinner01.setAdapter(adapter); // adapter(字串陣列接口)
		imageView01.setOnClickListener(new ImageViewOnClickListener());
		button01.setOnClickListener(new Button01OnClickListener());
	}
	
	private class ImageViewOnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			Intent intent = new Intent();
			intent.setClass(AppendProduct.this, GridImageView.class);
			startActivityForResult(intent, 2);
			
		}
    	
    }
	
	private class Button01OnClickListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			// 新增到資料庫
			Product product = new Product();
			product.setItemTitle(spinner01.getSelectedItem().toString());
			product.setItemPrice(Integer.parseInt(editText01.getText().toString()));
			product.setItemImageId(Args.ImageResPath.IMAGE_ID[position]);
			product.setItemImage(getByteArray(Args.ImageResPath.IMAGE_ID[position]));
			das.appendProduct(product);
			Toast.makeText(AppendProduct.this, "商品新增完畢!", Toast.LENGTH_SHORT).show();
		}
		
	}
	
    // 取得drawable's byte[]
    private byte[] getByteArray(int drawable) {
    	Drawable d = AppendProduct.this.getResources().getDrawable(drawable);
		Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();   
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		return baos.toByteArray();
    }
    
	// 等待回應結果
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub  
		super.onActivityResult(requestCode, resultCode, data);  
		if(requestCode == resultCode) {
			int position = Integer.parseInt(data.getCharSequenceExtra("position").toString());
			imageView01.setImageDrawable(getResources().getDrawable(Args.ImageResPath.IMAGE_ID[position]));
			this.position = position;
		}
	}
}
