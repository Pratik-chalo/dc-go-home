package com.dc.gohome;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	
	private MainFragment mainFragment;
	public static double latitude;
	public static double longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        GPSTracker gps = new GPSTracker(this);
        if(gps.canGetLocation()){
            
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
             
            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        
        if (savedInstanceState == null) {
            // Add the fragment on initial activity setup
            mainFragment = new MainFragment();
            getSupportFragmentManager()
            .beginTransaction()
            .add(android.R.id.content, mainFragment)
            .commit();
        } else {
            // Or set the fragment from restored state info
            mainFragment = (MainFragment) getSupportFragmentManager()
            .findFragmentById(android.R.id.content);
        }
    }
    
    
}
