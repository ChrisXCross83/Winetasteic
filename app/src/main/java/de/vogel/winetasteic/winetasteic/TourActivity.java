package de.vogel.winetasteic.winetasteic;


import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import de.vogel.winetasteic.winetasteic.models.Stage;
import fr.quentinklein.slt.LocationTracker;

public class TourActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapView mapView;
    TextView txtDescription;
    ImageView imageDescription;
    Button buttonNext;
    LocationTracker mTracker;
    Marker userMarker;

    int index = 0;

    List<Stage> locationList = new ArrayList<>();
    LatLng myPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            index = savedInstanceState.getInt("loc",0);
        }
        setContentView(R.layout.activity_tour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtDescription = (TextView)findViewById(R.id.txtDescription);
        imageDescription = (ImageView)findViewById(R.id.imgLocation);
        buttonNext = (Button)findViewById(R.id.btn_weiter);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                setMarker();

            }
        });

        init();


        mapView = (MapView)findViewById(R.id.map);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);




    }

    private void trackMe(){
        new LocationTracker(this){
            @Override
            public void onLocationFound(Location location) {
                LatLng hier = new LatLng(location.getLatitude(),location.getLongitude());
                myPosition = hier;
                userMarker = mMap.addMarker(new MarkerOptions().position(hier).title("Hier bin ich"));

                stopListen();

            }

            @Override
            public void onTimeout() {

            }
        };
    }

    private void setMarker(){
        if (index < locationList.size()  ) {
            txtDescription.setText(locationList.get(index).getName());
            Marker marker = mMap.addMarker(new MarkerOptions().position(locationList.get(index).getLatLng()).title(locationList.get(index).getName()));
            if(myPosition != null){
                animateMarker(userMarker, myPosition, false);
                //mMap.addMarker(new MarkerOptions().position(myPosition).title("ICH"));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationList.get(index).getLatLng(),17.0f));
            marker.showInfoWindow();

        }
        else{
            Toast.makeText(getApplicationContext(),"Ende erreicht",Toast.LENGTH_SHORT).show();
        }
    }

    private void init(){
        Stage l1 = new Stage("Till Eulenspiegel",49.7896f,9.9302f);
        Stage l2 = new Stage("Maulaffenbäck",49.795662f,9.930387f);
        Stage l3 = new Stage("Staatlicher Hofkeller",49.793836f,9.938459f);
        Stage l4 = new Stage("Alte Mainmühle",49.793313f,9.926961f);
        Stage l5 = new Stage("Juliusspital",49.797735f,9.932770f);

        locationList.add(l1);
        locationList.add(l2);
        locationList.add(l3);
        locationList.add(l4);
        locationList.add(l5);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        setMarker();
        trackMe();


    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("loc",index);
        mapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }

    public void animateMarker(final Marker marker, final LatLng toPosition,
                              final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();
        Point startPoint = proj.toScreenLocation(marker.getPosition());
        final LatLng startLatLng = proj.fromScreenLocation(startPoint);
        final long duration = 500;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * toPosition.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * toPosition.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        marker.setVisible(false);
                    } else {
                        marker.setVisible(true);
                    }
                }
            }
        });
    }
}
