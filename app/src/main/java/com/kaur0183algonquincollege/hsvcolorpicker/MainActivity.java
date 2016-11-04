package com.kaur0183algonquincollege.hsvcolorpicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;

public abstract class MainActivity extends AppCompatActivity implements Observer
        , SeekBar.OnSeekBarChangeListener {

    private static final String ABOUT_DIALOG_TAG = "About";
    private static final String LOG_TAG = "HSV";
    private AboutDialogFragment mAboutDialog;
    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mValueSB;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences settings = getSharedPreferences(getResources().getString(R.string.app_name), Context.MODE_PRIVATE);

        mAboutDialog = new AboutDialogFragment();

        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHueSB = (SeekBar) findViewById(R.id.hueSB);
        mSaturationSB = (SeekBar) findViewById(R.id.saturationSB);
        mValueSB = (SeekBar) findViewById(R.id.valueSB);

        mModel = new HSVModel();

        mHueSB.setMax((int) HSVModel.MAX_HUE);
        mSaturationSB.setMax((int) HSVModel.MAX_SATURATION);
        mValueSB.setMax((int) HSVModel.MAX_VALUE);

        mModel.addObserver(this);

        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);
        this.updateView();

    }

    @Override
    public void update(Observable observable, Object data) {
        updateView();
    }

    private void updateHue() {
        mHueSB.setProgress((int) mModel.getHue());
    }

    private void updateSaturation() {
        mSaturationSB.setProgress((int) mModel.getSaturation());
    }

    private void updateValue() {
        mValueSB.setProgress((int) mModel.getValue());
    }


    private void updateView() {
        updateColorSwatch();
        updateHue();
        updateSaturation();
        updateValue();
    }

    private void updateColorSwatch() {
        mColorSwatch.setBackgroundColor(mModel.getColorSwatch());
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

        if (id == R.id.action_about) {
            mAboutDialog.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
