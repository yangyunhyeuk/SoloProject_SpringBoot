package com.yang.app.blog;

import lombok.*;

@Data // 게터,세터
@AllArgsConstructor // 생성자
@NoArgsConstructor // 빈 생성자
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;
}
