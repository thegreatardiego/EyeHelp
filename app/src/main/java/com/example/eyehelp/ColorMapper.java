package com.example.eyehelp;

import android.graphics.Color;
import android.media.Image;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Averages color from image center and matches to closest named color
 */
public class ColorMapper {

    /**
     * Maps a color to a name
     */
    static class ColorMapping {
        int red;
        int green;
        int blue;
        String name;

        ColorMapping(String name, int color) {
            red = (color & 0xFF0000) >> 16;
            green = (color & 0xFF00) >> 8;
            blue = (color & 0xFF);
            this.name = name;
        }

        /**
         * Finds distance between two colors
         */
        int getDistanceSquare(int otherRed, int otherGreen, int otherBlue) {
            return (otherRed - red) * (otherRed - red)
                    + (otherGreen - green) * (otherGreen - green)
                    + (otherBlue - blue) * (otherBlue - blue);
        }
    }

    private List<ColorMapping> mColorMapping = new ArrayList<ColorMapping>();

    ColorMapper() {
        initColorMapping();
    }

    private void initColorMapping() {
        mColorMapping.add(new ColorMapping("maroon",0x85144B));
        mColorMapping.add(new ColorMapping("dark red",0x800000));
        mColorMapping.add(new ColorMapping("red",0xDC143C));
        mColorMapping.add(new ColorMapping("red",0xE00000));
        mColorMapping.add(new ColorMapping("red",0xCD5C5C));
        mColorMapping.add(new ColorMapping("red",0xF08080));
        mColorMapping.add(new ColorMapping("red",0xFA8072));
        mColorMapping.add(new ColorMapping("red",0xE9967A));
        mColorMapping.add(new ColorMapping("light salmon",0xFFA07A));
        mColorMapping.add(new ColorMapping("light pink",0xD5B2C8));
        mColorMapping.add(new ColorMapping("dark salmon",0xE9967A));
        mColorMapping.add(new ColorMapping("salmon",0xFA8072));
        mColorMapping.add(new ColorMapping("light salmon",0xFFA07A));
        mColorMapping.add(new ColorMapping("orange red",0xFF5000));
        mColorMapping.add(new ColorMapping("dark orange",0xFF8000));
        mColorMapping.add(new ColorMapping("orange",0xFFB000));
        mColorMapping.add(new ColorMapping("gold",0xFFD700));
        mColorMapping.add(new ColorMapping("green",0x3D9970));
        mColorMapping.add(new ColorMapping("yellow",0xC0C000));
        mColorMapping.add(new ColorMapping("yellow",0xFFFF00));
        mColorMapping.add(new ColorMapping("yellow",0xFFFF80));
        mColorMapping.add(new ColorMapping("yellow",0xFFFF40));
        mColorMapping.add(new ColorMapping("green",0x006400));
        mColorMapping.add(new ColorMapping("green",0x008000));
        mColorMapping.add(new ColorMapping("green",0x7CFC00));
        mColorMapping.add(new ColorMapping("green",0x7FFF00));
        mColorMapping.add(new ColorMapping("green",0x32CD32));
        mColorMapping.add(new ColorMapping("green",0x00FF00));
        mColorMapping.add(new ColorMapping("green",0x006400));
        mColorMapping.add(new ColorMapping("green",0x228B22));
        mColorMapping.add(new ColorMapping("green",0x00FF00));
        mColorMapping.add(new ColorMapping("lime green",0x32CD32));
        mColorMapping.add(new ColorMapping("light green",0x90EE90));
        mColorMapping.add(new ColorMapping("pale green",0x98FB98));
        mColorMapping.add(new ColorMapping("blue",0x66CDAA));
        mColorMapping.add(new ColorMapping("black",0x2F4F4F));
        mColorMapping.add(new ColorMapping("teal",0x39CCCC));
        mColorMapping.add(new ColorMapping("dark cyan",0x008B8B));
        mColorMapping.add(new ColorMapping("cyan",0x00FFFF));
        mColorMapping.add(new ColorMapping("light cyan",0xE0FFFF));
        mColorMapping.add(new ColorMapping("blue",0x00CED1));
        mColorMapping.add(new ColorMapping("blue",0x40E0D0));
        mColorMapping.add(new ColorMapping("blue",0x48D1CC));
        mColorMapping.add(new ColorMapping("blue",0xAFEEEE));
        mColorMapping.add(new ColorMapping("blue",0x7FDBFF));
        mColorMapping.add(new ColorMapping("blue",0x00BFFF));
        mColorMapping.add(new ColorMapping("blue",0xADD8E6));
        mColorMapping.add(new ColorMapping("blue",0x87CEEB));
        mColorMapping.add(new ColorMapping("light sky blue",0x87CEFA));
        mColorMapping.add(new ColorMapping("midnight blue",0x191970));
        mColorMapping.add(new ColorMapping("navy",0x000080));
        mColorMapping.add(new ColorMapping("dark blue",0x00008B));
        mColorMapping.add(new ColorMapping("medium blue",0x0000CD));
        mColorMapping.add(new ColorMapping("blue",0x0000FF));
        mColorMapping.add(new ColorMapping("royal blue",0x4169E1));
        mColorMapping.add(new ColorMapping("blue violet",0x8A2BE2));
        mColorMapping.add(new ColorMapping("indigo",0x4B0082));
        mColorMapping.add(new ColorMapping("blue",0x483D8B));
        mColorMapping.add(new ColorMapping("light blue",0x6A5ACD));
        mColorMapping.add(new ColorMapping("medium purple",0x9370DB));
        mColorMapping.add(new ColorMapping("dark magenta",0x8B008B));
        mColorMapping.add(new ColorMapping("dark violet",0x9400D3));
        mColorMapping.add(new ColorMapping("purple",0x800080));
        mColorMapping.add(new ColorMapping("plum",0xDDA0DD));
        mColorMapping.add(new ColorMapping("violet",0xEE82EE));
        mColorMapping.add(new ColorMapping("magenta",0xFF00FF));
        mColorMapping.add(new ColorMapping("deep pink",0xFF1493));
        mColorMapping.add(new ColorMapping("hot pink",0xFF69B4));
        mColorMapping.add(new ColorMapping("light pink",0xFFB6C1));
        mColorMapping.add(new ColorMapping("pink",0xFFC0CB));
        mColorMapping.add(new ColorMapping("light yellow",0xFFFFE0));
        mColorMapping.add(new ColorMapping("orange",0xA0522D));
        mColorMapping.add(new ColorMapping("light blue",0xF0F8FF));
        mColorMapping.add(new ColorMapping("black",0x000000));
        mColorMapping.add(new ColorMapping("white",0xC0C0C0));
        mColorMapping.add(new ColorMapping("white",0xF0F0F0));
        mColorMapping.add(new ColorMapping("white",0xFFFFFF));
        mColorMapping.add(new ColorMapping("violet",0x9F2B68));
        mColorMapping.add(new ColorMapping("bright purple",0xBF40BF));
        mColorMapping.add(new ColorMapping("violet",0x99004C));
        mColorMapping.add(new ColorMapping("indigo",0x4B0082));
        mColorMapping.add(new ColorMapping("maroon", 0x800000));
        mColorMapping.add(new ColorMapping("dark red", 0x8B0000));
        mColorMapping.add(new ColorMapping("brown", 0xA52A2A));
        mColorMapping.add(new ColorMapping("firebrick", 0xB22222));
        mColorMapping.add(new ColorMapping("teal", 0xDC143C));
        mColorMapping.add(new ColorMapping("red", 0xFF0000));
        mColorMapping.add(new ColorMapping("tomato", 0xFF6347));
        mColorMapping.add(new ColorMapping("coral", 0xFF7F50));
        mColorMapping.add(new ColorMapping("indian red", 0xCD5C5C));
        mColorMapping.add(new ColorMapping("light coral", 0xF08080));
        mColorMapping.add(new ColorMapping("dark salmon", 0xE9967A));
        mColorMapping.add(new ColorMapping("salmon", 0xFA8072));
        mColorMapping.add(new ColorMapping("light salmon", 0xFFA07A));
        mColorMapping.add(new ColorMapping("orange red", 0xFF4500));
        mColorMapping.add(new ColorMapping("dark orange", 0xFF8C00));
        mColorMapping.add(new ColorMapping("orange", 0xFFA500));
        mColorMapping.add(new ColorMapping("gold", 0xFFD700));
        mColorMapping.add(new ColorMapping("navajo white", 0xFFDEAD));
        mColorMapping.add(new ColorMapping("white", 0xFFFFFF));
        mColorMapping.add(new ColorMapping("snow", 0xFFFAFA));
        mColorMapping.add(new ColorMapping("honeydew", 0xF0FFF0));
        mColorMapping.add(new ColorMapping("mint cream", 0xF5FFFA));
        mColorMapping.add(new ColorMapping("azure", 0xF0FFFF));
        mColorMapping.add(new ColorMapping("alice blue", 0xF0F8FF));
        mColorMapping.add(new ColorMapping("ghost white", 0xF8F8FF));
        mColorMapping.add(new ColorMapping("gainsboro", 0xDCDCDC));
        mColorMapping.add(new ColorMapping("light gray", 0xD3D3D3));
        mColorMapping.add(new ColorMapping("silver", 0xC0C0C0));
        mColorMapping.add(new ColorMapping("black", 0x000000));
        mColorMapping.add(new ColorMapping("indigo", 0x4B0082));
        mColorMapping.add(new ColorMapping("neon green", 0x39FF14));
        mColorMapping.add(new ColorMapping("red", 0xFF0000));
        mColorMapping.add(new ColorMapping("blue", 0x0000FF));
        mColorMapping.add(new ColorMapping("yellow", 0xFFFF00));
        mColorMapping.add(new ColorMapping("teal", 0x008080));
        mColorMapping.add(new ColorMapping("chartreuse", 0x7FFF00));
        mColorMapping.add(new ColorMapping("amber", 0xFFBF00));
        mColorMapping.add(new ColorMapping("vermillion", 0xFF4500));
        mColorMapping.add(new ColorMapping("magenta", 0xFF00FF));
        mColorMapping.add(new ColorMapping("purple", 0x800080));
        mColorMapping.add(new ColorMapping("violet", 0xEE82EE));

    }

