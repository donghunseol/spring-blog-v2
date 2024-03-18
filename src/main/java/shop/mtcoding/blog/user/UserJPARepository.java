package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// 자동 컴퍼넌트 스캔이 된다. (@Repository 등을 적지 않아도 메모리에 뜬다! 라는 뜻!)
// save, findAll, findById, deleteById
public interface UserJPARepository extends JpaRepository<User, Integer> {

    // 추상메서드 생성
    // JPA 쿼리 메서드 : 사용하지 말아라 복잡도가 증가 한다.
    // @Query("select u from  User u where u.username = :username and u.password = :password")
    // Optional 을 달아주는 이유는 null 체크를 위해 해준다.
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
    Optional<User> findByUsername(@Param("username") String username);
}
