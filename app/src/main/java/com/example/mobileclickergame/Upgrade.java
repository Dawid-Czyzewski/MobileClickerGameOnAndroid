package com.example.mobileclickergame;

public class Upgrade {

    private int costExpPerTap;
    public int getCostExpPerTap(){
        return costExpPerTap;
    }
    public void setCostExpPerTap(int costExpPerTap){
        this.costExpPerTap=costExpPerTap;
    }
    private int costExpPerSecond;
    public int getCostExpPerSecond(){
        return costExpPerSecond;
    }
    public void setCostExpPerSecond(int costExpPerSecond){
        this.costExpPerSecond=costExpPerSecond;
    }
    public Upgrade(int costExpPerTap,int costExpPerSecond){
        this.costExpPerTap=costExpPerTap;
        this.costExpPerSecond=costExpPerSecond;
    }

    public void buyUpgradeExpPerTap(Player player,Level level,DataBaseHelper dataBaseHelper,Upgrade upgrade){

        if(player.getExp()-upgrade.getCostExpPerTap() >= 0){
            player.setExp(player.getExp()-upgrade.getCostExpPerTap());
            level.checkLevel(player,dataBaseHelper);
            player.setExpPerTap(player.getExpPerTap()+1);;
            setCostExpPerTap(upgrade.getCostExpPerTap()+40);

        }else{

        }
    }

    public void buyUpgradeExpPerSecond(Player player,Level level,DataBaseHelper dataBaseHelper,Upgrade upgrade){

        if(player.getExp()-upgrade.getCostExpPerSecond() >= 0){
            player.setExp(player.getExp()-upgrade.getCostExpPerSecond());
            level.checkLevel(player,dataBaseHelper);
            player.setExpPerSecond(player.getExpPerSecond()+1);;
            setCostExpPerSecond(upgrade.getCostExpPerSecond()+40);

        }else{

        }
    }
}
