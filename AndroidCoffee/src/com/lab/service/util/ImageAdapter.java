package com.lab.service.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	private Context mContext;
	private Integer width;
	private Integer height;
	private Integer[] mImageIds;
	
	public ImageAdapter(Context mContext, Integer width, Integer height, Integer[] mImageIds) {
		this.mContext = mContext;
		this.mImageIds = mImageIds;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			// if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			//設定圖片的寬、高
			imageView.setLayoutParams(new GridView.LayoutParams(width, height));
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
			imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}
		//設定圖片來源
		imageView.setImageResource(mImageIds[position]);
		return imageView;
	}
	
	@Override
	public int getCount() {
		return mImageIds.length;
	}

	@Override
	public Object getItem(int position) {
		return mImageIds[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}



}
