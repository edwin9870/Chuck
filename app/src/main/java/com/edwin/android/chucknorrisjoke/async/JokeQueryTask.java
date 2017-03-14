package com.edwin.android.chucknorrisjoke.async;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.edwin.android.chucknorrisjoke.adapter.JokesAdapter;
import com.edwin.android.chucknorrisjoke.util.ChuckNorrisNetworkUtil;

import java.util.List;

/**
 * Created by Edwin Ramirez Ventur on 3/13/2017.
 */

public class JokeQueryTask extends AsyncTask<String, Void, List<String>> {

    private ProgressBar mLoadingIndicatorProgressBar;
    private JokesAdapter mJokesAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mLoadingIndicatorProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);

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
        if(jokes != null) {
            mJokesAdapter.setJokes(jokes);
        }
        mLoadingIndicatorProgressBar.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }


    public void setmLoadingIndicatorProgressBar(ProgressBar mLoadingIndicatorProgressBar) {
        this.mLoadingIndicatorProgressBar = mLoadingIndicatorProgressBar;
    }

    public void setMjokesAdapter(JokesAdapter mJokesAdapter) {
        this.mJokesAdapter = mJokesAdapter;
    }

    public void setMrecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }
}
