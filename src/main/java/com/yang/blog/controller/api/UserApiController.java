package com.yang.blog.controller.api;

import com.yang.blog.controller.dto.ResponseDto;
import com.yang.blog.controller.service.UserService;
import com.yang.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController, save 호출됨");
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) {
        userService.회원정보수정(user);
        // 여기서 트랜잭션이 종료되어 DB값은 변경이 되었으나
        // 세션값은 반영되지 않은 상태이기 때문에 로그아웃하고 다시 들어오지 않는 이상
        // 직접 세션값을 변경해주어야 한다.

        // 세션 등록
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

// 로그인 요청은 스프링 시큐리티 라이브러리가 가로채게 할 예정

/*
다음은 전통적인 로그인 방식 (현 프로젝트에서 사용 x)
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController : login 호출됨");
        User principal = userService.로그인(user); // principal (접근 주체)

        if(principal != null){
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);

    }
    */


}
