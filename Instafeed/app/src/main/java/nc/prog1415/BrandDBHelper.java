package nc.prog1415;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//Coded by Jake Terryberry
public class BrandDBHelper extends SQLiteOpenHelper {
    SQLiteDatabase db = this.getWritableDatabase();

    public BrandDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //must pass database info to base constructor
        super(context, name, factory, version);
    }

    //methods are implicitly called once the Helper object is created
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate(SQLiteDatabase db) {
        //use the SQLite DB to create the tables
        db.execSQL("Create Table Brand (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Image Text)");
        db.execSQL("Create Table Store (ID INTEGER PRIMARY KEY AUTOINCREMENT, Location TEXT, Image TEXT, BrandID INTEGER)");
        db.execSQL("Create Table Review (ID INTEGER PRIMARY KEY AUTOINCREMENT, Words TEXT, Rating INTEGER, Latitude TEXT, Longitude TEXT, Useraddress TEXT, Date TEXT, StoreID INTEGER)");

        //Brand Seed Data
        ContentValues initBrandValues = new ContentValues();
        initBrandValues.put("Name", "Taco Bell");
        initBrandValues.put("Image", "https://i.imgur.com/VV1R1VS.png");
        db.insert("Brand", null, initBrandValues);
        initBrandValues.put("Name", "McDonald");
        initBrandValues.put("Image", "https://i.imgur.com/vtSKLP2.png");
        db.insert("Brand", null, initBrandValues);
        initBrandValues.put("Name", "Subway");
        initBrandValues.put("Image", "https://i.imgur.com/e0bcOPV.png");
        db.insert("Brand", null, initBrandValues);
        initBrandValues.put("Name", "Tim Horton");
        initBrandValues.put("Image", "https://i.imgur.com/OlzIHUw.jpg");
        db.insert("Brand", null, initBrandValues);
        initBrandValues.put("Name", "Panda Express");
        initBrandValues.put("Image", "https://i.imgur.com/4rw6Drj.png");
        db.insert("Brand", null, initBrandValues);

        //Store Seed Data
        ContentValues initStoreValues = new ContentValues();
        initStoreValues.put("Location", "5985 Mavis Rd, Mississauga, ON L5R 3V6");
        initStoreValues.put("Image", "https://i.imgur.com/OJ7u6Ij.jpg");
        initStoreValues.put("BrandID", 5);
        db.insert("Store", null, initStoreValues);
        initStoreValues.put("Location", "500 Glenridge Ave, St. Catharines, ON L2S 3A1");
        initStoreValues.put("Image", "https://i.imgur.com/kGTbTy2.jpg");
        initStoreValues.put("BrandID", 4);
        db.insert("Store", null, initStoreValues);
        initStoreValues.put("Location", "177 Queenston St, St. Catharines, ON L2R 3A3");
        initStoreValues.put("Image", "https://i.imgur.com/YaG8ZzJ.jpg");
        initStoreValues.put("BrandID", 4);
        db.insert("Store", null, initStoreValues);
        initStoreValues.put("Location", "6555 Lundys Ln, Niagara Falls, ON L2G 1V1");
        initStoreValues.put("Image", "https://i.imgur.com/ukPl2rX.jpg");
        initStoreValues.put("BrandID", 1);
        db.insert("Store", null, initStoreValues);
        initStoreValues.put("Location", "6348 Lundys Ln, Niagara Falls, ON L2G 1T6");
        initStoreValues.put("Image", "https://i.imgur.com/9sf1YwS.jpg");
        initStoreValues.put("BrandID", 2);
        db.insert("Store", null, initStoreValues);
        initStoreValues.put("Location", "7906 Lundys Ln, Niagara Falls, ON L2H 1H1");
        initStoreValues.put("Image", "https://i.imgur.com/zC9rdzq.jpg");
        initStoreValues.put("BrandID", 3);
        db.insert("Store", null, initStoreValues);

        //Review Seed Data
        String today = LocalDate.now().toString();
        ContentValues initReviewValues = new ContentValues();
        initReviewValues.put("Words", "Food was quick, staff was friendly");
        initReviewValues.put("Rating", 1);
        initReviewValues.put("Latitude", "1.1");
        initReviewValues.put("Longitude", "1.1");
        initReviewValues.put("Useraddress", "Unknown");
        initReviewValues.put("Date", today);
        initReviewValues.put("StoreID", 1);
        db.insert("Review", null, initReviewValues);
        initReviewValues.put("Words", "Food was okay, place smelled");
        initReviewValues.put("Rating", 0);
        initReviewValues.put("Latitude", "1.1");
        initReviewValues.put("Longitude", "1.1");
        initReviewValues.put("Useraddress", today);
        initReviewValues.put("Date", "Unknown");
        initReviewValues.put("StoreID", 1);
        db.insert("Review", null, initReviewValues);
        initReviewValues.put("Words", "Food Sucked");
        initReviewValues.put("Rating", 0);
        initReviewValues.put("Latitude", "1.1");
        initReviewValues.put("Longitude", "1.1");
        initReviewValues.put("Useraddress", today);
        initReviewValues.put("Date", "Unknown");
        initReviewValues.put("StoreID", 2);
        db.insert("Review", null, initReviewValues);
        initReviewValues.put("Words", "Place was lovely");
        initReviewValues.put("Rating", 1);
        initReviewValues.put("Latitude", "1.1");
        initReviewValues.put("Longitude", "1.1");
        initReviewValues.put("Useraddress", today);
        initReviewValues.put("Date", "Unknown");
        initReviewValues.put("StoreID", 3);
        db.insert("Review", null, initReviewValues);
        initReviewValues.put("Words", "Food was cold!");
        initReviewValues.put("Rating", 0);
        initReviewValues.put("Latitude", "1.1");
        initReviewValues.put("Longitude", "1.1");
        initReviewValues.put("Useraddress", today);
        initReviewValues.put("Date", "Unknown");
        initReviewValues.put("StoreID", 4);
        db.insert("Review", null, initReviewValues);
        initReviewValues.put("Words", "I had a blast of a time at this location, would go again.");
        initReviewValues.put("Rating", 1);
        initReviewValues.put("Latitude", "1.1");
        initReviewValues.put("Longitude", "1.1");
        initReviewValues.put("Useraddress", today);
        initReviewValues.put("Date", "Unknown");
        initReviewValues.put("StoreID", 5);
        db.insert("Review", null, initReviewValues);
        initReviewValues.put("Words", "Cheap and fast, gets the job done.");
        initReviewValues.put("Rating", 1);
        initReviewValues.put("Latitude", "1.1");
        initReviewValues.put("Longitude", "1.1");
        initReviewValues.put("Useraddress", today);
        initReviewValues.put("Date", "Unknown");
        initReviewValues.put("StoreID", 6);
        db.insert("Review", null, initReviewValues);
        initReviewValues.put("Words", "Just another filler review.");
        initReviewValues.put("Rating", 1);
        initReviewValues.put("Latitude", "1.1");
        initReviewValues.put("Longitude", "1.1");
        initReviewValues.put("Useraddress", today);
        initReviewValues.put("Date", "Unknown");
        initReviewValues.put("StoreID", 1);
        db.insert("Review", null, initReviewValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
