package com.example.bulldozer.revisonds_quizz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText login ;
    private EditText pwd ;
    private Button loginBtn ;

    SharedPreferences sharedPreferences ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (EditText) findViewById(R.id.loginET);
        pwd = (EditText) findViewById(R.id.pwdET) ;
        loginBtn= (Button) findViewById(R.id.loginBtn);
        sharedPreferences = getSharedPreferences("LoginDetails", this.MODE_PRIVATE);
        if (!sharedPreferences.getString("username","nodata").equals("nodata"))
        {
            Intent intent = new Intent(MainActivity.this,StartActivity.class) ;
            startActivity(intent);

        }

    }

    void onLoginClicked (View v)
    {
        String logintxt = login.getText().toString() ;
        String password = pwd.getText().toString();

        if (logintxt.equals("admin") && password.equals("123")) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username",logintxt);
            editor.putString("password",password);
            editor.commit();
            Intent intent = new Intent(MainActivity.this,StartActivity.class) ;
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this, "Worng username or password", Toast.LENGTH_SHORT).show();

        }


    }
}
