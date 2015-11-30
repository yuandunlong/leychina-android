package com.leychina.fragment;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.widget.tabindicator.fragment.LazyFragment;
import com.leychina.widget.tabindicator.view.indicator.Indicator;
import com.leychina.widget.tabindicator.view.indicator.IndicatorViewPager;
import com.leychina.widget.tabindicator.view.indicator.slidebar.ColorBar;
import com.leychina.widget.tabindicator.view.indicator.transition.OnTransitionTextListener;
import com.leychina.widget.tabindicator.view.viewpager.SViewPager;


public class ProjectsTabFragment extends LazyFragment {
	private String [] filmTabNames={"推荐","未完成","已完成"};

	private IndicatorViewPager indicatorViewPager;
	private LayoutInflater inflate;
	public static final String INTENT_STRING_TABNAME = "intent_String_tabname";
	public static final String INTENT_INT_INDEX = "intent_int_index";
	private String tabName;
	private int index;

	@Override
	protected void onCreateViewLazy(Bundle savedInstanceState) {
		super.onCreateViewLazy(savedInstanceState);
		setContentView(R.layout.fragment_tabmain);
		Resources res = getResources();

		Bundle bundle = getArguments();
		tabName = bundle.getString(INTENT_STRING_TABNAME);
		index = bundle.getInt(INTENT_INT_INDEX);

		SViewPager viewPager = (SViewPager) findViewById(R.id.fragment_tabmain_viewPager);
		Indicator indicator = (Indicator) findViewById(R.id.fragment_tabmain_indicator);
        viewPager.setHorizontalScrollBarEnabled(false);

		switch (index) {
		case 0:
            ColorBar colorBar=new ColorBar(getApplicationContext(),Color.RED,3);
            colorBar.setWidth(120);
			colorBar.setHeight(10);
			indicator.setScrollBar(colorBar);

			break;
		}

		float unSelectSize = 16;
		float selectSize = unSelectSize * 1.2f;

		int selectColor = res.getColor(R.color.red);
		int unSelectColor = res.getColor(R.color.white);
		indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor));

		viewPager.setOffscreenPageLimit(3);

		indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
		inflate = LayoutInflater.from(getApplicationContext());

		// 注意这里 的FragmentManager 是 getChildFragmentManager(); 因为是在Fragment里面
		// 而在activity里面用FragmentManager 是 getSupportFragmentManager()
		indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        viewPager.setCanScroll(false);
		Log.d("cccc", "Fragment 将要创建View " + this);
	}

	@Override
	protected void onResumeLazy() {
		super.onResumeLazy();
		Log.d("cccc", "Fragment所在的Activity onResume, onResumeLazy " + this);
	}

	@Override
	protected void onFragmentStartLazy() {
		super.onFragmentStartLazy();
		Log.d("cccc", "Fragment 显示 " + this);
	}

	@Override
	protected void onFragmentStopLazy() {
		super.onFragmentStopLazy();
		Log.d("cccc", "Fragment 掩藏 " + this);
	}

	@Override
	protected void onPauseLazy() {
		super.onPauseLazy();
		Log.d("cccc", "Fragment所在的Activity onPause, onPauseLazy " + this);
	}

	@Override
	protected void onDestroyViewLazy() {
		super.onDestroyViewLazy();
		Log.d("cccc", "Fragment View将被销毁 " + this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("cccc", "Fragment 所在的Activity onDestroy " + this);
	}

	private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

		public MyAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public View getViewForTab(int position, View convertView, ViewGroup container) {
			if (convertView == null) {
				convertView = inflate.inflate(R.layout.tab_top, container, false);
			}
			TextView textView = (TextView) convertView;
			textView.setText(filmTabNames[position]);
			return convertView;
		}

		@Override
		public Fragment getFragmentForPage(int position) {

			switch (position){
				case 0:
					RecommendFragment recommendFragment=new RecommendFragment();
					return recommendFragment;
			}
			ProjectsLayerFragment mainFragment = new ProjectsLayerFragment();
			Bundle bundle = new Bundle();
			bundle.putString(ProjectsLayerFragment.INTENT_STRING_TABNAME, tabName);
			bundle.putInt(ProjectsLayerFragment.INTENT_INT_POSITION, position);
			mainFragment.setArguments(bundle);
			return mainFragment;
		}
	}

}
