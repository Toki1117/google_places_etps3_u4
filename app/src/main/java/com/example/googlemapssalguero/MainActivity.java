package com.example.googlemapssalguero;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isServiceOk()) {
            init();
        }
    }

    private void init() {
        Button btnMap = findViewById(R.id.btn_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(i);
            }
        });
    }

    public boolean isServiceOk() {
        Log.d(TAG, "isServiceOK: Checking google service version");

        int avaible = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if (avaible == ConnectionResult.SUCCESS) {
            Log.d(TAG, "isServiceOK: Google play service is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(avaible)) {
            Log.d(TAG, "isServiceOK: an error ocurred but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, avaible, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "No puedes usar Google Maps", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
