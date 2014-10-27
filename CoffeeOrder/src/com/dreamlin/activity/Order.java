package com.dreamlin.activity;

import com.dreamlin.adapter.DrinkAdapter;
import com.dreamlin.model.DrinkInfo;
import com.example.coffeeorder.R;
import com.example.coffeeorder.R.id;
import com.example.coffeeorder.R.layout;
import com.example.coffeeorder.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Order extends Activity {
	public static DrinkInfo drinkInfos[] = new DrinkInfo[3];
	ListView lv;
	public static DrinkInfo drinkInfos2[] = new DrinkInfo[3];
	DrinkAdapter DA;
	public static int itemPosition = 0;
	public static int type = 0;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);

		DrinkInfo DI = new DrinkInfo("©@°Ø", "100");
		drinkInfos[0] = DI;
		DI = new DrinkInfo("¥¤¯ù", "120");
		drinkInfos[1] = DI;
		DI = new DrinkInfo("¬õ¯ù", "150");
		drinkInfos[2] = DI;

		DrinkInfo DI2 = new DrinkInfo("hh©@°Ø", "100");
		drinkInfos2[0] = DI2;
		DI2 = new DrinkInfo("hh¥¤¯ù", "120");
		drinkInfos2[1] = DI2;
		DI2 = new DrinkInfo("hh¬õ¯ù", "150");
		drinkInfos2[2] = DI2;

		DA = new DrinkAdapter(this, drinkInfos);

		lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(DA);
	}

	public void Hot(View v) {
		DA.setDrinkInfos(drinkInfos);
		// ¨ê·s
		DA.notifyDataSetChanged();
		type = 0;
	}

	public void Cold(View v) {
		DA.setDrinkInfos(drinkInfos2);
		// ¨ê·s
		DA.notifyDataSetChanged();
		type = 1;
	}

	public void Order(View v) {
		Intent intent = new Intent(Order.this, Result.class);
		startActivity(intent);
	}
}
