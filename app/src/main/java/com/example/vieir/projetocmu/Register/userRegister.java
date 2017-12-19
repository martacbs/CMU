package com.example.vieir.projetocmu.Register;

import android.content.ContentValues;
import android.content.Context;
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
import com.example.vieir.projetocmu.Models.User;
import com.example.vieir.projetocmu.R;

public class userRegister extends AppCompatActivity {

    EditText name, email, localidade, username, password, confirmarPassword, text1;
    Button registar, login;
    Context c;

    private void insertUser() throws Exception {

        DbHelper dbHelper = new DbHelper(userRegister.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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


                DbHelper dbHelper = new DbHelper(userRegister.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();


                if (name.length() == 0) {
                    Toast.makeText(userRegister.this, "Insira o seu nome", Toast.LENGTH_LONG).show();
                } else if (email.length() == 0) {
                    try{
                        
                    }catch(Exception e){
                        Toast.makeText(userRegister.this, "Email invalido", Toast.LENGTH_LONG).show();
                    }

                    Toast.makeText(userRegister.this, "Insira o seu e-mail", Toast.LENGTH_LONG).show();
                } else if (localidade.length() == 0) {
                    Toast.makeText(userRegister.this, "Insira a localidade", Toast.LENGTH_LONG).show();
                } else if (username.length() == 0) {
                    Toast.makeText(userRegister.this, "Insira um username", Toast.LENGTH_LONG).show();
                } else if (password.length() == 0) {
                    Toast.makeText(userRegister.this, "Insira uma password", Toast.LENGTH_LONG).show();
                } else if (confirmarPassword.length() == 0) {
                    Toast.makeText(userRegister.this, "Tem que confirmar a password", Toast.LENGTH_LONG).show();
                } else if (password.getText().toString().equals(confirmarPassword.getText().toString())) {
                    try{
                        User util= verificarEmaileUser();
                        if(util!=null) {
                            Toast.makeText(userRegister.this, "Adicionado com sucesso", Toast.LENGTH_LONG).show();
                            Intent x = new Intent(getApplicationContext(), login.class);
                            startActivity(x);
                        }
                    }catch(Exception e){
                        Toast.makeText(userRegister.this, "Erro no verificar user", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(userRegister.this, "As passwords que introduziu não são iguais", Toast.LENGTH_LONG).show();
                }



                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent x = new Intent(getApplicationContext(), login.class);
                        startActivity(x);
                    }
                });

            }
        });
    };
    private User verificarEmaileUser(){

        String user = username.getText().toString();
        String em = email.getText().toString();
        DbHelper dbHelper = new DbHelper(userRegister.this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query1 = "SELECT * FROM user WHERE username=?  ";
        Cursor c1 = db.rawQuery(query1, new String[]{user});
        String query2 = "SELECT * FROM user WHERE email=?  ";
        Cursor c2 = db.rawQuery(query2, new String[]{em});
        User utilizador = null;

        try {
            boolean condicao1;
            boolean condicao2;
                if (c1 != null && c1.moveToFirst()) {

                    utilizador = new User();
                    utilizador.setUsername(c1.getString(5));
                    condicao1=true;
                } else {
                    Toast.makeText(userRegister.this, "O username ja existe", Toast.LENGTH_LONG).show();
                    condicao1=false;
                }
                if (c2 != null && c2.moveToFirst()) {
                    utilizador = new User();
                    utilizador.setEmail(c2.getString(2));
                    condicao2=true;
                } else {
                    Toast.makeText(userRegister.this, "O email ja existe", Toast.LENGTH_LONG).show();
                    condicao2=false;
                }
                if(condicao1==true && condicao2==true){
                insertUser();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    return utilizador;
    }
}




