package com.yang.blog.repository;

import com.yang.blog.controller.dto.ReplySaveRequestDto;
import com.yang.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
    void mSave(ReplySaveRequestDto replySaveRequestDto);

}
