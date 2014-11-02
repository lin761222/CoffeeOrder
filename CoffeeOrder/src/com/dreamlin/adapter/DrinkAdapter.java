package com.dreamlin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dreamlin.activity.AboutMe;
import com.dreamlin.activity.DrinkDetail;
import com.dreamlin.activity.MainActivity;
import com.dreamlin.activity.Order;
import com.dreamlin.model.DrinkInfo;
import com.dreamlin.coffeeorder.R;

public class DrinkAdapter extends BaseAdapter {
	Context context;
	DrinkInfo DI[];

	public DrinkAdapter(Context context, DrinkInfo[] DI) {
		this.context = context;
		this.DI = DI;

	}

	@Override
	public int getCount() {
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

	public void setDrinkInfos(DrinkInfo[] drinkInfos) {
		this.DI = drinkInfos;
	}

	@Override
	public View getView(final int pos, View v, ViewGroup arg2) {
		v = LinearLayout.inflate(context, R.layout.drinkadapter_items, null);
		TextView txv1 = (TextView) v.findViewById(R.id.textView1);
		TextView txv2 = (TextView) v.findViewById(R.id.textView2);
		ImageView img = (ImageView) v.findViewById(R.id.imageView2);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent it = new Intent(context, DrinkDetail.class);
				Order.itemPosition = pos;
				context.startActivity(it);
			}
		});
		txv1.setText(DI[pos].getName());
		txv2.setText(DI[pos].getPrice());
		return v;
	}

}
