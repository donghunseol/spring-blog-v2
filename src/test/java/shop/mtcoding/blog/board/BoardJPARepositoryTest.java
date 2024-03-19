package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findBuIdJoinUserAndReplies_test(){
        // given
        int id = 4;

        // when
        // Board board = boardJPARepository.findByIdJoinUserAndReplies(id).get();

        // then

    }

    // save
    @Test
    public void save_test(){
        // given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();

        // when
        boardJPARepository.save(board);

        // then
        System.out.println("save_test/id(PK) : " + board.getId());
    }

    // findById
    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        // Optional 을 사용하는 이유는 null 처리를 자동으로 해준다.
        Optional<Board> boardOP = boardJPARepository.findById(id);

        if (boardOP.isPresent()) {
            Board board = boardOP.get();
            System.out.println("findById_test/title : " + board.getTitle());
        }

        // then
    }

    // findByIdJoinUser
    @Test
    public void findByIdJoinUser_test(){
        // given
        int id = 1;

        // when
        Optional<Board> board = boardJPARepository.findByIdJoinUser(id);

        // then

    }

    // findAll (sort)
    @Test
    public void findAll_test(){
        // given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        // when
        List<Board> boardList = boardJPARepository.findAll(sort);

        // then
        System.out.println("findAll_test : " + boardList);
    }

    // deleteById
    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        // delete query 가 안나오는 이유는?
        // 트랜잭션 종료 될 때 나오니깐! select 가 나오는 이유는 PC로 조회해서 remove 로 지워야 하기 때문이다!
        // remove 는 entity 를 조회해서 지우기 때문이다!
        boardJPARepository.deleteById(id);
        em.flush();

        // then

    }
}
