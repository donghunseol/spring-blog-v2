package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    // board 에 user 를 join 하고 싶을 때
    public Board findByIdJoinUser(int id){
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class); // 자동으로 pk랑 연결해 주기에 on이 필요없다!
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    // board 만 보고 싶을 때
    public Board findById(int id){
        // id, title, content, user_id (이질감), created_at
        Board board = em.find(Board.class, id);
        return board;
    }
}
