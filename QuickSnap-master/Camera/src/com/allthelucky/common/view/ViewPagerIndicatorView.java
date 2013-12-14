package com.allthelucky.common.view;

import java.util.*;
import java.util.Map.*;
import java.net.*;
import java.io.*;
import java.lang.*;
import android.view.*;       
import android.app.*;       
import android.os.*;
import android.content.*; 
import android.widget.*;
import android.view.View.*;
import android.util.*;
import android.support.v4.view.*;
import android.support.v4.view.ViewPager.*;
import com.allthelucky.common.view.*;
import com.allthelucky.common.view.TabIndicatorView.OnIndicateChangeListener;

/**
 * 文字切换标签+ViewPager内容区控件
 * 
 * @author savant-pan
 * 
 */
public class ViewPagerIndicatorView extends LinearLayout implements OnIndicateChangeListener, OnPageChangeListener {
	private TabIndicatorView tabIndicatorView;
	private ViewPager viewPager;
	public int itemnum;

	public ViewPagerIndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.initView();
	}

	public ViewPagerIndicatorView(Context context) {
		super(context);
		this.initView();
	}

	private void initView() {
		this.setOrientation(LinearLayout.VERTICAL);

		this.tabIndicatorView = new TabIndicatorView(getContext());
		this.viewPager = new ViewPager(getContext());

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		this.addView(tabIndicatorView, params);
		params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.addView(viewPager, params);

		this.tabIndicatorView.setOnIndicateChangeListener(this);
		this.viewPager.setOnPageChangeListener(this);

	}

	/**
	 * 设置显示标签文字及对应内容布局
	 * 
	 * @param titleViewMap
	 *            标题及对应View map数据
	 */
	public void setupLayout(ArrayList<View> titleViewMap, ArrayList<String> inStrList ) {
		if (titleViewMap == null || titleViewMap.size() == 0) {
			throw new NullPointerException();
		}

		final List<String> textList = inStrList;
		final List<View> viewList = titleViewMap;

		// 初始化TextTabIndicateView及ViewPager
		this.tabIndicatorView.setupLayout(textList);
		this.viewPager.setAdapter(new MyPagerAdapter(viewList));
			
	}

	@Override
	public void onTabChanged(int position) {
		itemnum = position;
		Toast.makeText(getContext(), "hekl", Toast.LENGTH_LONG);
		viewPager.setCurrentItem(position, true);
	}

	@Override
	public void onPageSelected(int position) {
		this.tabIndicatorView.setCurrentTab(position, false);//设置不通知接口返回位置
	}

	private class MyPagerAdapter extends PagerAdapter {
		private List<View> viewList = new ArrayList<View>();

		public MyPagerAdapter(List<View> viewList) {
			this.viewList = viewList;
		}

		@Override
		public int getCount() {
			return viewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(viewList.get(arg1));
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(viewList.get(arg1));
			return viewList.get(arg1);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

}
