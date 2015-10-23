package de.vogel.winetasteic.winetasteic;


import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import de.vogel.winetasteic.winetasteic.models.Stage;
import fr.quentinklein.slt.LocationTracker;

public class TourActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapView mapView;

    List<Stage> locationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();

        mapView = (MapView)findViewById(R.id.map);
        mapView.getMapAsync(this);
        mapView.onCreate(savedInstanceState);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        new LocationTracker(this){
            @Override
            public void onLocationFound(Location location) {
                LatLng hier = new LatLng(location.getLatitude(),location.getLongitude());
                if(mMap != null){
                    mMap.addMarker(new MarkerOptions().position(hier).title("Hier bin ich"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(hier));
                }

            }

            @Override
            public void onTimeout() {

            }
        };


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
        
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10.0f ) );
        PolylineOptions  lineoptions = new PolylineOptions();
        for (Stage stage:locationList) {
            mMap.addMarker(new MarkerOptions().position(stage.getLatLng()).title(stage.getName()));
            lineoptions.add(stage.getLatLng());
        }
        lineoptions.width(5);
        lineoptions.color(Color.RED);

        mMap.addPolyline(lineoptions);

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
        mapView.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }
}
