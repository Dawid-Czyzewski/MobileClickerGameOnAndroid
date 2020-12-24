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
    TextView lvl1,lvl2,lvl3,lvl4,lvl5;
    TextView exp1,exp2,exp3,exp4,exp5;
    TextView page1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);


        showLevels(dataBaseHelper);
    }

    public void showLevels(DataBaseHelper dataBaseHelper){
        List<Level> levels = dataBaseHelper.getAllLevels();
        page1 = findViewById(R.id.textView);
        page1.setText("Page: "+page+"/20");
    }

    public void nextPage(){
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
}