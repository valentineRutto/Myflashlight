package com.example.valentine.myflashlight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private Button btnSwitch;
    private boolean isFlashOn = false;
    private boolean hasFlash;
    private Camera camera;
    MediaPlayer mp;

    @Override
    protected void onStop() {
        super.onStop();
        if (camera != null) {
            camera.release();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout l=(LinearLayout)findViewById(R.id.mylayout);
        l.setOnTouchListener();

        hasFlash = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if (!hasFlash) {
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("ERROR");
            alert.setMessage("Sorry, your device doesnt support flash light!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog builder = alert.create();
            builder.show();
            return;
        }
        camera=Camera.open();
        final Camera.Parameters p=camera.getParameters();

btnSwitch=(Button) findViewById(R.id.button);
        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFlashOn) {
                    Log.i("info","torch is turn off!");
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(p);
                    camera.startPreview();
                    isFlashOn=true;
                }else {
                    Log.i("info","torch is turn on!");
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(p);
                    camera.startPreview();
                    isFlashOn=true;
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }



    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onStart() {
        super.onStart();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
