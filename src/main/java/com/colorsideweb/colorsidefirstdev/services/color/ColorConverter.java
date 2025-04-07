package com.colorsideweb.colorsidefirstdev.services.color;

import org.springframework.lang.NonNull;

import java.awt.*;

public class ColorConverter {


    public int[] hexToRgb(@NonNull String hex){
        double r = Integer.parseInt(hex.substring(1,3), 16);
        double g = Integer.parseInt(hex.substring(3,5), 16);
        double b = Integer.parseInt(hex.substring(5,7), 16);

        int red = (int) Math.round(r);
        int green = (int) Math.round(g);
        int blue = (int) Math.round(b);


        return new int[]{red, green, blue};
    }

    public int[] rgbToHsb(int r, int g, int b){
        float[] hsb = Color.RGBtoHSB(r,g,b,null);

        return new int[]{Math.round(hsb[0]*360), Math.round(hsb[1]*100), Math.round(hsb[2]*100)};
    }

    public int[] rgbToHsl(int red, int green, int blue){
        float r = red / 255;
        float g = green / 255;
        float b = blue / 255;

        float max = Math.max(r,g);
        max = Math.max(max, b);

        float min = Math.min(r,g);
        min = Math.min(min, b);

        float delta = max - min;

        float lum = (max+min) / 2;
        float sat = delta == 0 ? 0 : delta / (1- (Math.abs(2*lum - 1)));
        float hue = 0;

        if(delta == 0) hue = 0;
        else if (max == r) hue = 60 * ((g-b)/delta % 6);
        else if (max == g) hue = 60 * ((b-r) / delta + 2);
        else hue = 60 * ((r-g) / delta + 4);


        return new int[]{Math.round(hue),Math.round(sat*100),Math.round(lum*100)};
    }
}