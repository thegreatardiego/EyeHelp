package com.example.eyehelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Test extends AppCompatActivity {

    TextView result;
    ImageView numberimage;
    HashMap<String, Integer> map = new HashMap<>();
    ArrayList<String> List = new ArrayList<>();
    int i;
    Button btn1, btn2, btn3, btn4;
    TextView points;
    int p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        numberimage = findViewById(R.id.numberimage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        points = findViewById(R.id.points);
        i = 0;

        List.add("12");
        List.add("8");
        List.add("29");
        List.add("5");
        List.add("3");
        List.add("15");
        List.add("74");
        List.add("6");
        List.add("45");
        List.add("5");
        List.add("7");
        List.add("16");

        map.put(List.get(0), R.drawable.ishihara12);
        map.put(List.get(1), R.drawable.ishihara8);
        map.put(List.get(2), R.drawable.ishihara29);
        map.put(List.get(3), R.drawable.ishihara5);
        map.put(List.get(4), R.drawable.ishihara3);
        map.put(List.get(5), R.drawable.ishihara15);
        map.put(List.get(6), R.drawable.ishihara74);
        map.put(List.get(7), R.drawable.ishihara6);
        map.put(List.get(8), R.drawable.ishihara45);
        map.put(List.get(9), R.drawable.ishihara5_2);
        map.put(List.get(10), R.drawable.ishihara7);
        map.put(List.get(11), R.drawable.ishihara16);

        p = 0;
        start();

    }

    private void start(){
        points.setText(p + " / " + List.size());
        generate(i);
    }

    private void generate(int i){
        ArrayList<String> ListT = (ArrayList<String>) List.clone();
        String correct = List.get(i);
        ListT.remove(correct);
        Collections.shuffle(ListT);

        ArrayList<String> newList = new ArrayList<>();
        newList.add(ListT.get(0));
        newList.add(ListT.get(1));
        newList.add(ListT.get(2));
        newList.add(correct);
        // Collections.shuffle(newList); // No need to shuffle here
        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));
        numberimage.setImageResource(map.get(List.get(i)));
    }

    public void select(View view){
        String answer = ((Button) view).getText().toString().trim();
        String correct = List.get(i);
        if(answer.equals(correct)){
            p++;
            points.setText((p + " / " + List.size()));
        }

        i++;
        if (i > List.size() - 1) {
            numberimage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            Intent intent = new Intent(Test.this, GameOver.class);
            intent.putExtra("points", p);
            startActivity(intent);
            finish();
        }
        else{
            start();
        }
    }
}
