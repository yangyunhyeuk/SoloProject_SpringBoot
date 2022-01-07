package com.yang.app.blog.controller.api;

import com.yang.app.blog.controller.dto.ResponseDto;
import com.yang.app.blog.controller.service.UserService;
import com.yang.app.blog.model.RoleType;
import com.yang.app.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController, save 호출됨");
        user.setRole(RoleType.USER);
        int result = userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),result);
    }
}
