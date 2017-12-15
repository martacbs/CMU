package com.example.vieir.projetocmu.Register;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vieir.projetocmu.Database.DbHelper;
import com.example.vieir.projetocmu.R;

public class userRegister extends AppCompatActivity {

    EditText name, email,localidade, username, password,confirmarPassword,text1;
    Button registar,login;
    Context c;
    private void insertUser() throws Exception{
        DbHelper dbHelper = new DbHelper(userRegister.this);
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name.getText().toString());
        values.put("email", email.getText().toString());
        values.put("localidade", localidade.getText().toString());
        values.put("username", username.getText().toString());
        values.put("password", password.getText().toString());
        values.put("confirmarPassword", confirmarPassword.getText().toString());
        long rowId = db.insert("user", null, values);
        if (rowId < 0) {
            throw new Exception("Erro aqui");
        }
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        name=(EditText)findViewById(R.id.insertNome);
        email=(EditText)findViewById(R.id.insertEmail);
        localidade=(EditText)findViewById(R.id.insertLocalidade);
        username=(EditText)findViewById(R.id.insertUsername);
        password=(EditText)findViewById(R.id.insertPassword);
        confirmarPassword=(EditText)findViewById(R.id.insertConfirmPassword);

        registar = (Button)findViewById(R.id.button_salvarPessoa);
        login =(Button)findViewById(R.id.button_Login);

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(confirmarPassword.getText().toString())) {
                    try {
                        insertUser();
                        Toast.makeText(userRegister.this, "Adicionado com sucesso", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(userRegister.this, "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, login.class);
                startActivity(i);
            }
        });

    }

}
