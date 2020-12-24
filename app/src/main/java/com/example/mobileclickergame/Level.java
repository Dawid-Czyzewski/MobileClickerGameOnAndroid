package com.example.mobileclickergame;

import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Level {

    private int level;
    private int expRequire;


    public void setLevel(int level){
        this.level=level;
    }
    public int getLevel(){
        return level;
    }

    public int getExpRequire(){
        return expRequire;
    }

    public Level(int level,int expRequire){
        this.level=level;
        this.expRequire=expRequire;
    }

    public int[] putLevels(){

        int[] levels = new int[101];

        for(int i=0; i<=100; i++){
            levels[i]=i;
        }
        return levels;
    }

    public int[] putExp(){

        int[] exp = new int[101];
        int f=0 , k=30;

      for(int j=0; j<=100; j++){
          exp[j]=f;
          k+=200;
          f=random(f,k);
      }
      return exp;
    }

    public static void checkLevel(Player player, DataBaseHelper dataBaseHelper){
        List<Level> levels;
        Level level;

        levels=dataBaseHelper.getAllLevels();

        for(int l=0; l<=99;l++){

            level=levels.get(l);

            if(level.getExpRequire()<=player.getExp()){
                player.setLevel(level.getLevel());
            }
        }
    }

    public int random(int min,int max){
        int range=(max-min+1);
        int rand=(int)(Math.random() * range) + min;

        return rand;
    }
}
