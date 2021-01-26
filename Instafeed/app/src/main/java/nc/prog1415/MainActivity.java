package nc.prog1415;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.*;
import java.util.*;
//Coded by Jake Terryberry
public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ArrayList<Brand> brands;
    ListView brandListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load ArrayList with all Brands
        brands = new ArrayList<Brand>();
        brands = new Brand().getBrands(MainActivity.this, db);

        brandListView = findViewById(R.id.brandListView);

        BrandListAdapter brandListAdapter = new BrandListAdapter(this, brands);
        brandListView.setAdapter(brandListAdapter);
    }
}