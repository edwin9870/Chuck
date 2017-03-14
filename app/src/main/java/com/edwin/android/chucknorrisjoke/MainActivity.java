package com.edwin.android.chucknorrisjoke;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.edwin.android.chucknorrisjoke.adapter.JokesAdapter;
import com.edwin.android.chucknorrisjoke.async.JokeQueryTask;

public class MainActivity extends AppCompatActivity {

    private EditText mQueryEditText;
    private Button mSearchButton;
    private ProgressBar mLoadingIndicatorProgressBar;
    private RecyclerView mRecyclerView;
    private JokesAdapter mJokesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.rv_jokes_items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setReverseLayout(false);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(false);

        mJokesAdapter = new JokesAdapter();
        mRecyclerView.setAdapter(mJokesAdapter);

        mQueryEditText = (EditText) findViewById(R.id.et_query);
        mLoadingIndicatorProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        refreshJokes("hell");

        mSearchButton = (Button) findViewById(R.id.bt_search);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String wordToSearch =   mQueryEditText.getText().toString();
                Log.i(MainActivity.class.toString(), "Clicked button. Word to search: "+ wordToSearch);
                closeKeyboard();
                refreshJokes(wordToSearch);
            }
        });


    }

    private void closeKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private void refreshJokes(String wordToSearch) {

        JokeQueryTask jokeQueryTask = new JokeQueryTask();
        jokeQueryTask.setMrecyclerView(mRecyclerView);
        jokeQueryTask.setmLoadingIndicatorProgressBar(mLoadingIndicatorProgressBar);
        jokeQueryTask.setMjokesAdapter(mJokesAdapter);
        jokeQueryTask.execute(wordToSearch);
        Log.d(MainActivity.class.toString(), "Search completed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();

        if(itemThatWasClickedId == item.getItemId()) {
            Toast.makeText(this, "Refreshing", Toast.LENGTH_SHORT).show();
            //refreshJokes("hell");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
