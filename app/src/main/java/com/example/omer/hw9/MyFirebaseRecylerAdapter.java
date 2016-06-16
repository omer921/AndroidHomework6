package com.example.omer.hw9;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.firebase.client.Query;
import com.firebase.ui.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;



public class MyFirebaseRecylerAdapter extends FirebaseRecyclerAdapter<Movie,MyFirebaseRecylerAdapter.MovieViewHolder> {

    private Context mContext ;

   static OnItemClickListener mItemClickListner;

    public MyFirebaseRecylerAdapter(Class<Movie> modelClass, int modelLayout,
                                    Class<MovieViewHolder> holder, Query ref,Context context) {
        super(modelClass,modelLayout,holder,ref);
        this.mContext = context;
    }

    @Override
    protected void populateViewHolder(MovieViewHolder movieViewHolder, Movie movie, int i) {
        //movieViewHolder.vIcon = (ImageView)
        //TODO: Populate viewHolder by setting the movie attributes to cardview fields
        movieViewHolder.vTitle.setText(movie.getName());
        movieViewHolder.vDesc.setText(movie.getDescription());
        Picasso.with(mContext).load(movie.getUrl()).into(movieViewHolder.vIcon);
    }

    //TODO: Populate ViewHolder and add listeners.
    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        ImageView vIcon;
        TextView vDesc;
        TextView vTitle;
        ImageView vMenu;

    public MovieViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.icon);
            vDesc = (TextView) v.findViewById(R.id.description);
            vTitle = (TextView) v.findViewById(R.id.title);
            vMenu = (ImageView) v.findViewById(R.id.selection);
            vMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListner.onOverflowMenuClick(v, getLayoutPosition());
                }
            });
        }


    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListner) {
        this.mItemClickListner = mItemClickListner;
    }

    public interface OnItemClickListener {

        public void onOverflowMenuClick(View view, int position);

    }

}
