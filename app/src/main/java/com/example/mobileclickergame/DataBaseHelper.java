package com.example.mobileclickergame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBaseHelper extends SQLiteOpenHelper {

    Level level=new Level(0,0);
    public static final String Player_Table="Player_Table";
    public static final String Column_Player_Exp="Exp";
    public static final String Column_Player_Level="Level";
    public static final String Column_Player_ExpPerSecond="ExpPerSecond";
    public static final String Column_Player_ExpPerTap="ExpPerTap";
    public static final String Column_Player_Id="Id";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "LevelKing.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        int[] levels=level.putLevels();
        int[] exp= level.putExp();


        String createPlayerTable=
                "CREATE TABLE "+ Player_Table +
                        " ("+Column_Player_Id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        Column_Player_Exp+" INTEGER, "+
                        Column_Player_Level+" INTEGER, "+
                        Column_Player_ExpPerSecond+" INTEGER, "+
                        Column_Player_ExpPerTap+" INTEGER)";
        String createLevelTable=
                "CREATE TABLE Level_Table (Id INTEGER PRIMARY KEY AUTOINCREMENT, Level INTEGER , ExpRequire INTEGER )";
        String createUpgradeTable=
                "CREATE TABLE Upgrade_Table (Id INTEGER PRIMARY KEY AUTOINCREMENT, ExpPerTapCost INTEGER , ExpPerSecondCost INTEGER )";
        db.execSQL(createPlayerTable);
        db.execSQL(createLevelTable);
        db.execSQL(createUpgradeTable);
        updateLevels(levels,exp,db);
        addCosts(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Player player){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues player1 = new ContentValues();

        player1.put(Column_Player_Exp, player.getExp());
        player1.put(Column_Player_Level, player.getLevel());
        player1.put(Column_Player_ExpPerSecond, player.getExpPerSecond());
        player1.put(Column_Player_ExpPerTap, player.getExpPerTap());

        long insert = db.insert(Player_Table, null, player1);

        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean addCosts(SQLiteDatabase db){
        ContentValues upgrade = new ContentValues();

        upgrade.put("ExpPerTapCost",10);
        upgrade.put("ExpPerSecondCost",10);


        long insert = db.insert("Upgrade_Table", null,  upgrade);

        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "DELETE FROM " + Player_Table;

       Cursor cursor = db.rawQuery(queryString, null);

       if(cursor.moveToFirst()){
            return true;
       }
       else{
           return false;
       }
    }


    public boolean updatePlayer(Player player){
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "UPDATE "+Player_Table+
                " set "+Column_Player_Level+" = "+
                player.getLevel() +",  "+Column_Player_Exp+" ="+player.getExp() +" , "+Column_Player_ExpPerSecond+" = "+player.getExpPerSecond()+" , "+Column_Player_ExpPerTap+" = "+player.getExpPerTap();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean updateCosts(Upgrade upgrade){
        SQLiteDatabase db = this.getWritableDatabase();

        String queryString = "UPDATE Upgrade_Table set ExpPerSecondCost = "+upgrade.getCostExpPerSecond()+" , ExpPerTapCost = "+upgrade.getCostExpPerTap();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public List<Player> getAllPlayers(){
        List<Player> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + Player_Table;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst() ) {
            do{
                int playerId = cursor.getInt(0);
                int playerExp = cursor.getInt(1);
                int playerLevel = cursor.getInt(2);
                int playerExpPerSecond = cursor.getInt(3);
                int playerExpPerTap = cursor.getInt(4);

                Player newPLayer = new Player(playerId, playerExp,playerLevel,playerExpPerTap,playerExpPerSecond);
                returnList.add(newPLayer);

            }while (cursor.moveToNext());

        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }


    public boolean isPlayerInBase(){
        boolean answer;
        String queryString = "SELECT * FROM " + Player_Table;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst() ) {
            answer=true;
        }
        else{
            answer=false;
        }

        cursor.close();
        db.close();
        return answer;
    }


    public void updateLevels(int[] levels, int[] exp,SQLiteDatabase db){
        ContentValues level1 = new ContentValues();

        for(int d=0; d<=100; d++){
            level1.put("ExpRequire",exp[d]);
            level1.put("Level",levels[d]);

            db.insert("Level_Table", null, level1);
        }
    }


    public List<Level> getAllLevels(){
        List<Level> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + "Level_Table";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToNext() ) {
            do{
               int level1=cursor.getInt(1);
               int expRequire=cursor.getInt(2);

                Level level = new Level(level1,expRequire);
                returnList.add(level);

            }while (cursor.moveToNext());
        }
        else {

        }

        cursor.close();
        db.close();
        return returnList;
    }

    public Upgrade getCosts(){
        Upgrade upgrade = new Upgrade(10,10);

        String queryString = "SELECT * FROM Upgrade_Table";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
            upgrade.setCostExpPerTap(cursor.getInt(1));
            upgrade.setCostExpPerSecond(cursor.getInt(2));

        cursor.close();
        db.close();
        return upgrade;
    }
}