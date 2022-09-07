package com.nma.fashionblog.repository;

import com.nma.fashionblog.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepo extends JpaRepository<Like, Integer>{
    @Query(value = "SELECT  * FROM likes WHERE  user_id = ?1 AND post_id = ?2" , nativeQuery = true)
    Like findLikeByUserIdAndPostId(int user_id , int post_id);

    @Query(value = "SELECT  * FROM likes WHERE   post_id = ?1" , nativeQuery = true)
    List<Like> likeList(int post_id);
}
