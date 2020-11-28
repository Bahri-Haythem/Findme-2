package com.example.findme;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myRecyclerPositionAdapter extends RecyclerView.Adapter<myRecyclerPositionAdapter.MyViewHolder> {
    Context con;
    ArrayList<Position> data;

    public myRecyclerPositionAdapter(Context con, ArrayList<Position> data) {
        this.con = con;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(con);
        CardView element = (CardView)inf.inflate(R.layout.view_position,null);
        return new MyViewHolder(element);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Position p = data.get(position);
        holder.tv_id.setText(""+p.id);
        holder.tv_num.setText(""+p.numero);
        holder.tv_lon.setText(""+p.longitude);
        holder.tv_lat.setText(""+p.latitude);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_id,tv_num,tv_lon,tv_lat;
        Button btn_call,btn_map,btn_sms;
        public MyViewHolder(@NonNull View element) {
            super(element);
            tv_id=element.findViewById(R.id.tv_id_view);
            tv_num=element.findViewById(R.id.tv_num_view);
            tv_lon=element.findViewById(R.id.tv_lon_view);
            tv_lat=element.findViewById(R.id.tv_lat_view);

            btn_call = element.findViewById(R.id.btn_call_view);
            btn_map = element.findViewById(R.id.btn_map_view);
            btn_sms = element.findViewById(R.id.btn_sms);

            btn_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int indice = getAdapterPosition();
                    Toast.makeText(con, "appel a :"+data.get(indice).numero, Toast.LENGTH_SHORT).show();
                    //faire appel
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+data.get(indice).numero));
                    con.startActivity(callIntent);
                }
            });
            btn_map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Position p = data.get(getAdapterPosition());
                    Intent i = new Intent(con,MapsActivity.class);
                    i.putExtra("NUMERO",p.numero);
                    i.putExtra("LONGITUDE",p.longitude);
                    i.putExtra("LATITUDE",p.latitude);
                    con.startActivity(i);
                }
            });
            btn_sms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Position p = data.get(getAdapterPosition());
                    SmsManager manager = SmsManager.getDefault();
                    manager.sendTextMessage(p.numero,null,"findme#21.33#65.33",null,null); //ma position

                }
            });
        }
    }
}
