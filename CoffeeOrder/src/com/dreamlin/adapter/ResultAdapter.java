package com.dreamlin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreamlin.model.DrinkInfo;
import com.example.coffeeorder.R;

public class ResultAdapter extends BaseAdapter {
	Context context;
	DrinkInfo DI[];
	
	public ResultAdapter(Context context, DrinkInfo[] DI) {
		this.context = context;
		this.DI = DI;

	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return DI.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int pos, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		v = LinearLayout.inflate(context, R.layout.resultadapter_items, null);
		TextView textView = (TextView)v.findViewById(R.id.textView1);
		TextView textView2 = (TextView)v.findViewById(R.id.textView2);
		TextView textView3 = (TextView)v.findViewById(R.id.textView3);
		textView.setText(DI[pos].getName());
		textView2.setText(DI[pos].getNumber()+"");
		int sum = DI[pos].getNumber() * Integer.parseInt(DI[pos].getPrice());
		textView3.setText(sum+"");
		
		return v;
	}

}
