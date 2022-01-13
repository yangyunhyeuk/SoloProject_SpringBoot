package com.yang.blog.controller.service;

import com.yang.blog.model.RoleType;
import com.yang.blog.model.User;
import com.yang.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을해준다
// == IoC
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @org.springframework.transaction.annotation.Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); // 원문 : 1234
        String encPassword = encoder.encode(rawPassword); // 해쉬 암호화
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    public User 회원찾기(String username) {
        User user = userRepository.findByUsername(username).orElseGet(() -> {
            // == 회원이 없는 경우 빈 객체를 반환한다
            return new User();
        });
        return user;
    }


/*
    @Transactional(readOnly = true) // SELECT 할 때 트랜잭션 시작, 해당 서비스 종료 시 트랜잭션 종료 (정합성 유지)
    public User 로그인(User user) {
            return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
    */

}
