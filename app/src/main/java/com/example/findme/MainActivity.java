package com.example.findme;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    boolean permission_sms = false;
    boolean permission_call = false;
    boolean permission_gps = false;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
            permission_sms=true;
        }else{
            ActivityCompat.requestPermissions(this,new
                    String[]{
                            Manifest.permission.SEND_SMS
                            ,Manifest.permission.READ_SMS
                            ,Manifest.permission.RECEIVE_SMS},
                    1);
        }

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            permission_gps=true;
        }else{
            ActivityCompat.requestPermissions(this,new
                            String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    2); //1
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            startActivity(new Intent(MainActivity.this,Ajout.class));
                            }
                        }).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        PositionManager pm = new PositionManager(this,PositionHelper.nom_fichier,PositionHelper.ver);
        ArrayList<Position> data = pm.selectionnerTout();
        myRecyclerPositionAdapter ad = new myRecyclerPositionAdapter(this,data);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(ad);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            permission_sms =
                    (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED);

            if (!permission_sms){
                finish();
            }
        }
        if (requestCode == 2) {
            permission_call =
                    (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED);

            if (!permission_call){
                finish();
            }
        }
    }
}