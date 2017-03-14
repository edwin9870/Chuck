package com.edwin.android.chucknorrisjoke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edwin.android.chucknorrisjoke.R;

import java.util.List;

/**
 * Created by Edwin Ramirez Ventur on 3/13/2017.
 */

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesAdapterViewHolder>{

    private static final String TAG = JokesAdapter.class.getSimpleName();
    private List<String> mJokes;

    private TextView mJokeItemTextView;


    class JokesAdapterViewHolder extends RecyclerView.ViewHolder {

        public JokesAdapterViewHolder(View itemView) {
            super(itemView);
            mJokeItemTextView = (TextView) itemView.findViewById(R.id.tv_joke_item);
        }

        public void bind(int position) {
            mJokeItemTextView.setText(mJokes.get(position));
        }
    }

    public void setJokes(List<String> jokes) {
        this.mJokes = jokes;
        notifyDataSetChanged();
    }

    @Override
    public JokesAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int idJokeListItemLayout = R.layout.joke_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(idJokeListItemLayout, parent, false);
        return new JokesAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(JokesAdapterViewHolder holder, int position) {
        Log.d(TAG, "Position: #"+position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(mJokes == null) {
            return 0;
        }
        return mJokes.size();
    }

}
