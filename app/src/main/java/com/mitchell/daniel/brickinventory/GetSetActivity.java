package com.mitchell.daniel.brickinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GetSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_set);

        final EditText searchBox = (EditText) findViewById(R.id.set_number_textbox);
        Button searchButton = (Button)findViewById(R.id.set_number_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = searchBox.getText().toString();
                new GetSetPiecesTask(GetSetActivity.this).execute(s);
            }
        });
    }
}
