package com.example.eyehelp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    TextView pointsNew;
    TextView finalm;
    Button btnNearby;
    TextView ishiharatestref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        int p = getIntent().getExtras().getInt("points");
        pointsNew = findViewById(R.id.pointsNew);
        pointsNew.setText("" + p);
        btnNearby = findViewById(R.id.btnNearby);
        btnNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GameOver.this,MapsActivity.class);
                startActivity(intent);
            }
        });
        ishiharatestref=findViewById(R.id.ishiharatestref);
        ishiharatestref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://web.stanford.edu/group/vista/wikiupload/0/0a/Ishihara.14.Plate.Instructions.pdf";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        finalm = findViewById(R.id.finalm);

        int zero = Integer.parseInt("0");
        int twelve = Integer.parseInt("12");
        int seven = Integer.parseInt("7");
        int ten = Integer.parseInt("10");

        if(p ==0 ){
            finalm.setText("You have demonstrated significant difficulty in distinguishing colors, suggesting a more pronounced color vision deficiency. It is strongly recommended to consult with an eye care specialist for a comprehensive examination. ");
        }
        else{
            if(p >= ten){
                finalm.setText("Congratulations, You have demonstrated normal color vision!");
            } else if (p <= seven) {
                finalm.setText("You have shown some difficulty in distinguishing some colors. It can indicate a mild color vision deficiency. It is advisable to consult with an eye care professional for further evaluation");
            } else{
                finalm.setText("Congratulations, You have demonstrated normal color vision!");
            }
        }

    }

    public void restart(View view) {
        Intent intent = new Intent(GameOver.this, Test.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        finish();
    }
}
