package com.example.mdp_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    PageAdapter pageAdapter;
    ViewPager2 viewPager2;
    ArrayList<Fragment> fragList = new ArrayList<Fragment>();
    long[] pattern = {2000, 1000, 2000, 2000};
    int[] amplitudes = {0, 100, 0, 200};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.ViewPater2);
        //프로그먼트 선언
        Fragment0 fragment0 = new Fragment0();
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();
        //프래그먼트 리스트에 등록
        fragList.add(fragment0);
        fragList.add(fragment1);
        fragList.add(fragment2);
        fragList.add(fragment3);

        //어뎁터에 리스트 등록
        pageAdapter = new PageAdapter(this,fragList);
        //어뎁터 뷰페이저에 등록
        viewPager2.setAdapter(pageAdapter);
        //화면 표시 적용
        DotsIndicator indicator = findViewById(R.id.dots_indicator);
        indicator.setViewPager2(viewPager2);
    }



}