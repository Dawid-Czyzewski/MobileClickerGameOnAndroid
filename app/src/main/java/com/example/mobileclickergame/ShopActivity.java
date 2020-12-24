package com.example.mobileclickergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ShopActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(ShopActivity.this);

    Player player = new Player(0,0,0,0,0);
    Level level = new Level(0,0);
    Upgrade upgrade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        List<Player> playerList = dataBaseHelper.getAllPlayers();
        player=playerList.get(0);
        upgrade=dataBaseHelper.getCosts();
        refreshShop(player,upgrade);
    }
    public void buyUpgradeExpPerTap(View view){
        upgrade.buyUpgradeExpPerTap(player,level,dataBaseHelper,upgrade);
        dataBaseHelper.updatePlayer(player);
        dataBaseHelper.updateCosts(upgrade);
        refreshShop(player,upgrade);
    }

    public void buyUpgradeExpPerSecond(View view){
        upgrade.buyUpgradeExpPerSecond(player,level,dataBaseHelper,upgrade);
        dataBaseHelper.updatePlayer(player);
        dataBaseHelper.updateCosts(upgrade);
        refreshShop(player,upgrade);
    }

    public void refreshShop(Player player,Upgrade upgrade){
        TextView exp = findViewById(R.id.playerExp);
        TextView cost1= findViewById(R.id.cost);
        TextView cost2= findViewById(R.id.cost2);
        TextView expPerTap= findViewById(R.id.expPerTap);
        TextView expPerSecond= findViewById(R.id.expPerSecond);
        exp.setText("Exp: "+player.getExp());
        cost1.setText("Cost: "+upgrade.getCostExpPerTap());
        cost2.setText("Cost: "+upgrade.getCostExpPerSecond());
        expPerTap.setText("Exp per tap: "+player.getExpPerTap());
        expPerSecond.setText("Exp per second: "+player.getExpPerSecond());
    }

    public void backToGame(View view){
        Intent intent=new Intent(ShopActivity.this,GameActivity.class);
        startActivity(intent);
        finish();
    }
}