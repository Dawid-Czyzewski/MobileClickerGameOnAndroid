package com.example.mobileclickergame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class MainActivity extends AppCompatActivity {

       static boolean isNewGame;


    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void NewGame(View view){

        if(dataBaseHelper.isPlayerInBase()){

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

            builder.setTitle("New game");
            builder.setMessage("Are you sure, You want to start new game? You will lose your actual progress");

            builder.setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialogInterface,int i){

                    Intent intent=new Intent(MainActivity.this,GameActivity.class);
                    isNewGame=true;
                    Upgrade upgrade = new Upgrade(10,10);
                    dataBaseHelper.updateCosts(upgrade);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        }
        else{
            Intent intent=new Intent(MainActivity.this,GameActivity.class);
            isNewGame=true;
            startActivity(intent);
            finish();
        }

    }

    public void ContinueGame(View view){
        isNewGame=false;

         if(dataBaseHelper.isPlayerInBase()){
             Intent intent=new Intent(this,GameActivity.class);
             startActivity(intent);
             finish();
         }
        else{
             Toast.makeText(this,"Not saved game found",Toast.LENGTH_LONG).show();
        }
    }

    public void goToLevels(View view){
            Intent intent=new Intent(this,LevelsActivity.class);
            startActivity(intent);
            finish();
    }

    public void quit(View view){
        finish();
        System.exit(0);
    }
}