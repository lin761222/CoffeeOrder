package com.dreamlin.activity;

import com.dreamlin.coffeeorder.R;
import com.dreamlin.coffeeorder.R.id;
import com.dreamlin.coffeeorder.R.layout;
import com.dreamlin.coffeeorder.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShopMap extends Activity {
	WebView wv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_map);

		wv = (WebView) findViewById(R.id.webView1);
		// �Y��\��
		wv.getSettings().setBuiltInZoomControls(true);
		// JS�ĪG
		wv.getSettings().setJavaScriptEnabled(true);
		// ����Y��p�u��
		wv.invokeZoomPicker();

		// �إ߶W�s��
		wv.setWebViewClient(new WebViewClient());
		wv.setWebChromeClient(new WebChromeClient() {
			public void onProgressChanged(WebView view, int progress) {
				setProgress(progress * 100);
			}
		});
		wv.loadUrl("http://ppt.cc/IhyT");
	}

}
