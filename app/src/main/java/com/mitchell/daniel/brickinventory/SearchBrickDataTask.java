package com.mitchell.daniel.brickinventory;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by argetlam on 9/3/2017.
 */

public class SearchBrickDataTask extends AsyncTask<String, Void, String> {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    final private String REBRICKABLE_API_KEY = "key 6c17c2c742a2c63ccf783df3f9c02a0b";
    SearchBrickDataTask(){

    }

    @Override
    protected String doInBackground(String... params){

        String url = params[0];
        String result = "";
        String inputLine = "";

        try{
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
            Log.e("ERR",e.getMessage());
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(String string){
        super.onPostExecute(string);
    }
}
