package nc.prog1415;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//Coded by Jake Terryberry
public class Brand implements Serializable {
    public int id;
    public String name;
    public String image;

    public Brand(int iid, String iname, String iimage) {
        id = iid;
        name = iname;
        image = iimage;
    }

    public Brand() {
        id = 0;
        name = "Unknown";
        image = null;
    }

    public String toString() {
        return name;
    }

    public ArrayList<Brand> getBrands(Context context, SQLiteDatabase db) {
        //db Query
        BrandDBHelper helper = new BrandDBHelper(context, "nc.prog1415", null, 1);
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Brand", null);

        //Lists to hold results
        ArrayList<Brand> result = new ArrayList<Brand>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> brandNames = new ArrayList<String>();
        ArrayList<String> imageURLs = new ArrayList<String>();

        //Looping through all cursor columns to fill Lists
        int idIndex = cursor.getColumnIndex("ID");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            ids.add(Integer.parseInt(cursor.getString(idIndex)));

        int nameIndex = cursor.getColumnIndex("Name");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            brandNames.add(cursor.getString(nameIndex));

        int imageIndex = cursor.getColumnIndex("Image");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            imageURLs.add(cursor.getString(imageIndex));

        //Setting Global variable of ID's to have a List of active ID's for filtering
        ((MyApplication) ((Activity) context).getApplication()).setCurrentBrands(ids);

        //Setting Gloabl variable of Brand names to get Brand name displayed on ReviewActivity
        ((MyApplication) ((Activity) context).getApplication()).setBrandNames(brandNames);

        //Building brands from lists
        for (int i = 0; i < ids.size(); i++)
            result.add(new Brand(ids.get(i), brandNames.get(i), imageURLs.get(i)));

        return result;
    }
}
