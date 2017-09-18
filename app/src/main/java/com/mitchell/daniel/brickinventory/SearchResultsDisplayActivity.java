package com.mitchell.daniel.brickinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SearchResultsDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_display);

        Intent intent = getIntent();
        String s = intent.getStringExtra("JSON_RESULTS");
        Log.e("SEARCH_RESULT",s);
    }
}
