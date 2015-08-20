package com.superfunhappyproject.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.superfunhappyproject.app.ui.WordListAdapter;
import com.superfunhappyproject.app.util.WordLoaderService;

import java.util.ArrayList;
import java.util.Set;

import sfh.com.superfunhappyproject.R;


public class GenerateListActivity extends Activity {
    ListView wordListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_list);

        Set<String> loadedWords = WordLoaderService.loadWordsFromSharedPrefs(this);

        wordListView = (ListView) findViewById(R.id.listView);
        wordListView.setAdapter(new WordListAdapter(this, new ArrayList<>(loadedWords)));

    }


}
