package com.example.walla.erder;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.vision.barcode.Barcode;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AsyncResponse {

    private Restaurant restaurant;

    private final int REQUEST_CODE_PLACEPICKER = 1;
    private final int REQUEST_CODE_QRCODE_SCAN_CAMERA = 2;
    private final int REQUEST_CODE_ACCOUNT = 3;

    private final int PERMISSIONS_REQUEST_LOCATION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ********************************* start of code ****************************************
        displayCurrentAddress();
        displayRestaurantAtCurrentLocation();

//        Toast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
    }

    private void displayRestaurantAtCurrentLocation() {
        Button testButton = (Button) findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = (EditText) findViewById(R.id.enter_current_location);
                //String currentLocation = editText.getText().toString();
                String currentLocation = "1815 West 36th Street, Chicago, Illinois, 60609, United States";

                HashMap postData = new HashMap();
                postData.put("mobile", "android");
                postData.put("txtAddress", currentLocation);

                PostResponseAsyncTask postResponseAsyncTask = new PostResponseAsyncTask(MainActivity.this, postData);
                postResponseAsyncTask.execute("http://192.168.1.145/index.php"); // 10.0.2.2 in emulator

                //Toast.makeText(MainActivity.this, currentLocation, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // this is for Generic AsyncTask
    @Override
    public void processFinish(String jsonString) {
        Toast.makeText(MainActivity.this, jsonString, Toast.LENGTH_LONG).show();
        restaurant = new Restaurant();

        JsonParser jsonParser = new JsonParser(restaurant, jsonString);

        System.out.println(restaurant.toString());

    }

    private void displayCurrentAddress() {
        allowCustomViewInActionBar();

        Location currentGPSLocation = getCurrentGPSLocation();

        if (currentGPSLocation == null ) {
            return;
        }

        displayAddress(currentGPSLocation);
    }

    private void allowCustomViewInActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setCustomView(R.layout.action_bar_main);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);
        }
    }

    private Location getCurrentGPSLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        return getBestLocation(locationManager);
    }

    private Location getBestLocation(LocationManager locationManager) {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    PERMISSIONS_REQUEST_LOCATION);
        }

        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;

        for (String provider : providers) {
            Location l = locationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }
        }

        return bestLocation;
    }

    private void displayAddress(Location currentGPSLocation) {
        Address address = getAddress(currentGPSLocation);
        String currentAddressStr = getAddressStr(address);

        EditText enterCurrentLocation = (EditText) findViewById(R.id.enter_current_location);
        enterCurrentLocation.setText(currentAddressStr);
        enterCurrentLocation.clearFocus();
    }

    private Address getAddress(Location currentGPSLocation) {

        double longitude = currentGPSLocation.getLongitude();
        double latitude = currentGPSLocation.getLatitude();

        Address address = null;

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
             address = geocoder.getFromLocation(latitude, longitude, 1).get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }

    private String getAddressStr(Address currentAddress) {
        String address = currentAddress.getAddressLine(0);
        String city = currentAddress.getLocality();
        String state = currentAddress.getAdminArea();
        String postalCode = currentAddress.getPostalCode();
        String country = currentAddress.getCountryName();

        return address + ", " + city + ", " + state + ", " + postalCode + ", " + country;
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_QRCODE_SCAN_CAMERA && resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(MainActivity.this, "No QR Code Found", Toast.LENGTH_SHORT).show();
            } else {
                Barcode qrcode = data.getParcelableExtra("qrcode");
                Toast.makeText(MainActivity.this, "QR Code Result is: " + qrcode.displayValue, Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_CODE_PLACEPICKER && resultCode == RESULT_OK) {
            getPlaceFromPlacePicker(data);
        }
    }

    private void getPlaceFromPlacePicker(Intent data) {
        Place placeSelected = PlacePicker.getPlace(data, this);

        String name = placeSelected.getName().toString();
        String address = placeSelected.getAddress().toString();

        EditText enterCurrentLocation = (EditText) findViewById(R.id.enter_current_location);
        enterCurrentLocation.setText(name + ", " + address);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_map_marker) {
            startPlacePickerActivity();
            return true;
        } else if (id == R.id.action_qrcode_scan) {
            startScanQrcodeCameraActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startPlacePickerActivity() {
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();

        try {
            Intent intent = intentBuilder.build(this);
            startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startScanQrcodeCameraActivity() {
        Intent intent = new Intent(this, ScanQrcodeCameraActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QRCODE_SCAN_CAMERA);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_account) {
            startAccountActivity();
        } else if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startAccountActivity() {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ACCOUNT);
    }


}
