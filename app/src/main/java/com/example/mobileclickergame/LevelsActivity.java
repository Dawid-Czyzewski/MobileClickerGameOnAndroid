package com.example.mobileclickergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class LevelsActivity extends AppCompatActivity {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(LevelsActivity.this);
    int page=1;
    int [] levels1= new int[101];
    int [] exps1=new int[101];
    TextView lvl1,lvl2,lvl3,lvl4,lvl5;
    TextView exp1,exp2,exp3,exp4,exp5;
    TextView page1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        lvl1=findViewById(R.id.textView3);
        exp1=findViewById(R.id.textView4);
        uploadLevels();
        showLevels(dataBaseHelper);
    }

    public void showLevels(DataBaseHelper dataBaseHelper){
        page1 = findViewById(R.id.textView);
        page1.setText("Page: "+page+"/20");
        lvl1.setText(""+levels1[1]);
        exp1.setText(""+exps1[1]);
    }

    public void nextPage(View view){
        page++;
        page1.setText("Page: "+page+"/20");
    }

    public void backToMenu(View view){
        if(page==1){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            page--;
        }
        page1.setText("Page: "+page+"/20");
    }

    public void uploadLevels(){
        List<Level> levels = dataBaseHelper.getAllLevels();
        for(int i=0;i<=100;i++){
            Level level=levels.get(i);
            levels1[i]=level.getLevel();
        }
        for(int i=0;i<=100;i++){
            Level level=levels.get(i);
            exps1[i]=level.getExpRequire();
        }
    }
}