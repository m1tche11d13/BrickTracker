package com.mitchell.daniel.brickinventory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SearchBrickDataTask extends AsyncTask<String, Void, String> {

    private final Context mContext;

    SearchBrickDataTask(Context mContext){
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(String... params){

        String REQUEST_METHOD = "GET";
        int READ_TIMEOUT = 15000;
        int CONNECTION_TIMEOUT = 15000;
        String REBRICKABLE_API_KEY = "key 6c17c2c742a2c63ccf783df3f9c02a0b";

        String url = params[0];
        String result = "";
        String inputLine = "";

        try{
            Log.e("URL", url);

            URL myUrl = new URL(url);
            HttpsURLConnection connection = (HttpsURLConnection) myUrl.openConnection();

            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.setRequestProperty("Authorization", REBRICKABLE_API_KEY);

            connection.connect();

            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }

            reader.close();
            streamReader.close();

            result = stringBuilder.toString();
            Log.e("RESULT", result);

            return result;
        }

        catch(IOException e){
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(String string){
        super.onPostExecute(string);
        Intent intent = new Intent(mContext, SearchResultsDisplayActivity.class);
        intent.putExtra("JSON_RESULTS", string);
        mContext.startActivity(intent);
        ((Activity)mContext).finish();
    }
}