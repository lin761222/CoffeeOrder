package com.lab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class Main extends TabActivity  {
	//@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("商品總覽", getResources().getDrawable(android.R.drawable.ic_menu_manage))
                .setContent(new Intent(this, com.lab.activity.ProductList.class)));

        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("已選商品", getResources().getDrawable(android.R.drawable.ic_menu_agenda))
                .setContent(new Intent(this, com.lab.activity.OrderList.class)));
        
        tabHost.addTab(tabHost.newTabSpec("tab4")
                .setIndicator("新增商品", getResources().getDrawable(android.R.drawable.ic_menu_add))
                .setContent(new Intent(this, com.lab.activity.AppendProduct.class)));
        
        
    }
    

}
