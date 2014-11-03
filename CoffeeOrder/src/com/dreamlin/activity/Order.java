package com.dreamlin.activity;

import java.util.ArrayList;

import com.dreamlin.adapter.DrinkAdapter;
import com.dreamlin.db.DBHelper;
import com.dreamlin.model.DrinkInfo;
import com.dreamlin.coffeeorder.R;
import com.dreamlin.coffeeorder.R.id;
import com.dreamlin.coffeeorder.R.layout;
import com.dreamlin.coffeeorder.R.menu;

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
	public static DrinkInfo drinkInfos[] = new DrinkInfo[6];
	ListView lv;
	public static DrinkInfo drinkInfos2[] = new DrinkInfo[6];
	DrinkAdapter DA;
	public static int itemPosition = 0;
	public static int type = 0;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);

		DBHelper dbHelper = new DBHelper(this);

		ArrayList<DrinkInfo> DI = dbHelper.query();
		ArrayList<DrinkInfo> hotDI = new ArrayList<DrinkInfo>();
		ArrayList<DrinkInfo> coldDI = new ArrayList<DrinkInfo>();
		
		for(DrinkInfo drinkInfo : DI){
			if(drinkInfo.getType()==0){
				hotDI.add(drinkInfo);
			}else{
				coldDI.add(drinkInfo);
			}
		}
		
		drinkInfos = new DrinkInfo[hotDI.size()];
		for (int i = 0; i < hotDI.size(); i++) {
			drinkInfos[i] = hotDI.get(i);
		}
		
		drinkInfos2 = new DrinkInfo[coldDI.size()];
		for (int i = 0; i < coldDI.size(); i++) {
			drinkInfos2[i] = coldDI.get(i);
		}
		
//		drinkInfos = new DrinkInfo[DI.size()];
//		for (int i = 0; i < DI.size(); i++) {
//			drinkInfos[i] = DI.get(i);
//		}
//		 DrinkInfo DI = new DrinkInfo("¿@ÁY©@°Ø", "50");
//		 drinkInfos[0] = DI;
//		 DI = new DrinkInfo("¶Â©@°Ø", "40");
//		 drinkInfos[1] = DI;
//		 DI = new DrinkInfo("¥¤­»©@°Ø", "50");
//		 drinkInfos[2] = DI;
//		 DI = new DrinkInfo("¥d¥¬©_¿Õ", "60");
//		 drinkInfos[3] = DI;
//		 DI = new DrinkInfo("®³ÅK", "60");
//		 drinkInfos[4] = DI;
//		 DI = new DrinkInfo("­»¯ó®³ÅK", "70");
//		 drinkInfos[5] = DI;

//		DrinkInfo DI2 = new DrinkInfo("¦B¬õ¯ù", "30");
//		drinkInfos2[0] = DI2;
//		DI2 = new DrinkInfo("¦B¥¤¯ù", "35");
//		drinkInfos2[1] = DI2;
//		DI2 = new DrinkInfo("µâ´¶ªá¯ù", "30");
//		drinkInfos2[2] = DI2;
//		DI2 = new DrinkInfo("©Ù¯ù¤û¥¤", "35");
//		drinkInfos2[3] = DI2;
//		DI2 = new DrinkInfo("¬h¾í¥Ä", "30");
//		drinkInfos2[4] = DI2;
//		DI2 = new DrinkInfo("¿üÄõ¥¤¯ù", "35");
//		drinkInfos2[5] = DI2;
		

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
