package de.vogel.winetasteic.winetasteic.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by cbraun on 23.10.15.
 */
public class Stage {

    private String name;
    private float longitude;
    private float latitude;
    private Pub pub;
    private Challenge challenge;

    public Stage(String name,  float latitude,float longitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Pub getPub() {
        return pub;
    }

    public void setPub(Pub pub) {
        this.pub = pub;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public LatLng getLatLng(){
        LatLng latLng = new LatLng(this.getLatitude(),this.getLongitude());
        return latLng;
    }
}
