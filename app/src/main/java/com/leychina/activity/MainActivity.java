package com.leychina.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leychina.R;
import com.leychina.fragment.ArtistFragment;
import com.leychina.fragment.ProjectsTabFragment;
import com.leychina.fragment.UserCenterFragment;
import com.leychina.widget.tabindicator.view.indicator.Indicator;
import com.leychina.widget.tabindicator.view.indicator.IndicatorViewPager;
import com.leychina.widget.tabindicator.view.viewpager.SViewPager;

/**
 * Created by yuandunlong on 10/22/15.
 */
public class MainActivity extends FragmentActivity {

    private IndicatorViewPager indicatorViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);
        SViewPager viewPager= (SViewPager) findViewById(R.id.main_viewPager);
        Indicator indicator = (Indicator) findViewById(R.id.main_indicator);
        indicatorViewPager=new IndicatorViewPager(indicator,viewPager);
        indicatorViewPager.setAdapter(new TabViewPagerAdapter(getSupportFragmentManager()));

        viewPager.setCanScroll(false);
        viewPager.setOffscreenPageLimit(3);
    }


    private class TabViewPagerAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter{

        private String[] tabNames={"影片众酬","艺人社区","用户中心"};

        private String [] filmTabNames={"推荐","正在进行","成果展厅"};


        private int[]   tabIcons={R.drawable.maintab_1_selector,R.drawable.maintab_2_selector,R.drawable.maintab_3_selector};



        private LayoutInflater inflater;

        public TabViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater=LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {

            if(convertView==null) {
                convertView = inflater.inflate(R.layout.main_tab_text_view, container, false);
            }

            TextView tabText= (TextView) convertView;
            tabText.setText(tabNames[position]);
            tabText.setCompoundDrawablesWithIntrinsicBounds(0,tabIcons[position],0,0);
            return tabText;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            ProjectsTabFragment firstLayerFragment=new ProjectsTabFragment();
            Bundle bundle=new Bundle();
            bundle.putString(ProjectsTabFragment.INTENT_STRING_TABNAME, filmTabNames[position]);
            bundle.putInt(ProjectsTabFragment.INTENT_INT_INDEX, position);
            firstLayerFragment.setArguments(bundle);
            switch (position){
                case 0:

                    return firstLayerFragment;

                case 1:
                    ArtistFragment artistFragment=new ArtistFragment();

                    return artistFragment;
                case 2:
                    UserCenterFragment userCenterFragment=new UserCenterFragment();
                    return userCenterFragment;
                    //SignUpActivity.start(MainActivity.this);
            }

        return firstLayerFragment;
        }
    }
}
