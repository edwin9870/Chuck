package com.edwin.android.chucknorrisjoke.async;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.edwin.android.chucknorrisjoke.util.ChuckNorrisNetworkUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Edwin Ramirez Ventur on 3/13/2017.
 */

public class JokeQueryTask extends AsyncTask<String, Void, List<String>> {

    private TextView mJokeItemsTextView;
    private ProgressBar mLoadingIndicatorProgressBar;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mLoadingIndicatorProgressBar.setVisibility(View.VISIBLE);
        mJokeItemsTextView.setVisibility(View.INVISIBLE);

    }

    @Override
    protected List<String> doInBackground(String... params) {
        String query = params[0];

        List<String> result = ChuckNorrisNetworkUtil.getJokes(query);
        return result;
    }

    @Override
    protected void onPostExecute(List<String> jokes) {
        Log.d(JokeQueryTask.class.toString(), "Joke size: "+jokes.size());
        if(mJokeItemsTextView != null) {
            mJokeItemsTextView.setText("");
            for(String joke: jokes) {
                mJokeItemsTextView.append(joke+"\n\n\n");
            }
        }
        mLoadingIndicatorProgressBar.setVisibility(View.INVISIBLE);
        mJokeItemsTextView.setVisibility(View.VISIBLE);
    }

    public void setmJokeItemsTextView(TextView mJokeItemsTextView) {
        this.mJokeItemsTextView = mJokeItemsTextView;
    }

    public void setmLoadingIndicatorProgressBar(ProgressBar mLoadingIndicatorProgressBar) {
        this.mLoadingIndicatorProgressBar = mLoadingIndicatorProgressBar;
    }
}
