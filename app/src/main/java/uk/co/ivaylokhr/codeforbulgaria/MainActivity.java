package uk.co.ivaylokhr.codeforbulgaria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.List;
import java.util.Locale;


public class MainActivity extends Activity {

    ImageButton fireButton;
    ImageButton armsButton;
    ImageButton healthButton;
    ImageButton roadButton;
    LocationManager locationManager;
    Location bestLocation;
    LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Log.w("GPS ", String.valueOf(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)));
        Log.w("Networks ", String.valueOf(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)));
        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                if (isBetterLocation(location, bestLocation)) {
                    bestLocation = location;
                    Log.w("LOCATION", location.toString());
                }
                // Called when a new location is found by the network location provider.

            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.w("LOCATION extras", extras.toString());
            }

            public void onProviderEnabled(String provider) {
                Log.w("Enable", provider);
            }

            public void onProviderDisabled(String provider) {
                Log.w("Disable", provider);
            }
        };

// Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

//        Log.i("banana rama" , String.valueOf(longitude)+  "  " + String.valueOf(latitude));
//        42.7077557  23.2985805
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void addListenerOnButton() {
        //Select a specific button to bundle it with the action you want



        roadButton = (ImageButton) findViewById(R.id.road);

        roadButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();

            }

        });
        armsButton = (ImageButton) findViewById(R.id.arms);

        armsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {



            }

        });
        healthButton = (ImageButton) findViewById(R.id.health);

        healthButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, EmergencyHealthActivity.class);
                startActivity(intent);



            }

        });
        fireButton = (ImageButton) findViewById(R.id.fire);

        fireButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {




            }

        });








    }



    private static final int TWO_MINUTES = 1000 * 60 * 2;

    /** Determines whether one Location reading is better than the current Location fix
     * @param location  The new Location that you want to evaluate
     * @param currentBestLocation  The current Location fix, to which you want to compare the new one
     */
    protected boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(),
                currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

//
//
//    public static class LocationManagerHelper implements LocationListener {
//
//        private static double latitude;
//        private static double longitude;
//
//        @Override
//        public void onLocationChanged(Location loc) {
//            latitude = loc.getLatitude();
//            longitude = loc.getLongitude();
//            Log.i("MyLocation",Double.toString(latitude)+" "+Double.toString(longitude));
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) { }
//
//        @Override
//        public void onProviderEnabled(String provider) { }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            // TODO Auto-generated method stub
//
//        }
//
//        public static double getLatitude() {
//            return latitude;
//        }
//
//        public static double getLongitude() {
//            return longitude;
//        }

//    }





}
