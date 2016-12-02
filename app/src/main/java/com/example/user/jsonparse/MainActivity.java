package com.example.user.jsonparse;

import android.app.LauncherActivity;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<TakeData>>{
    private ListView lv1;
    private NewsAdapter mAdapter;
    private LoaderManager loaderManager;

    private static final String  THE_URL = "https://content.guardianapis.com/search?api-key=86ed165d-a2bc-4c6f-b66b-3784b360741d";
    private static int TAKEDATA_LOADER_ID =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (ListView)findViewById(R.id.thlist);
        mAdapter = new NewsAdapter(this,new ArrayList<TakeData>());
        lv1.setAdapter(mAdapter);

        loaderManager = getLoaderManager();
        loaderManager.initLoader(TAKEDATA_LOADER_ID,null,this);


        ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cManager.getActiveNetworkInfo();
        if(netInfo.isConnected()&&netInfo!=null){
            loaderManager = getLoaderManager();
            loaderManager.initLoader(TAKEDATA_LOADER_ID,null,this);

        }else {
            Toast.makeText(this,"no connection",Toast.LENGTH_SHORT).show();
        }


        }


    @Override
    public Loader<ArrayList<TakeData>> onCreateLoader(int i, Bundle bundle) {
        Log.e("this","Starting");
        return new DataLoader(this,THE_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<TakeData>> loader, ArrayList<TakeData> takeDatas) {

        Log.e("this","this is final");
        mAdapter.clear();

        if(takeDatas != null&& !takeDatas.isEmpty()){
            mAdapter.addAll(takeDatas);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<TakeData>> loader) {
        mAdapter.clear();
    }
}





