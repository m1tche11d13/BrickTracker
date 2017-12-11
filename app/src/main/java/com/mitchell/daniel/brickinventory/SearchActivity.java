package com.mitchell.daniel.brickinventory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edmodo.rangebar.RangeBar;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity {

    int leftYear = 0;
    int rightYear = 0;
    int leftParts = 0;
    int rightParts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        int year = Calendar.getInstance().get(Calendar.YEAR);

        final TextView yearIndex = (TextView) findViewById(R.id.year_index);
        final TextView partCountIndex = (TextView) findViewById(R.id.part_count_index);

        final EditText searchBox = (EditText) findViewById(R.id.search_box);

        yearIndex.setText("1950 - " + Integer.toString(year));
        partCountIndex.setText("0 - unlimited");

        final RangeBar year_selection_bar = (RangeBar) findViewById(R.id.year_selection_bar);
        year_selection_bar.setTickCount(year - 1949);
        year_selection_bar.setTickHeight(0);
        year_selection_bar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar, int leftIndex, int rightIndex) {
                leftYear = leftIndex;
                rightYear = rightIndex;
                yearIndex.setText(Integer.toString(leftIndex + 1950) + " - " + Integer.toString(rightIndex + 1950));
            }
        });

        final RangeBar part_count_selection_bar = (RangeBar) findViewById(R.id.part_count_selection_bar);
        part_count_selection_bar.setTickCount(101);
        part_count_selection_bar.setTickHeight(0);
        part_count_selection_bar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar, int leftIndex, int rightIndex) {
                leftParts = leftIndex;
                rightParts = rightIndex;
                if(rightIndex == 100)
                    partCountIndex.setText(Integer.toString(leftIndex*50) + " - unlimited");
                else
                    partCountIndex.setText(Integer.toString(leftIndex*50) + " - " + Integer.toString(rightIndex*50));
            }
        });

        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftYear = year_selection_bar.getLeftIndex();
                rightYear = year_selection_bar.getRightIndex();
                leftParts = part_count_selection_bar.getLeftIndex();
                rightParts = part_count_selection_bar.getRightIndex();

                String url = "https://rebrickable.com/api/v3/lego/sets/?min_year=";
                url += Integer.toString(leftYear + 1950);
                url += "&max_year=";
                url += Integer.toString(rightYear + 1950);
                url += "&min_parts=";
                url += Integer.toString(leftParts*50);
                if(rightParts != 100) {
                    url += "&max_parts=";
                    url += Integer.toString(rightParts*50);
                }
                url += "&search=";
                url += searchBox.getText();

                new SearchBrickDataTask(SearchActivity.this).execute(url);
            }
        });
    }
}