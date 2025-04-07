package com.colorsideweb.colorsidefirstdev.services.color;

import com.colorsideweb.colorsidefirstdev.enums.ImpressionType;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ImpressionRecognizer {

    public ImpressionType getColorImpression(@NonNull String colorName){

        if (colorName.equals("Black")) return ImpressionType.PROFESSIONAL_POWER;
        if (colorName.equals(("White"))) return ImpressionType.HOPE_SIMPLICITY_CLEANLINESS;
        if (colorName.equals("Gray")) return ImpressionType.STABILITY_MATURITY;
        if (colorName.equals("Red")) return ImpressionType.BOLD_EXCITEMENT_PASSION;
        if (colorName.equals("Blue")) return ImpressionType.TRUST_STRENGTH_PEACEFUL;
        if (colorName.equals("Yellow")) return ImpressionType.OPTIMISM_CLARITY;
        if (colorName.equals("Orange")) return ImpressionType.CONFIDENCE_CHEERFUL_FRIENDLY;
        if (colorName.equals("Purple")) return ImpressionType.WISE_CREATIVE_IMAGINARY_ROYALTY;
        if (colorName.equals("Green")) return ImpressionType.PEACEFUL_HEALTH_NATURE;

        return null;

    }
}
