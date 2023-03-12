package com.example.lockjavaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PatternLockViewListener {

    PatternLockView patternLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patternLockView = (PatternLockView)findViewById(R.id.pattern_lock_view);

        patternLockView.addPatternLockListener(this);
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onProgress(List<PatternLockView.Dot> progressPattern) {

    }

    @Override
    public void onComplete(List<PatternLockView.Dot> pattern) {
        if (PatternLockUtils.patternToString(patternLockView, pattern).equalsIgnoreCase("0125")){
            patternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            startActivity(intent);
            patternLockView.setInStealthMode(true);                                     // Set the pattern in stealth mode (pattern drawing is hidden)
//            patternLockView.setTactileFeedbackEnabled(true);                            // Enables vibration feedback when the pattern is drawn
//            patternLockView.setInputEnabled(false);
//            patternLockView.setAspectRatioEnabled(true);

        }else {
            patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }


    }
    @Override
    public void onCleared() {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}