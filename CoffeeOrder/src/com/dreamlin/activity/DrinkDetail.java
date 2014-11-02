package com.dreamlin.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dreamlin.model.DrinkInfo;
import com.dreamlin.coffeeorder.R;

public class DrinkDetail extends Activity {
	DrinkInfo drinkInfo;
	int numberConter;
	TextView txv1, txv2, txv4, txv5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drink_detail);
		if (Order.type == 0) {
			drinkInfo = Order.drinkInfos[Order.itemPosition];
		} else {
			drinkInfo = Order.drinkInfos2[Order.itemPosition];
		}

		txv1 = (TextView) findViewById(R.id.textView1);
		txv2 = (TextView) findViewById(R.id.textView2);
		txv4 = (TextView) findViewById(R.id.textView4);
		txv5 = (TextView) findViewById(R.id.textView5);
		txv1.setText(drinkInfo.getName());
		txv2.setText(drinkInfo.getPrice());
		txv4.setText(drinkInfo.getNumber() + "");

		numberConter = drinkInfo.getNumber();

	}

	public void add(View v) {
		numberConter++;
		drinkInfo.setNumber(numberConter);
		txv4.setText(drinkInfo.getNumber() + "");
		txv5.setText((drinkInfo.getNumber() * Integer.parseInt(drinkInfo
				.getPrice())) + "");
	}

	public void sub(View v) {
		if (drinkInfo.getNumber() <= 0) {

		} else {
			numberConter--;
			drinkInfo.setNumber(numberConter);
			txv4.setText(drinkInfo.getNumber() + "");
			txv5.setText((drinkInfo.getNumber() * Integer.parseInt(drinkInfo
					.getPrice())) + "");
		}
	}

	public void back(View v) {
		finish();
	}
}
