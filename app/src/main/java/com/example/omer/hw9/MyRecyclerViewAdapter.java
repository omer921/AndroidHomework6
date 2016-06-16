package com.example.omer.hw9;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/*
 * Created by Omer on 2/13/16.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Map<String, ?>> mDataset;
    private Context mContext;
    OnItemClickListener mItemClickListner;

public MyRecyclerViewAdapter(Context myContext, List<Map<String, ?>> myDataset) {
    mContext = myContext;
    mDataset = myDataset;
}

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListner) {
        this.mItemClickListner = mItemClickListner;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
        public void onOverflowMenuClick(View view, int position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder  {
        public ImageView vIcon;
        public TextView vTitle;
        public TextView vDescription;
        public ImageView vMenu;

        public ViewHolder(View v) {
            super(v);
            vIcon = (ImageView) v.findViewById(R.id.icon);
            vTitle = (TextView) v.findViewById(R.id.title);
            vDescription = (TextView) v.findViewById(R.id.description);
            vMenu = (ImageView) v.findViewById(R.id.selection);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListner != null) {
                        mItemClickListner.onItemClick(v, getLayoutPosition());
                        Log.d("Layout Position", ""+getLayoutPosition());
                    }
                }


            });

            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mItemClickListner.onItemLongClick(v, getLayoutPosition());
                    Log.d("Position", getLayoutPosition() + "");
                    return true;
                }
            });

            vMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListner.onOverflowMenuClick(v, getLayoutPosition());
                }
            });

        }

        public void bindMovieData(Map<String, ?> movie) {

            vTitle.setText((String) movie.get("name"));
            vDescription.setText((String) movie.get("description"));
            vIcon.setImageResource((Integer) movie.get("image"));

        }

    }





    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardView;
            cardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ViewHolder vH = new ViewHolder(cardView);
        return vH;
    }

    @Override
    public int getItemViewType(int position) {
        Map<String, ?> item = mDataset.get(position);
        double numD = (Double) item.get("rating");
        int num =  (int) Math.round(numD);

        return num;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        Map<String, ?> movie = mDataset.get(position);
        holder.bindMovieData(movie);


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
