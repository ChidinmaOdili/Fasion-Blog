package com.nma.fashionblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    private String title ;
    private String description;
    private String featuredImage;
    private int user_id;

}
