package com.dreamlin.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dreamlin.adapter.ResultAdapter;
import com.dreamlin.model.DrinkInfo;
import com.dreamlin.coffeeorder.R;

public class Result extends Activity {
	ListView listView;
	int price_sum = 0;
	TextView sum, total;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		listView = (ListView) findViewById(R.id.listView1);
		sum = (TextView) findViewById(R.id.sum);
		total = (TextView) findViewById(R.id.total);
		ArrayList<DrinkInfo> drinkInfos = new ArrayList<DrinkInfo>();

		for (DrinkInfo drinkInfo : Order.drinkInfos) {
			if (drinkInfo.getNumber() > 0) {
				drinkInfos.add(drinkInfo);
				price_sum += (Integer.parseInt(drinkInfo.getPrice()) * drinkInfo
						.getNumber());
			}
		}
		for (DrinkInfo drinkInfo : Order.drinkInfos2) {
			if (drinkInfo.getNumber() > 0) {
				drinkInfos.add(drinkInfo);
				price_sum += (Integer.parseInt(drinkInfo.getPrice()) * drinkInfo
						.getNumber());
			}
		}
		if (price_sum > 0) {
			sum.setText(price_sum + "");
		} else {
			sum.setText("");
			total.setText("");
		}
		DrinkInfo[] infos = new DrinkInfo[drinkInfos.size()];
		listView.setAdapter(new ResultAdapter(this, drinkInfos.toArray(infos)));

	}

	public void back(View v) {
		finish();
	}

//	public void mail(View v) {
//		Intent it = new Intent(Intent.ACTION_VIEW);
//		it.setData(Uri.parse("mailto:alex.chen1222@gmail.com"));
//		// 主旨
//		it.putExtra(Intent.EXTRA_SUBJECT, "請輸入主旨");
//		// 內文
//		it.putExtra(Intent.EXTRA_TEXT, "請輸入內文");
//		startActivity(it);
//	}

}
