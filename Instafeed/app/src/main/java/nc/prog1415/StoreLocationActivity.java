package nc.prog1415;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
//Coded by Jake Terryberry
public class StoreLocationActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ArrayList<Store> stores;
    ListView storeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_location);

        //Load ArrayList with all Stores
        int id = getIntent().getIntExtra("brandID", 1);
        stores = new ArrayList<Store>();
        stores = new Store().getStoresByBrandID(StoreLocationActivity.this, db, id);

        storeListView = findViewById(R.id.storeListView);

        StoreListAdapter storeListAdapter = new StoreListAdapter(this, stores);
        storeListView.setAdapter(storeListAdapter);
    }
}