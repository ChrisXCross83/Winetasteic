package de.vogel.winetasteic.winetasteic.models;

import android.graphics.drawable.Drawable;

/**
 * Created by cbraun on 23.10.15.
 */
public class Pub {

    String name ="";
    String description ="";
    Drawable image;

    public Pub(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
