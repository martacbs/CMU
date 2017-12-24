package com.example.vieir.projetocmu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.vieir.projetocmu.adapter.HorasAdapter;
import com.example.vieir.projetocmu.models.Horas;

import java.util.ArrayList;

public class HorasL extends AppCompatActivity {

    ArrayList<Horas> horas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_horas);

        RecyclerView rvHoras =(RecyclerView) findViewById(R.id.rvHoras);

        horas = createContactList(24);

        HorasAdapter adapter = new HorasAdapter(this, horas);

        rvHoras.setAdapter(adapter);

        rvHoras.setLayoutManager(new LinearLayoutManager(this));

    }

    private ArrayList<Horas> createContactList (int x){
        ArrayList<Horas> horas = new ArrayList<>(x);
        for(int i=0;i<x;i++){
            Horas h =null;
            String hora=  i + ":00" ;
            if(i< x){
                h =new Horas (hora);
            }else{
                h=new Horas(hora);
            }
            horas.add(h);
        }
        return horas;
    }
}
