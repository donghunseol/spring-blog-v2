package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em; // DI

    @Transactional // 고립성
    public Board save(Board board) {
        // 1. 비영속 객체
        em.persist(board); // 전달 (만약 pk를 가지고 들어간다면? update가 된다!)
        // 2. board -> 영속 객체
        return board;
    }
}
