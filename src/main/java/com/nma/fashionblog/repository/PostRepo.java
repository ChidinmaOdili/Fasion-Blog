package com.nma.fashionblog.repository;

import com.nma.fashionblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository <Post, Integer> {

    List<Post> findByTitleContaining(String keyword);
}
