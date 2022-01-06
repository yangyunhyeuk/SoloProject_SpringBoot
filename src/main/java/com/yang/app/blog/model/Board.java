package com.yang.app.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // ORM 클래스, DB에 매핑해주는 클래스임을 명시
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 사용할 예정, <html>태그가 섞여 디자인된다.

    @ColumnDefault("0")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER) // Board : Many, User = One
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트를 저장할 수 없다, 자바는 JPA를 사용 시 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedby : 연관관계의 주인이 아니다 == not FK == DB에 컬럼을 만들지 마시오
    private List<Reply> reply; // 한 게시글에 여러 댓글

    @CreationTimestamp // 데이터 입력 시 자동 기입
    private Timestamp createDate;
}
