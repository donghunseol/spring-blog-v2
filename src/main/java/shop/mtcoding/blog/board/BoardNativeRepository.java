package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private final EntityManager em; // DI

    @Transactional // 고립성
    public void save(String title, String content, String username){
        Query query = em.createNativeQuery("insert into board_tb(title, content, username, created_at) values (?, ?, ?, now())");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);

        query.executeUpdate();
    }
}
