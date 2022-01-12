package com.yang.blog.controller.service;

import com.yang.blog.model.RoleType;
import com.yang.blog.model.User;
import com.yang.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을해준다
// == IoC
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); // 원문 : 1234
        String encPassword = encoder.encode(rawPassword); // 해쉬 암호화
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void 회원정보수정(User user) {
        // 수정 시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정하는게 best이다.
        // select를 해서 User 오브젝트를 DB를 가져오는 이유는 영속화하기 위함이다.
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 UPDATE문을 날려준다.
        User persistence = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원찾기 실패");
        });
        String rawPassword = user.getPassword();
        String encPassword = encoder.encode(rawPassword);
        persistence.setPassword(encPassword);
        persistence.setEmail(user.getEmail());

        // 회원수정 함수 종료 시 == 서비스 종료 == 트랜잭션이 종료
        // => commit이 자동으로 된다
        // 영속화된 persistance 객체의 변화가 감지되면 == 더티체킹이 되면
        // 자동으로 update문을 날려주게 된다.
    }

/*
    @Transactional(readOnly = true) // SELECT 할 때 트랜잭션 시작, 해당 서비스 종료 시 트랜잭션 종료 (정합성 유지)
    public User 로그인(User user) {
            return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
    */

}
