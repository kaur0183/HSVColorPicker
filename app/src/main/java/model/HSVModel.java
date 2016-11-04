package model;

import android.graphics.Color;

import java.util.Observable;


public class HSVModel extends Observable{

    public static final float MAX_HUE = 359.00f;
    public static final float MAX_SATURATION = 100.00f;
    public static final float MAX_VALUE = 100.00f;
    public static final float MIN_HUE = 0f;
    public static final float MIN_SATURATION = 0f;
    public static final float MIN_VALUE = 0f;

    private float hue;
    private float saturation;
    private float value;

    public HSVModel() {this(MAX_HUE, MAX_SATURATION, MAX_VALUE );}

    public HSVModel( float hue, float saturation , float value){
        super();

        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }

    public float getHue() {return hue; }
    public float getValue() {return value; }
    public float getSaturation() {return saturation; }

    public void setHue(float hue) {
        this.hue = hue;

        this.updateObservers();
    }

    public void setValue(float value) {
        this.value = value ;

        this.updateObservers();
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;

        this.updateObservers();
    }

    // broadcast the update method to all registered observers
    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }

    public int getColorSwatch() {
        return Color.HSVToColor(new float[]{ hue, saturation, value } );
    }
}
