package com.colorsideweb.colorsidefirstdev.services.color;

import com.colorsideweb.colorsidefirstdev.enums.ColorType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ColorTypeRecognizer {

    private final ColorConverter converter;

    public ColorType getType(String hex){
        int[] rgb = converter.hexToRgb(hex);
        int[] hsb = converter.rgbToHsb(rgb[0], rgb[1], rgb[2]);

        int sat = hsb[1];
        int bright = hsb[2];

        if(isItJewel(sat, bright)) return ColorType.JEWEL;
        if(isItPastel(sat, bright)) return ColorType.PASTEL_KIDS;
        if(isItEarth(sat, bright)) return ColorType.EARTH;
        if(isItNeutral(sat, bright)) return ColorType.NEUTRAL;
        if(isItFluorescent(sat,bright)) return ColorType.FLUORESCENT_KIDS;
        if(isItShades(sat)) return ColorType.SHADES;

        return null;
    }

    private boolean isItShades(int sat) {
        return sat == 0;
    }

    private boolean isItFluorescent(int sat, int bright) {
        if(sat < 1 || sat > 10) return false;
        return bright < 70 || bright < 99;
    }
    private boolean isItNeutral(int sat, int bright) {
        if(sat < 1 || sat > 10) return false;
        if(bright < 70 || bright > 99) return false;
        return true;
    }

    private boolean isItEarth(int sat, int bright) {
        if(sat < 36 || sat > 41) return false;
        if(bright < 36 || bright > 77) return false;
        return true;
    }

    private boolean isItPastel(int sat, int bright) {
        if(sat < 14 || sat > 21) return false;
        if(bright < 89 || bright > 96) return false;
        return true;
    }

    private boolean isItJewel(int sat, int bright) {
        if(sat < 73 || sat > 82) return false;
        if(bright < 56 || bright > 76) return false;
        return true;
    }
}