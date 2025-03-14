package com.example.eyehelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Vector;

public class IshiharaColour extends AppCompatActivity {
    private Vector<Integer> number_inputs = new Vector<>();
    private static final int PLACEHOLDER = -1;

    private static final int[] protan_results = {
            12, 3, 70, 2, 5, 17, 21, PLACEHOLDER, PLACEHOLDER, PLACEHOLDER, PLACEHOLDER, PLACEHOLDER, PLACEHOLDER, 5, 45, 6, 2
    };

    private static final int[] deuteran_results = {
            12, 3, 70, 2, 5, 17, 21, PLACEHOLDER, PLACEHOLDER, PLACEHOLDER, PLACEHOLDER, PLACEHOLDER, PLACEHOLDER, 5, 45, 2, 4
    };

    private static final int[] regular_results = {
            12, 8, 29, 5, 3, 15, 74, 6, 45, 5, 7, 16, 73, PLACEHOLDER, PLACEHOLDER, 26, 42
    };

    private static final int[] test_plates = {
            R.drawable.ishihara12, R.drawable.ishihara8, R.drawable.ishihara29,
            R.drawable.ishihara5, R.drawable.ishihara3, R.drawable.ishihara15,
            R.drawable.ishihara74, R.drawable.ishihara6, R.drawable.ishihara45,
            R.drawable.ishihara5_2, R.drawable.ishihara7, R.drawable.ishihara16,
            R.drawable.ishihara73, R.drawable.ishiharax, R.drawable.ishiharax_2,
            R.drawable.ishihara26, R.drawable.ishihara42
    };

    EditText input;
    ImageView image;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ishiharacolour);

        input = findViewById(R.id.input);
        image = findViewById(R.id.test);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.reset) {
            number_inputs.clear();
            index = 0;
        } else if (view.getId() == R.id.next) {
            if (input.getText().toString().equals("")) {
                number_inputs.addElement(-1);
            } else {
                number_inputs.addElement(Integer.parseInt(input.getText().toString()));
            }
            input.getText().clear();
            if (index == 16) {  // last test plate
                displayResult();
            } else {
                ++index;
            }
        }
        image.setImageResource(test_plates[index]);
    }

    public void displayResult() {
        int p_counter = 0; // Protan color blindness counter
        int d_counter = 0; // Deuteran color blindness counter
        int m_counter = 0; // Monochrome color blindness counter

        // Loop through the user's inputs and compare with expected results
        for (int i = 0; i < number_inputs.size(); ++i) {
            if (number_inputs.get(i) == protan_results[i]) {
                p_counter++;
            }
            if (number_inputs.get(i) == deuteran_results[i]) {
                d_counter++;
            }
            if (number_inputs.get(i) != regular_results[i]) {
                m_counter++;
            }
        }

        Intent intent;

        // Determine the result based on the counters
        if (m_counter >= 3) {
            // Monochrome
            intent = new Intent(IshiharaColour.this, TestAchromatopsia.class);
        } else if (p_counter >= 3 || d_counter >= 3) {
            // If either protan or deuteran counters are 3 or more, choose the one with the higher count
            if (p_counter > d_counter) {
                // Protan
                intent = new Intent(IshiharaColour.this, TestProtan.class);
            } else {
                // Deuteran
                intent = new Intent(IshiharaColour.this, TestDeuteran.class);
            }
        } else {
            // Normal vision
            intent = new Intent(IshiharaColour.this, TestNormal.class);
        }

        startActivity(intent);
    }
}