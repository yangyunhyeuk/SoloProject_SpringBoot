package com.yang.app.blog.repository;

import com.yang.app.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO 역할
// 자동로 bean 등록이 된다
// @Repository  // 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {


}
