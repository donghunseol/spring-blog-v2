package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardNativeRepository.class)
@DataJpaTest
public class BoardNativeRepositoryTest {

    @Autowired // DI (IoC 에 있는 걸 DI 해준다)
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void updateById_test() {
        // given
        int id = 1;
        String title = "제목수정1";
        String content = "내용수정1";
        String username = "bori";

        // when
        boardNativeRepository.updateById(id, title, content, username);

        // then
        Board board = boardNativeRepository.findById(id);
        System.out.println("updateById_test/board : " + board);
        assertThat(board.getTitle()).isEqualTo("제목수정1");
        assertThat(board.getContent()).isEqualTo("내용수정1");
        assertThat(board.getUsername()).isEqualTo("bori");
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        boardNativeRepository.deleteById(id);

        // then
        List<Board> boardList = boardNativeRepository.findAll();
        System.out.println("deleteById_test/size : " + boardList.size());
        assertThat(boardList.size()).isEqualTo(3);
    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardNativeRepository.findAll();

        // then
        // 눈 검증
        System.out.println("findAll_test/size : " + boardList.size()); // 확실하게 구분 하기 위해 이름을 정확히 명시
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername()); // ssar을 조회하려는데 order by로 인해 2번지로 조회

        // org.assrtj.core.api
        // 데이터 검증
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;

        // when
        Board board = boardNativeRepository.findById(id);

        // then
        // 눈 검증
        System.out.println("findById_test : " + board);
        System.out.println("findById_test/id : " + board.getId()); // 확실하게 구분 하기 위해 이름을 정확히 명시
        System.out.println("findById_test/username : " + board.getUsername()); // ssar을 조회하려는데 order by로 인해 2번지로 조회

        // org.assrtj.core.api
        // 데이터 검증
        assertThat(board.getTitle()).isEqualTo("제목1");
        assertThat(board.getContent()).isEqualTo("내용1");
    }
}
