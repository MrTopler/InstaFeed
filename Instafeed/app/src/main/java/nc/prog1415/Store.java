package nc.prog1415;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
//Coded by Jake Terryberry
public class Store implements Serializable {
    public int id;
    public String location;
    public String image;
    public int brandID;

    public Store(int iid, String ilocation, String iimage, int ibrandID) {
        id = iid;
        location = ilocation;
        image = iimage;
        brandID = ibrandID;
    }

    public Store() {
        id = 0;
        location = "Unknown";
        image = "Unknown";
        brandID = 0;
    }

    public ArrayList<Store> getStoresByBrandID(Context context, SQLiteDatabase db, int brandID) {
        //db Query
        BrandDBHelper helper = new BrandDBHelper(context, "nc.prog1415", null, 1);
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Store WHERE BrandID = "+brandID, null);

        //Lists to hold results
        ArrayList<Store> result = new ArrayList<Store>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> locations = new ArrayList<String>();
        ArrayList<String> imageURLs = new ArrayList<String>();
        ArrayList<Integer> brandIDs = new ArrayList<Integer>();

        //Looping through all cursor columns to fill Lists
        int idIndex = cursor.getColumnIndex("ID");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            ids.add(Integer.parseInt(cursor.getString(idIndex)));

        int nameIndex = cursor.getColumnIndex("Location");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            locations.add(cursor.getString(nameIndex));

        int imageIndex = cursor.getColumnIndex("Image");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            imageURLs.add(cursor.getString(imageIndex));

        int brandIndex = cursor.getColumnIndex("BrandID");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            brandIDs.add(Integer.parseInt(cursor.getString(idIndex)));

        //Setting Global variable of ID's to have a List of active ID's for filtering, setting global arrayList<string> store locations for ReviewAtivity use
        ((MyApplication) ((Activity) context).getApplication()).setCurrentStores(ids);
        ((MyApplication) ((Activity) context).getApplication()).setStoreLocations(locations);
        ((MyApplication) ((Activity) context).getApplication()).setStoreImages(imageURLs);

        //Building stores from lists
        for (int i = 0; i < ids.size(); i++)
            result.add(new Store(ids.get(i), locations.get(i), imageURLs.get(i), brandIDs.get(i)));

        return result;
    }
}
