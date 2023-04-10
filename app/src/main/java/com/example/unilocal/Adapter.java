package com.example.unilocal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.unilocal.databinding.ActivityRegisterFormUser2Binding;
import com.example.unilocal.databinding.ActivityRegisterFormUser3Binding;

public class Adapter extends PagerAdapter {

    int[] layouts;
    public ImageButton btn;
    LayoutInflater layoutInflater;

    public Adapter(Context context, int[] layouts, ActivityRegisterFormUser3Binding act){
        this.layouts = layouts;
        this.btn = act.btnChooseImg;
        this.layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(layouts[position], container, false);
        btn = view.findViewById(R.id.btn_choose_img);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() { return layouts.length; }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
