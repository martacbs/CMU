package com.example.vieir.projetocmu.register;

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
import com.example.vieir.projetocmu.database.DbHelper;
import com.example.vieir.projetocmu.interests.UserInterests;

import com.example.vieir.projetocmu.models.User;
import com.example.vieir.projetocmu.R;

public class Login extends AppCompatActivity {

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
                User user = verificarUser();
                if (user != null) {
                    Toast.makeText(getApplicationContext(), "login sucessful", Toast.LENGTH_SHORT).show();
                    Intent d = new Intent(getApplicationContext(), UserInterests.class);
                    startActivity(d);
                }

            }
        });

    }

        private User verificarUser() {
            DbHelper dbHelper = new DbHelper(Login.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String username = user.getText().toString();
            String password = pass.getText().toString();
            String query = "SELECT * FROM user WHERE username=? AND password=? ";
            Cursor c = db.rawQuery(query, new String[]{username,password});
            User user = null;
            try {
                if (c != null && c.moveToFirst()) {

                    user = new User();
                    user.setUsername(c.getString(5));
                    user.setPassword(c.getString(6));
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "NAO DA", Toast.LENGTH_SHORT).show();
            }finally {
                if (c != null) {
                    c.close();
                }
             }

             return user;
        }
}
