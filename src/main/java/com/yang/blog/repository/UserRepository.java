package com.yang.blog.repository;

import com.yang.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO 역할
// 자동로 bean 등록이 된다
// @Repository  // 생략가능
public interface UserRepository extends JpaRepository<User, Integer> {


}


// 로그인을 위한 함수 필요
// JPA Naming 전략
// 다음의 쿼리의 인자에 ?가 들어가는 문법
// SELECT * FROM user WHERE username =? AND password = ?;
// User findByUsernameAndPassword(String username, String password);

// 다음과 같이 네이티브 쿼리로 해결 가능
// @Query(value="SELECT * FROM user WHERE username=?1 AND password = ?2", nativeQuery = true)
// User login(String username, String password);
