package com.nma.fashionblog.serviceImpl;

import com.nma.fashionblog.dto.*;
import com.nma.fashionblog.model.Comment;
import com.nma.fashionblog.model.Like;
import com.nma.fashionblog.model.Post;
import com.nma.fashionblog.model.User;
import com.nma.fashionblog.repository.CommentRepo;
import com.nma.fashionblog.repository.LikeRepo;
import com.nma.fashionblog.repository.PostRepo;
import com.nma.fashionblog.repository.UserRepo;
import com.nma.fashionblog.response.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.time.Month.AUGUST;
import static java.util.Calendar.SEPTEMBER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {
    @Mock
    private UserRepo userRepository;
    @Mock
    private LikeRepo likeRepository;
    @Mock
    private CommentRepo commentRepository;
    @Mock
    private PostRepo postRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private LocalDateTime localDateTime;
    private User user;
    private Comment comment;
    private Like like;
    private Post post;
    List<Like> likeList = new ArrayList<>();
    List<Post> postList = new ArrayList<>();
    List<Comment> commentList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        localDateTime = LocalDateTime.of(2022, AUGUST, 3, 6, 30, 40, 50000);
        user = new User(1, "Ana", "Ana@gmail.com", "Admin", "1234", localDateTime, localDateTime, postList, commentList, likeList);
        post = new Post(1, "title of post", "small description", "title-of-post", "0xEsddfmk.png", localDateTime, localDateTime, user, commentList, likeList);
        like = new Like(1, true, localDateTime, localDateTime, user, post);
        comment = new Comment(1, "lovely", localDateTime, localDateTime, post, user);
    }

    @Test
    void register() {
        UserDto userDTO = new UserDto("Ana", "Ana@gmail.com", "Admin", "1234");
        when(userRepository.save(user)).thenReturn(user);
        RegisterResponse registerResponse = new RegisterResponse("success", localDateTime, user);
        var actual = userService.register(userDTO);
        actual.setTimeStamp(localDateTime);
        actual.getUser().setCreatedAt(localDateTime);
        actual.getUser().setUpdatedAt(localDateTime);
        actual.getUser().setId(1);
        assertEquals(registerResponse,actual);
        assertEquals(actual.getUser().getName(),user.getName());
    }

    @Test
    void login_SuccessFullLogin() {
        LoginDto loginDto = new LoginDto("Ana@gmail.com" , "1234");
        when(userRepository.findUserByEmail("Ana@gmail.com")).thenReturn(Optional.ofNullable(user));
        LoginResponse loginResponse = new LoginResponse("success", localDateTime);
        var actual = userService.login(loginDto);
        actual.setTimeStamp(localDateTime);
        assertEquals(loginResponse, actual);
    }

    @Test
    void login_IncorrectPassword() {
        LoginDto loginDto = new LoginDto("Ana@gmail.com", "12jhgju34");
        when(userRepository.findUserByEmail("Ana@gmail.com")).thenReturn(Optional.ofNullable(user));
        LoginResponse loginResponse = new LoginResponse("password MisMatch", localDateTime);
        var actual = userService.login(loginDto);
        actual.setTimeStamp(localDateTime);
        assertEquals(loginResponse, actual);
    }

    @Test
    void createPost() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        PostDto postDto = new PostDto("title of post", "small description", "0xEsddfmk.png", 1);
        CreatePostResponse createPostResponse = new CreatePostResponse("success", localDateTime, post);
        var actual = userService.createPost(postDto);  //.createPost(postDto);
        actual.setTimeStamp(localDateTime);
        actual.getPost().setCreatedAt(localDateTime);
        actual.getPost().setUpdatedAt(localDateTime);
        actual.getPost().setId(1);
        actual.getPost().setSlug("title-of-post");
        assertEquals(createPostResponse, actual);
    }

    @Test
    void like() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        List<Like> likes = new ArrayList<>(Arrays.asList(like));
        when(likeRepository.likeList(1)).thenReturn(likes);
        LikeDto likeDto = new LikeDto(true);
        LikeResponse likeResponse = new LikeResponse("success", localDateTime, like, 1);
        var actual = userService.like(1, 1, likeDto);
        actual.setTimeStamp(localDateTime);
        actual.setLike(like);
        likeResponse.getLike().setUser(user);
        likeResponse.getLike().setPost(post);
        assertEquals(likeResponse, actual);
    }

    @Test
    void comment() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        CommentDto commentDto = new CommentDto("lovely");
        CommentResponse commentResponse = new CommentResponse("success", localDateTime, comment, post);
        var actual = userService.comment(1, 1, commentDto);
        actual.setTimeStamp(localDateTime);
        actual.setComment(comment);
        commentResponse.setComment(comment);
        commentResponse.setPost(post);
        assertEquals(commentResponse, actual);
    }


    @Test
    void findUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        assertEquals(user, userService.findUserById(1));
    }

    @Test
    void findPostById() {
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        assertEquals(post, userService.findPostById(1));
    }

    @Test
    void findUserByEmail() {
        when(userRepository.findUserByEmail("Ana@gmail.com")).thenReturn(Optional.ofNullable(user));
        assertEquals(user, userService.findUserByEmail("Ana@gmail.com"));
    }
}