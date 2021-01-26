package nc.prog1415;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
//Coded by Jake Terryberry
public class Review implements Serializable {
    public int id;
    public String review;
    public String latitude;
    public String longitude;
    public String userAddress;
    public String date;
    public int rating;
    public int storeID;

    public Review(int iid, String ireview, int irating, String ilatitude, String ilongitude, String iuserAddress, String idate, int istoreID) {
        id = iid;
        review = ireview;
        rating = irating;
        latitude = ilatitude;
        longitude = ilongitude;
        userAddress = iuserAddress;
        date = idate;
        storeID = istoreID;
    }

    public Review() {
        id = 0;
        review = "Unknown";
        rating = 1;
        latitude = "0.0";
        longitude = "0.0";
        userAddress = "Unknown";
        date = "Unknown";
        storeID = 0;
    }

    public ArrayList<Review> getReviewsByStoreID(Context context, SQLiteDatabase db, int storeID) {
        //db Query
        BrandDBHelper helper = new BrandDBHelper(context, "nc.prog1415", null, 1);
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Review WHERE StoreID = "+storeID, null);

        //Lists to hold results
        ArrayList<Review> result = new ArrayList<Review>();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<String> reviews = new ArrayList<String>();
        ArrayList<Integer> ratings = new ArrayList<Integer>();
        ArrayList<Integer> storeIDS = new ArrayList<Integer>();
        ArrayList<String> latitudes = new ArrayList<String>();
        ArrayList<String> longitudes = new ArrayList<String>();
        ArrayList<String> addresses = new ArrayList<String>();
        ArrayList<String> dates = new ArrayList<String>();

        //Looping through all cursor columns to fill Lists
        int idIndex = cursor.getColumnIndex("ID");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            ids.add(Integer.parseInt(cursor.getString(idIndex)));

        int nameIndex = cursor.getColumnIndex("Words");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            reviews.add(cursor.getString(nameIndex));

        int ratingIndex = cursor.getColumnIndex("Rating");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            ratings.add(Integer.parseInt(cursor.getString(ratingIndex)));

        int latIndex = cursor.getColumnIndex("Latitude");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            latitudes.add(cursor.getString(latIndex));

         int longIndex = cursor.getColumnIndex("Longitude");
         for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
             longitudes.add(cursor.getString(longIndex));

         int addressIndex = cursor.getColumnIndex("Useraddress");
         for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
             addresses.add(cursor.getString(addressIndex));

        int dateIndex = cursor.getColumnIndex("Date");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            dates.add(cursor.getString(dateIndex));

        int brandIndex = cursor.getColumnIndex("StoreID");
        for(cursor.moveToFirst(); !(cursor.isAfterLast());cursor.moveToNext())
            storeIDS.add(Integer.parseInt(cursor.getString(brandIndex)));

        //Building reviews from lists
        for (int i = 0; i < ids.size(); i++)
            result.add(new Review(ids.get(i), reviews.get(i), ratings.get(i), latitudes.get(i), longitudes.get(i), addresses.get(i), dates.get(i), storeIDS.get(i)));

        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertReview(Context context, SQLiteDatabase db, String rev, int rat, double lat, double lon, String add, int stoID) {
        BrandDBHelper helper = new BrandDBHelper(context, "nc.prog1415", null, 1);
        db = helper.getWritableDatabase();

        String slat = Double.toString(lat);
        String slon = Double.toString(lon);
        String date = LocalDate.now().toString();

        ContentValues initReviewValues = new ContentValues();
        initReviewValues.put("Words", rev);
        initReviewValues.put("Rating", rat);
        initReviewValues.put("Latitude", slat);
        initReviewValues.put("Longitude", slon);
        initReviewValues.put("Useraddress", add);
        initReviewValues.put("Date", date);
        initReviewValues.put("StoreID", stoID);
        db.insert("Review", null, initReviewValues);
    }
}
