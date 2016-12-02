package com.example.user.jsonparse;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by User on 27/11/2016.
 */

public class QueryUtil {

    private QueryUtil(){}


    public static ArrayList<TakeData> startConnection(String url)
    {
    URL url1 = null;
        String jsonResponse ="";
        try {
            url1 = new URL(url);
            jsonResponse = makeConnectionRequest(url1);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<TakeData> theData= parsingJson(jsonResponse);

        return theData;
    }


    private static String makeConnectionRequest(URL urlConnection)throws IOException{
        String jsonResponse ="";

        if(urlConnection == null) {
            return jsonResponse;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream IS1 =null;

        try{
        httpURLConnection = (HttpURLConnection)urlConnection.openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setConnectTimeout(20000);
        httpURLConnection.connect();

        if(httpURLConnection.getResponseCode() ==200) {
            IS1 = httpURLConnection.getInputStream();
            jsonResponse = readBuffer(IS1);
        }
        }catch(IOException e){

        }finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if(IS1!=null){
                IS1.close();
            }
        }return jsonResponse;
    }

    private static String readBuffer (InputStream inputStream ) throws IOException{
        StringBuilder StringB = new StringBuilder();
        if(inputStream != null){
            InputStreamReader ISR = new InputStreamReader(inputStream, Charset.defaultCharset());
            BufferedReader BR = new BufferedReader(ISR);

            String line = BR.readLine();
            while(line != null){
                StringB.append(line);
                line = BR.readLine();
            }
        }return  StringB.toString();
    }






    public static ArrayList<TakeData> parsingJson(String Data1){

        ArrayList<TakeData> Data2 = new ArrayList<>();

        try {
            JSONObject Jdata = new JSONObject(Data1);
            JSONObject mData = Jdata.getJSONObject("response");
            JSONArray root = mData.getJSONArray("results");
            for(int i = 0; i<root.length();i++){

                JSONObject section = root.getJSONObject(i);
                String sectionName = section.getString("sectionName");
                String Title = section.getString("webTitle");
                String date = section.getString("webPublicationDate");
                String nUrl = section.getString("webUrl");
                TakeData take = new TakeData(sectionName,Title,nUrl,date);
                Data2.add(take);
                Log.v("the json object","is done");
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


        return Data2;

    }

}
