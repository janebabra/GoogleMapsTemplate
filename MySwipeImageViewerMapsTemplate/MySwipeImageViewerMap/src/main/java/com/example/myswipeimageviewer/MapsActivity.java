package com.example.myswipeimageviewer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

    private static final LatLng grave = new LatLng(57.616029, 11.763617);

    private void setUpMap() {
    Marker marker =  mMap.addMarker(new MarkerOptions()
                .position(grave)
                .snippet("Southern Archipelago on Sweden's West Coast.")
                .title("Bronze Age Grave")
           /* By default, markers are oriented against the screen, and will not rotate or tilt with the camera.
              Flat markers are oriented against the surface of the earth, and will rotate and tilt with the camera.
              Both types of markers do not change size based on zoom.
              Use GroundOverlays if you desire this effect.*/
                .flat(true)
            // custom marker icon
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cemetary)));

        // shows allways title and snippet
        marker.showInfoWindow();

        // zooming in the map
       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(grave, 13));

        // add button for myLocation
        mMap.setMyLocationEnabled(true);
        // MAP_TYPE_TERRAIN, MAP_TYPE_HYBRID and MAP_TYPE_NONE
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);


    }


    public final void animateCamera(CameraUpdate cameraUpdate) {
        mMap.animateCamera(cameraUpdate);

    // Construct a CameraPosition focusing on the Bronze Age Grave and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(grave)      // Sets the center of the map to the Bronze Age Grave
                .zoom(13)           // Sets the zoom
                .bearing(90)        // Sets the orientation of the camera to east
                .tilt(45)           // Sets the tilt of the camera to 30 degrees
                .build();           // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

}
