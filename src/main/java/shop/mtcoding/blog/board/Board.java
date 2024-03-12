package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Table(name = "board_tb")
@Getter
@Entity
public class Board { // 모델링 (DB 세상에 있는 값을 가져와서 모델링 한다 해서 모델링 이라 한다)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;
    private String username;
    private Timestamp createdAt;
}
