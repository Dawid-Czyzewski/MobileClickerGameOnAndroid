package com.example.mobileclickergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.mobileclickergame.MainActivity.isNewGame;

public class GameActivity extends AppCompatActivity  {



    Player player=new Player(1,0,0,1,0);
    DataBaseHelper dataBaseHelper = new DataBaseHelper(GameActivity.this);
    private Handler handler = new Handler();
    public static boolean isGameWorking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView exp = findViewById(R.id.Exp);
        TextView lvl = findViewById(R.id.Lvl);
        isGameWorking=true;

        if(isNewGame==true){
            dataBaseHelper.deleteAll();
            dataBaseHelper.addOne(player);
            isNewGame=false;
        }

       List<Player> playerList = dataBaseHelper.getAllPlayers();
       player=playerList.get(0);
       player.refreshPlayer(player,exp,lvl);

       ExampleRunnable runnable = new ExampleRunnable();
      new Thread(runnable).start();


    }

    public void addExp(View view){
        dataBaseHelper.updatePlayer(player);
        player.addExp(player.getExpPerTap());

        Level.checkLevel(player,dataBaseHelper);
        dataBaseHelper.updatePlayer(player);

        TextView exp = findViewById(R.id.Exp);
        TextView lvl = findViewById(R.id.Lvl);


        player.refreshPlayer(player,exp,lvl);
    }

    public void backToMenu(View view){
        isGameWorking=false;
        Intent intent=new Intent(GameActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void goToShop(View view){
        isGameWorking=false;
        Intent intent=new Intent(GameActivity.this,ShopActivity.class);
        startActivity(intent);
        finish();
    }
    class ExampleRunnable implements Runnable{
        TextView exp = findViewById(R.id.Exp);
        TextView lvl = findViewById(R.id.Lvl);
        @Override
        public void run(){
                while (true) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            player.addExpPerSecond(player.getExpPerSecond());
                            player.refreshPlayer(player, exp, lvl);
                            dataBaseHelper.updatePlayer(player);
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(isGameWorking==false){
                        break;
                }
            }
        }
    }
}

