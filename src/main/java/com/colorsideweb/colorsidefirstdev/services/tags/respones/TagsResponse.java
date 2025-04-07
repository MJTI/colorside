package com.colorsideweb.colorsidefirstdev.services.tags.respones;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagsResponse {
    private List<String> tagName;
}
