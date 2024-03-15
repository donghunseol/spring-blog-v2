package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class) // IoC 등록 코드
@DataJpaTest // Datasource (connection pool), EntityManager (PersistContext 를 관리하는 매니저)
public class UserRepositoryTest {
    @Autowired // DI
    private UserRepository userRepository; // final을 사용하는 이유는? DI, IoC
    @Autowired
    private EntityManager em;

    @Test
    public void update_test(){
        // given
        int id = 1;
        String password = "4321";
        String email = "ssar@naver.com";

        // when
        userRepository.updateById(id,password,email);
        em.flush();

        // then

    }

    @Test
    public void findById_test(){
        // given
        int id = 1;

        // when
        userRepository.findById(id);

        // then

    }

    @Test
    public void findByUsername_test(){
        // given
        String username = "ssar";
        String password = "1234";
        UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO();
        loginDTO.setUsername("ssar");
        loginDTO.setPassword("1234");

        // when
        User user = userRepository.findByUsername(username, password);
        if(user == null){
            System.out.println("findByUsername_test/username : 아이디가 틀렸습니다");
        }else {
            if (user.getPassword().equals("1234")){
                System.out.println("findByUsername_test : 로그인 되었습니다");
            }else {
                System.out.println("findByUsername_test/password : 비밀번호가 틀렸습니다");
            }
        }

        // then
        Assertions.assertThat(user.getUsername()).isEqualTo("ssar");
    }
}
