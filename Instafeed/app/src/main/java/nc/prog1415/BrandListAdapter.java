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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
//Coded by Jake Terryberry
public class BrandListAdapter implements ListAdapter {

    ArrayList<Brand> arrayList;
    Context context;

    public BrandListAdapter(Context context, ArrayList<Brand> arrayList) {
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
        Brand brandData = arrayList.get(position);
        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.listview_brand, null);
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Retrieve true brandID via global list containing values of listview brands
                    int brandID = ((MyApplication) ((Activity) context).getApplication()).getCurrentBrands().get(position);

                    //Navigation to StoreLocationActivity, passing brandID for store select query, and assigning global brandName variable for ReviewActivity page
                    ((MyApplication) ((Activity) context).getApplication()).setSelectedBrandName(((MyApplication) ((Activity) context).getApplication()).getBrandNames().get(position));
                    Intent i = new Intent(context, StoreLocationActivity.class);
                    i.putExtra("brandID", brandID);
                    context.startActivity(i);
                }
            });
            TextView tittle = convertView.findViewById(R.id.listview_brand_textview);
            ImageView imag = convertView.findViewById(R.id.listview_brand_imageview);
            tittle.setText(brandData.name);
            Picasso.with(context)
                    .load(brandData.image)
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
