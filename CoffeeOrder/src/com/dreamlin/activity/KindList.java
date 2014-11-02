package com.dreamlin.activity;

import com.dreamlin.coffeeorder.R;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KindList extends Activity implements OnPageChangeListener {

	ViewPager viewPager;
	// ���I�I��
	ImageView[] tips;
	// �˹Ϥ���
	ImageView[] mImageViews;
	// �Ϥ��귽id
	int[] imgIdArray;
	// ��r��
	TextView txv, txv2;
	// String a = KindList.this.getResources().getString(R.string.kind_1);
	String txv_index[] = { "�g���X��", "�Ҥک@�ب�", "�q����X��", "�����j���[�K�B�z��", "�����ȩ@�ب�" };
	String txv_index2[] = { "��o�ũM���f�P�a���@�Ѽ���ӨӪ��@�P����A����p�q�M���A����̦n�~��A�X���w�©@�ت���Ź�̡C",
			"�W�ŻīסA�f�P�@�p�A�Y�רβ`����a���������H�ΰ_�q�몺����",
			"�����J�p���q�j�Q�����A�z�o�ʪ��g������A�f�P�@���O�ҵ��A�p�p���J�R���αa���¥��J�O���l��",
			"�K�B�z���@�دS����M��G�ר������]�w�A�H�O�d�V�h���G�סA�����ݩ󤤫װ������K�B�z�A�īקC�A��������@���A�p�׻P�����װ�",
			"���Φ�F���A���G�A���J�O�A���Ƶ��h�h�����ܤơA�f�P�a������몺�Ĳ��P�����h���ӽo�����A�l���h�O�@�@���i�i�P�B������[�[�����A�������z�L�H�`��" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kind_list);
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
		viewPager = (ViewPager) findViewById(R.id.viewPager);

		imgIdArray = new int[] { R.drawable.kind_1, R.drawable.kind_2,
				R.drawable.kind_3, R.drawable.kind_4, R.drawable.kind_5 };
		txv = (TextView) findViewById(R.id.txv);
		txv2 = (TextView) findViewById(R.id.txv2);

		// �N�I�I�[�J��ViewGroup��
		tips = new ImageView[imgIdArray.length];
		for (int i = 0; i < tips.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(10, 10));
			tips[i] = imageView;
			if (i == 0) {
				tips[i].setBackgroundResource(R.drawable.page1);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_now);
			}

			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 5;
			layoutParams.rightMargin = 5;
			group.addView(imageView, layoutParams);
		}
		// �N�Ϥ��˸���Ʋդ�
		mImageViews = new ImageView[imgIdArray.length];
		for (int i = 0; i < mImageViews.length; i++) {
			ImageView imageView = new ImageView(this);
			mImageViews[i] = imageView;
			imageView.setBackgroundResource(imgIdArray[i]);
			// �]�w��r
			txv.setText(txv_index[i]);
		}

		// �]�mAdapter
		viewPager.setAdapter(new MyAdapter());
		// �]�m��ť�A�D�n�O�]�m�I�I���I��
		viewPager.setOnPageChangeListener(this);
		// �]�mViewPager���q�{��, �]�m�����ת�100���A�o�ˤl�}�l�N�੹���ư�
		viewPager.setCurrentItem((mImageViews.length * 100));
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		Log.e("position", arg0 + "");
		setImageBackground(arg0 % mImageViews.length);
		setPageText(arg0 % mImageViews.length);

	}

	private void setPageText(int p) {
		txv.setText(txv_index[p]);
		txv2.setText(txv_index2[p]);
	}

	private void setImageBackground(int selectItems) {
		for (int i = 0; i < tips.length; i++) {
			if (i == selectItems) {
				tips[i].setBackgroundResource(R.drawable.page1);
			} else {
				tips[i].setBackgroundResource(R.drawable.page_now);
			}
		}
	}

	public class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mImageViews[position
					% mImageViews.length]);

		}

		/**
		 * ���J�Ϥ��i�h�A�η�e��position ���H�Ϥ��Ʋժ��ר��l�ƬO����
		 */
		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(mImageViews[position
					% mImageViews.length], 0);
			return mImageViews[position % mImageViews.length];
		}

	}

}
