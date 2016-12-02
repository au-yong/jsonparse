package com.example.user.jsonparse;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 27/11/2016.
 */

public class NewsAdapter extends ArrayAdapter<TakeData> {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;


    public NewsAdapter(Context context, ArrayList<TakeData> Data) {
        super(context, 0,Data);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View mView = convertView;
        if(mView == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           mView = layoutInflater.inflate(R.layout.list_item,parent,false);

        }
        TakeData newData = getItem(position);

        tv1 = (TextView)mView.findViewById(R.id.title);
        tv1.setText(newData.getTitle());

        tv2 = (TextView)mView.findViewById(R.id.section);
        tv1.setText(newData.getSection());
        tv3 = (TextView)mView.findViewById(R.id.time);
        tv1.setText(newData.getDate());
        return mView;







    }
}
