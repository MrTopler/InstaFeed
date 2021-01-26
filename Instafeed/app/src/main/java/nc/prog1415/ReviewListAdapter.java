package nc.prog1415;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
//Coded by Jake Terryberry
public class ReviewListAdapter implements ListAdapter {

    ArrayList<Review> arrayList;
    Context context;

    public ReviewListAdapter(Context context, ArrayList<Review> arrayList) {
        this.arrayList=arrayList;
        this.context=context;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }
    @Override
    public boolean isEnabled(int position) {
        return true;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Review reviewData = arrayList.get(position);
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.listview_review, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            TextView date = convertView.findViewById(R.id.listView_review_textview_date);
            TextView review = convertView.findViewById(R.id.listview_review_textview_review);
            ImageView image = convertView.findViewById(R.id.listview_review_imageview);
            date.setText(reviewData.userAddress); //placeholder for dates
            review.setText(reviewData.review);
            if (reviewData.rating == 0)
                Picasso.with(context).load(R.drawable.thumbs_down_grey).into(image);
            else
                Picasso.with(context).load(R.drawable.thumbs_up_grey).into(image);
        }
        return convertView;
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getViewTypeCount() {
        return arrayList.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
}
