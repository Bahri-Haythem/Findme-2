package com.example.findme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class myPositionAdapter extends BaseAdapter {
    Context con;
    ArrayList<Position> data;

    public myPositionAdapter(Context con, ArrayList<Position> data) {
        this.con = con;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = LayoutInflater.from(con);
        CardView element = (CardView)inf.inflate(R.layout.view_position,null);

        TextView tv_id=element.findViewById(R.id.tv_id_view);
        TextView tv_num=element.findViewById(R.id.tv_num_view);
        TextView tv_lon=element.findViewById(R.id.tv_lon_view);
        TextView tv_lat=element.findViewById(R.id.tv_lat_view);

        Button btn_call = element.findViewById(R.id.btn_call_view);
        Button btn_map = element.findViewById(R.id.btn_map_view);
        Button btn_sms = element.findViewById(R.id.btn_sms);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(con, "appel de :"+data.get(position).numero, Toast.LENGTH_SHORT).show();
            }
        });

        Position p = (Position) getItem(position);
        tv_id.setText(""+p.id);
        tv_num.setText(""+p.numero);
        tv_lon.setText(""+p.longitude);
        tv_lat.setText(""+p.latitude);

        return element;
    }
}
