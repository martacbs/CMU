package com.example.vieir.projetocmu;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vieir.projetocmu.Register.login;
import com.example.vieir.projetocmu.Register.userRegister;

public class MainActivity extends AppCompatActivity {

    Button register,login;
    Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register=(Button)findViewById(R.id.bRegister);
        login=(Button)findViewById(R.id.bLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent x= new Intent(getApplicationContext(),userRegister.class);
                startActivity(x);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y=new Intent(getApplicationContext(),login.class);
                startActivity(y);
            }
        });
    }


}
