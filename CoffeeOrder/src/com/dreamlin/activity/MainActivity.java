package com.dreamlin.activity;

import com.example.coffeeorder.R;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		addNewTab(this, AboutMe.class, "關於我們");
		addNewTab(this, KindList.class, "產品種類");
		addNewTab(this, Order.class, "產品明細");
		addNewTab(this, ShopMap.class, "商店資訊");

		getTabHost().setCurrentTab(0);
		getTabHost().requestFocus();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

	public void addNewTab(Context context, Class<?> cls, String tabName) {
		Resources res = getResources();
		Intent intent = new Intent().setClass(this, cls);
		TabSpec spec = getTabHost().newTabSpec(tabName)
				.setIndicator(tabName, res.getDrawable(R.drawable.ic_launcher))
				.setContent(intent);

		getTabHost().addTab(spec);
		getTabWidget().setBackgroundColor(Color.WHITE);
	}

}
