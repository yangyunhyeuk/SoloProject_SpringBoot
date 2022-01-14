package com.yang.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴!
@Entity // User 클래스를 통해 자동으로 MySQL에 테이블 생성
// @DynamicInsert //insert 시 null 값 빼버림
public class User {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.(오라클의 경우 SEQUENCE, MySQL의 경우 AUTOINCREMENT 사용)
    private int id; //시퀀스, autoincerment

    @Column(nullable = false,length = 100,unique = true)
    private String username; // 유저의 ID

    @Column(length = 100) // 후에 해쉬 전환 후 암호화
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // @ColumnDefault("'user'")
    // DB는 RoleType이 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다, 어느 회원이 admin, user, manager에 따라 권한 부여

    private String oauth; // kakao, google

    @CreationTimestamp // 시간이 자동으로 입력
    private Timestamp createDate;
}
