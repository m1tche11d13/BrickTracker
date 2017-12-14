package com.mitchell.daniel.brickinventory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;

import javax.net.ssl.HttpsURLConnection;

public class GetSetPiecesTask extends AsyncTask<String, Void, String> {

    private final Context mContext;

    GetSetPiecesTask(Context mContext){
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(String... params){

        Hashtable<Part, Integer[]> parts_obtained = new Hashtable<>();

        String REQUEST_METHOD = "GET";
        int READ_TIMEOUT = 15000;
        int CONNECTION_TIMEOUT = 15000;
        String REBRICKABLE_API_KEY = "key 6c17c2c742a2c63ccf783df3f9c02a0b";

        String url = "https://rebrickable.com/api/v3/lego/sets/" + params[0] + "/parts/";
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
            JsonReader reader = new JsonReader(streamReader);

            reader.beginObject();
            while(reader.hasNext()){
                String key = reader.nextName();
                switch(key){
                    case "results":
                        Log.e("HERE",key);
                        //Begins the array of results
                        reader.beginArray();
                        while(reader.hasNext()){
                            //Begins the individual result object
                            Part p = new Part();
                            int quantity = 0;
                            Integer arr[] = new Integer[2];
                            reader.beginObject();
                            while(reader.hasNext()){
                                String key2 = reader.nextName();
                                switch(key2){
                                    case "part":
                                        reader.beginObject();
                                        while(reader.hasNext()){
                                            String key3 = reader.nextName();
                                            switch(key3){
                                                case "part_num":
                                                    p.setPart_num(reader.nextString());
                                                    break;
                                                case "name":
                                                    p.setName(reader.nextString());
                                                    break;
                                                case "part_cat_id":
                                                    p.setPart_cat_id(reader.nextInt());
                                                    break;
                                                case "part_url":
                                                    p.setPart_url(reader.nextString());
                                                    break;
                                                case "part_img_url":
                                                    p.setPart_img_url(reader.nextString());
                                                    break;
                                                default:
                                                    reader.skipValue();
                                            }
                                        }
                                        reader.endObject();
                                        break;
                                    case "color":
                                        reader.beginObject();
                                        while(reader.hasNext()){
                                            String key4 = reader.nextName();
                                            switch(key4){
                                                case "id":
                                                    p.setPart_color_id(reader.nextInt());
                                                    break;
                                                case "name":
                                                    p.setPart_color_name(reader.nextString());
                                                    break;
                                                case "rgb":
                                                    p.setPart_color_rgb(reader.nextString());
                                                    break;
                                                case "is_trans":
                                                    p.setPart_color_trans(reader.nextBoolean());
                                                    break;
                                                default:
                                                    reader.skipValue();
                                            }
                                        }
                                        reader.endObject();
                                        break;
                                    case "quantity":
                                        quantity = reader.nextInt();
                                        break;
                                    default:
                                        reader.skipValue();
                                }
                            }
                            arr[0] = 0;
                            arr[1] = quantity;
                            parts_obtained.put(p, arr);
                            reader.endObject();
                        }
                        reader.endArray();
                        break;
                    default:
                        reader.skipValue();
                }
            }

            reader.close();
            streamReader.close();

            Log.e("RESULT", result);

            for(Part p : parts_obtained.keySet()){
                Log.e("PART:\t",p.toString());
                Log.e("COUNT:\t",parts_obtained.get(p)[0] + "/" + parts_obtained.get(p)[1]);
            }

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