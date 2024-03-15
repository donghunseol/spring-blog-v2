package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "board_tb")
@Data
@Entity
public class Board { // 모델링 (DB 세상에 있는 값을 가져와서 모델링 한다 해서 모델링 이라 한다)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    // ORM 진행 (ORM 할 것이다.)
    // @JoinColumn(name = "user_id") // 이것과 아래는 같은 현상이 일어난다.
    // @ManyToOne(fetch = FetchType.EAGER) 연관된 객체를 모두 조회
    // @ManyToOne(fetch = FetchType.LAZY) 내것만 조회
    @ManyToOne(fetch = FetchType.LAZY) // 연관 관계로 보고 설정된다.
    private User user; // DB에 컬럼 -> user_id (앞의 이름은 변수명으로 결정된다 users 면 users_id 가 된다)

    @CreationTimestamp // pc -> db 로 들어 갈때 (날짜 주입)
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }


}
