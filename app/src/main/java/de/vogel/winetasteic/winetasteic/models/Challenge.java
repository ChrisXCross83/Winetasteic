package de.vogel.winetasteic.winetasteic.models;

import android.graphics.drawable.Drawable;

/**
 * Created by cbraun on 23.10.15.
 */
public class Challenge {

    private String description;
    private Drawable challengeImage;

    public Challenge(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getChallengeImage() {
        return challengeImage;
    }

    public void setChallengeImage(Drawable challengeImage) {
        this.challengeImage = challengeImage;
    }
}
