package com.example.bulldozer.revisonds_quizz.Fragments;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bulldozer.revisonds_quizz.Entity.Player;
import com.example.bulldozer.revisonds_quizz.R;
import com.example.bulldozer.revisonds_quizz.utils.ScoreContract;
import com.example.bulldozer.revisonds_quizz.utils.ScoreDbHelper;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizzFragment extends Fragment implements View.OnClickListener {

    TextView scoreTV,ageTV ;
    TextView firstnbr ,secondnbr ,operator ;
    Button btnOk ;
    int result,age ;
    ScoreDbHelper mdbHelper ;
    String name ;
    int playerID=1 ;
    Player player=null ;



    public QuizzFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_quizz, container, false);

        ageTV= (TextView) v.findViewById(R.id.age) ;
        scoreTV= (TextView) v.findViewById(R.id.score) ;
        firstnbr=(TextView) v.findViewById(R.id.firstnbr);
        secondnbr=(TextView) v.findViewById(R.id.secondnbr);
        operator =(TextView) v.findViewById(R.id.operator);
        btnOk = (Button) v.findViewById(R.id.btnSubmit) ;
        btnOk.setOnClickListener(this);


        Random random = new Random() ;
         mdbHelper = new ScoreDbHelper(getContext()) ;
        InitGame();

        player=getCurrentPlayer();

        if (getArguments().containsKey("id"))
        {
            playerID=getArguments().getInt("id") ;

        }
        else
        {
            playerID=InitGame() ;
        }

        playerID=1 ;


       age=getArguments().getInt("age") ;
        name=getArguments().getString("name");
        ageTV.setText(ageTV.getText().toString()+age);



if (player!=null) {
    Log.d("playername=", player.getName());
    Log.d("score=", player.getScore());
    Log.d("total=",player.getTotal());
    String score = player.getScore();
    String rounds =player.getTotal();
    scoreTV.setText(score + "/" + rounds);
}

        if (age< 10)
        {
            int min = 0;
            int max = 10;
            int first = random.nextInt(max - min + 1) + min;
            int second = random.nextInt(max - min + 1) + min;
            operator.setText("+");
            firstnbr.setText(first+"");
            secondnbr.setText(second+"");

            result=first+second ;


        }
        else if (age >= 10 && age <= 20)
        {
            int min = 10;
            int max = 100;
            int first = random.nextInt(max - min + 1) + min;
            int second = random.nextInt(max - min + 1) + min;
            operator.setText("-");
            firstnbr.setText(first+"");
            secondnbr.setText(second+"");

            result=first-second ;

        }

        else if (age > 20 )
        {
            int min = 100;
            int max = 1000;
            int first = random.nextInt(max - min + 1) + min;
            int second = random.nextInt(max - min + 1) + min;
            operator.setText("/");
            firstnbr.setText(first+"");
            secondnbr.setText(second+"");

            result=first/second ;

        }



        return v ;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.btnSubmit:
                Toast.makeText(getContext(), "button clicked", Toast.LENGTH_SHORT).show();
               UpdateScore();
                Bundle args =new Bundle();

    if (player!=null) {
        args.putString("name", player.getName());
        args.putString("score", player.getScore());
        args.putString("tatal", player.getTotal());
    }
                args.putInt("age",age);
                args.putInt("id",playerID);

                QuizzFragment quizzFragment = new QuizzFragment() ;
                quizzFragment.setArguments(args);
                getFragmentManager().beginTransaction().remove(this).replace(R.id.FragmentContainer,quizzFragment).commit();
                break ;



        }

    }

    public void UpdateScore()
    {

        if (player!=null)
        {
            SQLiteDatabase db = mdbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            int newscore ;
            int oldscore= Integer.parseInt(player.getScore()) ;
            int roundnbr = Integer.parseInt(player.getTotal())  ;




            if (validAnswer()){newscore=++oldscore; roundnbr++; }
            else {
                newscore=oldscore;
                ++roundnbr;
            }

            values.put(ScoreContract.ScoreEntery.COLUMN_NAME_Name,name);
            values.put(ScoreContract.ScoreEntery.COLUMN_NAME_score,newscore);
            values.put(ScoreContract.ScoreEntery.COLUMN_NAME_total,roundnbr);


            db.update(ScoreContract.ScoreEntery.TABLE_NAME,values,"_id="+playerID,null);

            scoreTV.setText(newscore+"/"+roundnbr);
        }

    }

    public Player getCurrentPlayer()
    {

        String[] projection = {
                ScoreContract.ScoreEntery._ID,
                ScoreContract.ScoreEntery.COLUMN_NAME_Name,
                ScoreContract.ScoreEntery.COLUMN_NAME_score,
                ScoreContract.ScoreEntery.COLUMN_NAME_total
        };

        SQLiteDatabase dbreadt = mdbHelper.getReadableDatabase() ;

        Log.d("id=",playerID+"") ;



        Cursor cursor = dbreadt.query(ScoreContract.ScoreEntery.TABLE_NAME,projection,"_id="+playerID,null,null,null,null) ;



        while(cursor.moveToNext()) {
            String playername = cursor.getString(cursor.getColumnIndexOrThrow(ScoreContract.ScoreEntery.COLUMN_NAME_Name));
            String playerScore = cursor.getString(cursor.getColumnIndexOrThrow(ScoreContract.ScoreEntery.COLUMN_NAME_score));
            String playerTotal = cursor.getString(cursor.getColumnIndexOrThrow(ScoreContract.ScoreEntery.COLUMN_NAME_total));
            Log.d("cursor", cursor.getCount() + "");
            player = new Player(playername,playerScore,playerTotal) ;
        }







        return player ;

    }

    private Boolean validAnswer()
    {
        int answer = Integer.getInteger(firstnbr.getText().toString(),0);
        if (answer==result)
        {
            return true ;
        }
        else {
            return false ;
        }
    }

    private int InitGame()
    {
        long newRowID ;

        name = getArguments().getString("name") ;
        String name2 = getArguments().getString("name") ;
            SQLiteDatabase db = mdbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ScoreContract.ScoreEntery.COLUMN_NAME_Name,name);
            values.put(ScoreContract.ScoreEntery.COLUMN_NAME_score,0);
            values.put(ScoreContract.ScoreEntery.COLUMN_NAME_total,0);
            newRowID = db.insert(ScoreContract.ScoreEntery.TABLE_NAME,null,values);
            Toast.makeText(getContext(), "row id : " + newRowID, Toast.LENGTH_SHORT).show();
            return Integer.valueOf(newRowID+"") ;



    }




}
