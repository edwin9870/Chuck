package com.edwin.android.chucknorrisjoke;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.edwin.android.chucknorrisjoke.async.JokeQueryTask;

public class MainActivity extends AppCompatActivity {

    private TextView mJokeItems;
    private EditText mQueryEditText;
    private Button mSearchButton;
    private ProgressBar mLoadingIndicatorProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJokeItems = (TextView) findViewById(R.id.tv_joke_items);
        mQueryEditText = (EditText) findViewById(R.id.et_query);
        mLoadingIndicatorProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        refreshJokes("hell");

        mSearchButton = (Button) findViewById(R.id.bt_search);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String wordToSearch =   mQueryEditText.getText().toString();
                Log.i(MainActivity.class.toString(), "Clicked button. Word to search: "+ wordToSearch);
                refreshJokes(wordToSearch);
            }
        });


    }

    private void refreshJokes(String wordToSearch) {

        JokeQueryTask jokeQueryTask = new JokeQueryTask();
        jokeQueryTask.setmJokeItemsTextView(mJokeItems);
        jokeQueryTask.setmLoadingIndicatorProgressBar(mLoadingIndicatorProgressBar);
        jokeQueryTask.execute(wordToSearch);
        Log.d(MainActivity.class.toString(), "Search completed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //TODO Add a toast message when is refresh button is clicked
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
