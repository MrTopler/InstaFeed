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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
//Coded by Jake Terryberry
public class StoreListAdapter implements ListAdapter {
    ArrayList<Store> arrayList;
    Context context;

    public StoreListAdapter(Context context, ArrayList<Store> arrayList) {
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
        Store storeData = arrayList.get(position);
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.listview_store, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Retrieve true storeID and storeLocation via global list containing values of listview stores
                    int storeID = ((MyApplication) ((Activity) context).getApplication()).getCurrentStores().get(position);
                    String storeLocation = ((MyApplication) ((Activity) context).getApplication()).getStoreLocations().get(position);
                    String storeImage = ((MyApplication) ((Activity) context).getApplication()).getStoreImages().get(position);

                    //Navigation to ReviewActivity, passing storeID for review select query, and passing storeLocation
                    Intent i = new Intent(context, ReviewActivity.class);
                    i.putExtra("storeID", storeID);
                    i.putExtra("storeLocation", storeLocation);
                    i.putExtra("storeImage", storeImage);
                    context.startActivity(i);
                }
            });
            TextView tittle = convertView.findViewById(R.id.listview_store_textview);
            ImageView imag = convertView.findViewById(R.id.listview_store_imageview);
            tittle.setText(storeData.location);
            Picasso.with(context)
                    .load(storeData.image)
                    .into(imag);
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
