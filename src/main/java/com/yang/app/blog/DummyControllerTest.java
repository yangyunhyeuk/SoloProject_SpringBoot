package com.yang.app.blog;

import com.yang.app.blog.model.RoleType;
import com.yang.app.blog.model.User;
import com.yang.app.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

// html 파일이 아니라 data를 리턴해주는 RestController
@RestController
public class DummyControllerTest {

    // 의존성 주입, 이제 UserRepository은 사용만 해~
    @Autowired // 상위 클래스가 메모리에 뜰 때 쏙 넣어준다.
    private UserRepository userRepository;

    // save 함수는 id를 전달하지 않으면 insert를 해주고
    // id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해준다.
    // id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다.

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try {
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            return "삭제 실패 [해당 아이디 DB에 존재 X], id : "+id;
        }
        return "삭제 성공 id : "+id;
    }



    // email, password 정보 필요
    // @RequestBody == 제이슨 데이터 통신을 위함
    // json 데이터를 요청 => 자바 오브젝트로 전환(MessageConverter의 Jackson 라이브러리가 변환)


    // 하단에 save 함수를 주석처리하였는데 @Transactional를 추가해줌으로 데이터가 수정됨을 확인할 수 있다.
    // == 더티 체킹
    // Transactional를 사용하면 save를 하지 않아도 update가 된다.
    // @Transactional // 함수 종료 시에 자동 commit
    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser){
        System.out.println("id : "+id);
        System.out.println("pw : "+requestUser.getPassword());
        System.out.println("email : "+requestUser.getEmail());

        // 영속화는 다음 줄에서 진행된다.
        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });

        // 더티체킹이란?
        // 위에서 영속화가 진행된 후에 다음에서 값 변경할 때,
        // 트랜잭션 종료 시 변경을 감지하여 데이터베이스에 자동으로 UPDATE 작업을 한다.
         user.setPassword(requestUser.getPassword());
         user.setEmail(requestUser.getEmail());

        // userRepository.save(user);
        return user;
    }















    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    // 한 페이지 당 2건에 데이터를 리턴 받아 볼 예정
    // 기존 jsp는 로직을 직접 짜야하나, 스프링 부트는 pageable 기능 제공
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    //{id} 주소로 파라미터를 전달받을 수 있다
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user/4를 찾으면 DB에서 데이터를 못찾아올 수 있어
        // optional로 User 객체를 감싸서 가져올테니, null 판별하렴

//
//        // 람다식
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자는 없습니다.");
//        });
//

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 사용자가 없습니다.");
            }
        });


///////////////////////////////////////////////////
        // 가져올 데이터가 null이면 다음의 메서드가 수행되어 빈 객체를 User에 넣어 null은 아니다.
        // User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
        //    @Override
        //    public User get() {
        //        return new User();
        //    }
        // });
///////////////////////////////////////////////////
        // user 객체 == 자바 Object
        // 웹 브라우저가 이해할 수 있게 json 데이터로 변환 필요
        // 스프링 부트는 MessageConverter가 응답 시에 자동 작동
        // 만약 자바 오브젝트를 리턴할 시 MessageConverter가 Jackson 라이브러리를 호출하여
        // user 오브젝트를 json으로 변환하여 호출
        return user;
    }

    // http://localhost:8000/blog/dummy/join (요청)
    // http의 바디에 username, password, email 데이터를 갖고 요청 시 해당 함수 파라미터에 쏙 들어감
    @PostMapping("/dummy/join")
    public String join(User user){ // key = value (규칙)

        System.out.println("username : "+user.getUsername());
        System.out.println("password : "+user.getPassword());
        System.out.println("email : "+user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

}
