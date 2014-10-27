package com.lab.activity.sub;

import com.lab.service.util.Args;
import com.lab.service.util.ImageAdapter;
import com.lab.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class GridImageView extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview_layout);
        GridView gridView01 = (GridView)findViewById(R.id.GridView01);
        gridView01.setAdapter(new ImageAdapter(this, 150, 150, Args.ImageResPath.IMAGE_ID)); // �]�w image ���f
        gridView01.setOnItemClickListener(new GridView01OnItemClickListener());
    }
    
    // GridView ���U�ﶵ��ť�� 
	private class GridView01OnItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view,	int position, long id) {
			// �^���T��
			Intent intent = new Intent();
			intent.putExtra("position", String.valueOf(position));
			setResult(2, intent); // resultCode = 2
			
			// ���� Receive
			GridImageView.this.finish();
			
		}
	}
}
