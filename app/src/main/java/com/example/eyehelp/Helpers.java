package com.example.eyehelp;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

public class Helpers {
    public static Map<String, Integer> getColorNamesMap(Context context) {
        Map<String, Integer> colors = new HashMap<>();
        colors.put(context.getString(R.string.colorNameRed), 0);
        colors.put(context.getString(R.string.colorNameOrange), 30);
        colors.put(context.getString(R.string.colorNameYellow), 60);
        colors.put(context.getString(R.string.colorNameYellowGreen), 90);
        colors.put(context.getString(R.string.colorNameGreen), 120);
        colors.put(context.getString(R.string.colorNameGreenCyan), 150);
        colors.put(context.getString(R.string.colorNameCyan), 180);
        colors.put(context.getString(R.string.colorNameBlueCyan), 210);
        colors.put(context.getString(R.string.colorNameBlue), 240);
        colors.put(context.getString(R.string.colorNameViolet), 270);
        colors.put(context.getString(R.string.colorNameMagenta), 300);
        colors.put(context.getString(R.string.colorNameRose), 330);
        return colors;
    }

    public static float[] convertHSVToHSL(float[] hsv) {
        float h = hsv[0];
        float s = hsv[1];
        float v = hsv[2];

        float l = (2 - s) * v / 2;

        if (l != 0.0f) {
            s = l == 1.0f ? 0.0f : (l < 0.5 ? s * v / (l * 2) : s * v / (2 - l * 2));
        }

        return new float[]{h, s, l};
    }
}
