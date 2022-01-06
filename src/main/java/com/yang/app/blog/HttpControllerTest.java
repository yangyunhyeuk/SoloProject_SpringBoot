package com.yang.app.blog;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(html 파일)
// @Controller

// 사용자가 요청 -> 그에 대한 응답(Data)을 수행
@RestController
public class HttpControllerTest {

    // 인터넷 브라우저 요청은 get 방식 뿐이 안된다.
    // http://localhost:8080/http/get(select)
    @GetMapping("/http/get")
    public String getTest(){
        return "get 요청";
    }
    // http://localhost:8080/http/post(insert)
    @PostMapping("/http/post")
    public String postTest(){
        return "post 요청";
    }
    // http://localhost:8080/http/put(update)
    @PutMapping("/http/put")
    public String putTest(){
        return "put 요청";
    }
    // http://localhost:8080/http/delete(delete)
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }
}
