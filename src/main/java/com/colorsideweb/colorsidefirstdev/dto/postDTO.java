package com.colorsideweb.colorsidefirstdev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class postDTO {
    private String userName;
    private String colorName;
    private String description;
    private List<String> tags;
    private List<String> colors;

}
