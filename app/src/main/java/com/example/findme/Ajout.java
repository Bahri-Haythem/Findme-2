package com.example.findme;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class Ajout extends AppCompatActivity {

    private Button btnval,btnqte;
    private EditText ednbr,edlong,edlat;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        Bundle b= this.getIntent().getExtras();
        if(b!=null){
            String body = b.getString("CONTENU");
        }

        btnval = findViewById(R.id.val);
        btnqte = findViewById(R.id.qte);

        ednbr = findViewById(R.id.ed1);
        edlong = findViewById(R.id.ed2);
        edlat = findViewById(R.id.ed3);

        btnqte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ajout.this.finish();
            }
        });

        btnval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nbr = ednbr.getText().toString();
                String lon = edlong.getText().toString();
                String lat = edlat.getText().toString();

                PositionManager manager = new PositionManager(Ajout.this,PositionHelper.nom_fichier,1);
                manager.inserer(nbr,lon,lat);
            }
        });

        Bundle bundle =this.getIntent().getExtras();
        if(bundle!=null){
            String body = bundle.getString("BODY");
            String num = bundle.getString("NUMERO");
            ednbr.setText(num);
            String [] t = body.split("#");
            edlong.setText(t[1]);
            edlat.setText(t[2]);
        }else{
            ednbr.setHint("ecrire votre numero ...");//recuoeration numero de sim
            FusedLocationProviderClient mClient=
                    LocationServices.getFusedLocationProviderClient(this.getApplicationContext()
                    );
            mClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null){
                        edlong.setText(location.getLongitude()+"");
                        edlat.setText(location.getLatitude()+"");
                    }
                }
            });
            LocationRequest request = LocationRequest.create().setInterval(10).setSmallestDisplacement(10);
            LocationCallback mcall = new LocationCallback(){
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    Location location = locationResult.getLastLocation();
                    if(location!=null){
                        edlong.setText(location.getLongitude()+"");
                        edlat.setText(location.getLatitude()+"");
                    }
                }
            };
            mClient.requestLocationUpdates(request,mcall,null);
        }
    }
}
