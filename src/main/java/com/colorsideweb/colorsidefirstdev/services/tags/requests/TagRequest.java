package com.colorsideweb.colorsidefirstdev.services.tags.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagRequest {
    private String tagName;
    private String postName;
}
