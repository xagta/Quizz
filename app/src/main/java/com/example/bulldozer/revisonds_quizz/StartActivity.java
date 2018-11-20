package com.example.bulldozer.revisonds_quizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bulldozer.revisonds_quizz.Fragments.InitFragment;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        InitFragment initFragment = new InitFragment() ;

        getSupportFragmentManager().beginTransaction().add(R.id.FragmentContainer,initFragment).commit() ;

    }
}
