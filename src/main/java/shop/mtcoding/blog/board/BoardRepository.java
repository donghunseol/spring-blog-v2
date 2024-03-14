package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAllV2() {
        String q1 = "select b from Board b order by b.id desc"; // 빌더패턴 쓸 예정
        List<Board> boardList = em.createQuery(q1, Board.class).getResultList();
        List<Integer> userIds = boardList.stream().map(board -> board.getUser().getId()).distinct().collect(Collectors.toList());

        String q2 = "select u from User u where u.id in (";
        for (int i = 1; i <= userIds.size(); i++) {
            if (i == userIds.size()) {
                q2 = q2 + ":id" + i + ")";
            } else {
                q2 = q2 + ":id" + i + ",";
            }
        }

        Query query = em.createQuery(q2, User.class);

        int num = 1;
        for (int i : userIds) {
            query.setParameter("id" + num, i);
            num++;
        }
        List<User> userList = query.getResultList();

        for (Board board : boardList) {
            for (User user : userList) {
                if (board.getUser().getId().equals(user.getId())) {
                    board.setUser(user);
                }
            }
        }

        System.out.println(boardList);

        return boardList; // user 가 채워져야 한다.
    }

    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    // board 에 user 를 join 하고 싶을 때
    public Board findByIdJoinUser(int id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class); // 자동으로 pk랑 연결해 주기에 on이 필요없다!
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    // board 만 보고 싶을 때
    public Board findById(int id) {
        // id, title, content, user_id (이질감), created_at
        Board board = em.find(Board.class, id);
        return board;
    }
}
