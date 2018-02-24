package androidtest.com.androidtest.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidtest.com.androidtest.MainActivity;
import androidtest.com.androidtest.Model.SongResponse;
import androidtest.com.androidtest.R;
import androidtest.com.androidtest.interfaces.onItemClickListener;

/**
 * Created by e071367 on 2/22/2018.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {

    private List<SongResponse.SongModel> songsList;
    private onItemClickListener listener;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView trackNameTxt, artistNameTxt;
        public ImageView thumnailImg;
        public LinearLayout mLinlayout;

        public MyViewHolder(View view) {
            super(view);
            artistNameTxt = (TextView) view.findViewById(R.id.artist_name);
            trackNameTxt = (TextView) view.findViewById(R.id.track_name);
            thumnailImg = (ImageView) view.findViewById(R.id.album_thumnail);
            mLinlayout = (LinearLayout) view.findViewById(R.id.mainlayout);

        }
    }


    public SongAdapter(MainActivity mainActivity, List<SongResponse.SongModel> songsArrayList) {
        this.songsList = songsArrayList;
        this.mContext = mainActivity;
        this.listener = (onItemClickListener) mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_song, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final SongResponse.SongModel mSongModel = songsList.get(position);
        holder.artistNameTxt.setText(mSongModel.getArtistName());
        holder.trackNameTxt.setText(mSongModel.getTrackName());

        Glide.with(mContext)
                .load(mSongModel.getArtworkUrl60())
                .centerCrop()
                .crossFade()
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.thumnailImg);

        holder.mLinlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemDetailClick(mSongModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }
}
