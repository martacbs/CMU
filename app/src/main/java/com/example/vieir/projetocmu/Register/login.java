package com.example.vieir.projetocmu.Register;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vieir.projetocmu.Database.DbHelper;
import com.example.vieir.projetocmu.Interests.userInterests;
import com.example.vieir.projetocmu.Database.DbHelper;
import com.example.vieir.projetocmu.R;

public class login extends AppCompatActivity {

    Button login;
    EditText user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.buttonLogin);
        user = (EditText) findViewById(R.id.insertUser);
        pass = (EditText) findViewById(R.id.insertPass);
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("username", user.getText().toString());
                values.put("password", pass.getText().toString());

                verificarUser();

            }
        });

    }

        private void verificarUser() {

            DbHelper dbHelper = new DbHelper(login.this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String username = user.getText().toString();
            String password = pass.getText().toString();
            String sql = "SELECT * FROM user WHERE usename=? AND password=?";
            try {

                Cursor c = db.rawQuery(sql, null);

                if (c != null) {
                    if (c.getCount() > 0) {
                        c.moveToNext();
                        Toast.makeText(getApplicationContext(), "login sucessful", Toast.LENGTH_SHORT).show();
                        Intent d=new Intent(getApplicationContext(),userInterests.class);
                        startActivity(d);
                    }
                    }

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "NAO DA", Toast.LENGTH_SHORT).show();
            }

        }

}
