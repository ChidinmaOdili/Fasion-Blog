package com.nma.fashionblog.service;

import com.nma.fashionblog.dto.*;
import com.nma.fashionblog.model.Post;
import com.nma.fashionblog.response.*;

public interface UserService {
    RegisterResponse register(UserDto userDto);

    LoginResponse login(LoginDto loginDto);

    CreatePostResponse createPost(PostDto postDto);

    CommentResponse comment(int user_id , int post_id , CommentDto commentDto);

    LikeResponse like(int user_id , int post_id , LikeDto likeDto);

    SearchCommentResponse searchComment(String keyword);

    SearchPostResponse searchPost(String keyword);
    Post findPostById(int id);


}

