package com.colorsideweb.colorsidefirstdev.services.color;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ColorRecognizer {

    private final ColorConverter converter;


    public String getColorName(@NonNull String hex, @NonNull String colorName){
        if(isItBlack(hex)) return "Black";
        if(isItWhite(hex)) return "White";

        int[] rgb = converter.hexToRgb(hex);
        int[] hsl = converter.rgbToHsl(rgb[0], rgb[1], rgb[2]);

        int hue = hsl[0];
        int sat = hsl[1];
        int lum = hsl[2];

        if(isItRed(hue, sat, lum)) return "Red";
        if(isItOrange(hue, sat, lum)) return "Orange";
        if(isItYellow(hue, sat, lum)) return "Yellow";
        if(isItGreen(hue, sat, lum)) return "Green";
        if(isItCyan(hue, sat, lum)) return "Cyan";
        if(isItBlue(hue, sat, lum)) return "Blue";
        if(isItPurple(hue, sat, lum)) return "Purple";
        if(isItPink(hue, sat, lum)) return "Pink";
        if(isItGray(sat,lum)) return "Gray";

        return checkColorName(colorName);
    }

    private String checkColorName(@NonNull String colorName) {
        colorName.toLowerCase();

        if(colorName.contains("red")) return "Red";
        if(colorName.contains("orange")) return "Orange";
        if(colorName.contains("yellow")) return "Yellow";
        if(colorName.contains("green")) return "Green";
        if(colorName.contains("cyan")) return "Cyan";
        if(colorName.contains("blue")) return "Blue";
        if(colorName.contains("purple")) return "Purple";
        if(colorName.contains("pink") || colorName.contains("magenta")) return "Pink";
        if(colorName.contains("gray") || colorName.contains("silver")) return "Gray";
        if(colorName.contains("black")) return "Black";
        if(colorName.contains("white")) return "White";

        return "Null";
    }

    private boolean isItGray(int sat, int lum) {
        if(sat > 8) return false;
        if(lum < 40 || lum > 80) return false;

        return true;
    }

    private boolean isItPink(int hue, int sat, int lum) {
        if(hue < 289 || hue > 344) return false;
        return isSatAndLum(sat, lum);
    }

    private boolean isItPurple(int hue, int sat, int lum) {
        if(hue < 259 || hue > 288) return false;
        return isSatAndLum(sat, lum);
    }

    private boolean isSatAndLum(int sat, int lum){
        if(sat < 50) return false;
        if(lum < 35 || lum > 65) return false;

        return true;
    }

    private boolean isItBlue(int hue, int sat, int lum) {
        if(hue < 183 || hue > 258) return false;
        return isSatAndLum(sat, lum);
    }

    private boolean isItCyan(int hue, int sat, int lum) {
        if(hue < 159 || hue > 182) return false;
        return isSatAndLum(sat, lum);
    }

    private boolean isItGreen(int hue, int sat, int lum) {
        if(hue < 78 || hue > 158) return false;
        return isSatAndLum(sat, lum);
    }

    private boolean isItYellow(int hue, int sat, int lum) {
        if(hue < 42 || hue > 77) return false;
        return isSatAndLum(sat, lum);
    }

    private boolean isItOrange(int hue, int sat, int lum) {
        if(hue < 12 || hue > 41) return false;
        return isSatAndLum(sat, lum);
    }

    private boolean isItRed(int hue, int sat, int lum) {
        if(hue < 345 && hue > 11) return false;
        return isSatAndLum(sat, lum);
    }

    private boolean isItWhite(@NonNull String rgb) {
        boolean isWhite = true;
        int i = 0;

        while (i<rgb.length() && isWhite == true){
            String r = rgb.substring(i, i+1);
            if(r.equalsIgnoreCase("f") || r.equalsIgnoreCase("e")){
                i += 2;
            }
            else {
                isWhite = false;
            }
        }
        return isWhite;
    }

    private boolean isItBlack(@NonNull String rgb){
        boolean isBlack = true;
        int i = 0;

        while (i<rgb.length() && isBlack == true){
            String r = rgb.substring(i, i+1);
            if(r == "1" || r == "0"){
                i += 2;
            }
            else {
                isBlack = false;
            }
        }
        return isBlack;
    }
}