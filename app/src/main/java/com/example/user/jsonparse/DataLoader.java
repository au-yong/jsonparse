package com.example.user.jsonparse;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by User on 27/11/2016.
 */

public class DataLoader extends AsyncTaskLoader<ArrayList<TakeData>> {


    private String murl;

    public DataLoader(Context context,String url) {
        super(context);
        murl = url;
    }

    @Override
    protected void onStartLoading() {
        super.forceLoad();
    }

    @Override
    public ArrayList<TakeData> loadInBackground() {
        if(murl==null){
            return null;
        }
        ArrayList<TakeData> takeDatas= QueryUtil.startConnection(murl);
        return takeDatas;
    }
}
