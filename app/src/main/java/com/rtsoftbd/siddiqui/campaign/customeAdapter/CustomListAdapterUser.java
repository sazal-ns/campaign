/*
 * Copyright By Noor Nabiul Alam Siddiqui  (c) 2017.
 *  www.fb.com/sazal.ns
 */

package com.rtsoftbd.siddiqui.campaign.customeAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rtsoftbd.siddiqui.campaign.R;
import com.rtsoftbd.siddiqui.campaign.model.User;

import java.util.List;

import androidbangladesh.bengali.support.BengaliUnicodeString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RTsoftBD_Siddiqui on 2017-03-17.
 */

public class CustomListAdapterUser extends BaseAdapter {

    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<User> users;

    public CustomListAdapterUser(Activity activity, List<User> users) {
        this.activity = activity;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (layoutInflater == null) layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) convertView = layoutInflater.inflate(R.layout.row_user_list, null);

        ViewHolder viewHolder = new ViewHolder(convertView);

        User user = users.get(position);

        String name = user.getName();
        //BengaliUnicodeString.getBengaliUTF(activity.getApplication().getApplicationContext(), name, viewHolder.ms_NameTextView);
        viewHolder.ms_NameTextView.setText( user.getName());
        viewHolder.ms_GanderTextView.setText("( "+user.getGender()+" )");
        viewHolder.ms_MobileTextView.setText(user.getPhone());
        viewHolder.ms_EmailTextView.setText(user.getEmail());
        viewHolder.ms_UpozilaTextView.setText(user.getUpozila()+",");
        viewHolder.ms_UnionTextView.setText(user.getUpo_union()+",");
        viewHolder.ms_WordTextView.setText(user.getWord_cha());

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.nameTextView) TextView ms_NameTextView;
        @BindView(R.id.ganderTextView) TextView ms_GanderTextView;
        @BindView(R.id.mobileTextView) TextView ms_MobileTextView;
        @BindView(R.id.emailTextView) TextView ms_EmailTextView;
        @BindView(R.id.upozilaTextView) TextView ms_UpozilaTextView;
        @BindView(R.id.unionTextView) TextView ms_UnionTextView;
        @BindView(R.id.wordTextView) TextView ms_WordTextView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
