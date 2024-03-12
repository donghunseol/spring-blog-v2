package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardNativeRepository.class)
@DataJpaTest
public class BoardNativeRepositoryTest {

    @Autowired // DI (IoC 에 있는 걸 DI 해준다)
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void findAll_test(){
        // given

        // when
        List<Board> boardList = boardNativeRepository.findAll();

        // then
        // 눈 검증
        System.out.println("findAll_test/size : " + boardList.size()); // 확실하게 구분 하기 위해 이름을 정확히 명시
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername()); // ssar을 조회하려는데 order by로 인해 2번지로 조회

        // org.assrtj.core.api
        // 데이터 검증
        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

}
