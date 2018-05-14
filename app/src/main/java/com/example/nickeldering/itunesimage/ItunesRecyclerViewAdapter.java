package com.example.nickeldering.itunesimage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ProgressDialog;

import com.example.yangxu.itunesimage.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by nickeldering on 6/11/17.
 */

public class ItunesRecyclerViewAdapter extends RecyclerView.Adapter<ItunesRecyclerViewAdapter.ItunesImageViewHolder> {
    private static final String TAG = "ItunesRecyclerViewAdapt";
    private List<Photo> mPhotosList;
    private Context mContext;
    private int loadCounter = 0;
    private ProgressDialog pd;
    private boolean loadComplete = false;

    public ItunesRecyclerViewAdapter(Context context, List<Photo> photosList) {
        mContext = context;
        mPhotosList = photosList;
        pd = new ProgressDialog(mContext);
    }

    @Override
    public ItunesImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Called by the layout manager when it needs a new view
        Log.d(TAG, "onCreateViewHolder: new view requested");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse, parent, false);
        return new ItunesImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItunesImageViewHolder holder, int position) {
        // Called by the layout manager when it wants new data in an existing row
        if ((mPhotosList == null) || (mPhotosList.size() == 0)) {
            holder.thumbnail.setImageResource(R.drawable.placeholder);
            holder.title.setText("Failed to load photos");
        } else {

            Photo photoItem = mPhotosList.get(position);
            Log.d(TAG, "onBindViewHolder: " + photoItem.getmTitle() + " --> " + position);
            Picasso.with(mContext).load(photoItem.getmImage())
                    .error(R.drawable.placeholder)
                    .placeholder(R.drawable.placeholder)
                    .into(holder.thumbnail);
            holder.title.setText(photoItem.getmTitle());

            loadCounter++;
            if (!loadComplete) {
                pd.setMessage("Loading Photo " + loadCounter + ", Press Screen to continue");
                pd.show();
                if (loadCounter == 25)
                    loadComplete = true;
            } else
                pd.dismiss();
        }
    }

    @Override
    public int getItemCount() {
        return ((mPhotosList != null) && (mPhotosList.size() != 0) ? mPhotosList.size() : 1);
    }

    void loadNewData(List<Photo> newPhotos) {
        mPhotosList = newPhotos;
        notifyDataSetChanged();
    }

    public Photo getPhoto(int position) {
        return ((mPhotosList != null) && (mPhotosList.size() != 0) ? mPhotosList.get(position) : null);
    }

    static class ItunesImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "ItunesImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public ItunesImageViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "ItunesImageViewHolder: starts");
            this.thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}