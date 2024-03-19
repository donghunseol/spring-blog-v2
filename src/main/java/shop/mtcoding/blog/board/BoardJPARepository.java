package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    // 통신 2 회 인데 12번 (LAZY Loading 을 추천, 이걸 사용하려면 그냥 OneToMany 하나만 만들어 주면 된다)
    // 지금은 이거 사용할 거!
    @Query("select b from Board b join fetch b.user u where b.id = :id")
    Optional<Board> findByIdJoinUser(@Param("id") int id);

//    // 통신 1 회 인데 28번
//    @Query("select b from Board b join fetch b.user u left join fetch b.replies r where b.id = :id")
//    Optional<Board> findByIdJoinUserAndReplies(@Param("id")int id);
}
