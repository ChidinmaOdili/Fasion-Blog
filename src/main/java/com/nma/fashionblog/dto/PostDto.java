package com.nma.fashionblog.dto;

import lombok.Data;

@Data
public class PostDto {
    private String title ;
    private String description;
    private String featuredImage;
    private int user_id;

}
