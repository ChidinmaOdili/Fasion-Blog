package com.nma.fashionblog.repository;

import com.nma.fashionblog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>{

    List<Comment> findByCommentContaining(String keyword);
}
