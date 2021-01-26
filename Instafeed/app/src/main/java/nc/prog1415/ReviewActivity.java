package nc.prog1415;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
//Coded by Jake Terryberry
public class ReviewActivity extends AppCompatActivity implements DialogReview.DialogReviewListener {

    SQLiteDatabase db;
    ArrayList<Review> reviews;
    ListView reviewListView;
    ImageView ivStoreImage;
    TextView txtviewBrandName;
    TextView txtviewLocation;
    TextView txtviewLikes;
    TextView txtviewDislikes;
    TextView txtviewTotalCount;
    Button btnWriteReview;
    int storeID;
    String storeLocation;
    String storeImage;
    Location gps_loc = null;
    Location network_loc = null;
    Location final_loc;
    double longitude;
    double latitude;
    String userCountry;
    String userAddress;
    String finalUserAddress = "Unknown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        //Retrieve values passed along with intent
        storeID = getIntent().getIntExtra("storeID", 0);
        storeLocation = getIntent().getStringExtra("storeLocation");
        storeImage = getIntent().getStringExtra("storeImage");

        //DB Command to fill reviews with selected storeID
        reviews = new ArrayList<Review>();
        reviews = new Review().getReviewsByStoreID(ReviewActivity.this, db, storeID);

        reviewListView = findViewById(R.id.listView_review);

        ReviewListAdapter reviewListAdapter = new ReviewListAdapter(this, reviews);
        reviewListView.setAdapter(reviewListAdapter);

        //Top store image
        ivStoreImage = (ImageView)findViewById(R.id.imageViewLocationRevAct);
        Picasso.with(ReviewActivity.this).load(storeImage).into(ivStoreImage);

        //Top brand name
        String brandName = ((MyApplication) ((Activity) ReviewActivity.this).getApplication()).getSelectedBrandName();
        txtviewBrandName = (TextView)findViewById(R.id.textViewBrandNameRevAct);
        txtviewBrandName.setText(brandName);

        //Top location
        txtviewLocation = (TextView)findViewById(R.id.textViewAdressRevAct);
        txtviewLocation.setText(storeLocation);

        //Generate total likes/dislikes
        Integer pos = 0;
        Integer neg = 0;
        for (Review b : reviews) {
            if (b.rating == 0)
                neg++;
            else
                pos++;
        }

        //Top likes/dislikes
        txtviewLikes = (TextView)findViewById(R.id.textViewPositiveReviews);
        String l = "Likes: " + Integer.toString(pos);
        txtviewLikes.setText(l);
        txtviewDislikes = (TextView)findViewById(R.id.textViewNegativeReviews);
        String d = "Dislikes: " + Integer.toString(neg);
        txtviewDislikes.setText(d);

        //Total Review Count
        txtviewTotalCount = (TextView)findViewById(R.id.txtViewReviewCount);
        String totalCount = "Reviews: "+Integer.toString(reviews.size());
        txtviewTotalCount.setText(totalCount);

        //btnWriteReview ini
        btnWriteReview = (Button)findViewById(R.id.btnReview);
        btnWriteReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiablog();
            }
        });

        //Getting user location for review taken from: https://stackoverflow.com/questions/57863500/how-can-i-get-my-current-location-in-android-using-gps
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        try {
            gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (gps_loc != null) {
            final_loc = gps_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        }
        else if (network_loc != null) {
            final_loc = network_loc;
            latitude = final_loc.getLatitude();
            longitude = final_loc.getLongitude();
        }
        else {
            latitude = 0.0;
            longitude = 0.0;
        }

        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE}, 1);

        try {

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                userCountry = addresses.get(0).getCountryName();
                userAddress = addresses.get(0).getAddressLine(0);
                finalUserAddress = userCountry + ", " + userAddress;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openDiablog() {
        DialogReview dialogReview = new DialogReview();
        dialogReview.show(getSupportFragmentManager(), "dialog review");
    }


    @Override
    public void applyReview(String review, int rating) {
        review = review.trim();
        new Review().insertReview(ReviewActivity.this, db, review, rating, latitude, longitude, finalUserAddress, storeID);
        Intent i = new Intent(ReviewActivity.this, ReviewActivity.class);
        i.putExtra("storeID", storeID);
        i.putExtra("storeLocation", storeLocation);
        i.putExtra("storeImage", storeImage);
        ReviewActivity.this.startActivity(i);
    }
}