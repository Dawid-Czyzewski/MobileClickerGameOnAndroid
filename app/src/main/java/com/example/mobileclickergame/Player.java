package com.example.mobileclickergame;

import android.widget.TextView;

public class Player {

    private int id;
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    private int expierence;
    public int getExp(){
        return expierence;
    }

    public void setExp(int exp){
        this.expierence=exp;
    }

    private int level;
    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level=level;
    }

    private int expPerTap;
    public int getExpPerTap(){
        return expPerTap;
    }
    public void setExpPerTap(int expPerTap){
       this.expPerTap=expPerTap;
    }
    
    private int expPerSecond;
    public int getExpPerSecond(){
        return expPerSecond;
    }

    public void setExpPerSecond(int expPerSecond){
        this.expPerSecond=expPerSecond;
    }

    public Player(int id,int exp,int level,int expPerTap,int expPerSecond){
        this.expierence=exp;
        this.level=level;
        this.expPerSecond=expPerSecond;
        this.expPerTap=expPerTap;
        this.id=id;
    }

    public int addExp(int expPerTap){
        return  expierence+=expPerTap;
    }
    public void addExpPerSecond(int expPerSecond){
        expierence+=expPerSecond;
    }

    public void refreshPlayer(Player player, TextView viewExp,TextView viewLvl){
        
        viewExp.setText("Exp: "+player.getExp());
        viewLvl.setText("Level: "+player.getLevel());
    }
}
