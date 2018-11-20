package com.example.bulldozer.revisonds_quizz.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.bulldozer.revisonds_quizz.R;

import java.util.Calendar;
import java.util.Date;


public class InitFragment extends Fragment implements View.OnClickListener {

    EditText nameEdit;
    DatePicker datePicker;
    Button btnCommncer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragement_init, container, false);

        datePicker = (DatePicker) view.findViewById(R.id.DatePicker);
        nameEdit = (EditText) view.findViewById(R.id.NameEditText);
        btnCommncer = (Button) view.findViewById(R.id.btnCommencer);

        btnCommncer.setOnClickListener(this);
        return view;

    }


    @Override
    public void onClick(View v) {
        int year = datePicker.getYear();

        int thisyear = Calendar.getInstance().get(Calendar.YEAR);

        int age = thisyear - year;

        Bundle args = new Bundle();
        String name = nameEdit.getText().toString();


        args.putInt("age", age);
        args.putString("name", name);

        QuizzFragment quizzFragment = new QuizzFragment();
        quizzFragment.setArguments(args);

        getFragmentManager().beginTransaction().replace(R.id.FragmentContainer, quizzFragment).commit();

    }
}