    /**
     * Computes the central color from the image
     * @param image the image from the camera
     * @return the central color of the image
     */
    public static int getCentralColor(Image image) {
        ByteBuffer yBuffer = image.getPlanes()[0].getBuffer();
        ByteBuffer uBuffer = image.getPlanes()[1].getBuffer();
        ByteBuffer vBuffer = image.getPlanes()[2].getBuffer();
        int width = image.getWidth();
        int height = image.getHeight();

        int yRowStride = image.getPlanes()[0].getRowStride();
        int uRowStride = image.getPlanes()[1].getRowStride();
        int vRowStride = image.getPlanes()[2].getRowStride();

        int yPixelStride = image.getPlanes()[0].getPixelStride();
        int uPixelStride = image.getPlanes()[1].getPixelStride();
        int vPixelStride = image.getPlanes()[2].getPixelStride();

        // get average yuv values for image center
        int sumOfY = 0;
        int sumOfU = 0;
        int sumOfV = 0;
        int threeEighthOfHeight = (height * 3) / 8;
        int fiveEighthOfHeight = (height * 5) / 8;
        int threeEighthOfWidth = (width * 3) / 8;
        int fiveEighthOfWidth = (width * 5) / 8;
        int countCentralPixels = 0;
        // go through all center pixels and add up all Y, U, and V values
        for (int y = threeEighthOfHeight; y < fiveEighthOfHeight; y++) {
            for (int x = threeEighthOfWidth; x < fiveEighthOfWidth; x++) {
                int indexY = (y * yRowStride) + (x * yPixelStride);
                int indexU = (y / 2 * uRowStride) + (x / 2 * uPixelStride);
                int indexV = (y / 2 * vRowStride) + (x / 2 * vPixelStride);
                sumOfY += yBuffer.get(indexY) & 255;
                sumOfU += uBuffer.get(indexU) & 255;
                sumOfV += vBuffer.get(indexV) & 255;
                countCentralPixels++;
            }
        }
        // average out Y,U, and V values and move the base line
        int yAverage = sumOfY / countCentralPixels - 16;
        int uAverage = sumOfU / countCentralPixels - 128;
        int vAverage = sumOfV / countCentralPixels - 128;

        // convert YUV color space to RGB color space
        int red = (int) (1.164 * yAverage + 1.596 * vAverage);
        int green = (int) (1.164 * yAverage - 0.392 * uAverage - 0.813 * vAverage);
        int blue = (int) (1.164 * yAverage + 2.017 * uAverage);

        // clip RGB values to be within valid 0-255 range
        red = Math.min(255, Math.max(0, red));
        green = Math.min(255, Math.max(0, green));
        blue = Math.min(255, Math.max(0, blue));

        // convert to opaque integer color
        int centralColor = Color.argb(255, red, green, blue);
        return centralColor;
    }

    /**
     * Finds the closest color from a known list of colors
     * @param color the integer color value
     * @return the closest named color to given color
     */
    public String getColorName(int color) {
        int closest = Integer.MAX_VALUE;
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        String closestColor = "unknown";
        for (int i = 0; i < mColorMapping.size(); i++) {
            ColorMapping mapping = mColorMapping.get(i);
            int distance = mapping.getDistanceSquare(red, green, blue);
            if (closest > distance) {
                closest = distance;
                closestColor = mapping.name;
            }
        }
        return closestColor;
    }
}