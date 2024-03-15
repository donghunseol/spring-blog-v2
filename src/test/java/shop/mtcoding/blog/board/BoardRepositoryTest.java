package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.assertj.core.api.AbstractObjectArrayAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardRepository.deleteById(id); // delete query 발동함

        // then
        Assertions.assertThat(boardRepository.findAll().size()).isEqualTo(3);
    }

    @Test
    public void readomquery_test() {
        // given

        // when
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
        System.out.println(q2);

        Query query = em.createQuery(q2, User.class);

        int num = 1;
        for (int i : userIds) {
            query.setParameter("id"+num, i);
            num++;
            System.out.println(userIds);
            System.out.println(i);
        }
        List<User> userList = query.getResultList();
        System.out.println(userList);

        for (Board board : boardList) {
            for (User user : userList) {
                if (board.getUser().getId().equals(user.getId())) {
                    board.setUser(user);
                    break;
                }
            }
        }

        System.out.println(boardList);


        // select u from User u where u.id in(:id1, :id2);
//        List<Board> boardList = boardRepository.findAll();
//        List<Integer> userIds = boardList.stream().map(board -> board.getUser().getId()).distinct().collect(Collectors.toList());
//        for (int i : userIds){
//            System.out.println(i);
//        }

//        String q = "select u from User u where u.id in (";
//        for (int i = 1; i <= ids.length; i++) {
//            if (i == ids.length) {
//                q = q + i + ")";
//            } else {
//                q = q + i + ",";
//            }
//        }
        //System.out.println(q);


        // then

    }

    @Test
    public void findAll_custom_inquery_test() {
        List<Board> boardList = boardRepository.findAll();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for (int i : userIds) {
            System.out.println(i);
        }

        // select * from user_tb where id in (3,2,1,1);
        // select * from user_tb where id in (3,2,1);
    }

    @Test
    public void findAll_lazyloading_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();

        // then
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername()); // lazy loading
        });

    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();

        // then
    }

    @Test
    public void findById_test() {
        int id = 1;

        boardRepository.findById(id);
    }
}
