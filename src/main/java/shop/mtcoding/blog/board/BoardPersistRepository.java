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

    // 직접 조회
    @Transactional
    public void deleteByIdV2(int id) {
        Board board = findById(id);
        em.remove(board); // PC에 객체 지우고, (트랜잭션 종료 시) 삭제 쿼리를 전송함
    } // 시작 begin() / 종료 성공 commit() 실패 rollback()

    // 쿼리문 직접 작성
    @Transactional
    public void deleteByIdV1(int id) {
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Board findById(int id) {
        Board board = em.find(Board.class, id); // (클래스, 프라이머리키)
        return board;
    }

    public List<Board> findAll(){
        Query query = em.createQuery("SELECT b FROM Board b  ORDER BY b.id DESC", Board.class); // JPQL에 대한 연습이 필요하다.
        return query.getResultList();
    }

    @Transactional // 고립성
    public Board save(Board board) {
        // 1. 비영속 객체
        em.persist(board); // 전달 (만약 pk를 가지고 들어간다면? update가 된다!)
        // 2. board -> 영속 객체
        return board;
    }
}
